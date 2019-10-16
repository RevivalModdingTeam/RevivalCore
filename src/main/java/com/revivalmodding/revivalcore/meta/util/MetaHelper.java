package com.revivalmodding.revivalcore.meta.util;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.meta.util.PEnumHandler.MetaPower;
import com.revivalmodding.revivalcore.util.helper.EnumHelper.InjectionTypes;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

public class MetaHelper {

    public static void getRandomMetaPower(EntityPlayer player) {
        Random random = new Random();
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        int p = random.nextInt(MetaPower.values().length);
        powerData.setMetaPower(p);
    }

    public static String getMetaPowerName(int id) {
        if (id == -1) {
            return "none";
        }
        return MetaPower.values()[id].getName();
    }

    public static InjectionTypes getMetaEnum(String name) {
        for(InjectionTypes it : InjectionTypes.values()) {
            if(it.getName().equals(name)) {
                return it;
            }
        }
        return InjectionTypes.EMPTY;
    }

    public static boolean hasPowers(EntityPlayer player) {
        return CoreCapabilityImpl.getInstance(player).getMetaPowerData().hasMetaPowers();
    }

    public static boolean hasPower(EntityPlayer player, String name) {
        for(MetaPower power : MetaPower.values()) {
            if(power.getName().equals(name))
                return true;
        }
        return false;
    }

    public static void setEmptyPower(EntityPlayer player) {
        CoreCapabilityImpl.getInstance(player).getMetaPowerData().clear();
    }

    public static int getPowerId(String name) {
        for(MetaPower p : MetaPower.values()) {
            if(p.getName().equals(name)) {
                return p.getID();
            }
        }
        return MetaPower.values().length + 1;
    }

    public static void setMetaPower(EntityPlayer player, String name) {
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        for(MetaPower power : MetaPower.values()) {
            if(power.getName().equals(name)) {
                powerData.setMetaPower(power.getID());
            }
        }
    }
}
