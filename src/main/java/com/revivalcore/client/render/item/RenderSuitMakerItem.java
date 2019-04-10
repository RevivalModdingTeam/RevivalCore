package com.revivalcore.client.render.item;

import com.revivalcore.client.models.ModelSuitMaker;
import com.revivalcore.client.render.tileentity.RenderSuitMaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class RenderSuitMakerItem extends TileEntityItemStackRenderer {

    Minecraft mc;
    ModelSuitMaker suitMaker = new ModelSuitMaker();

    public RenderSuitMakerItem() {
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, float partialTicks) {
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(RenderSuitMaker.TEXTURE);
        GlStateManager.translate(0.5, -0.455F, 0);
        GlStateManager.rotate(0, 0.0F, 0.0F, 0.0F);
        suitMaker.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
