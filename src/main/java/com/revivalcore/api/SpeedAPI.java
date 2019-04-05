package com.revivalcore.api;

import com.revivalcore.common.capabilities.CapabilitySpeedster;
import com.revivalcore.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Josia50
 * on 5/04/2019.
 */

public class SpeedAPI {

    public static void setSpeed(EntityPlayer player, float level) {
        player.setAIMoveSpeed(level);
    }

    public static void setSpeedFromCap(EntityPlayer player) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        setSpeed(player, cap.getSpeedLevel());
        cap.sync();
    }

    public static void setSpeedToCap(EntityPlayer player, float level) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        cap.setSpeedLevel(level);
        setSpeed(player, level);
        cap.sync();
    }
}