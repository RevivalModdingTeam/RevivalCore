package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * <b>WIP</b>
 */
public abstract class AbilityBase implements IRegistryEntry
{
	public final int maxCooldown;
	public int currentCooldown;
	private final String name;
	private boolean active;
	
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
	
	public static NBTTagCompound writeNBT(String name) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setString("name", name);
		return comp;
	}
	
	public static AbilityBase readNBT(NBTTagCompound comp) {
		if(!comp.hasKey("name"))
			return null;
		
		return getAbilityFromKey(comp.getString("name"));
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
