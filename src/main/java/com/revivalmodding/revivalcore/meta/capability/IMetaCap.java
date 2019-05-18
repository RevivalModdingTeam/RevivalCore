package com.revivalmodding.revivalcore.meta.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMetaCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setMetaPower(int metaPower);

    int getMetaPower();

    boolean hasMetaPowers();

    void setExhaustionLevel(double exhaustionLevel);

    double getexhaustionLevel();

    void setPowerEnabled(boolean enable);

    boolean isPowerEnabled();

    void clear();

}
