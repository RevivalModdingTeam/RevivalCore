package com.psychokiller.revivalcore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.ItemStack;

public class SuitMakerRecipeRegistry 
{
	private static final Set<SuitMakerRecipe> RECIPES = new HashSet<SuitMakerRecipe>();
	
	public static void register(SuitMakerRecipe recipe)
	{
		if(RECIPES.contains(recipe))
			RECIPES.add(recipe);
	}
	
	public final class SuitMakerRecipe
	{
		private final SuitMakerIngredient[] ingredients;
		private final ItemStack result;
		
		public SuitMakerRecipe(ItemStack result, SuitMakerIngredient... ingredients)
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
	
	public final class SuitMakerIngredient
	{
		public final int index;
		public final ItemStack ingredient;
		
		public SuitMakerIngredient(int slotIndex, ItemStack ingredient)
		{
			this.index = slotIndex;
			this.ingredient = ingredient;
		}
	}
}
