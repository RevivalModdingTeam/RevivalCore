package com.revivalmodding.revivalcore.core.capability;

import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerTrailData;
import net.minecraft.nbt.NBTTagCompound;

public interface ICoreCapability {

    void setTrailData(PlayerTrailData trailData);

    PlayerTrailData getTrailData();

    void setAbilityData(PlayerAbilityData abilityData);

    PlayerAbilityData getAbilityData();

    void setMetaPowerData(PlayerMetaPowerData metaPowerData);

    PlayerMetaPowerData getMetaPowerData();

    NBTTagCompound toNBT();

    void fromNBT(NBTTagCompound nbt);

    void tick();

    void sync();
}
