package com.revivalcore.meta.util;

import com.revivalcore.meta.capability.CapabilityMeta;
import com.revivalcore.meta.capability.IMetaCap;
import com.revivalcore.meta.util.PEnumHandler.MetaPower;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

public class MetaHelper {

    public static void getRandomMetaPower(EntityPlayer player) {
        Random random = new Random();
        IMetaCap cap = CapabilityMeta.get(player);
        int p = random.nextInt(MetaPower.values().length);
        cap.setMetaPower(p);
    }

    public static String getMetaPowerName(int id) {
        if(id != -1) {
            return MetaPower.values()[id].getName();
        }
        return "none";
    }
}
