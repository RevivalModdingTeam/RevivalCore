package com.revivalmodding.revivalcore.proxy;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    void preInit(FMLPreInitializationEvent e);

    void init(FMLInitializationEvent e);

    void postInit(FMLPostInitializationEvent e);

    void registerModelBakeryVariants();

    void displayGuiScreen(GuiScreen screen);
}
