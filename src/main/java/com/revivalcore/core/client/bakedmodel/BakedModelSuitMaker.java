package com.revivalcore.core.client.bakedmodel;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;

public class BakedModelSuitMaker implements IBakedModel
{
	@Override
	public ItemOverrideList getOverrides() 
	{
		return ItemOverrideList.NONE;
	}
	
	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) 
	{
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public boolean isAmbientOcclusion()
	{
		return false;
	}
	
	@Override
	public boolean isBuiltInRenderer()
	{
		return true;
	}
	
	@Override
	public boolean isGui3d()
	{
		return false;
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) 
	{
		Matrix4f m4f = new Matrix4f();
		m4f.setIdentity();
		TRSRTransformation trsrt = new TRSRTransformation(m4f);
		switch(cameraTransformType)
		{
			default: break;
		}
		
		return Pair.of(this, trsrt.getMatrix());
	}
}
