package com.revivalcore.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISpeedsterCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setSpeedster(boolean speedster);

    boolean isSpeedster();

    void setSpeedLevel(float level);

    float getSpeedLevel();
}
