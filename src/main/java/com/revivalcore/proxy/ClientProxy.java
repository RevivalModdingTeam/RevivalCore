package com.revivalcore.proxy;

import com.revivalcore.core.registry.Registries;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        Registries.TileRegistry.bindEntityTEISR();
    }

    @Override
    public void init(FMLInitializationEvent e) {
    }


    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }


    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}

