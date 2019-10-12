package com.revivalmodding.revivalcore.core.client.render.trail;

import net.minecraft.util.math.Vec3d;

public enum TrailPart {

    HEAD(0, 1.7, 0),
    BODY(0, 1, 0),
    RIGHT_ARM(0.5, 1.2, 0),
    LEFT_ARM(-0.5, 1.2, 0),
    RIGHT_LEG(0.2, 0.25, 0),
    LEFT_LEG(-0.2, 0.25, 0);

    private Vec3d offset;

    TrailPart(double x, double y, double z) {
        this.offset = new Vec3d(x, y, z);
    }

    public Vec3d offset() {
        return offset;
    }
}
