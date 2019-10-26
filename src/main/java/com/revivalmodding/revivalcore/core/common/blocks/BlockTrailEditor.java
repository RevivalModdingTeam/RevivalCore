package com.revivalmodding.revivalcore.core.common.blocks;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.client.gui.GuiTrailEditor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrailEditor extends BlockBasic {

    private final int accessLevel;

    public BlockTrailEditor(String name, int accessLevel) {
        super(name, Material.IRON);
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) {
            RevivalCore.proxy.displayGuiScreen(new GuiTrailEditor(playerIn, accessLevel));
        }
        return true;
    }
}
