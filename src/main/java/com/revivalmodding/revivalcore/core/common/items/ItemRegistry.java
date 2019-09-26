package com.revivalmodding.revivalcore.core.common.items;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlockRegistry;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuitMultiSeason;
import com.revivalmodding.revivalcore.util.RCMods;
import com.revivalmodding.revivalcore.util.helper.EnumHelper.InjectionTypes;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static ItemArmor.ArmorMaterial SUIT_MATERIAL = EnumHelper.addArmorMaterial("suit", "", -1, new int[4], 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    @GameRegistry.ObjectHolder(RevivalCore.MODID)
    public static final class CoreItems {
        public static final Item COFFEE_MUG = null;
        public static final ItemSuitMaker SUIT_MAKER = null;
        public static final ItemInjection EMPTY_INJECTION = null;
        public static final ItemInjection SPEEDFORCE_INJECTION = null;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        SUIT_MATERIAL = EnumHelper.addArmorMaterial("suit", "", -1, new int[4], 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
        event.getRegistry().registerAll(
                new ItemEatable("coffee_mug", 3, 0F, true),
                new ItemSuitMaker(CoreBlockRegistry.CoreBlocks.SUIT_MAKER),
                new ItemInjection("empty_injection", InjectionTypes.EMPTY)
        );
        if(RCMods.Mods.SPEEDSTERREBORN.isLoaded()) {
            event.getRegistry().registerAll(
                    new ItemInjection("speedforce_injection", InjectionTypes.SPEEDSTER)
            );
        }
    }
}
