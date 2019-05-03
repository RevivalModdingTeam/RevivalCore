package com.revivalmodding.revivalcore.core.registry;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.recipes.IRVRecipeRegistry;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;

import java.util.HashSet;
import java.util.Set;

public class SuitMakerRecipeRegistry implements IRVRecipeRegistry<RVRecipe> {
    public static SuitMakerRecipeRegistry instance;

    public static Set<RVRecipe> RECIPES;

    public static void init()
    {
    	instance = new SuitMakerRecipeRegistry();
    	RECIPES = new HashSet<RVRecipe>();
    }
    
    @Override
    public void register(RVRecipe recipe) {
        if (!RECIPES.contains(recipe))
            RECIPES.add(recipe);
    }

    @Override
    public void register(RVRecipe... recipeArr) {
        for (RVRecipe r : recipeArr)
            register(r);
    }
    
    @Override
    public void getRecipes()
    {
    	RevivalCore.logger.info("Printing all recipes... ");
    	
    	for(RVRecipe r : RECIPES)
    	{
    		RevivalCore.logger.info(r);
    	}
    }
}
