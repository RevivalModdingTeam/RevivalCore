package com.revivalcore.superpowerbase.abilities;

import com.google.gson.JsonObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AbilityGenerator {

    public ResourceLocation loc;
    public String key;
    public JsonObject data;

    public AbilityGenerator(ResourceLocation loc, String key, JsonObject data) {
        this.loc = loc;
        this.key = key;
        this.data = data;
    }

    public PowerBase create(EntityLivingBase entity, PowerBase.AbilityMap abilities) {
    	PowerBase ab = null;
        try {
            if (PowerBase.ABILITY_REGISTRY.containsKey(loc)) {
                ab = PowerBase.ABILITY_REGISTRY.getValue(loc).getAbilityClass().getConstructor(EntityLivingBase.class).newInstance(entity);
                ab.readFromAddonPack(data, abilities);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return ab;
    }

}
