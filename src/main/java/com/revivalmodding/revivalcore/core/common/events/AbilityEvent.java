package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.Ability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class AbilityEvent extends Event {
	
	public EntityPlayer player;
	private Ability ability;
	
	public AbilityEvent(EntityPlayer player, Ability ability) {
		this.ability = ability;
		this.player = player;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public static class Activate extends AbilityEvent {
		public Activate(Ability ability, EntityPlayer player) {
			super(player, ability);
		}
	}
	
	public static class Deactivate extends AbilityEvent {
		public Deactivate(Ability ability, EntityPlayer player) {
			super(player, ability);
		}
	}
}
