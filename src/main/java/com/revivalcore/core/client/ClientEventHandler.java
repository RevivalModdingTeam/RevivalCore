package com.revivalcore.core.client;

import com.revivalcore.core.client.bakedmodel.BakedModelSuitMaker;
import com.revivalcore.core.common.blocks.CoreBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void keyPressed(InputEvent.KeyInputEvent e) {

    }
    
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent e)
    {
    	Item item = Item.getItemFromBlock(CoreBlocks.SUIT_MAKER);
    	ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName(), "inventory");
    	e.getModelRegistry().putObject(mrl, new BakedModelSuitMaker());
    }
}
