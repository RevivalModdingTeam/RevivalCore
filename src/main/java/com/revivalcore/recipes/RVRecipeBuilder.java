package com.revivalcore.recipes;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RVRecipeBuilder
{
	private ItemStack result;
	private RVIngredient[] ingredients = new RVIngredient[0];
	
	private RVRecipeBuilder() {}
	
	public static RVRecipeBuilder create()
	{
		return new RVRecipeBuilder();
	}
	
	public RVRecipeBuilder result(Item item, int count)
	{
		result = new ItemStack(item, count);
		return this;
	}
	
	public RVRecipeBuilder result(Block block, int count)
	{
		result = new ItemStack(block, count);
		return this;
	}
	
	public RVRecipeBuilder addIngredient(Item item, int count, int slotIndex)
	{
		ItemStack ing = new ItemStack(item, count);
		if(ingredients.length == 0)
			ingredients[0] = new RVIngredient(slotIndex, ing);
		
		else ingredients[ingredients.length] = new RVIngredient(slotIndex, ing);
		
		return this;
	}
	
	public RVRecipeBuilder addIngredient(Block block, int count, int slotIndex)
	{
		ItemStack ing = new ItemStack(block, count);
		if(ingredients.length == 0)
			ingredients[0] = new RVIngredient(slotIndex, ing);
		
		else ingredients[ingredients.length] = new RVIngredient(slotIndex, ing);
		
		return this;
	}
	
	public RVRecipe build() throws IllegalArgumentException
	{
		result = Preconditions.checkNotNull(result);
		if(ingredients.length == 0) throw new IllegalArgumentException("Ingredient array cannot be empty!");
		
		return new RVRecipe(result, ingredients);
	}
}
