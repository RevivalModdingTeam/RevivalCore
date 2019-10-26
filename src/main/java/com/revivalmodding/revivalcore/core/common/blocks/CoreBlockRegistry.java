package com.revivalmodding.revivalcore.core.common.blocks;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import com.revivalmodding.revivalcore.util.helper.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CoreBlockRegistry {

    @GameRegistry.ObjectHolder(RevivalCore.MODID)
    public static final class CoreBlocks {

        public static final BlockSuitMaker SUIT_MAKER = null;
        public static final BlockTrailEditor TRAIL_EDITOR = null;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new BlockSuitMaker(Material.ROCK).setRegistryName("suit_maker").setTranslationKey("suit_maker"),
                new BlockTrailEditor("trail_editor", 1).setCreativeTab(RevivalCore.coretab),
                new BlockTrailEditor("trail_editor_advanced", 2).setCreativeTab(RevivalCore.coretab)
        );
    }
}
