package com.RevivalCore.common.tileentity;

import java.util.Set;

import com.RevivalCore.common.events.SHItemCraftedEvent;
import com.RevivalCore.recipes.SHIngredient;
import com.RevivalCore.recipes.SHRecipe;
import com.RevivalCore.util.helper.RVHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Toma1O6
 * @param <R> - the type of set containing all recipes related to this TE
 */
public interface ICraftSystem<R extends SHRecipe>
{
	default void slotChanged(TileEntitySH te)
	{
		if(!te.getStackInSlot(getOutput()).isEmpty())
			return;
		
		boolean running = false;
		SHRecipe recipe = null;
		
		for(SHRecipe rec : this.getRegistry())
		{
			running = true;
			
			for(SHIngredient i : rec.getIngredients())
			{
				if(!RVHelper.areItemstacksCraftable(i.ingredient, te.getStackInSlot(i.index)))
				{
					running = false;
					break;
				}
			}
			
			if(running)
			{
				recipe = rec;
				break;
			}
		}
		
		// all ingredients were valid (ingnoring slots which weren't specified by the recipe)
		if(running && recipe != null)
		{
			te.setInventorySlotContents(this.getOutput(), recipe.getResult());
			MinecraftForge.EVENT_BUS.post(new SHItemCraftedEvent(te.getWorld(), recipe.getResult()));
			this.consumeIngredients(recipe, te);
		}
	}
	
	default void consumeIngredients(SHRecipe recipe, TileEntitySH te)
	{
		for(int i = 0; i < recipe.getIngredients().length; i++)
		{
			ItemStack ingredient = recipe.getIngredients()[i].ingredient;
			int index = recipe.getIngredients()[i].index;
			ItemStack teStack = te.getStackInSlot(index);
			te.setInventorySlotContents(index, new ItemStack(teStack.getItem(), teStack.getCount() - ingredient.getCount()));
		}
	}
	
	/**
	 * @return the slot index of first slot in craft matrix
	 */
	public int getCraftingMatrixStart();
	
	/**
	 * @return the slot index of last slot in craft matrix
	 */
	public int getCraftingMatrixEnd();
	
	/**
	 * @return the output slot index
	 */
	public int getOutput();
	
	public Set<R> getRegistry();
	
	default boolean isSlotInCraftMatrix(int slotIndex)
	{
		return slotIndex >= this.getCraftingMatrixStart() && slotIndex <= this.getCraftingMatrixEnd();
	}
}
