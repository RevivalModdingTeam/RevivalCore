package com.revivalmodding.revivalcore.core.client.trail;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TrailPreset {

    private TrailInfo trailInfo;
    public Trail trail;
    public TrailOptionalData data;

    public TrailPreset updatePreset(Trail trail, @Nullable TrailOptionalData optionalData) {
        this.trail = trail;
        this.data = optionalData;
        this.getTrailInfo().forceUpdate();
        return this;
    }

    public TrailInfo getTrailInfo() {
        if(trailInfo == null) {
            this.trailInfo = new TrailInfo();
        }
        return trailInfo;
    }

    public NBTTagCompound saveToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        trail.writeTrailToNBT(nbt);
        if(data != null) {
            data.saveToNBT(nbt);
        }
        return nbt;
    }

    public void loadFromNBT(NBTTagCompound nbt) {
        Trail trail = Trail.createDefaultTrail();
        trail.readTrailFromNBT(nbt);
        this.data = null;
        if(nbt.hasKey("trailData")) {
            this.data = new TrailOptionalData(trail);
            this.data.readFromNBT(nbt);
        }
        this.trailInfo = new TrailInfo().forceUpdate();
    }

    public Trail getTrail() {
        return trail;
    }

    @Nullable
    public TrailOptionalData getOptionalData() {
        return data;
    }

    public class TrailInfo {

        private String formattedTrailInfo = "";

        public TrailInfo forceUpdate() {
            TrailPreset preset = TrailPreset.this;
            Trail trail = preset.getTrail();
            TrailOptionalData optionalData = preset.getOptionalData();
            StringBuilder builder = new StringBuilder();
            builder.append("Main trail attributes/");
            builder.append("Length: ").append(trail.getLength()).append("/");
            builder.append("Width: ").append(trail.getWidth()).append("/");
            builder.append("Color: ").append("/");
            builder.append("Additional trail attributes:/");
            if(optionalData == null) {
                builder.append("None/");
                this.formattedTrailInfo = builder.toString();
                return this;
            }
            builder.append("Secondary color: ").append(optionalData.secondaryColor == -1 ? "-" : "").append("/");
            builder.append("Stage colors: ").append(optionalData.stageColors == null ? "-" : "/");
            if(optionalData.stageColors != null) {
                for(int i = 0; i < optionalData.stageColors.length; i++) {
                    boolean last = i == optionalData.stageColors.length - 1;
                    builder.append("[").append(i).append("]:").append(last ? "" : "/");
                }
            }
            this.formattedTrailInfo = builder.toString();
            return this;
        }

        public String getFormattedTrailInfo() {
            return formattedTrailInfo;
        }
    }
}
