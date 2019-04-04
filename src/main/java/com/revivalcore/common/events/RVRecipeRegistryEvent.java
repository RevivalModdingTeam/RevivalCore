package com.revivalcore.common.events;

import com.revivalcore.recipes.RVRecipe;
import com.revivalcore.revivalcore.RevivalCore;
import com.revivalcore.revivalcore.SuitMakerRecipeRegistry;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Set;

public class RVRecipeRegistryEvent
{
	private static Set<RVRecipe> RECIPES;
	
	public static final class SuitMakerRecipeRegistryEvent extends Event
	{
		public SuitMakerRecipeRegistryEvent(Set<RVRecipe> recipes) 
		{
			RevivalCore.logger.info("Starting RV recipe registration");
			RECIPES = recipes;
		}
		
		/**
		 *   In order to create new recipe entry use the RVRecipeBuilder.create()...build()
		 */
		public void register(RVRecipe recipe)
		{
			SuitMakerRecipeRegistry.instance.register(recipe);
		}
	}
}
