package com.RevivalCore.util.handlers;

import com.RevivalCore.util.helper.powers.SlowMotionAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler
{
 // TODO Handle Stuff
 public static boolean doAllowed = true;

    @SubscribeEvent
    public static void EntityLivingEvent(LivingEvent.LivingUpdateEvent e) {
        if(doAllowed) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
             SlowMotionAPI.SlowProjectiles(player, 0.3);
        }
    }
}

