package com.revivalmodding.revivalcore.util.handlers;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ExhaustionHandler {

    public static void addExhaustion(EntityPlayer player, double addexhaustion) {
        IMetaCap cap = CapabilityMeta.get(player);
        cap.setExhaustionLevel(cap.getexhaustionLevel() + addexhaustion);
    }

    public static void setExhaustion(EntityPlayer player, double setexhaustion) {
        IMetaCap cap = CapabilityMeta.get(player);
        cap.setExhaustionLevel(setexhaustion);
    }

    @SubscribeEvent
    public static void handleExhaustion(LivingEvent.LivingUpdateEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if(isMoving(player)) addExhaustion(player, 0.001);
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
}
