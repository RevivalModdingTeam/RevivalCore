package com.revivalcore.superpowerbase.abilities.data;

import com.google.gson.JsonObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;

public class AbilityDataTextComponent extends AbilityData<ITextComponent> {

    public AbilityDataTextComponent(String key) {
        super(key);
    }

    @Override
    public ITextComponent parseValue(JsonObject jsonObject, ITextComponent defaultValue) {
        if (!JsonUtils.hasField(jsonObject, this.jsonKey))
            return defaultValue;
        return ITextComponent.Serializer.jsonToComponent(JsonUtils.getJsonObject(jsonObject, this.jsonKey).toString());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt, ITextComponent value) {
        nbt.setString(this.key, ITextComponent.Serializer.componentToJson(value));
    }

    @Override
    public ITextComponent readFromNBT(NBTTagCompound nbt, ITextComponent defaultValue) {
        if (!nbt.hasKey(this.key))
            return defaultValue;
        return ITextComponent.Serializer.jsonToComponent(nbt.getString(this.key));
    }

    @Override
    public String getDisplay(ITextComponent value) {
        return ITextComponent.Serializer.componentToJson(value);
    }
}
