package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {

    public static KeyBinding ENABLE;
    public static KeyBinding POWER1;
    public static KeyBinding POWER2;
    public static KeyBinding POWER3;

    public static void init() {
        ENABLE = new KeyBinding(RevivalCore.MODID + ".keybinds.enable", Keyboard.KEY_X, RevivalCore.NAME);
        ClientRegistry.registerKeyBinding(ENABLE);
        POWER1 = new KeyBinding(RevivalCore.MODID + ".keybinds.power1", Keyboard.KEY_N, RevivalCore.NAME);
        ClientRegistry.registerKeyBinding(POWER1);
        POWER2 = new KeyBinding(RevivalCore.MODID + ".keybinds.power2", Keyboard.KEY_O, RevivalCore.NAME);
        ClientRegistry.registerKeyBinding(POWER2);
        POWER3 = new KeyBinding(RevivalCore.MODID + ".keybinds.power3", Keyboard.KEY_C, RevivalCore.NAME);
        ClientRegistry.registerKeyBinding(POWER3);
    }
}
