package com.revivalmodding.revivalcore.core.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CoreCapabilityProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ICoreCapability.class)
    public static final Capability<ICoreCapability> DATA = null;

    private ICoreCapability instance = DATA.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == DATA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == DATA ? DATA.cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound)DATA.getStorage().writeNBT(DATA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        DATA.getStorage().readNBT(DATA, instance, null, nbt);
    }
}
