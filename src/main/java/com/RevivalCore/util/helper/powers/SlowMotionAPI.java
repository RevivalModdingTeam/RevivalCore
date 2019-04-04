package com.RevivalCore.util.helper.powers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class SlowMotionAPI {

    /**
     * Created by Josia50
     * on 4/04/2019.
     */

    public static void SlowProjectiles(EntityPlayer player, double speed) {
        double s = speed; // Default speed is 0.3 //
         int v = 0;

        for (Entity e : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(5, 5, 5))) {
            if (e instanceof IProjectile) {
                e.getEntityData().setInteger("edited", 0);
                if (e.getEntityData().getInteger("edited") == 0) {

                    if (e.getHorizontalFacing() == EnumFacing.NORTH) {
                        e.setVelocity(v, v, -s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.SOUTH) {
                        e.setVelocity(v, v, s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.EAST) {
                        e.setVelocity(s, v, v);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.WEST) {
                        e.setVelocity(s, v, v);
                    }
                    System.out.println(e.getHorizontalFacing());
                    e.getEntityData().setInteger("edited", 1);
                }
            }
        }
    }
}
