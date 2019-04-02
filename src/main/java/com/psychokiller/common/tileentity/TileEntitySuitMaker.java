package com.psychokiller.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntitySuitMaker extends TileEntitySH
{
	public static final TextComponentTranslation NAME = new TextComponentTranslation("container.suitMaker");
	protected NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
	
	@Override
	public ITextComponent getDisplayName()
	{
		return NAME;
	}
	
	@Override
	public String getName()
	{
		return NAME.getKey();
	}
	
	@Override
	public NonNullList<ItemStack> getInventory() 
	{
		return inventory;
	}
}
