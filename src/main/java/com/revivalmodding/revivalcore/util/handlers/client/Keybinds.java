package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.client.gui.AbilityGUI;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketAbilityAction;
import com.revivalmodding.revivalcore.network.packets.PacketSetPower;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

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
            PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(Minecraft.getMinecraft().player).getMetaPowerData();

            if(Keybinds.POWER1.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(0, PacketAbilityAction.AbilityAction.TOGGLE));
            }
            if(Keybinds.POWER2.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(1, PacketAbilityAction.AbilityAction.TOGGLE));
            }
            if(Keybinds.POWER3.isPressed()) {
            	NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(2, PacketAbilityAction.AbilityAction.TOGGLE));
            }
            if(Keybinds.ABILITYGUI.isPressed()) {
            	Minecraft.getMinecraft().displayGuiScreen(new AbilityGUI(Minecraft.getMinecraft().player));
            }
            
            if (powerData.hasMetaPowers() || powerData.isPowerActivated()) {
                if (Keybinds.ENABLE.isPressed()) {
                    NetworkManager.INSTANCE.sendToServer(new PacketSetPower());
                }
            }
        }
    }
}
