package com.revivalcore.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapSpeedstersStorage implements Capability.IStorage<ISpeedsterCap> {

    @CapabilityInject(ISpeedsterCap.class)
    public static Capability<CapabilitySpeedster> CAPABILITY = null;

    @Override
    public NBTBase writeNBT(Capability<ISpeedsterCap> capability, ISpeedsterCap instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<ISpeedsterCap> capability, ISpeedsterCap instance, EnumFacing side, NBTBase nbt) {
        instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
    }

    public static class SpeedsterCapProvider implements ICapabilitySerializable<NBTTagCompound> {

        CapabilitySpeedster cap;

        public SpeedsterCapProvider(EntityPlayer player) {
            this.cap = new CapabilitySpeedster(player);
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
            return (NBTTagCompound) CapSpeedstersStorage.CAPABILITY.getStorage().writeNBT(CapSpeedstersStorage.CAPABILITY, cap, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CapSpeedstersStorage.CAPABILITY.getStorage().readNBT(CapSpeedstersStorage.CAPABILITY, cap, null, nbt);
        }
    }
}
