package com.revivalmodding.revivalcore.core.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
	
	public static NBTTagCompound writeIngredientNBT(NBTTagCompound compound, RVIngredient ingredient)
	{
		NBTTagCompound c = new NBTTagCompound();
		c.setInteger("slotID", ingredient.index);
		c.setInteger("itemID", Item.getIdFromItem(ingredient.ingredient.getItem()));
		c.setInteger("itemCount", ingredient.ingredient.getCount());
		compound.setTag("itemstack", c);
		return compound;
	}
	
	public static RVIngredient readIngredientFromNBT(NBTTagCompound compound)
	{
		if(compound.hasKey("itemstack"))
		{
			NBTTagCompound c = compound.getCompoundTag("itemstack");
			int slot = c.getInteger("slotID");
			ItemStack ing = new ItemStack(Item.getItemById(c.getInteger("itemID")), c.getInteger("itemCount"));
			return new RVIngredient(slot, ing);
		}
		
		return new RVIngredient(0, ItemStack.EMPTY);
	}
}
