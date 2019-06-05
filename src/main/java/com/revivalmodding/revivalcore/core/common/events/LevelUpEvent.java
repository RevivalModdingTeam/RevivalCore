package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LevelUpEvent extends Event {
	public EntityPlayer player;
	public IAbilityCap abilities;
	
	public LevelUpEvent(EntityPlayer player, IAbilityCap cap) {
		this.player = player;
		this.abilities = cap;
	}
}
