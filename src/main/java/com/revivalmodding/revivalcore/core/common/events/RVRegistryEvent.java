package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.registry.Registries;

import net.minecraftforge.fml.common.eventhandler.Event;

public class RVRegistryEvent extends Event{

    /**
     *  Event is called during <u>initialization</u> phase of RevivalCore
     */
    public static class SuitMakerRecipeRegistryEvent extends RVRegistryEvent {

        /**
         * In order to create new recipe entry use the RVRecipeBuilder.create()...build()
         */
        public void register(RVRecipe recipe) {
            Registries.SuitMakerRecipeRegistry.instance().register(recipe);
        }
    }
    
    /**
     *  Event is called during <u>post-initialization</u> phase of RevivalCore
     */
    public static class SuitRegistryEvent extends RVRegistryEvent {
    	
    	public void register(AbstractSuit suit)
    	{
    		Registries.SuitRegistry.instance().register(suit);
    	}
    	
    	public void register(AbstractSuit[] suit)
    	{
    		Registries.SuitRegistry.instance().registerAll(suit);
    	}
    }
}
