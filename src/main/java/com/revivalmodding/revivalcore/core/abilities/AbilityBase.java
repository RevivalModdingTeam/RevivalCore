package com.revivalmodding.revivalcore.core.abilities;

import java.util.List;

import javax.annotation.Nonnull;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	
	/**
	 * Icon into ability gui
	 */
	@SideOnly(Side.CLIENT)
	@Nonnull
	public abstract ResourceLocation getIcon();
	
	/**
	 * Amount of levels to buy this unlock the ability
	 */
	public abstract int getAbilityPrice();
	
	/**
	 * Name for displaying on GUI buttons
	 */
	public abstract String getFullName();
	
	public void update(EntityPlayer player) {
		if(hasCooldown()) {
			--currentCooldown;
		}
	}
	
	/**
	 *  Called first on server, then is sent to client.
	 */
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
			for(AbilityBase base : cap.getAbilities()) {
				if(base.getName().equalsIgnoreCase(abilityName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static final boolean hasUnlockedAbility(EntityPlayer player, String abilityName) {
		IAbilityCap cap = IAbilityCap.Impl.get(player);
		if(cap != null) {
			for(AbilityBase base : cap.getUnlockedAbilities()) {
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
		List<AbilityBase> active = abilities.getAbilities();
		if(powerKeybind <= active.size() - 1) {
			return active.get(powerKeybind);
		}
		return null;
	}
	
	public static final AbilityBase getAbilityFromKey(String key) {
		for(AbilityBase base : Registries.ABILITIES) {
			if(base.getName().equalsIgnoreCase(key)) {
				return base;
			}
		}
		RevivalCore.logger.error("Couldn't find ability with key {}, skipping...", key.toUpperCase());
		return null;
	}
	
	public static NBTTagCompound writeNBT(String name) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setString("abilityID", name);
		return comp;
	}
	
	public static AbilityBase readNBT(NBTTagCompound comp) {
		if(!comp.hasKey("abilityID"))
			return null;
		
		return getAbilityFromKey(comp.getString("abilityID"));
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
