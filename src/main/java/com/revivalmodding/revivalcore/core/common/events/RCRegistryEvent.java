package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class RCRegistryEvent<T extends IRegistryEntry> extends Event{

	public void register(T entry) {
		entry.getRegistry().register(entry);
	}

	public void registerAll(T[] entries) {
		for(T t : entries) {
			this.register(t);
		}
	}

    public static class SuitMakerRecipeRegistryEvent extends RCRegistryEvent<RVRecipe> {
    }

    public static class SuitRegistryEvent extends RCRegistryEvent<AbstractSuit> {
    }

    public static class AbilityRegistryEvent extends RCRegistryEvent<Ability> {
    }
}
