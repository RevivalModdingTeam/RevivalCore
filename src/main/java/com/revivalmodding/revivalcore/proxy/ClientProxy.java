package com.revivalmodding.revivalcore.proxy;

import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.util.handlers.client.Keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
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
    	Keybinds.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Override
    public void registerModelBakeryVariants() {

    }

    @Override
    public void displayGuiScreen(GuiScreen screen) {
        Minecraft.getMinecraft().displayGuiScreen(screen);
    }
}

