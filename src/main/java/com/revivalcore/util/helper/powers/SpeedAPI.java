package com.revivalcore.util.helper.powers;

import com.revivalcore.capabilities.CapSpeedstersStorage;
import com.revivalcore.capabilities.CapabilitySpeedster;
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
        CapabilitySpeedster cap = player.getCapability(CapSpeedstersStorage.CAP, null);
        // setSpeed(player, cap.getSpeedLevel); // TODO getSpeedLevel initialize
        cap.sync();
    }

    public static void setSpeedToCap(EntityPlayer player, float level) {
        CapabilitySpeedster cap = player.getCapability(CapSpeedstersStorage.CAP, null);
        // cap.setSpeedLevel(float);
        setSpeed(player, level);
        cap.sync();
    }
}
