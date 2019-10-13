package com.revivalmodding.revivalcore.core.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CoreCapabilityStorage implements Capability.IStorage<ICoreCapability> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICoreCapability> capability, ICoreCapability instance, EnumFacing side) {
        return instance.toNBT();
    }

    @Override
    public void readNBT(Capability<ICoreCapability> capability, ICoreCapability instance, EnumFacing side, NBTBase nbt) {
        instance.fromNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
    }
}
