package com.revivalmodding.revivalcore.core.common.blocks;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.tileentity.BasicTileEntities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTrailEditor extends BlockBasic {

    private final int accessLevel;

    public BlockTrailEditor(String name, int accessLevel) {
        super(name, Material.IRON);
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) {
            RevivalCore.proxy.displayTrailEditorGui(playerIn, accessLevel);
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return accessLevel == 1 ? new BasicTileEntities.TileEntityTrailEditorBasic() : new BasicTileEntities.TileEntityTrailEditorAdvanced();
    }
}
