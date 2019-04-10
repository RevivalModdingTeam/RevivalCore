package com.revivalcore.core.common.items;

import com.revivalcore.core.common.blocks.CoreBlocks;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class CoreItems {
    public static final List<Item> ITEM_LIST = new ArrayList<Item>();

    public static final Item SUIT_MAKER_ITEM = new ItemSuitMaker(CoreBlocks.SUIT_MAKER);

}
