package com.revivalmodding.revivalcore.core.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LevelUpEvent extends Event {
	public EntityPlayer player;
	public int level;
	
	public LevelUpEvent(EntityPlayer player, int level) {
		this.player = player;
		this.level = level;
	}
}
