package com.revivalmodding.revivalcore.core.client.trail;

import net.minecraft.util.math.Vec3d;

public enum TrailPart {

    HEAD(-0.2, 1.7, 0),
    BODY(-0.2, 1, 0),
    RIGHT_ARM(-0.2, 1.2, 0.5),
    LEFT_ARM(-0.2, 1.2, -0.5),
    RIGHT_LEG(-0.2, 0.4, 0.25),
    LEFT_LEG(-0.2, 0.4, -0.25);

    private Vec3d offset;

    TrailPart(double x, double y, double z) {
        this.offset = new Vec3d(x, y, z);
    }

    public Vec3d offset() {
        return this.offset;
    }
}
