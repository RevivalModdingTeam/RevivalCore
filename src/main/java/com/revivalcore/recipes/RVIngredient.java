package com.revivalcore.recipes;

import net.minecraft.item.ItemStack;

public class RVIngredient
{
	public final int index;
	public final ItemStack ingredient;
	
	public RVIngredient(int slotIndex, ItemStack ingredient)
	{
		this.index = slotIndex;
		this.ingredient = ingredient;
	}
	
	@Override
	public String toString()
	{
		return "{SlotID:"+index + ",Item:" + ingredient.getItem().getRegistryName() + "}";
	}
}
