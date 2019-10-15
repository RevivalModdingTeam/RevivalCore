package com.revivalmodding.revivalcore.core.capability;

import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public interface ICoreCapability {

    void setTrailData(PlayerTrailData trailData);

    @Nonnull
    PlayerTrailData getTrailData();

    void setAbilityData(PlayerAbilityData abilityData);

    @Nonnull
    PlayerAbilityData getAbilityData();

    void setMetaPowerData(PlayerMetaPowerData metaPowerData);

    @Nonnull
    PlayerMetaPowerData getMetaPowerData();

    NBTTagCompound toNBT();

    void fromNBT(NBTTagCompound nbt);

    void tick();

    void sync();
}
