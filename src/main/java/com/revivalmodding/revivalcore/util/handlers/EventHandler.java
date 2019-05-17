package com.revivalmodding.revivalcore.util.handlers;

import com.revivalmodding.revivalcore.core.RCoreConfig;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent playerEvent) {
        EntityPlayer player = playerEvent.player;
        if (!player.world.isRemote && RCoreConfig.revivalCore.updatechecker) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status.equals(ForgeVersion.Status.OUTDATED)) {
                TextComponentString msg = new TextComponentString(TextFormatting.BLUE + "[RevivalCore] : New Update Available!");
                msg.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/revivalcore"));
                msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open Website")));
                player.sendMessage(msg);
            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent e) {
    	for(EntityPlayer player : e.world.playerEntities) {
    		AbstractSuit suit = AbstractSuit.getSuit(player);
    		if(suit != null)
    			suit.handleEffects(player);
    	}
    }
}

