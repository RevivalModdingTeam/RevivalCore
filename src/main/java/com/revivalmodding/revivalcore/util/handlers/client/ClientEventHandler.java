package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.client.bakedmodel.BakedModelSuitMaker;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
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

    @SubscribeEvent
    public static void keyPressed(InputEvent.KeyInputEvent e) {}

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent e) {
        Item item = Item.getItemFromBlock(CoreBlocks.SUIT_MAKER);
        ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName(), "inventory");
        e.getModelRegistry().putObject(mrl, new BakedModelSuitMaker());
    }
}
