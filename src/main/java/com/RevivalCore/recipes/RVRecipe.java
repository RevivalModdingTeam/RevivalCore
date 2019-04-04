package com.RevivalCore.recipes;

import net.minecraft.item.ItemStack;

public class RVRecipe
{
	private final RVIngredient[] ingredients;
	private final ItemStack result;
	
	protected RVRecipe(ItemStack result, RVIngredient... ingredients)
	{
		this.result = result;
		this.ingredients = ingredients;
	}
	
	public RVIngredient[] getIngredients()
	{
		return ingredients;
	}
	
	public ItemStack getResult()
	{
		return result;
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
