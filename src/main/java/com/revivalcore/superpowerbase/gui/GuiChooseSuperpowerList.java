package com.revivalcore.superpowerbase.gui;

import com.revivalcore.superpowerbase.Superpowerbase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.GuiScrollingList;

public class GuiChooseSuperpowerList extends GuiScrollingList {

    public GuiChooseSuperpower parent;

    public GuiChooseSuperpowerList(Minecraft client, GuiChooseSuperpower parent) {
        super(client, 248, 145, ((parent.height - parent.getYSize()) / 2) + 19, ((parent.height - parent.getYSize()) / 2) + 19 + 145, ((parent.width - parent.getXSize()) / 2) + 4, 27, parent.width, parent.height);
        this.parent = parent;
    }

    @Override
    protected int getSize() {
        return parent.superpowers.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        parent.selected = index;
        parent.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1, 1);
    }

    @Override
    protected boolean isSelected(int index) {
        return index == parent.selected;
    }

    @Override
    protected void drawBackground() {

    }

    @Override
    protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
        Superpowerbase s = parent.superpowers.get(slotIdx);
        RenderHelper.drawStringWithOutline(s.getDisplayName(), left + 30, slotTop + 7, 0xffffff, 0);

        parent.mc.getTextureManager().bindTexture(GuiChooseSuperpower.TEX);
        parent.drawTexturedModalRect(this.left + 2, slotTop, 0, 189, 22, 22);

        GlStateManager.pushMatrix();
        GlStateManager.translate(left + 5, slotTop + 3, 0);
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        s.renderIcon(parent.mc, parent, 0, 0);
        GlStateManager.popMatrix();
    }

}
