package com.psychokiller.util.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class PlayerHelper {

    public static void sendMessage(EntityPlayer player, String mes, boolean statusbar) {
        if(!player.world.isRemote) {
            player.sendStatusMessage(new TextComponentTranslation(mes), statusbar);
        }
    }
}
