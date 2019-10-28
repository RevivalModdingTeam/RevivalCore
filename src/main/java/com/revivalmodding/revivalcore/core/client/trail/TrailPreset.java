package com.revivalmodding.revivalcore.core.client.trail;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TrailPreset {

    private TrailInfo trailInfo;
    @Nonnull
    private Trail trail;
    @Nullable
    private TrailOptionalData optionalData;

    public void updatePreset() {
        this.getTrailInfo().forceUpdate();
    }

    public TrailInfo getTrailInfo() {
        if(trailInfo == null) {
            this.trailInfo = new TrailInfo();
        }
        return trailInfo;
    }

    public void saveToNBT(NBTTagCompound tag) {
        NBTTagCompound nbt = new NBTTagCompound();
        this.trail.writeTrailToNBT(nbt);
        if(optionalData != null) {
            optionalData.saveToNBT(nbt);
        }
        tag.setTag("preset", nbt);
    }

    public void loadFromNBT(NBTTagCompound tag) {
        NBTTagCompound nbt = tag.hasKey("preset") ? tag.getCompoundTag("preset") : new NBTTagCompound();
        this.trail = Trail.createDefaultTrail();
        this.trail.readTrailFromNBT(nbt);
        if(nbt.hasKey("trailData")) {
            this.optionalData = new TrailOptionalData(this.trail);
            this.optionalData.readFromNBT(nbt);
        }
        this.trailInfo = new TrailInfo().forceUpdate();
    }

    public class TrailInfo {

        private String formattedTrailInfo = "";

        public TrailInfo forceUpdate() {
            TrailPreset preset = TrailPreset.this;
            Trail trail = preset.trail;
            TrailOptionalData optionalData = preset.optionalData;
            return this;
        }

        public String getFormattedTrailInfo() {
            return formattedTrailInfo;
        }
    }
}
