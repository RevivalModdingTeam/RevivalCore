package com.revivalmodding.revivalcore.core.client.trail;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TrailRenderHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(e.phase == TickEvent.Phase.END && player != null) {
            CoreCapabilityImpl.getInstance(player).getTrailData().onTick(player);
        }
    }

    @SubscribeEvent
    public static void renderTrails(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        PlayerTrailData trailData = CoreCapabilityImpl.getInstance(player).getTrailData();
        trailData.getTrailRenderer().renderTrail(player, trailData.getTrail(), trailData.getAdditionalTrailData(), e.getPartialRenderTick());
    }
}
