package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class GuiTrailEditor extends GuiScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(":)");

    private final EntityPlayer player;
    private final ICoreCapability cap;

    private final ArrayList<ColorSlider> sliders = new ArrayList<>();

    private int x;
    private int y;

    public GuiTrailEditor(final EntityPlayer player, NBTTagCompound capData) {
        this.player = player;
        this.cap = CoreCapabilityImpl.getInstance(player);
    }

    @Override
    public void initGui() {
        this.x = (this.width - 150) / 2;
        this.y = (this.height - 150) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.sliders.forEach(slider -> slider.renderSlider(this.mc, mouseX, mouseY));
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    private class ColorSlider {

        private final ResourceLocation SLIDER_TEXTURE = null;
        private int colorModifier;
        private int xPos, yPos;
        private int width, height;
        private String displayText;

        public ColorSlider(String name, int x, int y, int w, int h) {
            this.colorModifier = 128;
            this.xPos = x;
            this.yPos = y;
            this.width = w;
            this.height = h;
            this.displayText = name;
            GuiTrailEditor.this.sliders.add(this);
        }

        public void renderSlider(Minecraft mc, int x, int y) {
            ImageHelper.drawCustomSizedImage(mc, SLIDER_TEXTURE, xPos, yPos, width, height, false);
            String text = displayText + ": " + colorModifier;
            mc.fontRenderer.drawStringWithShadow(text, xPos + (width - mc.fontRenderer.getStringWidth(text)) / 2, yPos, this.getTextColor());
        }

        private int getTextColor() {
            switch (displayText) {
                case "RED": return (int)(0xFF0000 * (colorModifier/255F));
                case "GREEN": return (int)(0x00FF00 * (colorModifier/255F));
                case "BLUE": return (int)(0x0000FF * (colorModifier/255F));
                default: return 0;
            }
        }
    }
}
