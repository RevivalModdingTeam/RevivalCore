package com.revivalcore.core.common.items;

import com.revivalcore.RevivalCore;
import net.minecraft.item.ItemFood;

public class ItemEatable extends ItemFood {

    public ItemEatable(String name ,int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(RevivalCore.coretab);
    }
}
