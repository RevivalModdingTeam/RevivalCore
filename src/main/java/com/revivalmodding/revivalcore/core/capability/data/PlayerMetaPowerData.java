package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerMetaPowerData {

    private int metaPower;
    private boolean hasPowerMalfunctioned;
    private boolean isVibrating;
    private boolean canVibrate;
    private float exhaustionLevel;
    private float malfunctionLevel;

    private boolean temporaryMalfunc;

    public void setMetaPower(int power) {
        this.metaPower = power;
    }

    public int getMetaPower() {
        return metaPower;
    }

    public void setMalfunctionState(boolean state) {
        this.hasPowerMalfunctioned = state;
    }

    public boolean hasMalfunctioned() {
        return hasPowerMalfunctioned;
    }

    public boolean hasMetaPowers() {
        return this.getMetaPower() > -1;
    }

    public void setVibratingState(boolean state) {
        this.isVibrating = state;
    }

    public boolean isVibrating() {
        return isVibrating;
    }

    public void setVibratingAllowed(boolean canVibrate) {
        this.canVibrate = canVibrate;
    }

    // TODO Once Superpower registry is in , let this check if the power itself allows vibrating
    public boolean canVibrate() {
        return canVibrate;
    }

    public void setExhaustionLevel(float exhaustionLevel) {
        this.exhaustionLevel = exhaustionLevel;
    }

    public float getExhaustionLevel() {
        return exhaustionLevel;
    }

    public void clear() {
        this.setMetaPower(-1);
    }

    public void update(EntityPlayer player) {
        if (this.getExhaustionLevel() > 0) {
            this.setExhaustionLevel(this.getExhaustionLevel() - 0.1F);
        }

        this.temporaryMalfunc = this.getExhaustionLevel() > 10;

        if (this.getExhaustionLevel() >= malfunctionLevel) {
            if (!this.hasMalfunctioned()) {
                this.setMalfunctionState(true);
                PlayerHelper.sendMessage(player, "You can't use your powers!, You're too exhausted!", true);
            }
        } else {
            if (this.hasMalfunctioned() && this.getExhaustionLevel() < malfunctionLevel - 12) {
                this.setMalfunctionState(false);
                PlayerHelper.sendMessage(player, "You can use your powers again!", true);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("metaPowerID", metaPower);
        tag.setBoolean("powerMalfunctioned", hasPowerMalfunctioned);
        tag.setBoolean("isVibrating", isVibrating);
        tag.setBoolean("canVibrate", canVibrate);
        tag.setFloat("exhaustion", exhaustionLevel);
        tag.setBoolean("tempMalfunc", temporaryMalfunc);
        nbt.setTag("playerMetaPowerData", tag);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagCompound tag = nbt.hasKey("playerMetaPowerData") ? nbt.getCompoundTag("playerMetaPowerData") : new NBTTagCompound();
        this.metaPower = tag.getInteger("metaPowerID");
        this.hasPowerMalfunctioned = tag.getBoolean("powerMalfunctioned");
        this.isVibrating = tag.getBoolean("isVibrating");
        this.canVibrate = tag.getBoolean("canVibrate");
        this.exhaustionLevel = tag.getFloat("exhaustion");
        this.temporaryMalfunc = tag.getBoolean("tempMalfunc");
    }
}
