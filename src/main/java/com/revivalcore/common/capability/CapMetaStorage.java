package com.revivalcore.common.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapMetaStorage implements Capability.IStorage<IMetaCap> {

    @CapabilityInject(IMetaCap.class)
    public static Capability<CapabilityMeta> CAPABILITY = null;

    @Override
    public NBTBase writeNBT(Capability<IMetaCap> capability, IMetaCap instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IMetaCap> capability, IMetaCap instance, EnumFacing side, NBTBase nbt) {
        instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
    }

    public static class MetaCapProvider implements ICapabilitySerializable<NBTTagCompound> {

        CapabilityMeta cap;

        public MetaCapProvider(EntityPlayer player) {
            this.cap = new CapabilityMeta(player);
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CAPABILITY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == CAPABILITY ? (T) cap : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CapMetaStorage.CAPABILITY.getStorage().writeNBT(CapMetaStorage.CAPABILITY, cap, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CapMetaStorage.CAPABILITY.getStorage().readNBT(CapMetaStorage.CAPABILITY, cap, null, nbt);
        }
    }
}
