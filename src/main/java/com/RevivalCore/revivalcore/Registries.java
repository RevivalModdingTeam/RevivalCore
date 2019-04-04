package com.RevivalCore.revivalcore;

import com.RevivalCore.common.blocks.BlockSuitMaker;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class Registries
{
	@ObjectHolder(RevivalCore.MODID)
	public static final class Items
	{
		
	}
	
	@ObjectHolder(RevivalCore.MODID)
	public static final class Blocks
	{
		public static final BlockSuitMaker SUIT_MAKER = null;
	}
	
	@EventBusSubscriber
	public static class Registry
	{
		static final List<ItemBlock> itemblocks = new ArrayList<ItemBlock>();
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> e)
		{
			final Block[] blocks =
			{
				new BlockSuitMaker("suit_maker")
			};
			
			e.getRegistry().registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> e)
		{
			final Item[] items = 
			{
			};
			
			e.getRegistry().registerAll(items);
			itemblocks.forEach(e.getRegistry()::register);
		}
		
		public static void registerItemBlock(Block block)
		{
			ItemBlock iBlock = new ItemBlock(block);
			iBlock.setRegistryName(block.getRegistryName());
			itemblocks.add(iBlock);
		}

		public static void registerEntity(EntityEntry entityEntry) {
		}

		@SubscribeEvent
		public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
			IForgeRegistry<EntityEntry> reg = e.getRegistry();
			EntityEntries.ENTITY_ENTRIES.forEach(reg::register);
		}

		public static class EntityEntries {
			public static final List<EntityEntry> ENTITY_ENTRIES = new ArrayList<EntityEntry>();
		}

		// Use in preinit in mod.
		public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
			GameRegistry.registerTileEntity(clazz, new ResourceLocation(RevivalCore.MODID, name));
		}
	}
	
	@EventBusSubscriber(Side.CLIENT)
	public static class ModelRegistry
	{
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent e)
		{
			final IForgeRegistry<Block> blocks = ForgeRegistries.BLOCKS;
			final IForgeRegistry<Item> items = ForgeRegistries.ITEMS;
			
			// block model registry
			for(ResourceLocation l : blocks.getKeys())
			{
				if(l.getPath().equals(RevivalCore.MODID))
				{
					register(blocks.getValue(l));
				}
			}
			
			// item model registry
			for(ResourceLocation l : items.getKeys())
			{
				if(l.getPath().equals(RevivalCore.MODID))
				{
					register(items.getValue(l));
				}
			}
		}

		private static void register(Item item)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		
		private static void register(Block block)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
	}
}
