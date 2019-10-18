package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class GuiTrailEditor extends GuiScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/traileditor.png");

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
        this.x = (this.width - 176) / 2;
        this.y = (this.height - 166) / 2;

        this.addButton(new Button(0, false, 100, 10));
        this.addButton(new Button(1, true, 130, 10));
        this.addButton(new Button(2, false, 100, 40));
        this.addButton(new Button(3, true, 130, 40));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(x, y, 0, 0, 176, 166);
        this.sliders.forEach(slider -> slider.renderSlider(this.mc, mouseX, mouseY));
        this.buttonList.forEach(btn -> btn.drawButton(this.mc, mouseX, mouseY, partialTicks));
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    private class Button extends GuiButton {

        private final boolean isAddBtn;

        public Button(int id, boolean button, int x, int y) {
            super(id, x, y, 20, 20, "");
            this.isAddBtn = button;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.hovered = mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
            int variant = this.enabled ? this.hovered ? 1 : 0 : 2;
            double uStart = variant == 0 ? 0.0D : variant == 1 ? 20/256D : 40/256D;
            double uEnd = uStart + 20/256D;
            double vStart = isAddBtn ? 166/256D : 186/256D;
            double vEnd = vStart + 20/256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, uStart, vStart, uEnd, vEnd, false);
        }
    }

    private class ColorSlider {

        /** The color modifier, <0; 100> */
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
            ImageHelper.drawCustomSizedImage(mc, TEXTURE, xPos, yPos, width, height, false);
            String text = displayText + ": " + colorModifier;
            mc.fontRenderer.drawStringWithShadow(text, xPos + (width - mc.fontRenderer.getStringWidth(text)) / 2, yPos, this.getTextColor());
        }

        public void renderSliderIcon(Minecraft mc, int x, int y) {
            int sliderCenter = this.xPos + (int)(this.width * (colorModifier / 100.0F));
            boolean hovered = this.isMouseOnSliderIcon(sliderCenter, x, y);
            double u = hovered ? 7/256D : 0;
            double uEnd = u + 7/256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, sliderCenter - 3, this.yPos, sliderCenter + 3, 20, u, 226/256D, uEnd, 246/256D, false);
        }

        public boolean isMouseOnSlider(int mx, int my) {
            return mx >= this.xPos && mx <= this.xPos + this.width && my >= this.yPos && my <= this.yPos + this.height;
        }

        public boolean isMouseOnSliderIcon(int mx, int my) {
            int btnCenter = this.xPos + (int)(this.width * (colorModifier / 100.0F));
            return this.isMouseOnSliderIcon(btnCenter, mx, my);
        }

        public boolean isMouseOnSliderIcon(int btnCenter, int mx, int my) {
            return my >= this.yPos && my <= this.yPos + this.height && mx >= btnCenter - 3 && mx <= btnCenter + 3;
        }

        private int getTextColor() {
            switch (displayText) {
                case "RED": return (int)(0xFF0000 * (colorModifier/100F));
                case "GREEN": return (int)(0x00FF00 * (colorModifier/100F));
                case "BLUE": return (int)(0x0000FF * (colorModifier/100F));
                default: return 0;
            }
        }
    }
}
