package com.revivalmodding.revivalcore.core.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

    public BlockBasic(String name, Material material) {
        super(material);
        this.setTranslationKey(name);
        this.setRegistryName(name);
    }
}
