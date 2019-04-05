package com.revivalcore.superpowerbase.abilities.powers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AbilityTogglePower extends AbilityToggle {

    public AbilityTogglePower(EntityLivingBase entity) {
        super(entity);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawIcon(Minecraft mc, Gui gui, int x, int y) {
        Item item = Item.getItemFromBlock(Blocks.BARRIER);
        float zLevel = Minecraft.getMinecraft().getRenderItem().zLevel;
        Minecraft.getMinecraft().getRenderItem().zLevel = -100.5F;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(item), 0, 0);
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().getRenderItem().zLevel = zLevel;
    }

    @Override
    public void updateTick() {

    }

}
