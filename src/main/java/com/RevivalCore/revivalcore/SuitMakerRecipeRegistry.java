package com.RevivalCore.revivalcore;

import java.util.HashSet;
import java.util.Set;

import com.RevivalCore.recipes.ISHRecipeRegistry;
import com.RevivalCore.recipes.SHRecipe;

public class SuitMakerRecipeRegistry implements ISHRecipeRegistry<SHRecipe>
{
	public static SuitMakerRecipeRegistry instance;
	public static final Set<SHRecipe> RECIPES = new HashSet<SHRecipe>();
	
	@Override
	public void register(SHRecipe recipe)
	{
		if(!RECIPES.contains(recipe))
			RECIPES.add(recipe);
	}
}
