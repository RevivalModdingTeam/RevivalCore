package com.revivalmodding.revivalcore.core.client.trail;

import net.minecraft.util.math.Vec3d;

public enum TrailPart {
    BODY(-0.2, 1, 0),

    RIGHT_ARM(-0.2, 1.2, 0.25),
    LEFT_ARM(-0.2, 1.2, -0.25),

    RIGHT_LEG(-0.2, 0.4, 0.20),
    LEFT_LEG(-0.2, 0.4, -0.20),

    RIGHT(-0.2, 1.7, 0.15),
    LEFT(-0.2, 1.7, -0.15),

    HEAD(-0.2, 1.4, 0),

    RIGHT_ARM_2(-0.2, 1.2, 0.15),
    LEFT_ARM_2(-0.2, 1.2, -0.15),

    RIGHT_LEG_2(-0.2, 0.9, 0.22),
    LEFT_LEG_2(-0.2, 0.9, -0.22);

    private Vec3d offset;

    TrailPart(double x, double y, double z) {
        this.offset = new Vec3d(x, y, z);
    }

    public Vec3d offset() {
        return this.offset;
    }
}
