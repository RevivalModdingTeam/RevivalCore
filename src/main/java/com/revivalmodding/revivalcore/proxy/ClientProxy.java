package com.revivalmodding.revivalcore.proxy;

import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.util.handlers.client.ClientEventHandler;
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
        ClientEventHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Override
    public void registerModelBakeryVariants() {

    }
}

