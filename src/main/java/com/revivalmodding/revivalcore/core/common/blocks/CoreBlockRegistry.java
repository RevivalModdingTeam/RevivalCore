package com.revivalmodding.revivalcore.core.common.blocks;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class CoreBlockRegistry {

    @GameRegistry.ObjectHolder(RevivalCore.MODID)
    public static final class CoreBlocks {

        public static final BlockSuitMaker SUIT_MAKER = null;
        public static final BlockTrailEditor TRAIL_EDITOR = null;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        ItemRegistry.ITEMBLOCK_QUEUE = new ArrayList<>();
        event.getRegistry().registerAll(
                new BlockSuitMaker("suit_maker", Material.ROCK),
                new BlockTrailEditor("trail_editor_basic", 1).setCreativeTab(RevivalCore.coretab),
                new BlockTrailEditor("trail_editor_advanced", 2).setCreativeTab(RevivalCore.coretab)
        );
    }
}
