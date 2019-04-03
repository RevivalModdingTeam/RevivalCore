package com.psychokiller.recipes;

import net.minecraft.item.ItemStack;

public class SHRecipe
{
	private final SHIngredient[] ingredients;
	private final ItemStack result;
	
	public SHRecipe(ItemStack result, SHIngredient... ingredients)
	{
		this.result = result;
		this.ingredients = ingredients;
	}
	
	public boolean containsIngredient(ItemStack stack)
	{
		for(int i = 0; i < ingredients.length; i++)
		{
			ItemStack ing = ingredients[i].ingredient;
			
			if(ItemStack.areItemStacksEqual(stack, ing))
			{
				return true;
			}
		}
		
		return false;
	}
}
