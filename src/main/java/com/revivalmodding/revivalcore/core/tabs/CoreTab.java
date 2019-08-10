package com.revivalmodding.revivalcore.core.tabs;

import com.revivalmodding.revivalcore.core.common.blocks.CoreBlockRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CoreTab extends CreativeTabs {

    public CoreTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.getItemFromBlock(CoreBlockRegistry.CoreBlocks.SUIT_MAKER));
    }
}
