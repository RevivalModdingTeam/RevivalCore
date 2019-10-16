package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.Ability;

import com.revivalmodding.revivalcore.network.packets.PacketAbilityAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nullable;

public class AbilityEvent extends Event {
	
	public EntityPlayer player;
	private Ability ability;
	public final PacketAbilityAction.AbilityAction action;

	public AbilityEvent(EntityPlayer player, @Nullable Ability ability, PacketAbilityAction.AbilityAction action) {
		this.ability = ability;
		this.player = player;
		this.action = action;
	}
	
	public Ability getAbility() {
		return ability;
	}

	public PacketAbilityAction.AbilityAction getAction() {
		return action;
	}
}
