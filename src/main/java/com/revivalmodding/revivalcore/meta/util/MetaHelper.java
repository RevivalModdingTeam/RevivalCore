package com.revivalmodding.revivalcore.meta.util;

import java.util.Random;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.PEnumHandler.MetaPower;

import net.minecraft.entity.player.EntityPlayer;

public class MetaHelper {

    public static void getRandomMetaPower(EntityPlayer player) {
        Random random = new Random();
        IMetaCap cap = CapabilityMeta.get(player);
        int p = random.nextInt(MetaPower.values().length);
        cap.setMetaPower(p);
    }

    public static String getMetaPowerName(int id) {
        if(id == -1) {
            return "none";
        }
        return MetaPower.values()[id].getName();
    }
}
