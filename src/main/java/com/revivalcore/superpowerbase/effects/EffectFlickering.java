package com.revivalcore.superpowerbase.effects;

import com.revivalcore.superpowerbase.suitsets.SuitSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;

import java.awt.*;

public class EffectFlickering extends Effect {

    public Color color;
    public boolean useSuitFlicker;

    @Override
    public void readSettings(JsonObject json) {
        JsonArray array = JsonUtils.getJsonArray(json, "color");
        this.color = new Color(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());

        this.useSuitFlicker = JsonUtils.getBoolean(json, "use_suit_flicker", false);
    }

    public Color getColor(EntityPlayer entity) {
        if (!useSuitFlicker)
            return color;
        else {
            SuitSet suitSet = SuitSet.getSuitSet(entity);
            if (suitSet != null && suitSet.getData() != null && suitSet.getData().hasKey("flicker")) {
                NBTTagCompound data = suitSet.getData().getCompoundTag("flicker");
                return new Color(data.getFloat("red"), data.getFloat("green"), data.getFloat("blue"));
            }

            return color;
        }
    }

}

