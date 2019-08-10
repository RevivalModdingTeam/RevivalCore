package com.revivalmodding.revivalcore.core.common.items;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlockRegistry;
import com.revivalmodding.revivalcore.util.RCMods;
import com.revivalmodding.revivalcore.util.helper.EnumHelper.InjectionTypes;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemRegistry {

    @GameRegistry.ObjectHolder(RevivalCore.MODID)
    public static final class CoreItems {
        public static final Item COFFEE_MUG = null;
        public static final ItemSuitMaker SUIT_MAKER = null;
        public static final ItemInjection EMPTY_INJECTION = null;
        public static final ItemInjection SPEEDFORCE_INJECTION = null;
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
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
