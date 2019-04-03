package com.psychokiller.recipes;

import java.util.Set;

/**
 * @param <R> - the output recipe
 */
public interface ISHRecipeRegistry<R> 
{
	public void register(R recipe);
}
