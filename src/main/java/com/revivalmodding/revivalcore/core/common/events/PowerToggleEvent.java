package com.revivalmodding.revivalcore.core.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 *  Called on server side
 */
public class PowerToggleEvent extends Event {
	
	public final EntityPlayer player;
	private final boolean hasActivatedPower;
	
	public PowerToggleEvent(EntityPlayer player, boolean activePower) {
		this.player = player;
		this.hasActivatedPower = activePower;
	}
	
	public boolean hasActivatedPowers() {
		return hasActivatedPower;
	}
}
