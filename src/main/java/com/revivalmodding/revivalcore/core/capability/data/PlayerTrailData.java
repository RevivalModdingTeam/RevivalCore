package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.core.client.render.trail.Trail;
import com.revivalmodding.revivalcore.core.client.render.trail.TrailOptionalData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

// TODO - all
public class PlayerTrailData {

    private Trail mainTrail;
    private TrailOptionalData additionalTrailData;

    public void setTrail(Trail trail) {
        this.mainTrail = trail;
    }

    public Trail getTrail() {
        return mainTrail;
    }

    public void setAdditionalTrailData(TrailOptionalData trailData) {
        this.additionalTrailData = trailData;
    }

    public TrailOptionalData getAdditionalTrailData() {
        return additionalTrailData;
    }

    public void onTick(EntityPlayer player) {
        mainTrail.updateTrail(player);
    }

    public void toNBT(NBTTagCompound nbt) {
        // TODO
    }

    public void fromNBT(NBTTagCompound nbt) {
        // TODO
    }
}
