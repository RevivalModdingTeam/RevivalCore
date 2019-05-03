package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.util.EnumFacing;

public class RenderHelpers {

    public static float getAngleFromFacing(EnumFacing facing) {
        float angle = 180;
        if (facing.equals(EnumFacing.EAST)) angle = 270;
        if (facing.equals(EnumFacing.SOUTH)) angle = 0;
        if (facing.equals(EnumFacing.WEST)) angle = 90;
        return angle;
    }
}
