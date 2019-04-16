package com.revivalcore.core.registry;

import com.revivalcore.RevivalCore;
import com.revivalcore.core.client.render.tileentity.RenderSuitMaker;
import com.revivalcore.core.common.blocks.CoreBlocks;
import com.revivalcore.core.common.events.RVRecipeRegistryEvent;
import com.revivalcore.core.common.items.CoreItems;
import com.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalcore.core.recipes.RVRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

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
        public static void onSuitMakerRecipesRegister(RVRecipeRegistryEvent.SuitMakerRecipeRegistryEvent e) {
            e.register(RVRecipeBuilder.create().name("test0").result(Items.DIAMOND, 1).addIngredient(Items.IRON_AXE, 1, 1).build());
            e.register(RVRecipeBuilder.create().name("test1").result(Items.GOLD_INGOT, 1).addIngredient(Items.IRON_DOOR, 1, 1).addIngredient(Items.APPLE, 1, 2).build());
        }


        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            CoreItems.registerRenders();
        }

        @SubscribeEvent
        public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
            IForgeRegistry<EntityEntry> reg = e.getRegistry();
            reg.registerAll();
        }

        public static class EntityEntries {

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

        @SideOnly(Side.CLIENT)
        public static void bindEntityTEISR() {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuitMaker.class, new RenderSuitMaker());
        }
    }
}
