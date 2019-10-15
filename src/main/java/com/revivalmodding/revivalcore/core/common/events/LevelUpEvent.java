package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LevelUpEvent extends Event {
	public EntityPlayer player;
	public ICoreCapability capability;
	
	public LevelUpEvent(EntityPlayer player, ICoreCapability cap) {
		this.player = player;
		this.capability = cap;
	}
}
