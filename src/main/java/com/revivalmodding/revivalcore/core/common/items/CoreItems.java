package com.revivalmodding.revivalcore.core.common.items;

import java.util.ArrayList;
import java.util.List;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CoreItems {

    public static final List<Item> ITEM_LIST = new ArrayList<Item>();

    public static Item coffee_mug,suit_maker;
    public static ItemSuit suit_head,suit_body,suit_legs,suit_boots;

    public static void init() {
    	
    	ArmorMaterial debugMat = EnumHelper.addArmorMaterial("debug", "idk", 100, new int[] {1,1,1,1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    	
        coffee_mug = registerItem(new ItemEatable("coffee_mug", 3, 0f, false), true);
        suit_maker = registerItem(new ItemSuitMaker(CoreBlocks.SUIT_MAKER), true);
        suit_head = registerItem(new ItemSuit("suit_head", debugMat, 1, EntityEquipmentSlot.HEAD));
        suit_body = registerItem(new ItemSuit("suit_body", debugMat, 1, EntityEquipmentSlot.CHEST));
        suit_legs = registerItem(new ItemSuit("suit_legs", debugMat, 2, EntityEquipmentSlot.LEGS));
        suit_boots = registerItem(new ItemSuit("suit_boots", debugMat, 1, EntityEquipmentSlot.FEET));
    }

    public static void registerRenders() {
        registerRender(coffee_mug);
        registerRender(suit_maker);
    }
    
    public static <T extends Item> T registerItem(T item) {
    	ITEM_LIST.add(item);
    	return item;
    }
    
    /*public static Item registerItem(Item item) {
    	ITEM_LIST.add(item);
    	return item;
    }*/

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
