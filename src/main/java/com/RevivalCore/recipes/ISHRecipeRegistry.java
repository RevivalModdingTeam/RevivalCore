package com.RevivalCore.recipes;

/**
 * @param <R> - the output recipe
 */
public interface ISHRecipeRegistry<R> 
{
	public void register(R recipe);
}
