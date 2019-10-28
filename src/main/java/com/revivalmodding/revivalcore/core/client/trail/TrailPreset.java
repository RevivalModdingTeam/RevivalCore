package com.revivalmodding.revivalcore.core.client.trail;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class TrailPreset {

    private TrailInfo trailInfo;
    private NBTTagCompound trailData;

    public void updatePreset(Trail trail, @Nullable TrailOptionalData optionalData) {
        this.getTrailInfo().forceUpdate();
        this.trailData = this.saveToNBT(trail, optionalData);
    }

    public TrailInfo getTrailInfo() {
        if(trailInfo == null) {
            this.trailInfo = new TrailInfo();
        }
        return trailInfo;
    }

    public NBTTagCompound saveToNBT(Trail trail, @Nullable TrailOptionalData optionalData) {
        NBTTagCompound nbt = new NBTTagCompound();
        trail.writeTrailToNBT(nbt);
        if(optionalData != null) {
            optionalData.saveToNBT(nbt);
        }
        return nbt;
    }

    public void loadFromNBT(NBTTagCompound nbt) {
        Trail trail = Trail.createDefaultTrail();
        trail.readTrailFromNBT(nbt);
        TrailOptionalData optionalData;
        if(nbt.hasKey("trailData")) {
            optionalData = new TrailOptionalData(trail);
            optionalData.readFromNBT(nbt);
        } else optionalData = null;
        this.trailData = nbt;
        this.trailInfo = new TrailInfo().forceUpdate();
    }

    public Trail getTrail() {
        Trail trail = Trail.createDefaultTrail();
        if(trailData.hasKey("trail")) trail.readTrailFromNBT(trailData);
        return trail;
    }

    @Nullable
    public TrailOptionalData getOptionalData() {
        if(trailData.hasKey("trailData")) {
            TrailOptionalData optionalData = new TrailOptionalData(this.getTrail());
            optionalData.readFromNBT(trailData);
            return optionalData;
        }
        return null;
    }

    public class TrailInfo {

        private String formattedTrailInfo = "";

        public TrailInfo forceUpdate() {
            TrailPreset preset = TrailPreset.this;
            Trail trail = preset.getTrail();
            TrailOptionalData optionalData = preset.getOptionalData();
            StringBuilder builder = new StringBuilder();
            builder.append("Main trail attributes\n");
            builder.append("Length: ").append(trail.getLength()).append("\n");
            builder.append("Width: ").append(trail.getWidth()).append("\n");
            builder.append("Color: ").append("\n");
            builder.append("Additional trail attributes:\n");
            if(optionalData == null) {
                builder.append("None\n");
                return this;
            }
            builder.append("Secondary color: ").append(optionalData.secondaryColor == -1 ? "-" : "").append("\n");
            builder.append("Stage colors: ").append(optionalData.stageColors == null ? "-" : "\n");
            if(optionalData.stageColors != null) {
                for(int i = 0; i < optionalData.stageColors.length; i++) {
                    boolean last = i == optionalData.stageColors.length - 1;
                    builder.append("[").append(i).append("]:").append(last ? "" : "\n");
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
