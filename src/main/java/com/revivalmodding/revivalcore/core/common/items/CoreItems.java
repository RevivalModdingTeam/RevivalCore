package com.revivalmodding.revivalcore.core.common.items;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class CoreItems {

    public static final List<Item> ITEM_LIST = new ArrayList<Item>();

    public static Item coffee_mug,suit_maker;

    public static void init() {
        coffee_mug = registerItem(new ItemEatable("coffee_mug", 3, 0f, false), true);
        suit_maker = registerItem(new ItemSuitMaker(CoreBlocks.SUIT_MAKER), true); // TODO Make it work
    }

    public static void registerRenders() {
        registerRender(coffee_mug);
        registerRender(suit_maker);
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

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(CoreItems.ITEM_LIST.toArray(new Item[0]));
    }
}
