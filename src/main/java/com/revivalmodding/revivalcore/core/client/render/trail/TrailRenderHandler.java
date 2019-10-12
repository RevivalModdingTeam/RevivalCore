package com.revivalmodding.revivalcore.core.client.render.trail;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TrailRenderHandler {

    private static Trail playerTrail;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if(e.phase == TickEvent.Phase.END && Minecraft.getMinecraft().player != null) {
            if(playerTrail == null) playerTrail = Trail.TrailBuilder.create().trailLength(8).trailWidth(10).color(0.0F, 1.0F, 0.5F).build();
            playerTrail.updateTrail(Minecraft.getMinecraft().player);
        }
    }

    @SubscribeEvent
    public static void renderTrails(RenderWorldLastEvent e) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(playerTrail != null) playerTrail.renderTrail(player, e.getPartialTicks());
    }
}
