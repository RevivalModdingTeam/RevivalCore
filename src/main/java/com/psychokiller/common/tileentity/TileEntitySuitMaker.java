package com.psychokiller.common.tileentity;

import java.util.Set;

import com.psychokiller.recipes.SHRecipe;
import com.psychokiller.revivalcore.SuitMakerRecipeRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntitySuitMaker extends TileEntitySH implements ICraftSystem<SHRecipe>
{
	public static final TextComponentTranslation NAME = new TextComponentTranslation("container.suitMaker");
	protected NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(17, ItemStack.EMPTY);
	
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
	
	@Override
	public int getOutput()
	{
		return 0;
	}
	
	@Override
	public int getCraftingMatrixEnd() 
	{
		return 1;
	}
	
	@Override
	public int getCraftingMatrixStart()
	{
		return 16;
	}
	
	@Override
	public Set<SHRecipe> getRegistry()
	{
		return SuitMakerRecipeRegistry.RECIPES;
	}
}
