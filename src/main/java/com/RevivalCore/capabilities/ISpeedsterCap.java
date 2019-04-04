package com.RevivalCore.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISpeedsterCap extends INBTSerializable<NBTTagCompound> {

    void sync();
}
