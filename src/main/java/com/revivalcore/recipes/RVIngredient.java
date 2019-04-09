package com.revivalcore.recipes;

import net.minecraft.init.Items;
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
			int slotID = compound.hasKey("slotID") ? compound.getInteger("slotID") : 0;
			ItemStack ingredient = compound.hasKey("itemID") ? compound.hasKey("itemCount") ?
					new ItemStack(Item.getItemById(compound.getInteger("itemID")), compound.getInteger("itemCount")) : 
						new ItemStack(Item.getItemById(compound.getInteger("itemID"))) : ItemStack.EMPTY;
						
			return new RVIngredient(slotID, ingredient);
		}
		
		return new RVIngredient(0, ItemStack.EMPTY);
	}
}
