package com.revivalmodding.revivalcore.util.handlers.client;

import org.lwjgl.input.Keyboard;

import com.revivalmodding.revivalcore.RevivalCore;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {

    public static KeyBinding ENABLE;
    public static KeyBinding POWER1;
    public static KeyBinding POWER2;
    public static KeyBinding POWER3;
    
    private static KeyBinding init(String langKey, int key) {
    	KeyBinding keyBinding = new KeyBinding(RevivalCore.MODID + ".keybinds." + langKey, key, RevivalCore.NAME);
    	ClientRegistry.registerKeyBinding(keyBinding);
    	return keyBinding;
    }
    
    public static void init() {
    	ENABLE = init("enable", Keyboard.KEY_X);
    	POWER1 = init("power1", Keyboard.KEY_N);
    	POWER2 = init("power2", Keyboard.KEY_O);
    	POWER3 = init("power3", Keyboard.KEY_C);
    }
}
