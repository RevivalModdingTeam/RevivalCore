package com.revivalcore.superpowerbase.events;

import com.revivalcore.superpowerbase.Superpowerbase;
import com.RevivalCore.util.handlers.SuperpowerHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class LivingSuperpowerEvent extends LivingEvent {

    private final Superpowerbase superpower;

    public LivingSuperpowerEvent(EntityLivingBase entity, Superpowerbase superpower) {
        super(entity);
        this.superpower = superpower;
    }

    public Superpowerbase getSuperpower() {
        return this.superpower;
    }

    public Superpowerbase getPreviousSuperpower() {
        return SuperpowerHandler.getSuperpower(getEntityLiving());
    }

    @Cancelable
    public static class PlayerGetsSuperpowerEvent extends LivingSuperpowerEvent {

        public PlayerGetsSuperpowerEvent(EntityPlayer player, Superpowerbase superpower) {
            super(player, superpower);
        }

    }

}
