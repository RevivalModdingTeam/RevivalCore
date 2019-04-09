package com.revivalcore.common.tileentity;

import com.revivalcore.recipes.RVRecipe;

import net.minecraft.tileentity.TileEntity;

public interface IProcessCraftSystem<R extends RVRecipe> extends ICraftSystem<R>
{
	void setProcessing(boolean processing);
	
	void setRecipe(RVRecipe recipe);
	
	RVRecipe getRecipe();
	
	void setProcessTimer(byte timer);
	
	void process();
	
	void resetProcessTimer();
	
	byte getProcessTimer();
	
	boolean isProcessing();
	
	default void onProcessFinished(TileEntityRC te) throws IllegalStateException
	{
		if(!(te instanceof IProcessCraftSystem))
			throw new IllegalStateException(te.getName() + " must be implement the IProcessCraftSystem!");
		
		if(this.getRecipe() == null)
			throw new IllegalStateException("Recipe cannot be null!");
		
		this.setProcessing(false);
		this.resetProcessTimer();
		
		te.addItemStackToInventory(getOutput(), this.getRecipe().constructResult());
	}
}
