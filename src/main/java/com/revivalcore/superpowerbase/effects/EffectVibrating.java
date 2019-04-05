package com.revivalcore.superpowerbase.effects;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EffectVibrating extends Effect {

    @Override
    public void readSettings(JsonObject json) {

    }

    public static boolean isVibrating(Entity entity) {
        if (entity instanceof EntityPlayer) {
            for (EffectVibrating effect : EffectHandler.getEffectsByClass((EntityPlayer) entity, EffectVibrating.class)) {
                if (EffectHandler.canEffectBeDisplayed(effect, (EntityPlayer) entity)) {
                    return true;
                }
            }
        }

        return false;
    }

}

