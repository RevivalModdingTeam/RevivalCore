package com.revivalcore.superpowerbase.abilities.events;

import java.util.Map;

import com.revivalcore.superpowerbase.abilities.PowerBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;

public class InitPowersEvent extends LivingEvent {

    protected Map<String, PowerBase> abilities;
    protected PowerBase.EnumAbilityContext context;

    public InitPowersEvent(EntityLivingBase entity, Map<String, PowerBase> abilities, PowerBase.EnumAbilityContext context) {
        super(entity);
        this.abilities = abilities;
        this.context = context;
    }

    public Map<String, PowerBase> getAbilities() {
        return abilities;
    }

    public PowerBase.EnumAbilityContext getContext() {
        return context;
    }

    public static class Pre extends InitPowersEvent {

        public Pre(EntityLivingBase entity, Map<String, PowerBase> abilities, PowerBase.EnumAbilityContext context) {
            super(entity, abilities, context);
        }
    }

    public static class Post extends InitPowersEvent {

        public Post(EntityLivingBase entity, Map<String, PowerBase> abilities, PowerBase.EnumAbilityContext context) {
            super(entity, abilities, context);
        }
    }

}

