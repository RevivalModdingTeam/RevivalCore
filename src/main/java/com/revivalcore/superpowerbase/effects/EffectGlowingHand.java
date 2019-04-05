package com.revivalcore.superpowerbase.effects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;

import java.awt.*;

public class EffectGlowingHand extends Effect {

    public Color color;
    public float size;

    @Override
    public void readSettings(JsonObject json) {
        JsonArray array = JsonUtils.getJsonArray(json, "color");
        this.color = new Color(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
        this.size = JsonUtils.getFloat(json, "size", 1F);
    }

}
