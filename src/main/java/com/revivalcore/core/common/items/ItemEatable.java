package com.revivalcore.core.common.items;

import com.revivalcore.RevivalCore;
import com.revivalcore.util.helper.IHasModel;
import net.minecraft.item.ItemFood;

public class ItemEatable extends ItemFood implements IHasModel {

    public ItemEatable(String name ,int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(RevivalCore.coretab);

        CoreItems.ITEM_LIST.add(this);
    }


    @Override
    public void registerModels() {
        RevivalCore.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
