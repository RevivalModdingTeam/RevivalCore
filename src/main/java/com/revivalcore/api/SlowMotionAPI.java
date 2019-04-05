package com.revivalcore.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class SlowMotionAPI {

    /**
     * Created by Josia50
     * on 4/04/2019.
     */

    public static void SlowProjectiles(EntityPlayer player, int range ,double speed) {
        double s = speed; // Default speed is 0.3 //
         int v = 0;

        for (Entity e : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(range, range, range))) {
            if (e instanceof IProjectile) {
                e.getEntityData().setInteger("edited", 0);
                if (e.getEntityData().getInteger("edited") == 0) {

                    if (e.getHorizontalFacing() == EnumFacing.NORTH) {
                        e.setVelocity(v, v, -s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.SOUTH) {
                        e.setVelocity(v, v, s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.EAST) { // TODO FIX EAST
                        e.setVelocity(s, v, v);
                    }                                                // StackTrace = Checks for North & South rotation only could be fixed with angles and being limited angle.

                    if (e.getHorizontalFacing() == EnumFacing.WEST) { // TODO FIX WEST
                        e.setVelocity(s, v, v);
                    }
                    System.out.println(e.getHorizontalFacing());
                    e.getEntityData().setInteger("edited", 1); // TODO use NBT Helper
                }
            }
        }
    }

    public static void SlowOtherPlayers(EntityPlayer player, int range) {
        for(EntityPlayer player1 : player.world.getEntitiesWithinAABB(EntityPlayer.class, player.getEntityBoundingBox().grow(range, range, range))) {
            SpeedAPI.setSpeedToCap(player1, 1.0F);
        }
    }
}
