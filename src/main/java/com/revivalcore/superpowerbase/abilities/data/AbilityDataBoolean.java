package com.revivalcore.superpowerbase.abilities.data;

import com.google.gson.JsonObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;

public class AbilityDataBoolean extends AbilityData<Boolean> {

    public AbilityDataBoolean(String key) {
        super(key);
    }

    @Override
    public Boolean parseValue(JsonObject jsonObject, Boolean defaultValue) {
        return JsonUtils.getBoolean(jsonObject, this.jsonKey, defaultValue);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt, Boolean value) {
        nbt.setBoolean(this.key, value);
    }

    @Override
    public Boolean readFromNBT(NBTTagCompound nbt, Boolean defaultValue) {
        if (!nbt.hasKey(this.key))
            return defaultValue;
        return nbt.getBoolean(this.key);
    }

}
