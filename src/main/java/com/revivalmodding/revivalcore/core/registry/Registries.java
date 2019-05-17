package com.revivalmodding.revivalcore.core.registry;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderSuitMaker;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import com.revivalmodding.revivalcore.core.common.events.RVRegistryEvent;
import com.revivalmodding.revivalcore.core.common.items.CoreItems;
import com.revivalmodding.revivalcore.core.common.suits.SuitDebug;
import com.revivalmodding.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalmodding.revivalcore.core.recipes.RVRecipeBuilder;
import com.revivalmodding.revivalcore.util.helper.IHaveItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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
        public static void onSuitMakerRecipesRegister(RVRegistryEvent.SuitMakerRecipeRegistryEvent e) {
        	//TODO: recipes for suit maker
        	e.register(
        			RVRecipeBuilder.create()
        			.name("debug")
        			.result(CoreItems.coffee_mug)
        			.addIngredient(Items.DIAMOND, 2)
        			.craftingTime(250)
        			.build());
        }

        @SubscribeEvent
        public static void onSuitRegister(RVRegistryEvent.SuitRegistryEvent e) {
        	e.register(new SuitDebug());
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            CoreItems.registerRenders();
            for(Block block : CoreBlocks.BLOCK_LIST) {
                if(block instanceof IHaveItem) {
                    if(((IHaveItem) block).hasItem())
                        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
                }
            }
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
