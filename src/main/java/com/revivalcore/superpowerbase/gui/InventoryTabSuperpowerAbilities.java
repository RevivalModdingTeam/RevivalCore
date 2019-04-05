package com.revivalcore.superpowerbase.gui;

import com.RevivalCore.client.tabs.AbstractTab;
import com.RevivalCore.client.tabs.TabRegistry;
import com.RevivalCore.proxy.ClientProxy;
import com.RevivalCore.revivalcore.RCConfig;
import com.RevivalCore.util.handlers.SuperpowerHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class InventoryTabSuperpowerAbilities extends AbstractTab {

    public ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");

    public InventoryTabSuperpowerAbilities() {
        super(0, 0, 0, new ItemStack(Items.BREAD));
    }

    @Override
    public void onTabClicked() {
        if (shouldAddToList() && ClientProxy.canClickTab())
            Minecraft.getMinecraft().displayGuiScreen(SuperpowerHandler.getSuperpower(Minecraft.getMinecraft().player).getAbilityGui(Minecraft.getMinecraft().player));
    }

    @Override
    public boolean shouldAddToList() {
        return RCConfig.modules.superpowers && SuperpowerHandler.hasSuperpower(Minecraft.getMinecraft().player) && SuperpowerHandler.getSuperpower(Minecraft.getMinecraft().player).shouldAddToTab(Minecraft.getMinecraft().player);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        int newPotionOffset = TabRegistry.getPotionOffsetNEI();
        if (newPotionOffset != this.potionOffsetLast) {
            this.x += newPotionOffset - this.potionOffsetLast;
            this.potionOffsetLast = newPotionOffset;
        }
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            int yTexPos = this.enabled ? 3 : 32;
            int ySize = this.enabled ? 25 : 32;
            int xOffset = this.id == 2 ? 0 : 1;
            int yPos = this.y + (this.enabled ? 3 : 0);

            mc.renderEngine.bindTexture(this.texture);
            this.drawTexturedModalRect(this.x, yPos, xOffset * 28, yTexPos, 28, ySize);

            RenderHelper.enableGUIStandardItemLighting();
            this.zLevel = 100.0F;
            this.itemRender.zLevel = 100.0F;
            GlStateManager.enableLighting();
            GlStateManager.enableRescaleNormal();
            if (shouldAddToList()) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(this.x + 6, this.y + 8, 0);
                GlStateManager.scale(0.5D, 0.5D, 0.5D);
                SuperpowerHandler.getSuperpower(Minecraft.getMinecraft().player).renderIcon(Minecraft.getMinecraft(), 0, 0);
                GlStateManager.popMatrix();
            } else {
                ItemStack empty = new ItemStack(Blocks.BARRIER);
                this.itemRender.renderItemAndEffectIntoGUI(empty, this.x + 6, this.y + 8);
                this.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, empty, this.x + 6, this.y + 8, null);
            }
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            this.itemRender.zLevel = 0.0F;
            this.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();
        }
    }

}
