package com.RevivalCore.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class CoreBlocks {
    public static final List<Block> BLOCK_LIST = new ArrayList<Block>();

    public static final Block SUIT_MAKER = new BlockSuitMaker("suit_maker", Material.IRON);
}
