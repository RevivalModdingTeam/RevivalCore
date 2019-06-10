package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class AbilityEvent extends Event {
	
	public EntityPlayer player;
	private AbilityBase ability;
	
	public AbilityEvent(EntityPlayer player, AbilityBase ability) {
		this.ability = ability;
		this.player = player;
	}
	
	public AbilityBase getAbility() {
		return ability;
	}
	
	public static class Activate extends AbilityEvent {
		public Activate(AbilityBase ability, EntityPlayer player) {
			super(player, ability);
		}
	}
	
	public static class Deactivate extends AbilityEvent {
		public Deactivate(AbilityBase ability, EntityPlayer player) {
			super(player, ability);
		}
	}
}
