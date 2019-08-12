package com.revivalmodding.revivalcore.core.abilities;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

/**
 * Base class for all abilities
 * Each ability has to be registered in RVRegistryEvent.AbilityRegistryEvent
 * Once registered, players can unlock and use this ability using the AbilityGUI
 *
 * Each ability interaction fires some event (AbilityEvent.Activate and AbilityEvent.Deactivate)
 * Keep in mind that not all abilites might call the .Deactivate event, design feature/flaw, whatever
 *
 * - Made by Toma
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
	 * Amount of levels to buy this the ability
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
	 * Ability won't be activated unless this returns true. 
	 */
	public boolean canActivateAbility(EntityPlayer player) {
		return true;
	}
	
	/** Called when player removes this ability from active ability list **/
	public void onAbilityDeactivated(EntityPlayer player) {
		
	}

	/**
	 * Description which will be displayed when player hovers over this
	 * ability inside AbilityGUI.
	 * return null for no description
	 */
	@Nullable
	public String[] getHoveredDescription() {
		return null;
	}
	
	public final void toggleAbility() {
		active = !active;
	}
	
	public final boolean hasCooldown() {
		return currentCooldown > 0;
	}
	
	public final boolean isActive() {
		return active;
	}

	/**
	 * @return if player has currently active ability with the name of abilityName
	 */
	public static boolean hasAbility(EntityPlayer player, String abilityName) {
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
	
	public static boolean hasAbility(AbilityBase ability, Collection<AbilityBase> collection) {
		for(AbilityBase a : collection) {
			if(a.getName().equalsIgnoreCase(ability.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasUnlockedAbility(EntityPlayer player, String abilityName) {
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
	
	public static AbilityBase getAbility(EntityPlayer player, int powerKeybind) {
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

	/**
	 * Called only during capability sync, you shouldn't have to call this method
	 * @param key - the ability registry name
	 * @return
	 */
	public static AbilityBase getAbilityFromKey(String key) {
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
	public String toString() {
		return "Ability:[registry name="+name+", active="+active+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbilityBase) {
			return ((AbilityBase)obj).getName().equals(this.getName());
		}
		return false;
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
