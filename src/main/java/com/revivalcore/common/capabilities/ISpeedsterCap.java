package com.revivalcore.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISpeedsterCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setSpeedster(boolean speedster);

    boolean isSpeedster();

    void setSpeedLevel(int level);

    int getSpeedLevel();

    void setPhasing(boolean phase);

    boolean isPhasing();

    void setLevel(int level);

    int getLevel();

    void setXP(double xp);

    double getXP();


}
