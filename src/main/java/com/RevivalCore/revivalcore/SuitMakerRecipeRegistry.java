package com.RevivalCore.revivalcore;

import com.RevivalCore.recipes.ISHRecipeRegistry;
import com.RevivalCore.recipes.SHRecipe;

import java.util.HashSet;
import java.util.Set;

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
