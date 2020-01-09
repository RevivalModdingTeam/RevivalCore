package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.common.events.RCRegistryEvent;
import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Core class for abilities
 * Each ability has it's own {@link Ability#name} field, which has to be unique
 * for all abilities. {@link Ability#displayName} is for GUIs.
 * For more information see {@link AbilityBuilder} which contains
 * description for all fields in this class.
 *
 * This class is instance of {@link IRegistryEntry} which means
 * there's custom {@link RCRegistryEvent} fired for this. All abilities should be registered
 * in the {@link RCRegistryEvent.AbilityRegistryEvent} event. To register the ability
 * you can use the event's {@link RCRegistryEvent#register(IRegistryEntry)} function or
 * use the {@link Ability#register(RCRegistryEvent.AbilityRegistryEvent)} directly from
 * the {@link Ability}
 *
 *
 * Created by Toma, 8.1.2020
 */
public class Ability implements IRegistryEntry
{
	public final String name, displayName;
	public final int price;
	public final ResourceLocation iconLocation;
	public final int cooldownLimit;
	protected final Consumer<EntityPlayer> activate, deactivate, tick;
	protected final Consumer<AbilityUseContex> use;
	protected final Predicate<EntityPlayer> activationVerification;
	@Nullable
	private final String[] hoveredDesc;
	private int cooldown;
	private boolean toggled = false;

	protected Ability(AbilityBuilder builder) {
		this.name = builder.registryName;
		this.displayName = builder.displayName;
		this.hoveredDesc = builder.hover;
		this.price = builder.price;
		this.iconLocation = builder.iconLocation;
		this.activate = builder.activateAction;
		this.deactivate = builder.deactivateAction;
		this.tick = builder.tickUpdate;
		this.use = builder.useAction;
		this.activationVerification = builder.activateValidator;
		this.cooldownLimit = builder.cooldown;
	}

	public void register(RCRegistryEvent.AbilityRegistryEvent event) {
		event.register(this);
	}

	public String getDisplayName() {
		return displayName;
	}

	@Nullable
	public String[] getHoveredDescription() {
		return hoveredDesc;
	}

	public void onUse(EntityPlayer player) {
		this.use.accept(AbilityUseContex.newContex(this, player));
	}

	public void onActivate(EntityPlayer player) {
		this.activate.accept(player);
	}

	public void onDeactivate(EntityPlayer player) {
		this.deactivate.accept(player);
	}

	public void onTick(EntityPlayer player) {
		this.tick.accept(player);
	}

	public boolean canActivate(EntityPlayer player) {
		return this.activationVerification.test(player);
	}

	public int getPrice() {
		return price;
	}

	public ResourceLocation getGuiIcon() {
		return iconLocation;
	}

	public boolean hasCooldown() {
		return cooldown > 0;
	}

	public void setMaxCooldown() {
		this.cooldown = this.cooldownLimit;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public void toggle() {
		this.toggled = !this.toggled;
	}

	public boolean isToggled() {
		return toggled;
	}

	public int getCooldown() {
		return cooldown;
	}

	public String toString() {
		return "Ability:[Registry name="+name+", cooldown="+cooldown+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ability) {
			return ((Ability)obj).getName().equals(this.getName());
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

	public static Ability fromString(String key) {
		for(Ability a : Registries.AbilityRegistry.instance().getRegistry()) {
			if(a.getName().equalsIgnoreCase(key)) {
				return a;
			}
		}
		return null;
	}

	public static Ability get(EntityPlayer player, int key) {
		return CoreCapabilityImpl.getInstance(player).getAbilityData().getActiveAbilities()[key];
	}

	public static boolean hasAbility(String key, EntityPlayer player, boolean includeUnlocked) {
		return hasAbility(fromString(key), player, includeUnlocked);
	}

	public static boolean hasAbility(Ability ability, EntityPlayer player, boolean includeUnlocked) {
		PlayerAbilityData data = CoreCapabilityImpl.getInstance(player).getAbilityData();
		if(includeUnlocked) {
			for(Ability a : data.getUnlockedAbilities()) {
				if(a.getName().equalsIgnoreCase(ability.getName())) {
					return true;
				}
			}
			return false;
		}
		for(Ability a : data.getActiveAbilities()) {
			if(a.getName().equalsIgnoreCase(ability.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isUnlocked(String key, EntityPlayer player) {
		return isUnlocked(fromString(key), player);
	}

	static boolean isUnlocked(Ability ability, EntityPlayer player) {
		PlayerAbilityData data = CoreCapabilityImpl.getInstance(player).getAbilityData();
		for(Ability a : data.getUnlockedAbilities()) {
			if(a.getName().equalsIgnoreCase(ability.getName())) {
				return true;
			}
		}
		return false;
	}
}
