package com.revivalcore.superpowerbase.effects;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class EffectSkinOverlay extends Effect {

    public ResourceLocation texture;
    public float size;
    public boolean glow;

    @Override
    public void readSettings(JsonObject json) {
        this.texture = new ResourceLocation(JsonUtils.getString(json, "texture"));
        this.size = JsonUtils.getFloat(json, "size");
        this.glow = JsonUtils.getBoolean(json, "glow", false);
    }

}
