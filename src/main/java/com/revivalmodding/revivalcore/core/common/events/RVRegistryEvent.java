package com.revivalmodding.revivalcore.core.common.events;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.registry.SuitMakerRecipeRegistry;
import com.revivalmodding.revivalcore.core.registry.SuitRegistry;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.HashMap;
import java.util.Set;

public class RVRegistryEvent extends Event{

    private static Set<RVRecipe> RECIPES;

    /**
     *  Event is called during <u>initialization</u> phase of RevivalCore
     */
    public static class SuitMakerRecipeRegistryEvent extends RVRegistryEvent {
    	
        public SuitMakerRecipeRegistryEvent(Set<RVRecipe> recipes) {
            RevivalCore.logger.info("Starting RV recipe registration");
            RECIPES = recipes;
        }

        /**
         * In order to create new recipe entry use the RVRecipeBuilder.create()...build()
         */
        public void register(RVRecipe recipe) {
            SuitMakerRecipeRegistry.instance.register(recipe);
        }
    }
    
    /**
     *  Event is called during <u>post-initialization</u> phase of RevivalCore
     */
    public static class SuitRegistryEvent extends RVRegistryEvent {
    	
    	public HashMap getSuits()
    	{
    		return SuitRegistry.SUITS;
    	}
    	
    	public void register(String name, AbstractSuit suit)
    	{
    		SuitRegistry.putEntry(name, suit);
    		System.out.println("registered");
    	}
    }
}
