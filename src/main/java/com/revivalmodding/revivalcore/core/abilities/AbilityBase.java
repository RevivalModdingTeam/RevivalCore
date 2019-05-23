package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;

import net.minecraft.entity.player.EntityPlayer;

/**
 * <b>WIP</b>
 */
public abstract class AbilityBase implements IRegistryEntry
{
	private final String name;
	public final int maxCooldown;
	public int currentCooldown;
	
	public AbilityBase(String name, int cooldown) {
		this.name = name;
		this.maxCooldown = cooldown;
	}
	
	public abstract void onAbilityActivated();
	
	public void update() {
		if(hasCooldown()) {
			--currentCooldown;
		}
	}
	
	public boolean hasCooldown() {
		return currentCooldown > 0;
	}
	
	public static final boolean hasAbility(EntityPlayer player) {
		return false;
	}
	
	public static final <T extends AbilityBase> T getAbility(EntityPlayer player) {
		return null;
	}
	
	public static final AbilityBase getAbilityFromKey(String key) {
		for(AbilityBase base : Registries.ABILITIES) {
			if(base.getName().equalsIgnoreCase(key)) {
				return base;
			}
		}
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public IRegistry getRegistry() {
		return AbilityRegistry.instance();
	}
}
