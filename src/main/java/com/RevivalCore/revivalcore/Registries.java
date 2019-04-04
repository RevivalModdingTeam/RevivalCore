package com.RevivalCore.revivalcore;

import com.RevivalCore.client.render.RenderSuitMaker;
import com.RevivalCore.common.blocks.CoreBlocks;
import com.RevivalCore.common.items.CoreItems;
import com.RevivalCore.common.tileentity.TileEntitySuitMaker;
import com.RevivalCore.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class Registries {

    @EventBusSubscriber
    public static class Registry {

        @SubscribeEvent
        public static void onItemRegister(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(CoreItems.ITEM_LIST.toArray(new Item[0]));
        }

        @SubscribeEvent
        public static void onBlockRegister(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(CoreBlocks.BLOCK_LIST.toArray(new Block[0]));
        }


        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            for (Item item : CoreItems.ITEM_LIST) {
                if (item instanceof IHasModel) {
                    ((IHasModel) item).registerModels();
                }
            }

            for (Block block : CoreBlocks.BLOCK_LIST) {
                if (block instanceof IHasModel) {
                    ((IHasModel) block).registerModels();
                }
            }
        }

        public static void registerEntity(EntityEntry entityEntry) {
            EntityEntries.ENTITY_ENTRIES.add(entityEntry);
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

    public static class TileRegistry {
        public static void init() {
            Registries.Registry.registerTileEntity(TileEntitySuitMaker.class, "suit_maker");
        }

        public static void bindEntityTEISR() {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuitMaker.class, new RenderSuitMaker());
        }
    }
}
