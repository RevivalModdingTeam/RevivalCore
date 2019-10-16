package com.revivalmodding.revivalcore.core.capability;

import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class CoreCapabilityImpl implements ICoreCapability {

    private PlayerMetaPowerData playerMetaPowerData;
    private PlayerAbilityData playerAbilityData;
    private PlayerTrailData playerTrailData;

    protected EntityPlayer capOwner;

    public CoreCapabilityImpl() {
        this(null);
    }

    public CoreCapabilityImpl(EntityPlayer owner) {
        this.capOwner = owner;
    }

    public static ICoreCapability getInstance(EntityPlayer player) {
        return player.getCapability(CoreCapabilityProvider.DATA, null);
    }

    @Nonnull
    @Override
    public PlayerMetaPowerData getMetaPowerData() {
        if (playerMetaPowerData == null) playerMetaPowerData = new PlayerMetaPowerData();
        return playerMetaPowerData;
    }

    @Override
    public void setMetaPowerData(PlayerMetaPowerData metaPowerData) {
        this.playerMetaPowerData = metaPowerData;
    }

    @Nonnull
    @Override
    public PlayerAbilityData getAbilityData() {
        if (playerAbilityData == null) playerAbilityData = new PlayerAbilityData();
        return playerAbilityData;
    }

    @Override
    public void setAbilityData(PlayerAbilityData abilityData) {
        this.playerAbilityData = abilityData;
    }

    @Nonnull
    @Override
    public PlayerTrailData getTrailData() {
        if (playerTrailData == null) playerTrailData = new PlayerTrailData();
        return playerTrailData;
    }

    @Override
    public void setTrailData(PlayerTrailData trailData) {
        this.playerTrailData = trailData;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.getMetaPowerData().writeToNBT(nbt);
        this.getAbilityData().writeToNBT(nbt);
        this.getTrailData().toNBT(nbt);
        return nbt;
    }

    @Override
    public void fromNBT(NBTTagCompound nbt) {
        this.getMetaPowerData().readFromNBT(nbt);
        this.getAbilityData().readFromNBT(nbt);
        this.getTrailData().fromNBT(nbt);
    }

    @Override
    public void sync() {

    }

    @Override
    public void tick() {
        this.getMetaPowerData().update(capOwner);
        this.getAbilityData().update(capOwner);
    }
}
