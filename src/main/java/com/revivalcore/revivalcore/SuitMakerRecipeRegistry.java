package com.revivalcore.revivalcore;

import com.revivalcore.recipes.IRVRecipeRegistry;
import com.revivalcore.recipes.RVRecipe;

import java.util.HashSet;
import java.util.Set;

public class SuitMakerRecipeRegistry implements IRVRecipeRegistry<RVRecipe>
{
	public static SuitMakerRecipeRegistry instance;
	public static final Set<RVRecipe> RECIPES = new HashSet<RVRecipe>();
	
	@Override
	public void register(RVRecipe recipe)
	{
		if(!RECIPES.contains(recipe))
			RECIPES.add(recipe);
	}
	
	@Override
	public void register(RVRecipe... recipeArr) 
	{
		for(RVRecipe r : recipeArr)
			register(r);
	}
}
