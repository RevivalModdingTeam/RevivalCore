package com.revivalmodding.revivalcore.util.handlers.client;

import org.lwjgl.input.Keyboard;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.client.gui.AbilityGUI;
import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketSetPower;
import com.revivalmodding.revivalcore.network.packets.PacketToggleAbility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

public class Keybinds {

    public static KeyBinding ENABLE;
    public static KeyBinding POWER1;
    public static KeyBinding POWER2;
    public static KeyBinding POWER3;
    public static KeyBinding ABILITYGUI;

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
        ABILITYGUI = init("abilitygui", Keyboard.KEY_Y);
    }


    @Mod.EventBusSubscriber(Side.CLIENT)
    public static class Handler {
        @SubscribeEvent
        public static void keyPressed(InputEvent.KeyInputEvent e) {
            IMetaCap metaCap = CapabilityMeta.get(Minecraft.getMinecraft().player);

            if(Keybinds.POWER1.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketToggleAbility(0));
            }
            if(Keybinds.POWER2.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketToggleAbility(1));
            }
            if(Keybinds.POWER3.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketToggleAbility(2));
            }
            if(Keybinds.ABILITYGUI.isPressed()) {
            	Minecraft.getMinecraft().displayGuiScreen(new AbilityGUI(Minecraft.getMinecraft().player));
            }
            
            if (metaCap.hasMetaPowers() || metaCap.isPowerEnabled()) {
                if (Keybinds.ENABLE.isPressed()) {
                    NetworkManager.INSTANCE.sendToServer(new PacketSetPower());
                }
            }
        }
    }
}
