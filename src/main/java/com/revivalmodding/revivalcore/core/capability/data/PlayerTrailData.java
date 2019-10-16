package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.core.client.trail.renderers.TrailRenderer;
import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import com.revivalmodding.revivalcore.core.client.trail.renderers.TrailRendererSimple;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerTrailData {

    private TrailRenderer trailRenderer;
    private Trail mainTrail;
    private TrailOptionalData additionalTrailData;

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

    public void onTick(EntityPlayer player) {
        this.getTrail().updateTrail(player);
    }

    public void toNBT(NBTTagCompound nbt) {

    }

    public void fromNBT(NBTTagCompound nbt) {
        // TODO
        this.trailRenderer = new TrailRendererSimple();
    }
}
