package com.revivalcore.core.client.render.tileentity;

import com.revivalcore.RevivalCore;
import com.revivalcore.core.client.models.ModelSuitMaker;
import com.revivalcore.core.common.blocks.BlockSuitMaker;
import com.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalcore.util.helper.RenderHelpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSuitMaker extends TileEntitySpecialRenderer<TileEntitySuitMaker> {

    public ModelSuitMaker modelSuitMaker = new ModelSuitMaker();
    public static  ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID, "textures/blocks/suit_maker.png");
    Minecraft mc;
    EntityItem itemE = new EntityItem(null, 0.0D, 0.0D, 0.0D);

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
        
        // item part
        if(te.isProcessing() || !te.getStackInSlot(te.getOutput()).isEmpty())
        {
        	renderItem(te, x, y, z, partialTicks, state, te.getStackInSlot(te.getOutput()).isEmpty());
        }
    }

    @Override
    protected void bindTexture(ResourceLocation location) {
        super.bindTexture(location);
    }
    
    private void renderItem(TileEntitySuitMaker te, double x, double y, double z, float partialTicks, IBlockState state, boolean isEmpty)
    {
    	if(isEmpty)
    	{
        	if(itemE == null || te.getRecipe().getResult().getItem() != itemE.getItem().getItem())
        	{
        		itemE.setItem(te.getRecipe().constructResult());
        		itemE.setItem(new ItemStack(itemE.getItem().getItem(), 1));
        		itemE.hoverStart = 0f;
        	}
        	
        	EnumFacing facing = state.getBlock().getBedDirection(state, this.getWorld(), te.getPos());
        	
        	GlStateManager.pushMatrix();
        	{
        		GL11.glDisable(GL11.GL_LIGHTING);
        		GlStateManager.color(1f, 1f, 1f, te.getProgressionStage());
        		GlStateManager.translate((float) x + 0.5F, (float) y + 0.05F, (float) z + 0.5F);
        		GlStateManager.translate(0, 0.5, 0);
        		this.renderItemWithFacing(facing, te.getProgressionStage());
        		mc.getRenderManager().renderEntity(itemE, 0D, 0D, 0D, 0F, 0F, false);
        		GL11.glEnable(GL11.GL_LIGHTING);
        	}
        	GlStateManager.popMatrix();
    	}
    	
    	else
    	{
        	if(itemE == null || te.getStackInSlot(te.getOutput()).getItem() != itemE.getItem().getItem())
        	{
        		itemE.setItem(te.getStackInSlot(te.getOutput()));
        		itemE.setItem(new ItemStack(itemE.getItem().getItem(), 1));
        		itemE.hoverStart = 0f;
        	}
        	
        	EnumFacing facing = state.getBlock().getBedDirection(state, this.getWorld(), te.getPos());
        	
        	GlStateManager.pushMatrix();
        	{
        		GL11.glDisable(GL11.GL_LIGHTING);
        		GlStateManager.enableAlpha();
        		GlStateManager.translate((float) x + 0.5F, (float) y + 0.05F, (float) z + 0.5F);
        		GlStateManager.translate(0, 0.5, 0);
        		this.renderItemWithFacing(facing, 1f);
        		mc.getRenderManager().renderEntity(itemE, 0D, 0D, 0D, 0F, 0F, false);
        		GlStateManager.disableAlpha();
        		GL11.glEnable(GL11.GL_LIGHTING);
        	}
        	GlStateManager.popMatrix();
    	}
    }
    
    private void renderItemWithFacing(EnumFacing facing, float progress)
    {
    	// 0 - south, 1 - west, 2 - north, 3 - south
    	switch(facing)
    	{
    		case SOUTH: {
        		GlStateManager.rotate(90f, 1f, 0f, 0f);
        		GlStateManager.translate(0.1, -1.2, -0.15);
        		break;
    		}
    		
    		case NORTH: {
        		GlStateManager.rotate(270f, 1f, 0f, 0f);
        		GlStateManager.translate(0, 0, -0.3);
    		}
    	}
    }
}
