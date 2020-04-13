package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler
{

    @SubscribeEvent
    public static void renderPlayerPre(RenderPlayerEvent.Pre e) {
        Random r = RevivalCore.getRandom();
        EntityPlayer player = e.getEntityPlayer();
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        GlStateManager.pushMatrix();
        if (powerData.isVibrating()) {
            GlStateManager.enableBlend();
            for (int i = 0; i < 1; i++) {
                GlStateManager.translate((r.nextFloat() - 1F) / 80, 0, (r.nextFloat() - 1F) / 80);
                GlStateManager.color(1, 1, 1, 0.4F);
            }
        }
    }

    @SubscribeEvent
    public static void renderPlayerPost(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        if (powerData.isVibrating()) {
            GlStateManager.disableBlend();
        }
        GlStateManager.popMatrix();
    }
}
