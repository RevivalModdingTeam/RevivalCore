package com.revivalmodding.revivalcore.core.client.render.trail;

import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class TrailOptionalData {

    private final Trail parent;

    private int[] stageColors;
    private int secondaryColor;
    private boolean isRainbow;

    public TrailOptionalData(Trail parent) {
        this(parent, -1);
    }

    public TrailOptionalData(Trail parent, int secondaryColor) {
        this(parent, secondaryColor, false, null);
    }

    public TrailOptionalData(Trail parent, int secondaryColor, boolean isRainbow, @Nullable int[] stageColors) {
        this.parent = parent;
        this.stageColors = new int[parent.length];
        this.secondaryColor = secondaryColor;
        this.isRainbow = isRainbow;
        if(stageColors == null) return;
        if(stageColors.length < parent.length) throw new IllegalArgumentException("Stage color array length must be >= parent trail length!");
        System.arraycopy(stageColors, 0, this.stageColors, 0, parent.length);
    }

    @SideOnly(Side.CLIENT)
    public void onTrailRender(Vec3d from, Vec3d to, int primaryColor, int primaryTrailWidth) {
        // TODO render secondary trail, but smaller
    }

    public boolean hasSecondaryColor() {
        return secondaryColor > -1;
    }
}
