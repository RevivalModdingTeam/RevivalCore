package com.revivalcore.core.common.items;

import com.revivalcore.RevivalCore;
import com.revivalcore.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemSuitMaker extends ItemBlock implements IHasModel {

    public ItemSuitMaker(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName()+ "_item");
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);

        CoreItems.ITEM_LIST.add(this);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public void registerModels() {
        RevivalCore.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
