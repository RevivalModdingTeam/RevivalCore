package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import com.revivalmodding.revivalcore.core.client.trail.TrailPreset;
import com.revivalmodding.revivalcore.core.client.trail.renderers.TrailRenderer;
import com.revivalmodding.revivalcore.core.client.trail.renderers.TrailRendererSimple;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerTrailData {

    private TrailRenderer trailRenderer;
    private Trail mainTrail;
    private TrailOptionalData additionalTrailData;
    private TrailPreset[] presets;

    public void setTrail(Trail trail) {
        this.mainTrail = trail;
    }

    @Nonnull
    public Trail getTrail() {
        if(mainTrail == null) {
            mainTrail = Trail.createDefaultTrail();
        }
        return mainTrail;
    }

    public void setAdditionalTrailData(TrailOptionalData trailData) {
        this.additionalTrailData = trailData;
    }

    @Nullable
    public TrailOptionalData getAdditionalTrailData() {
        return additionalTrailData;
    }

    public void setTrailRenderer(TrailRenderer renderer) {
        this.trailRenderer = renderer;
    }

    public TrailRenderer getTrailRenderer() {
        if(trailRenderer == null) this.setTrailRenderer(new TrailRendererSimple());
        return trailRenderer;
    }

    public void setPresets(TrailPreset[] presets) {
        this.presets = presets;
    }

    public TrailPreset[] getPresets() {
        if(this.presets == null) this.presets = new TrailPreset[6];
        return presets;
    }

    public void onTick(EntityPlayer player) {
        this.getTrail().updateTrail(player);
    }

    public void toNBT(NBTTagCompound nbt) {
        NBTTagCompound tag = new NBTTagCompound();
        this.getTrail().writeTrailToNBT(tag);
        if(this.additionalTrailData != null) {
            this.additionalTrailData.saveToNBT(tag);
        }
        NBTTagList presetList = new NBTTagList();
        for(int i = 0; i < this.getPresets().length; i++) {
            TrailPreset preset = this.getPresets()[i];
            if(preset != null) {
                presetList.appendTag(preset.saveToNBT());
            }
        }
        if(!presetList.isEmpty()) {
            tag.setTag("presets", presetList);
        }
        nbt.setTag("trailData", tag);
    }

    public void fromNBT(NBTTagCompound nbt) {
        this.additionalTrailData = null;
        NBTTagCompound tag = nbt.hasKey("trailData") ? nbt.getCompoundTag("trailData") : new NBTTagCompound();
        this.mainTrail = this.getTrail();
        this.mainTrail.readTrailFromNBT(tag);
        if(tag.hasKey("trailData")) {
            this.additionalTrailData = new TrailOptionalData(this.mainTrail);
            this.additionalTrailData.readFromNBT(tag);
        }
        this.presets = new TrailPreset[6];
        if(tag.hasKey("presets")) {
            NBTTagList list = tag.getTagList("presets", Constants.NBT.TAG_COMPOUND);
            for(int i = 0; i < list.tagCount(); i++) {
                TrailPreset preset = new TrailPreset();
                preset.loadFromNBT(list.getCompoundTagAt(i));
                this.presets[i] = preset;
            }
        }
        this.trailRenderer = new TrailRendererSimple();
    }
}
