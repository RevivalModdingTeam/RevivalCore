package com.revivalcore.superpowerbase.abilities.suppliers;

import com.revivalcore.superpowerbase.abilities.PowerBase;

import net.minecraft.util.ResourceLocation;

public class PowerEntries extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<PowerEntries> {

    private Class<? extends PowerBase> clazz;

    public PowerEntries(Class<? extends PowerBase> clazz, ResourceLocation registryName) {
        this.clazz = clazz;
        this.setRegistryName(registryName);
    }

    public Class<? extends PowerBase> getAbilityClass() {
        return clazz;
    }

}
