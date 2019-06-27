package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries.AbilityRegistry;
import com.revivalmodding.revivalcore.core.registry.Registries.SuitMakerRecipeRegistry;
import com.revivalmodding.revivalcore.core.registry.Registries.SuitRegistry;

import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class RVRegistryEvent<T extends IRegistryEntry> extends Event{

	public abstract void register(T entry);
	public abstract void registerAll(T[] entries);
	
    /**
     *  Event is called during <u>initialization</u> phase of RevivalCore
     */
    public static class SuitMakerRecipeRegistryEvent extends RVRegistryEvent<RVRecipe> {

        /**
         * In order to create new recipe entry use the RVRecipeBuilder.create()...build()
         */
    	@Override
        public void register(RVRecipe entry) {
            SuitMakerRecipeRegistry.instance().register(entry);
        }
    	
    	@Override
    	public void registerAll(RVRecipe[] entries) {
    		for(RVRecipe rec : entries) {
    			register(rec);
    		}
    	}
    }
    
    /**
     *  Event is called during <u>post-initialization</u> phase of RevivalCore
     */
    public static class SuitRegistryEvent extends RVRegistryEvent<AbstractSuit> {
    	
    	@Override
    	public void register(AbstractSuit entry)
    	{
    		SuitRegistry.instance().register(entry);
    	}
    	
    	@Override
    	public void registerAll(AbstractSuit[] entries) {
    		for(AbstractSuit suit : entries) {
    			register(suit);
    		}
    	}
    }
    
    /**
     *  Event is called during <u>initialization</u> phase of RevivalCore
     */
    public static class AbilityRegistryEvent extends RVRegistryEvent<AbilityBase> {
    	
    	@Override
    	public void register(AbilityBase entry) {
    		AbilityRegistry.instance().register(entry);
    	}
    	
    	@Override
    	public void registerAll(AbilityBase[] entries) {
    		for(AbilityBase base : entries) {
    			register(base);
    		}
    	}
    }
}
