package com.revivalcore.superpowerbase.effects;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class EffectSkinChange extends Effect {

    public ResourceLocation texture;

    @Override
    public void readSettings(JsonObject json) {
        this.texture = new ResourceLocation(JsonUtils.getString(json, "texture"));
    }

}
