package com.revivalmodding.revivalcore.proxy;

import com.revivalmodding.revivalcore.core.client.gui.GuiTrailEditor;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.util.handlers.client.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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
    public void displayTrailEditorGui(EntityPlayer player, int accessLevel) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiTrailEditor(player, accessLevel));
    }
}

