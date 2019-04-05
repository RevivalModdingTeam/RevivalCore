package com.revivalcore.recipes;

import net.minecraft.item.ItemStack;

public class RVRecipe
{
	private final RVIngredient[] ingredients;
	private final ItemStack result;
	private final String name;
	
	protected RVRecipe(String name, ItemStack result, RVIngredient... ingredients) throws IllegalArgumentException
	{
		this.result = result;
		this.ingredients = ingredients;
		if(name.isEmpty())
			throw new IllegalArgumentException("Recipe name cannot be empty!");
		this.name = name;
	}
	
	public RVIngredient[] getIngredients()
	{
		return ingredients;
	}
	
	public ItemStack getResult()
	{
		return result;
	}
	
	public String getName()
	{
		return name;
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
	
	@Override
	public String toString()
	{
		return "Recipe: [Name=" + name + ", Result=" + result + ", Ingredients=" + ingredients + "]";
	}
}
