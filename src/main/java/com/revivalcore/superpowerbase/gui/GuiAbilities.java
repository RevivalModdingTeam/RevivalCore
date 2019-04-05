package com.revivalcore.superpowerbase.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.RevivalCore.client.tabs.TabRegistry;
import com.RevivalCore.revivalcore.RevivalCore;
import com.revivalcore.superpowerbase.Superpowerbase;
import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.suppliers.AbilityContainerSuperpower;
import com.revivalcore.superpowerbase.abilities.suppliers.PowerContainer;
import com.revivalcore.superpowerbase.suitsets.SuitSet;
import com.RevivalCore.util.container.ContainerDummy;
import com.RevivalCore.util.handlers.SuperpowerHandler;
import com.google.common.collect.ImmutableList;

public class GuiAbilities extends GuiContainer {

    public static final ResourceLocation TEX = new ResourceLocation(RevivalCore.MODID, "textures/gui/abilities.png");

    public EntityPlayer player;
    public PowerBase.EnumAbilityContext context;
    public PowerContainer data;
    public GuiAbilityList list;
    public List<PowerBase> abilities;
    public int mouseX;
    public int mouseY;

    public int xSize_ = 256;
    public int ySize_ = 189;

    public int selectedAbility = -1;

    public GuiAbilities(EntityPlayer player, PowerBase.EnumAbilityContext context) {
        super(new ContainerDummy());
        this.player = player;
        this.context = context;
        this.data = PowerBase.getAbilityContainer(context, player);
        this.abilities = ImmutableList.copyOf(data.getAbilities());
    }

    @Override
    public void initGui() {
        super.initGui();
        this.xSize = 256;
        this.ySize = 189;
        this.xSize_ = xSize;
        this.ySize_ = ySize;

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButton10x(32, i + 239, j + 90, "?"));
        list = new GuiAbilityList(mc, this);

        int cornerX = i;
        int cornerY = j;

        TabRegistry.updateTabValues(cornerX, cornerY, InventoryTabSuperpowerAbilities.class);
        TabRegistry.addTabsToList(this.buttonList);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        if (this.list != null) {
            this.list.handleMouseInput(this.mouseX, this.mouseY);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1);

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        mc.getTextureManager().bindTexture(TEX);
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        GlStateManager.color(1, 1, 1);

        GlStateManager.pushMatrix();
        GlStateManager.translate(i + 80, j + 46, 0);
        GlStateManager.scale(3, 3, 3);
        if (this.context == PowerBase.EnumAbilityContext.SUPERPOWER && SuperpowerHandler.hasSuperpower(this.player))
            SuperpowerHandler.getSuperpower(this.player).renderIcon(mc, 0, 0);
        GlStateManager.popMatrix();

        mc.getTextureManager().bindTexture(TEX);

        if (this.context == PowerBase.EnumAbilityContext.SUPERPOWER && SuperpowerHandler.getSuperpower(this.player).canLevelUp() && data != null && data instanceof AbilityContainerSuperpower) {
            Superpowerbase superpower = SuperpowerHandler.getSuperpower(this.player);
            AbilityContainerSuperpower data = (AbilityContainerSuperpower) this.data;
            this.drawTexturedModalRect(i + 125, j + 171, 0, 215, 81, 5);
            float xp = (float) data.getXP() / (float) superpower.getXPForLevel(data.getLevel() + 1);
            if (data.getLevel() == superpower.getMaxLevel())
                xp = 1;
            this.drawTexturedModalRect(i + 125, j + 171, 0, 220, (int) (xp * 81), 5);

            RenderHelper.drawStringWithOutline("" + data.getLevel(), i + 112, j + 170, 0x98d06b, 0x081c11);

            if (data.getLevel() < superpower.getMaxLevel()) {
                boolean unicode = mc.fontRenderer.getUnicodeFlag();
                mc.fontRenderer.setUnicodeFlag(true);
                String xpProgress = data.getXP() + "/" + superpower.getXPForLevel(data.getLevel() + 1);
                int length = 120 - mc.fontRenderer.getStringWidth(xpProgress) / 2;
                mc.fontRenderer.drawString(xpProgress, i + length + 45, j + 175, 0x555555);
                mc.fontRenderer.setUnicodeFlag(unicode);
            }

        }

        String name = TextFormatting.UNDERLINE + (this.context == PowerBase.EnumAbilityContext.SUPERPOWER && SuperpowerHandler.hasSuperpower(this.player) ? SuperpowerHandler.getSuperpower(player).getDisplayName() : (this.context == PowerBase.EnumAbilityContext.SUIT && SuitSet.getSuitSet(player) != null ? SuitSet.getSuitSet(player).getDisplayName() : ""));
        int x = this.xSize / 2 - mc.fontRenderer.getStringWidth(name) / 2;
        mc.fontRenderer.drawString(name, i + x, j + 10, 0x373737);

        if (list != null) {
            this.list.drawScreen(mouseX, mouseY, partialTicks);

            GuiButton info = null;
            for (GuiButton button : this.buttonList) {
                if (button instanceof GuiButton10x)
                    info = button;
            }
            if (info != null && selectedAbility > -1 && info.enabled && mouseX >= info.x && mouseX <= info.x + info.width && mouseY >= info.y && mouseY <= info.y + info.height) {
                PowerBase ability = this.abilities.get(selectedAbility);

                if (ability != null) {
                    List<String> list = new ArrayList<String>();
                    for (String s : ability.getDisplayDescription().split("\n")) {
                        for (String s2 : mc.fontRenderer.listFormattedStringToWidth(s, 250)) {
                            list.add(s2);
                        }
                    }
                    if (mc.gameSettings.advancedItemTooltips) {
                        list.add(TextFormatting.DARK_GRAY + "ID: " + ability.getAbilityEntry().getRegistryName().toString());
                        list.add(TextFormatting.DARK_GRAY + "Key: " + ability.getKey());
                    }

                    this.drawHoveringText(list, mouseX - 250, mouseY + 10);
                }
            }
        }

    }

}
