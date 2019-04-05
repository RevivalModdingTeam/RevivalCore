package com.revivalcore.superpowerbase.events;

import com.revivalcore.superpowerbase.abilities.PowerBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

public class AbilityKeyEvent extends Event {

    public Side side;
    public PowerBase ability;
    public boolean pressed;

    private AbilityKeyEvent(PowerBase ability, Side side, boolean pressed) {
        this.ability = ability;
        this.side = side;
        this.pressed = pressed;
    }

    @Cancelable
    public static class Client extends AbilityKeyEvent {

        public Client(PowerBase ability, boolean pressed) {
            super(ability, Side.CLIENT, pressed);
        }

    }

    @Cancelable
    public static class Server extends AbilityKeyEvent {

        public EntityLivingBase entity;

        public Server(PowerBase ability, EntityLivingBase entity, boolean pressed) {
            super(ability, Side.SERVER, pressed);
            this.entity = entity;
        }

    }

}
