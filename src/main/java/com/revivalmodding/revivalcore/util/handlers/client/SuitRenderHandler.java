package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.core.client.render.LayerSuitBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(Side.CLIENT)
public class SuitRenderHandler {

    private static List<UUID> playersWithRenderLayer = new ArrayList<>();

    @SubscribeEvent
    public static void renderSuitOnPlayer(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        if(!playersWithRenderLayer.contains(player.getUniqueID())) {
            playersWithRenderLayer.add(player.getUniqueID());
            e.getRenderer().addLayer(new LayerSuitBase<EntityPlayer>(e.getRenderer()));
        }
    }
}
