package com.psychokiller.common.blocks;

import com.psychokiller.common.tileentity.TileEntitySuitMaker;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSuitMaker extends BlockBasic
{
	public BlockSuitMaker(String name)
	{
		super(name);
	}
	
	// TODO
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntitySuitMaker();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
}
