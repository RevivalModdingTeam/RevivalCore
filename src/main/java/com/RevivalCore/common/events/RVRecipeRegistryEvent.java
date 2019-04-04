package com.RevivalCore.common.events;

import java.util.Set;

import com.RevivalCore.recipes.IRVRecipeRegistry;
import com.RevivalCore.recipes.RVRecipe;
import com.RevivalCore.revivalcore.RevivalCore;
import com.RevivalCore.revivalcore.SuitMakerRecipeRegistry;

import net.minecraftforge.fml.common.eventhandler.Event;

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
