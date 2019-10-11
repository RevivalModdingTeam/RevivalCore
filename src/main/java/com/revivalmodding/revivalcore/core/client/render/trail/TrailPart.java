package com.revivalmodding.revivalcore.core.client.render.trail;

import net.minecraft.util.math.Vec3d;

public enum TrailPart {

    HEAD,
    BODY,
    RIGHT_ARM,
    LEFT_ARM,
    RIGHT_LEG,
    LEFT_LEG;

    private Vec3d offset;

    public Vec3d offset() {
        return offset;
    }
}
