package com.psychokiller.recipes;

import net.minecraft.item.ItemStack;

public class SHIngredient
{
	public final int index;
	public final ItemStack ingredient;
	
	public SHIngredient(int slotIndex, ItemStack ingredient)
	{
		this.index = slotIndex;
		this.ingredient = ingredient;
	}
}
