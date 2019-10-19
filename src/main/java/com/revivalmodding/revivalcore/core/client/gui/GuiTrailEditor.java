package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiTrailEditor extends GuiScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/traileditor.png");

    private final EntityPlayer player;
    private final ICoreCapability editedCap;
    private final NBTTagCompound originalNBT;
    private EditorType editor;
    private boolean madeValidChanges = false;

    private final ArrayList<ColorSlider> sliders = new ArrayList<>();
    private final ArrayList<EditorPanelSwitch> editors = new ArrayList<>();

    private int x;
    private int y;

    private int selectedButtonIndex = 0;

    public GuiTrailEditor(final EntityPlayer player) {
        this.player = player;
        this.editedCap = new CoreCapabilityImpl(player);
        this.originalNBT = CoreCapabilityImpl.getInstance(player).toNBT();
        this.editedCap.fromNBT(originalNBT);
        editor = EditorType.GENERAL;
    }

    @Override
    public void initGui() {
        this.x = (this.width - 176) / 2;
        this.y = (this.height - 166) / 2;
        this.editors.clear();
        for(EditorType editor : EditorType.values()) {
            int i = editor.ordinal();
            this.editors.add(i, new EditorPanelSwitch(x - 20, y + 10 + i * 25, editor));
        }

        this.updateGUIElements();
    }

    public void updateGUIElements() {
        this.buttonList.clear();
        this.sliders.clear();

        PlayerTrailData trailData = this.editedCap.getTrailData();
        int trailLength = trailData.getTrail().getLength();
        int trailWidth = trailData.getTrail().getWidth();

        switch (editor) {
            case GENERAL: {
                this.addButton(new Button(0, false, x + 80, y + 10).state(trailLength > 2));
                this.addButton(new Button(1, true, x + 146, y + 10).state(trailLength < 10));
                this.addButton(new Button(2, false, x + 80, y + 35).state(trailLength < trailWidth));
                this.addButton(new Button(3, true, x + 146, y + 35).state(trailWidth < 15));
                break;
            }
            case COLOR: {
                for(int i = 0; i < trailLength; i++) {
                    this.addButton(new TrailPartButton(i, x + (i/5 >= 1 ? 30 : 10) , y + 10 + (i%5) * 18));
                }
                this.sliders.add(new ColorSlider("RED", x + 66, y + 10, 100, 16));
                this.sliders.add(new ColorSlider("GREEN", x + 66, y + 28, 100, 16));
                this.sliders.add(new ColorSlider("BLUE", x + 66, y + 46, 100, 16));
                this.updateTrailColorSliders(0);
                break;
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (editor) {
            case GENERAL: {
                switch (button.id) {
                    case 0: {
                        this.addToTrailLength(-1);
                        break;
                    }
                    case 1: {
                        this.addToTrailLength(1);
                        int length = this.editedCap.getTrailData().getTrail().getLength();
                        int width = this.editedCap.getTrailData().getTrail().getWidth();
                        if(length > width) {
                            editedCap.getTrailData().getTrail().setWidth(length);
                        }
                        break;
                    }
                    case 2: {
                        this.addToTrailWidth(-1);
                        break;
                    }
                    case 3: {
                        this.addToTrailWidth(1);
                        break;
                    }
                }
                this.updateGUIElements();
                break;
            }
            case COLOR: {
                if(button instanceof TrailPartButton) {
                    TrailPartButton trailPartButton = (TrailPartButton) button;
                    this.selectedButtonIndex = trailPartButton.trailIndex;
                    this.updateTrailColorSliders(this.selectedButtonIndex);
                }
                break;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.editors.forEach(editMode -> editMode.drawPanel(this.mc, mouseX, mouseY, partialTicks));
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(x, y, 0, 0, 176, 166);
        this.sliders.forEach(slider -> slider.drawButton(this.mc, mouseX, mouseY, partialTicks));
        this.buttonList.forEach(btn -> btn.drawButton(this.mc, mouseX, mouseY, partialTicks));
        this.editor.handleTypeDataRender(this.mc, mouseX, mouseY, partialTicks, x, y, this);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if(mouseButton == 0) {
            for(EditorPanelSwitch panelSwitch : this.editors) {
                if(panelSwitch.mouseOver && panelSwitch.editorType != this.editor) {
                    this.editor = panelSwitch.editorType;
                    this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    this.updateGUIElements();
                    break;
                }
            }
            for(ColorSlider slider : this.sliders) {
                if(slider.mousePressed(this.mc, mouseX, mouseY)) {
                    this.selectedButton = slider;
                    break;
                }
            }
        }
    }

    private void addToTrailLength(int amount) {
        this.editedCap.getTrailData().getTrail().setLength(this.editedCap.getTrailData().getTrail().getLength() + amount);
    }

    private void addToTrailWidth(int amount) {
        this.editedCap.getTrailData().getTrail().setWidth(this.editedCap.getTrailData().getTrail().getWidth() + amount);
    }

    private void updateTrailColorSliders(int index) {
        PlayerTrailData trailData = this.editedCap.getTrailData();
        int color = trailData.getAdditionalTrailData() != null && trailData.getAdditionalTrailData().stageColors != null ? trailData.getAdditionalTrailData().stageColors[index] : trailData.getTrail().getColor();
        int red = (int)(((color >> 16) & 255) / 255F * 100);
        int green = (int)(((color >> 8) & 255) / 255F * 100);
        int blue = (int)((color & 255) / 255F * 100);
        this.sliders.get(0).colorModifier = red;
        this.sliders.get(1).colorModifier = green;
        this.sliders.get(2).colorModifier = blue;
    }

    private class Button extends GuiButton {

        private final boolean isAddBtn;

        public Button(int id, boolean button, int x, int y) {
            super(id, x, y, 20, 20, "");
            this.isAddBtn = button;
        }

        public Button state(boolean state) {
            this.enabled = state;
            return this;
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

    private class TrailPartButton extends GuiButton {

        public int trailIndex;

        public TrailPartButton(int trailIndex, int x, int y) {
            super(-1, x, y, 16, 16, "");
            this.trailIndex = trailIndex;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.hovered = GuiTrailEditor.this.selectedButtonIndex == trailIndex || mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
            int variant = this.enabled ? this.hovered ? 1 : 0 : 2;
            double uStart = variant == 0 ? 60/256D : variant == 1 ? 80/256D : 100/256D;
            double uEnd = uStart + 20/256D;
            double vStart = 166/256D;
            double vEnd = vStart + 20/256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, uStart, vStart, uEnd, vEnd, false);
            mc.fontRenderer.drawString(trailIndex +"", this.x + 5, this.y + 4, 0xFFFFFF);
        }
    }

    private class ColorSlider extends GuiButton {

        /** The color modifier, <0; 100> */
        private int colorModifier;
        private String displayText;
        private boolean isMouseBtnDown = false;

        public ColorSlider(String name, int x, int y, int w, int h) {
            super(0, x, y, w, h, "");
            this.displayText = name;
        }

        @Override
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            if(this.isMouseOnSlider(mouseX, mouseY)) {
                int modifier = mouseX - this.x;
                this.colorModifier = modifier > 100 ? 100 : modifier < 0 ? 0 : modifier;
                this.isMouseBtnDown = true;
                return true;
            }
            return false;
        }

        @Override
        public void mouseReleased(int mouseX, int mouseY) {
            this.isMouseBtnDown = false;
        }

        @Override
        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
            if(isMouseBtnDown) {
                int modifier = mouseX - this.x;
                this.colorModifier = modifier > 100 ? 100 : modifier < 0 ? 0 : modifier;
            }
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.hovered = isMouseOnSliderIcon(mouseX, mouseY);
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x - 3, this.y, width + 6, height, 0, 206/256D, 100/256D, 225/256D, false);
            String text = displayText + ": " + colorModifier;
            mc.fontRenderer.drawString(text, this.x + (width - mc.fontRenderer.getStringWidth(text)) / 2, this.y + 5, this.getTextColor());
            this.renderSliderIcon(mc, mouseX, mouseY);
            this.mouseDragged(mc, mouseX, mouseY);
        }

        public void renderSliderIcon(Minecraft mc, int x, int y) {
            int sliderCenter = this.x + (int)(this.width * (colorModifier / 100.0F));
            double u = hovered ? 7/256D : 0;
            double uEnd = u + 7/256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, sliderCenter - 3, this.y, 6, 16, u, 226/256D, uEnd, 246/256D, false);
        }

        public void moveSliderTo(int x) {
            int modifier = x - this.x;
            this.colorModifier = modifier < 0 ? 0 : modifier > 100 ? 100 : modifier;
        }

        public boolean isMouseOnSlider(int mx, int my) {
            return mx >= this.x && mx <= this.x + this.width && my >= this.y && my <= this.y + this.height;
        }

        public boolean isMouseOnSliderIcon(int mx, int my) {
            int btnCenter = this.x + (int)(this.width * (colorModifier / 100.0F));
            return this.isMouseOnSliderIcon(btnCenter, mx, my);
        }

        public boolean isMouseOnSliderIcon(int btnCenter, int mx, int my) {
            return my >= this.y && my <= this.y + this.height && mx >= btnCenter - 3 && mx <= btnCenter + 3;
        }

        private int getTextColor() {
            switch (displayText) {
                case "RED": return (int)(0xFF * (colorModifier/100F)) << 16;
                case "GREEN": return (int)(0xFF * (colorModifier/100F)) << 8;
                case "BLUE": return (int)(0xFF * (colorModifier/100F));
                default: return 0;
            }
        }
    }

    private class EditorPanelSwitch {

        private float progress;
        private int x;
        private int y;
        private String text;
        private EditorType editorType;
        public boolean mouseOver;

        public EditorPanelSwitch(int x, int y, EditorType type) {
            this.x = x;
            this.y = y;
            this.editorType = type;
        }

        public void drawPanel(Minecraft mc, int mx, int my, float partialTicks) {
            float animationSpeed = 0.025F;
            int xStart = this.x - (int)(40 * progress);
            boolean selected = this.editorType == GuiTrailEditor.this.editor;
            this.mouseOver = my >= this.y && my <= this.y + 20 && mx >= xStart && mx <= xStart + 20 + 40 * progress;
            this.progress = this.mouseOver || selected ? progress < 1.0F ? progress + animationSpeed : 1.0F : progress > 0.0F ? progress - animationSpeed : 0.0F;
            this.progress = progress > 1.0F ? 1.0F : progress < 0.0F ? 0.0F : progress;
            double vStart = selected ? 20/256D : 0D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, xStart, y, 80, 20, 176/256D, vStart, 1, vStart + 20/256D, false);
            ImageHelper.drawImageWithUV(mc, TEXTURE, xStart + 3, y + 3, 14, 14, 224/256D, (128+editorType.ordinal()*32)/256D, 1, (160+editorType.ordinal()*32)/256D, false);
            mc.fontRenderer.drawString(editorType.getName(), xStart + 20, this.y + 6, 0xFFFFFF);
        }
    }

    private enum EditorType {
        GENERAL("General"),
        COLOR("Colors"),
        PREVIEW("Preview"),
        PRESETS("Presets");

        private String name;

        EditorType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void handleTypeDataRender(Minecraft mc, int mouseX, int mouseY, float partialTicks, int x, int y, GuiTrailEditor parentGUI) {
            switch (this) {
                case GENERAL: {
                    mc.fontRenderer.drawString("Trail length", x + 10, y + 16, 0x555555);
                    mc.fontRenderer.drawString("Trail width", x + 10, y + 42, 0x555555);
                    String length = parentGUI.editedCap.getTrailData().getTrail().getLength() + "";
                    String width = parentGUI.editedCap.getTrailData().getTrail().getWidth() + "";
                    mc.fontRenderer.drawString(length, x + 90 + (66 - mc.fontRenderer.getStringWidth(length)) / 2, y + 16, 0x555555);
                    mc.fontRenderer.drawString(width, x + 90 + (66 - mc.fontRenderer.getStringWidth(width)) / 2, y + 42, 0x555555);
                    break;
                }
                case COLOR: {
                    if(parentGUI.sliders == null || parentGUI.sliders.size() < 3) return;
                    float r = parentGUI.sliders.get(0).colorModifier / 100F;
                    float g = parentGUI.sliders.get(1).colorModifier / 100F;
                    float b = parentGUI.sliders.get(2).colorModifier / 100F;
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferBuilder = tessellator.getBuffer();
                    GlStateManager.disableTexture2D();
                    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    bufferBuilder.pos(x + 63, y + 70, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos( x + 63, y + 80, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos( x + 169, y + 80, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos( x + 169, y + 70, 0).color(r, g, b, 1F).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    int color = Trail.TrailColorHelper.convertToInt(r, g, b);
                    mc.fontRenderer.drawString("[DEC]: " + color, x + 63, y + 90, 0x555555);
                    mc.fontRenderer.drawString("[HEX]: #" + Integer.toHexString(color).toUpperCase(), x + 63, y + 100, 0x555555);
                    break;
                }
            }
        }
    }

    private interface IMouseDraggable {

        void mouseClicked(Minecraft mc, int mx, int my, int button);

        void mouseReleased(Minecraft mc, int mx, int my);

        void dragMouse(Minecraft mc, int mx, int my);
    }
}
