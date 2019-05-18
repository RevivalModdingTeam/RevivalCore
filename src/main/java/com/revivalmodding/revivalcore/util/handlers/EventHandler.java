package com.revivalmodding.revivalcore.util.handlers;

import java.util.Random;

import com.revivalmodding.revivalcore.core.RCoreConfig;
import com.revivalmodding.revivalcore.core.common.events.RVItemCraftedEvent;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.tileentity.IProcessCraftSystem;
import com.revivalmodding.revivalcore.util.helper.Constants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
        if (!player.world.isRemote) {
        	// update checker
        	if(RCoreConfig.revivalCore.updatechecker) {
                ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
                if (version.status.equals(ForgeVersion.Status.OUTDATED)) {
                    TextComponentString msg = new TextComponentString(TextFormatting.BLUE + "[RevivalCore] : New Update Available!");
                    msg.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/revivalcore"));
                    msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open Website")));
                    player.sendMessage(msg);
                }
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
    
    @SubscribeEvent
    public static void onSuitCrafted(RVItemCraftedEvent e) {
    	if(!e.getWorld().isRemote) {
    		for(int i = 0; i < 3 + Constants.RANDOM.nextInt(5); i++) {
    			EntityXPOrb xp = new EntityXPOrb(e.getWorld(), e.position.getX(), e.position.getY() + 2, e.position.getZ(), Constants.RANDOM.nextInt(5) + 1);
    			applyMotion(xp);
    			e.getWorld().spawnEntity(xp);
    		}
    	}
    }
    
    private static void applyMotion(Entity entity) {
    	final Random rand = Constants.RANDOM;
    	entity.setVelocity((rand.nextDouble() - rand.nextDouble()) * 0.1, rand.nextDouble(), (rand.nextDouble() - rand.nextDouble()) * 0.1);
    }
}

