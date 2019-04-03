package com.psychokiller.common.tileentity;

import java.util.Set;

import com.psychokiller.recipes.SHRecipe;
import com.psychokiller.revivalcore.RevivalCore;

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
		
		for(SHRecipe rec : this.getRegistry())
		{
			
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
