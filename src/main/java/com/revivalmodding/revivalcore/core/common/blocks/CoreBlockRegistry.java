package com.revivalmodding.revivalcore.core.common.blocks;

import com.google.common.base.Preconditions;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;

@GameRegistry.ObjectHolder(RevivalCore.MODID)

public class CoreBlockRegistry {

    public static final Block SUIT_MAKER = new BlockSuitMaker("suit_maker", Material.ROCK);
    public static final Block TRAIL_EDITOR_BASIC = new BlockTrailEditor("trail_editor_basic", 1).setCreativeTab(RevivalCore.coretab);
    public static final Block TRAIL_EDITOR_ADVANCED = new BlockTrailEditor("trail_editor_advanced", 2).setCreativeTab(RevivalCore.coretab);



    @Mod.EventBusSubscriber(modid = RevivalCore.MODID)
    public static class CoreBlocks {
        public static final NonNullList<Item> ITEM_BLOCKS = NonNullList.<Item>create();
        @SubscribeEvent
        public static void registerBlock(final RegistryEvent.Register<Block> event) {
            final Block[] blocks = {
                    SUIT_MAKER,
                    TRAIL_EDITOR_BASIC,
                    TRAIL_EDITOR_ADVANCED
            };

            event.getRegistry().registerAll(blocks);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(SUIT_MAKER),
                    new ItemBlock(TRAIL_EDITOR_BASIC),
                    new ItemBlock(TRAIL_EDITOR_ADVANCED)
            };

            for(final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has a null registry name.", block);
                event.getRegistry().register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }

        }
    }
}
