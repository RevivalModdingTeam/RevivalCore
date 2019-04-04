package com.revivalcore.client.render;

import com.revivalcore.client.models.ModelSuitMaker;
import com.revivalcore.common.blocks.BlockSuitMaker;
import com.revivalcore.common.tileentity.TileEntitySuitMaker;
import com.revivalcore.revivalcore.RevivalCore;
import com.revivalcore.util.helper.RenderHelpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuitMaker extends TileEntitySpecialRenderer<TileEntitySuitMaker> {

    public ModelSuitMaker modelSuitMaker = new ModelSuitMaker();
    public ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID, "textures/blocks/suitmaker.png");
    Minecraft mc;

    public RenderSuitMaker() {
        mc = Minecraft.getMinecraft();
    }


    @Override
    public void render(TileEntitySuitMaker te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.6, y + 1.5, z - 0.2);
        GlStateManager.rotate(180, 1, 0, 0);
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        if (state.getBlock() instanceof BlockSuitMaker) {
            GlStateManager.rotate(RenderHelpers.getAngleFromFacing(state.getValue(BlockSuitMaker.FACING)), 0, 1, 0);
        }
        mc.getTextureManager().bindTexture(TEXTURE);
        modelSuitMaker.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
