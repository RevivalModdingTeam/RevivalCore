package com.revivalcore.superpowerbase.abilities.data;

import com.google.gson.JsonObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;

public class AbilityDataInteger extends AbilityData<Integer> {

    public AbilityDataInteger(String key) {
        super(key);
    }

    @Override
    public Integer parseValue(JsonObject jsonObject, Integer defaultValue) {
        return JsonUtils.getInt(jsonObject, this.jsonKey, defaultValue);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt, Integer value) {
        nbt.setInteger(this.key, value);
    }

    @Override
    public Integer readFromNBT(NBTTagCompound nbt, Integer defaultValue) {
        if (!nbt.hasKey(this.key))
            return defaultValue;
        return nbt.getInteger(this.key);
    }
}
