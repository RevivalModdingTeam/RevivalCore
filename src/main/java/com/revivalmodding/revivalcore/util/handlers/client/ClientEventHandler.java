package com.revivalmodding.revivalcore.util.handlers.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber
public class ClientEventHandler
{


    public static boolean vibrating = false;     // TODO Please make this check if player has vibrating enabled

    @SubscribeEvent
    public static void renderPlayerPre(RenderPlayerEvent.Pre e) {
        Random r = new Random();
        GlStateManager.pushMatrix();

        if (vibrating) {
            GlStateManager.enableBlend();
            for (int i = 0; i < 2; i++) {
                GlStateManager.translate((r.nextFloat() - 0.5F) / 15, 0, (r.nextFloat() - 0.5F) / 15);
                GlStateManager.color(1, 1, 1, 0.4F);
            }
        }
    }

    @SubscribeEvent
    public static void renderPlayerPost(RenderPlayerEvent.Post e) {
        if (vibrating) {
            GlStateManager.disableBlend();
        }
        GlStateManager.popMatrix();
    }
}
