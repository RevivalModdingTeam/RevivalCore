package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityProvider;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import com.revivalmodding.revivalcore.core.client.trail.TrailPreset;
import com.revivalmodding.revivalcore.core.client.trail.renderers.TrailRenderer;
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
import java.util.Arrays;
import java.util.function.Predicate;

public class GuiTrailEditor extends GuiScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/traileditor.png");

    private final EntityPlayer player;
    private final ICoreCapability editedCap;
    private final NBTTagCompound originalNBT;
    private final int playerPermissionLevel;

    private final ArrayList<ColorSlider> sliders = new ArrayList<>();
    private final ArrayList<EditorPanelSwitch> editors = new ArrayList<>();
    private final ArrayList<Preset> presets = new ArrayList<>();
    private ColorInputField[] inputFields = null;

    private EditorType editor;
    private boolean madeValidChanges = false;
    private int x;
    private int y;

    private int selectedButtonIndex = 0;
    private int totalCost = 0;

    public GuiTrailEditor(final EntityPlayer player, int playerPermissionLevel) {
        this.player = player;
        this.editedCap = new CoreCapabilityImpl(player);
        this.originalNBT = CoreCapabilityImpl.getInstance(player).toNBT();
        this.editedCap.fromNBT(originalNBT);
        editor = EditorType.GENERAL;
        this.playerPermissionLevel = playerPermissionLevel;
    }

    @Override
    public void initGui() {
        this.x = (this.width - 176) / 2;
        this.y = (this.height - 166) / 2;
        this.editors.clear();
        for (EditorType editor : EditorType.values()) {
            int i = editor.ordinal();
            this.editors.add(i, new EditorPanelSwitch(x - 20, y + 10 + i * 22, editor));
        }

        this.updateGUIElements();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if(this.inputFields != null) {
            for(ColorInputField field : this.inputFields) {
                if(field.isListening) {
                    field.addCharacter(typedChar);
                }
            }
        }
    }

    public void updateGUIElements() {
        this.buttonList.clear();
        this.sliders.clear();
        this.presets.clear();
        this.inputFields = null;

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
                for (int i = 0; i < trailLength; i++) {
                    int color = trailData.getAdditionalTrailData() != null && trailData.getAdditionalTrailData().stageColors != null ? trailData.getAdditionalTrailData().stageColors[i] : trailData.getTrail().getColor();
                    this.addButton(new TrailPartButton(i, color, x + (i / 5 >= 1 ? 30 : 10), y + 10 + (i % 5) * 18));
                }
                this.sliders.add(new ColorSlider("RED", x + 66, y + 10, 100, 16));
                this.sliders.add(new ColorSlider("GREEN", x + 66, y + 28, 100, 16));
                this.sliders.add(new ColorSlider("BLUE", x + 66, y + 46, 100, 16));
                this.updateTrailColorSliders(0);
                GuiButton button = new GuiButton(10, x + 10, y + 136, 156, 20, "Apply [1 Level]");
                button.enabled = false;
                this.addButton(button);
                this.inputFields = new ColorInputField[2];
                this.inputFields[0] = new ColorInputField(x + 85, y + 85, 84, 16, false);
                this.inputFields[1] = new ColorInputField(x + 85, y + 102, 84, 16, true);
                this.onColorValueModified(0);
                break;
            }
            case PRESETS: {
                for(int i = 0; i < 6; i++) {
                    TrailPreset preset = this.editedCap.getTrailData().getPresets()[i];
                    this.presets.add(this.new Preset(i, x + 10, y + 10 + i * 25, preset));
                }
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
                        if (length > width) {
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
                if (button instanceof TrailPartButton) {
                    TrailPartButton trailPartButton = (TrailPartButton) button;
                    this.selectedButtonIndex = trailPartButton.trailIndex;
                    this.updateTrailColorSliders(this.selectedButtonIndex);
                } else if (button.id == 10) {
                    int partColor = this.getSlidersColor();
                    this.saveTrailColorPart(partColor);
                }
                break;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.editors.forEach(editMode -> editMode.drawPanel(this.mc, mouseX, mouseY, partialTicks));
        this.mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color(1f, 1f, 1f, 1f);
        this.drawTexturedModalRect(x, y, 0, 0, 176, 166);
        this.presets.forEach(preset -> preset.draw(mc, mouseX, mouseY, partialTicks));
        this.sliders.forEach(slider -> slider.drawButton(this.mc, mouseX, mouseY, partialTicks));
        this.buttonList.forEach(btn -> btn.drawButton(this.mc, mouseX, mouseY, partialTicks));
        if(this.inputFields != null) {
            for(ColorInputField field : this.inputFields) {
                field.draw(this.mc, mouseX, mouseY);
            }
        }
        this.editor.handleTypeDataRender(this.mc, mouseX, mouseY, partialTicks, x, y, this);
    }

    /**
     *
     * @param mode - 2 = Decimal field update; 1 = Hexadecimal field update; 0 = Slider update
     */
    public void onColorValueModified(int mode) {
        if(mode == 0) {
            int newColor = this.getSlidersColor();
            for(ColorInputField field : this.inputFields) {
                field.value = field.hex ? Integer.toHexString(newColor).toUpperCase() : newColor + "";
            }
        } else {
            if(mode == 1) {
                if(this.inputFields[1].value.isEmpty()) return;
                int color = Integer.decode("0x" + this.inputFields[1].value.toLowerCase());
                this.setSliderColors(color);
                this.inputFields[0].value = color + "";
            } else if(mode == 2) {
                if (this.inputFields[0].value.isEmpty()) return;
                int color = Integer.parseInt(this.inputFields[0].value);
                this.setSliderColors(color);
                this.inputFields[1].value = Integer.toHexString(color).toUpperCase();
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            for (EditorPanelSwitch panelSwitch : this.editors) {
                if (panelSwitch.mouseOver && panelSwitch.editorType != this.editor && !panelSwitch.isLocked) {
                    this.editor = panelSwitch.editorType;
                    this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    this.updateGUIElements();
                    break;
                }
            }
            for (ColorSlider slider : this.sliders) {
                if (slider.mousePressed(this.mc, mouseX, mouseY)) {
                    this.selectedButton = slider;
                    break;
                }
            }
            if(inputFields != null) {
                for(ColorInputField inputField : this.inputFields) {
                    inputField.updateStatus(mouseX, mouseY);
                }
            }
            for(Preset preset : presets) {
                if(preset.onClick(mc, mouseX, mouseY)) {
                    this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
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
        int red = (int) (((color >> 16) & 255) / 255F * 100);
        int green = (int) (((color >> 8) & 255) / 255F * 100);
        int blue = (int) ((color & 255) / 255F * 100);
        this.sliders.get(0).colorModifier = red;
        this.sliders.get(1).colorModifier = green;
        this.sliders.get(2).colorModifier = blue;
    }

    private void saveTrailColorPart(int color) {
        Trail trail = editedCap.getTrailData().getTrail();
        TrailOptionalData data = editedCap.getTrailData().getAdditionalTrailData();
        if(data == null) {
            data = new TrailOptionalData(trail, -1, new int[trail.getLength()]);
            editedCap.getTrailData().setAdditionalTrailData(data);
            Arrays.fill(data.stageColors, trail.getColor());
            data.stageColors[selectedButtonIndex] = color;
        } else if(data.stageColors.length != trail.getLength()) {
            int[] newColors = new int[trail.getLength()];
            for(int i = 0; i < trail.getLength(); i++) {
                newColors[i] = i < data.stageColors.length ? data.stageColors[i] : trail.getColor();
            }
            editedCap.getTrailData().setAdditionalTrailData(new TrailOptionalData(trail, data.secondaryColor, newColors));
        } else {
            data.stageColors[selectedButtonIndex] = color;
        }
    }

    private int getSlidersColor() {
        return Trail.TrailColorHelper.convertToInt(this.sliders.get(0).colorModifier / 100.0F, this.sliders.get(1).colorModifier / 100.0F, this.sliders.get(2).colorModifier / 100.0F);
    }

    public void setSliderColors(int color) {
        int red = (int) (((color >> 16) & 255) / 255F * 100);
        int green = (int) (((color >> 8) & 255) / 255F * 100);
        int blue = (int) ((color & 255) / 255F * 100);
        this.sliders.get(0).colorModifier = red;
        this.sliders.get(1).colorModifier = green;
        this.sliders.get(2).colorModifier = blue;
    }

    private enum EditorType {
        GENERAL("General", 0),
        COLOR("Colors", 1),
        PREVIEW("Preview", 1),
        PRESETS("Presets", 2);

        private final String name;
        private final int permissionAccessLevel;

        EditorType(String name, int permissionAccessLevel) {
            this.name = name;
            this.permissionAccessLevel = permissionAccessLevel;
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
                    if (parentGUI.sliders == null || parentGUI.sliders.size() < 3) return;
                    float r = parentGUI.sliders.get(0).colorModifier / 100F;
                    float g = parentGUI.sliders.get(1).colorModifier / 100F;
                    float b = parentGUI.sliders.get(2).colorModifier / 100F;
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferBuilder = tessellator.getBuffer();
                    GlStateManager.disableTexture2D();
                    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    bufferBuilder.pos(x + 63, y + 70, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos(x + 63, y + 80, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos(x + 169, y + 80, 0).color(r, g, b, 1F).endVertex();
                    bufferBuilder.pos(x + 169, y + 70, 0).color(r, g, b, 1F).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    mc.fontRenderer.drawString("DEC:", x + 63, y + 90, 0x555555);
                    mc.fontRenderer.drawString("HEX:", x + 63, y + 106, 0x555555);
                    break;
                }
                case PREVIEW: {
                    ICoreCapability tempCap = new CoreCapabilityImpl(parentGUI.player);
                    NBTTagCompound nbt = parentGUI.editedCap.toNBT();
                    tempCap.fromNBT(nbt);
                    Trail trail = tempCap.getTrailData().getTrail();
                    TrailOptionalData optionalData = tempCap.getTrailData().getAdditionalTrailData();
                    TrailRenderer trailRenderer = tempCap.getTrailData().getTrailRenderer();
                    trailRenderer.renderTrailIntoGUI(trail, optionalData, x + 10, y + 40, 156, 110);
                    mc.fontRenderer.drawString("Current trail preview", x + 10, y + 10, 0x333333);
                    break;
                }
            }
        }
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
            double uStart = variant == 0 ? 0.0D : variant == 1 ? 20 / 256D : 40 / 256D;
            double uEnd = uStart + 20 / 256D;
            double vStart = isAddBtn ? 166 / 256D : 186 / 256D;
            double vEnd = vStart + 20 / 256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, uStart, vStart, uEnd, vEnd, false);
        }
    }

    private class TrailPartButton extends GuiButton {

        public final int trailIndex;

        public TrailPartButton(int trailIndex, int originalColor, int x, int y) {
            super(-1, x, y, 16, 16, "");
            this.trailIndex = trailIndex;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.hovered = GuiTrailEditor.this.selectedButtonIndex == trailIndex || mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
            int variant = this.enabled ? this.hovered ? 1 : 0 : 2;
            double uStart = variant == 0 ? 60 / 256D : variant == 1 ? 80 / 256D : 100 / 256D;
            double uEnd = uStart + 20 / 256D;
            double vStart = 166 / 256D;
            double vEnd = vStart + 20 / 256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.width, this.height, uStart, vStart, uEnd, vEnd, false);
            mc.fontRenderer.drawString(trailIndex + "", this.x + 5, this.y + 4, 0xFFFFFF);
        }
    }

    private class ColorSlider extends GuiButton {

        /**
         * The color modifier, <0; 100>
         */
        private int colorModifier;
        private String displayText;
        private boolean isMouseBtnDown = false;

        public ColorSlider(String name, int x, int y, int w, int h) {
            super(0, x, y, w, h, "");
            this.displayText = name;
        }

        @Override
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
            if (this.isMouseOnSlider(mouseX, mouseY)) {
                int modifier = mouseX - this.x;
                this.colorModifier = modifier > 100 ? 100 : modifier < 0 ? 0 : modifier;
                this.isMouseBtnDown = true;
                GuiTrailEditor.this.onColorValueModified(0);
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
            if (isMouseBtnDown) {
                int modifier = mouseX - this.x;
                this.colorModifier = modifier > 100 ? 100 : modifier < 0 ? 0 : modifier;
                GuiTrailEditor.this.onColorValueModified(0);
            }
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.hovered = isMouseOnSliderIcon(mouseX, mouseY);
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x - 3, this.y, width + 6, height, 0, 206 / 256D, 100 / 256D, 225 / 256D, false);
            String text = displayText + ": " + colorModifier;
            mc.fontRenderer.drawString(text, this.x + (width - mc.fontRenderer.getStringWidth(text)) / 2, this.y + 5, this.getTextColor());
            this.renderSliderIcon(mc, mouseX, mouseY);
            this.mouseDragged(mc, mouseX, mouseY);
        }

        public void renderSliderIcon(Minecraft mc, int x, int y) {
            int sliderCenter = this.x + (int) (this.width * (colorModifier / 100.0F));
            double u = hovered ? 7 / 256D : 0;
            double uEnd = u + 7 / 256D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, sliderCenter - 3, this.y, 6, 16, u, 226 / 256D, uEnd, 246 / 256D, false);
        }

        public void moveSliderTo(int x) {
            int modifier = x - this.x;
            this.colorModifier = modifier < 0 ? 0 : modifier > 100 ? 100 : modifier;
        }

        public boolean isMouseOnSlider(int mx, int my) {
            return mx >= this.x && mx <= this.x + this.width && my >= this.y && my <= this.y + this.height;
        }

        public boolean isMouseOnSliderIcon(int mx, int my) {
            int btnCenter = this.x + (int) (this.width * (colorModifier / 100.0F));
            return this.isMouseOnSliderIcon(btnCenter, mx, my);
        }

        public boolean isMouseOnSliderIcon(int btnCenter, int mx, int my) {
            return my >= this.y && my <= this.y + this.height && mx >= btnCenter - 3 && mx <= btnCenter + 3;
        }

        private int getTextColor() {
            switch (displayText) {
                case "RED":
                    return (int) (0xFF * (colorModifier / 100F)) << 16;
                case "GREEN":
                    return (int) (0xFF * (colorModifier / 100F)) << 8;
                case "BLUE":
                    return (int) (0xFF * (colorModifier / 100F));
                default:
                    return 0;
            }
        }
    }

    private class EditorPanelSwitch {

        public boolean mouseOver;
        private float progress;
        private int x;
        private int y;
        private String text;
        private EditorType editorType;
        private final boolean isLocked;

        public EditorPanelSwitch(int x, int y, EditorType type) {
            this.x = x;
            this.y = y;
            this.editorType = type;
            this.isLocked = GuiTrailEditor.this.playerPermissionLevel < type.permissionAccessLevel;
        }

        public void drawPanel(Minecraft mc, int mx, int my, float partialTicks) {
            float animationSpeed = 0.025F;
            int xStart = this.x - (int) (40 * progress);
            boolean selected = this.editorType == GuiTrailEditor.this.editor;
            this.mouseOver = my >= this.y && my <= this.y + 20 && mx >= xStart && mx <= xStart + 20 + 40 * progress;
            this.progress = this.mouseOver || selected ? progress < 1.0F ? progress + animationSpeed : 1.0F : progress > 0.0F ? progress - animationSpeed : 0.0F;
            this.progress = progress > 1.0F ? 1.0F : progress < 0.0F ? 0.0F : progress;
            double vStart = selected ? 20 / 256D : 0D;
            ImageHelper.drawImageWithUV(mc, TEXTURE, xStart, y, 80, 20, 176 / 256D, vStart, 1, vStart + 20 / 256D, false);
            ImageHelper.drawImageWithUV(mc, TEXTURE, xStart + 3, y + 3, 14, 14, 224 / 256D, (128 + editorType.ordinal() * 32) / 256D, 1, (160 + editorType.ordinal() * 32) / 256D, false);
            if(isLocked) {
                ImageHelper.drawImageWithUV(mc, TEXTURE, xStart + 3, y + 3, 14, 14, 192 / 256D, 224 / 256D, 224 / 256D, 1, false);
            }
            mc.fontRenderer.drawString(editorType.getName(), xStart + 20, this.y + 6, isLocked ? 0xDD0000: 0xFFFFFF);
        }
    }

    private class ColorInputField {

        private int x, y, w, h;
        private boolean hex;
        private String value = "";
        private final Predicate<Character> textValidator;
        private boolean isListening = false;
        private byte cursorTimer = -128;

        public ColorInputField(int x, int y, int w, int h, boolean hex) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.hex = hex;
            this.textValidator = c -> ColorInputField.this.hex ? (Character.isDigit(c) || (c > 64 && c < 71) || (c > 96 && c < 103)) && ColorInputField.this.value.length() < 6 : Character.isDigit(c) && ColorInputField.this.value.length() < 8;
        }

        public void draw(Minecraft mc, int mx, int my) {
            ImageHelper.drawImageWithUV(mc, TEXTURE, this.x, this.y, this.w, this.h, 176/256D, 40/256D, 1.0D, 60/256D, false);
            this.renderCursorIcon(mc);
            mc.fontRenderer.drawString(value, this.x + 3, this.y + 4, 0xFFFFFF);
        }

        public void updateStatus(int mx, int my) {
            this.isListening = mx >= x && mx <= x + w && my >= y && my <= y + h;
        }

        public void addCharacter(char c) {
            if(c == '\b') {
                if(value.length() > 0) {
                    value = value.substring(0, value.length() - 1);
                    GuiTrailEditor.this.onColorValueModified(hex ? 1 : 2);
                }
            } else if(textValidator.test(c)) {
                if(c > 70) c -= 32;
                if(!hex) {
                    int newValue = Integer.parseInt(value + c);
                    if(newValue > 16777215) {
                        this.value = "16777215";
                        GuiTrailEditor.this.onColorValueModified(2);
                        return;
                    }
                }
                value = value + c;
                GuiTrailEditor.this.onColorValueModified(hex ? 1 : 2);
            }
        }

        private void renderCursorIcon(Minecraft mc) {
            if(isListening) {
                this.cursorTimer += 2;
                if(cursorTimer >= 0) {
                    int start = mc.fontRenderer.getStringWidth(value);
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder builder = tessellator.getBuffer();
                    builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                    GlStateManager.disableTexture2D();
                    builder.pos(x + 3 + start, y + 2, 0).color(1f, 1f, 1f, 1f).endVertex();
                    builder.pos(x + 3 + start, y + h - 2, 0).color(1f, 1f, 1f, 1f).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                }
            }
        }
    }

    private class Preset {

        private int index;
        private int x, y;
        public GuiButton load, save, delete;
        private TrailPreset storedPreset;

        public Preset(int id, int x, int y, TrailPreset preset) {
            this.index = id;
            this.x = x;
            this.y = y;
            this.storedPreset = preset;
            this.load = new GuiButton(id, x + 50, y, 40, 20, "Load");
            this.save = new GuiButton(id, x + 95, y, 40, 20, "Save");
            this.delete = new GuiButton(id, x + 140, y, 20, 20, "X");
            this.load.enabled = this.storedPreset != null;
            this.delete.enabled = this.load.enabled;
        }

        public void draw(Minecraft mc, int mx, int my, float partialTicks) {
            this.load.drawButton(mc, mx, my, partialTicks);
            this.save.drawButton(mc, mx, my, partialTicks);
            this.delete.drawButton(mc, mx, my, partialTicks);
            boolean mouseOver = mx >= x && mx <= x + 43 && my >= y && my <= y + 20;
            if(mouseOver) {
                GlStateManager.pushMatrix();
                GlStateManager.disableTexture2D();
                GlStateManager.translate(0, 0, 1);
                ImageHelper.drawColorShape(mx, my + 5, 100, 50, 0.1F, 0.1F, 0.1F);
                GlStateManager.enableTexture2D();
                GlStateManager.popMatrix();
                boolean flag = storedPreset == null;
                try {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(0, 0, 2);
                    String[] info = flag ? new String[] {"Empty"} : storedPreset.getTrailInfo().getFormattedTrailInfo().split("/");
                    for(int i = 0; i < info.length; i++) {
                        String display = info[i];
                        mc.fontRenderer.drawString(display, mx + 3, my + 8 + i * 15, 0xFFFFFF);
                    }
                    GlStateManager.popMatrix();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mc.fontRenderer.drawString("Preset " + (index+1), x, y + 6, mouseOver ? 0xFFFF44 : 0x444444);
        }

        public boolean onClick(Minecraft mc, int mx, int my) {
            if(load.enabled) {
                if(load.mousePressed(mc, mx, my)) {
                    this.loadButtonClicked();
                    return true;
                }
            }
            if(save.enabled) {
                if(save.mousePressed(mc, mx, my)) {
                    this.saveButtonClicked();
                    return true;
                }
            }
            if(delete.enabled) {
                if(delete.mousePressed(mc, mx, my)) {
                    this.storedPreset = null;
                    this.load.enabled = false;
                    this.delete.enabled = false;
                    return true;
                }
            }
            // TODO sync on server
            mc.player.getCapability(CoreCapabilityProvider.DATA, null).fromNBT(GuiTrailEditor.this.editedCap.toNBT());
            return false;
        }

        private void saveButtonClicked() {
            PlayerTrailData trailData = GuiTrailEditor.this.editedCap.getTrailData();
            TrailPreset preset = new TrailPreset().updatePreset(trailData.getTrail(), trailData.getAdditionalTrailData());
            trailData.getPresets()[this.index] = preset;
            this.storedPreset = preset;
            this.load.enabled = true;
            this.delete.enabled = true;
        }

        private void loadButtonClicked() {
            // TODO load
        }
    }
}
