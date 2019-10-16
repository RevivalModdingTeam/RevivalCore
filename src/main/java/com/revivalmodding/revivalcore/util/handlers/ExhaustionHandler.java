package com.revivalmodding.revivalcore.util.handlers;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ExhaustionHandler {

    public static void addExhaustion(EntityPlayer player, float addexhaustion) {
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        powerData.setExhaustionLevel(powerData.getExhaustionLevel() + addexhaustion);
    }

    public static void setExhaustion(EntityPlayer player, float setexhaustion) {
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        powerData.setExhaustionLevel(setexhaustion);
    }

    @SubscribeEvent
    public static void handleExhaustion(LivingEvent.LivingUpdateEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if(isMoving(player)) addExhaustion(player, 0.001F);
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
}
