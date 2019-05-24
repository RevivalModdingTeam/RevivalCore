package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.network.NetworkManager;
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


    @Mod.EventBusSubscriber(Side.CLIENT)
    public static class Handler {
        @SubscribeEvent
        public static void keyPressed(InputEvent.KeyInputEvent e) {
            IMetaCap metaCap = CapabilityMeta.get(Minecraft.getMinecraft().player);

            if (metaCap.hasMetaPowers()) {
                if (Keybinds.ENABLE.isPressed()) {
                    NetworkManager.INSTANCE.sendToServer(new PacketSetPower());
                }
            }
        }
    }
}
