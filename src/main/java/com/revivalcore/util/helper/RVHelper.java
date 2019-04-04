package com.revivalcore.util.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RVHelper
{
	public static NBTTagCompound getOrCreateStackNBT(ItemStack stack)
	{
		return stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
	}
	
	public static boolean areItemstacksCraftable(ItemStack recipe, ItemStack testStack)
	{
		return testStack.getItem() == recipe.getItem() && testStack.getCount() >= recipe.getCount();
	}
}
