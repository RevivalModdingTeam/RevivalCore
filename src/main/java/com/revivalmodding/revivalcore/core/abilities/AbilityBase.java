package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

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
	
	public AbilityBase(String name) {
		this(name, 0);
	}
	
	public void update(EntityPlayer player) {
		if(hasCooldown()) {
			--currentCooldown;
		}
	}
	
	public void toggleAbility() {
		active = !active;
	}
	
	public boolean hasCooldown() {
		return currentCooldown > 0;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public static final boolean hasAbility(EntityPlayer player, String abilityName) {
		IAbilityCap cap = IAbilityCap.Impl.get(player);
		if(cap != null) {
			for(AbilityBase base : cap.getAbilities(player)) {
				if(base.getName().equalsIgnoreCase(abilityName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static final AbilityBase getAbility(EntityPlayer player, int powerKeybind) {
		if(powerKeybind > 2) {
			return null;
		}
		IAbilityCap abilities = IAbilityCap.Impl.get(player);
		AbilityBase[] active = abilities.getAbilities(player);
		if(powerKeybind <= active.length - 1) {
			return active[powerKeybind];
		}
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
