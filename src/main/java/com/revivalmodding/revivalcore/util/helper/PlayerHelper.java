package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class PlayerHelper {

    public static void sendMessage(EntityPlayer player, String mes, boolean statusbar) {
        if(!player.world.isRemote) {
            player.sendStatusMessage(new TextComponentTranslation(mes), statusbar);
        }
    }

    public static void setboolean(EntityPlayer player, String name, boolean bool) {
        player.getEntityData().setBoolean(name, bool);
    }

    public static void setint(EntityPlayer player, String name, int in) {
        player.getEntityData().setInteger(name, in);
    }

    public static boolean getboolean(EntityPlayer player, String name) {
        return player.getEntityData().getBoolean(name);
    }

    public static int getint(EntityPlayer player, String name) {
        return player.getEntityData().getInteger(name);
    }
}
