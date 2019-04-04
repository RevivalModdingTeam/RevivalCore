package com.revivalcore.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapSpeedstersStorage implements IStorage {

    @CapabilityInject(ISpeedsterCap.class)
    public static Capability<CapabilitySpeedster> CAP = null;

    @Override
    public NBTBase writeNBT(Capability capability, Object instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability capability, Object instance, EnumFacing side, NBTBase nbt) {

    }

    public static class SpeedsterCapProvider implements ICapabilityProvider {

        CapabilitySpeedster cap;

        public SpeedsterCapProvider(EntityPlayer player) {
            this.cap = new CapabilitySpeedster(player);
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CAP;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == CAP ? (T) cap : null;
        }
    }
}
