package com.revivalmodding.revivalcore.meta.util;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.PEnumHandler.MetaPower;
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
        if (id == -1) {
            return "none";
        }
        return MetaPower.values()[id].getName();
    }

    public static boolean hasPowers(EntityPlayer player) {
        return CapabilityMeta.get(player).hasMetaPowers();
    }

    public static boolean hasPower(EntityPlayer player, String name) {
        IMetaCap cap = CapabilityMeta.get(player);
        if (PEnumHandler.MetaPower.valueOf(name).getID() == cap.getMetaPower()) {
            return true;
        }
        return false;
    }

    public static void setEmptyPower(EntityPlayer player) {
        CapabilityMeta.get(player).clear();
    }

    public static void setMetaPower(EntityPlayer player, String name) {
        IMetaCap cap = CapabilityMeta.get(player);
        for(MetaPower power : MetaPower.values()) {
            if(power.getName().equals(name)) {
                cap.setMetaPower(power.getID());
            }
        }
    }
}
