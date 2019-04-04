package com.revivalcore.recipes;

/**
 * @param <R> - the output recipe
 */
public interface IRVRecipeRegistry<R> {
    void register(R recipe);

    void register(R... recipeArr);
}
