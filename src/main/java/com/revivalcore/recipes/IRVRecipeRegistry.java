package com.revivalcore.recipes;

/**
 * @param <R> - the output recipe
 */
public interface IRVRecipeRegistry<R> 
{
	public void register(R recipe);
	
	public void register(R... recipeArr);
}
