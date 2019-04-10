package com.revivalcore.common.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMetaCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

}
