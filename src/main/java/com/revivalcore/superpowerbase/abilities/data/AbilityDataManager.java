package com.revivalcore.superpowerbase.abilities.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;


import java.util.*;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.suppliers.EnumSync;

public class AbilityDataManager implements INBTSerializable<NBTTagCompound> {

    public final PowerBase ability;
    protected Map<AbilityData<?>, AbilityDataEntry<?>> dataEntryList = new LinkedHashMap<>();
    protected Map<AbilityData<?>, Object> dataEntryDefaults = new LinkedHashMap<>();
    public EnumSync sync = EnumSync.NONE;

    public AbilityDataManager(PowerBase ability) {
        this.ability = ability;
    }

    public <T> AbilityData<T> register(AbilityData<T> data, T defaultValue) {
        dataEntryList.put(data, new AbilityDataEntry<T>(data, defaultValue));
        dataEntryDefaults.put(data, defaultValue);
        return data;
    }

    public <T> void set(AbilityData<T> data, T value) {
        AbilityDataEntry entry = getEntry(data);

        if (entry != null && !entry.getValue().equals(value)) {
            entry.setValue(value);
            this.ability.sync = this.ability.sync.add(data.syncType);
            this.ability.dirty = true;
        }
    }

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(AbilityDataManager.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public <T> T get(AbilityData<T> data) {
        AbilityDataEntry entry = getEntry(data);
        return entry == null ? null : (T) entry.getValue();
    }

    private <T> AbilityDataEntry<T> getEntry(AbilityData<T> data) {
        return (AbilityDataEntry<T>) dataEntryList.get(data);
    }

    public boolean has(AbilityData data) {
        return dataEntryList.containsKey(data);
    }

    public <T> T getDefaultValue(AbilityData<T> data) {
        return (T) this.dataEntryDefaults.get(data);
    }

    public <T> AbilityDataManager reset(AbilityData<T> data) {
        this.set(data, getDefaultValue(data));
        return this;
    }

    public Set<AbilityData<?>> getData() {
        return this.dataEntryList.keySet();
    }

    public List<AbilityData<?>> getSettingData() {
        List<AbilityData<?>> list = new ArrayList<>();
        for (AbilityData<?> data : this.getData()) {
            if (data.isUserSetting()) {
                list.add(data);
            }
        }
        return list;
    }

    public Collection<AbilityDataEntry<?>> getDataEntries() {
        return this.dataEntryList.values();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (AbilityData data : dataEntryList.keySet()) {
            if (data.canBeSaved())
                data.writeToNBT(nbt, getEntry(data).getValue());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (AbilityData data : dataEntryList.keySet()) {
            getEntry(data).setValue(data.readFromNBT(nbt, getDefaultValue(data)));
        }
    }

    public NBTTagCompound serializeNBTSync() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (AbilityData data : dataEntryList.keySet()) {
            data.writeToNBT(nbt, getEntry(data).getValue());
        }
        return nbt;
    }

    public void deserializeNBTSync(NBTTagCompound nbt) {
        for (AbilityData data : dataEntryList.keySet()) {
            getEntry(data).setValue(data.readFromNBT(nbt, getDefaultValue(data)));
        }
    }

}

