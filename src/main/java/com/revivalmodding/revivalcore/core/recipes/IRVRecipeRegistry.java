package com.revivalmodding.revivalcore.core.recipes;

/**
 * @param <R> - the output recipe
 */
public interface IRVRecipeRegistry<R extends RVRecipe> {
	
    void register(R recipe);

    void register(R... recipeArr);
    
    void getRecipes();
}
