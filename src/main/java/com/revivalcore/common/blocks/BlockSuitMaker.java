package com.revivalcore.common.blocks;

import com.revivalcore.common.capabilities.CapabilitySpeedster;
import com.revivalcore.common.capabilities.ISpeedsterCap;
import com.revivalcore.common.tileentity.TileEntitySuitMaker;
import com.revivalcore.core.RevivalCore;
import com.revivalcore.util.handlers.GuiHandlerRV;
import com.revivalcore.util.helper.PlayerHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSuitMaker extends BlockBasic implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    protected static final AxisAlignedBB MODEL_NORTH_AABB = new AxisAlignedBB(0, 0, 0, 2, 0.6, 1); // Still needs to be done
    protected static final AxisAlignedBB MODEL_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 2, 0.6, 0); // Still needs to be done
    protected static final AxisAlignedBB MODEL_WEST_AABB = new AxisAlignedBB(0.2, 0, 1, 1, 0.6, -2);
    protected static final AxisAlignedBB MODEL_EAST_AABB = new AxisAlignedBB(-0.2, 0, -1, 1, 0.6, 1); // Still needs to be done
    protected static final AxisAlignedBB MODEL_UP_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.6, 1); // Still needs to be done

    public BlockSuitMaker(String name) {
        super(name);
        setCreativeTab(RevivalCore.coretab);
    }

    // TODO Open GUI
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!playerIn.isSneaking() && !worldIn.isRemote)
        {
        	playerIn.openGui(RevivalCore.instance, GuiHandlerRV.GUI_SUITMAKER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }


    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        switch (blockState.getValue(FACING)) {
            case EAST:
                return MODEL_EAST_AABB;
            case WEST:
                return MODEL_WEST_AABB;
            case SOUTH:
                return MODEL_SOUTH_AABB;
            case NORTH:
                return MODEL_NORTH_AABB;
            default:
                return MODEL_UP_AABB;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(FACING)) {
            case EAST:
                return new AxisAlignedBB(0.2, 0, 0, 1, 0.6, 1);
            case WEST:
                return new AxisAlignedBB(0.2, 0, 0, 1, 0.6, 1);
            case SOUTH:
                return new AxisAlignedBB(0, 0, 0, -1, 0.6, 1); // TODO Still needs to be configered
            case NORTH:
                return new AxisAlignedBB(1, 0, 1, -1, 0.6, 1); // TODO Still needs to be configered
            default:
                return new AxisAlignedBB(-1, 0, -1, 1, 0.6, 1);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySuitMaker();
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) 
    {
    	return new TileEntitySuitMaker();
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
