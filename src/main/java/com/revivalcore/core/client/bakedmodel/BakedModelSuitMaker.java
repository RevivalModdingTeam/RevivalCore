package com.revivalcore.core.client.bakedmodel;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
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
		final Matrix4f m4f = new Matrix4f();
		m4f.setIdentity();
		TRSRTransformation trsrt = new TRSRTransformation(m4f);
		Vector3f scale;
		Vector3f translate;
		Quat4f leftRot = new Quat4f();
		Quat4f rightRot = new Quat4f();
		switch(cameraTransformType)
		{
			case FIRST_PERSON_RIGHT_HAND: {
				translate = new Vector3f(0.18f, 0.05f, 0.1f);
				scale = new Vector3f(0.6f, 0.6f, 0.6f);
				leftRot = new Quat4f(0f, 180f, 0f, 150f);
				rightRot = new Quat4f(0f, 0f, 90f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			//TODO - finish
			case FIRST_PERSON_LEFT_HAND: {
				translate = new Vector3f(0.18f, 0.05f, 0.1f);
				scale = new Vector3f(0.6f, 0.6f, 0.6f);
				leftRot = new Quat4f(0f, 180f, 0f, 150f);
				rightRot = new Quat4f(0f, 0f, 270f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			case THIRD_PERSON_RIGHT_HAND: {
				translate = new Vector3f(0f, 0.2f, 0f);
				scale = new Vector3f(0.4f, 0.4f, 0.4f);
				leftRot = new Quat4f(0f, 180f, 0f, 0f);
				rightRot = new Quat4f(1f, 0f, 0f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			//TODO - finish
			case THIRD_PERSON_LEFT_HAND: {
				translate = new Vector3f(0, 0, 0);
				scale = new Vector3f(0, 0, 0);
				leftRot = new Quat4f(0f, 0f, 0f, 0f);
				rightRot = new Quat4f(0f, 0f, 0f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			case GUI: {
				translate = new Vector3f(0f, -0.2f, 0f);
				scale = new Vector3f(0.4f, 0.4f, 0.4f);
				leftRot = new Quat4f(0f, 180f, 0f, 0f);
				rightRot = new Quat4f(0f, 0f, 180f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			case GROUND: {
				translate = new Vector3f(0f, 0f, -0.2f);
				scale = new Vector3f(0.5f, 0.5f, 0.5f);
				leftRot = new Quat4f(0f, 180f, 0f, 0f);
				rightRot = new Quat4f(0f, 0f, 0.1f, 0f);
				trsrt = new TRSRTransformation(translate, leftRot, scale, rightRot);
				return Pair.of(this, trsrt.getMatrix());
			}
			
			default: break;
		}
		
		return Pair.of(this, trsrt.getMatrix());
	}
}
