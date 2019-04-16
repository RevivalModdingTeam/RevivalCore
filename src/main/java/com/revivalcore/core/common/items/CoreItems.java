package com.revivalcore.core.common.items;

import com.revivalcore.RevivalCore;
import com.revivalcore.core.common.blocks.CoreBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class CoreItems {
    public static final List<Item> ITEM_LIST = new ArrayList<Item>();

    public static Item suit_maker_item, coffee_mug;

    public static void init() {
        suit_maker_item = registerItem(new ItemSuitMaker(CoreBlocks.SUIT_MAKER), true);
        coffee_mug = registerItem(new ItemEatable("coffee_mug", 3, 0f, false), true);
    }

    public static void registerRenders() {
        registerRender(suit_maker_item);
        registerRender(coffee_mug);
    }

    public static Item registerItem(Item item, boolean tab) {
        if (tab)
            item.setCreativeTab(RevivalCore.coretab);
        ITEM_LIST.add(item);
        return item;
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerRenderMeta(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(RevivalCore.MODID, name), "inventory"));
    }
}
