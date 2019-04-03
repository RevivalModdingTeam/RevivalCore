package com.psychokiller.revivalcore;

import java.util.HashSet;
import java.util.Set;

import com.psychokiller.recipes.ISHRecipeRegistry;
import com.psychokiller.recipes.SHRecipe;

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
