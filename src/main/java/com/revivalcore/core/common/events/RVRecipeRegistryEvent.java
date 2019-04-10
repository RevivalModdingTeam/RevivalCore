package com.revivalcore.core.common.events;

import com.revivalcore.RevivalCore;
import com.revivalcore.core.recipes.RVRecipe;
import com.revivalcore.core.registry.SuitMakerRecipeRegistry;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Set;

public class RVRecipeRegistryEvent extends Event{

    private static Set<RVRecipe> RECIPES;

    public static class SuitMakerRecipeRegistryEvent extends RVRecipeRegistryEvent {
    	
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
}
