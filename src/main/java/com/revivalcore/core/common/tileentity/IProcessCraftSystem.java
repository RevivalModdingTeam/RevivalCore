package com.revivalcore.core.common.tileentity;

import com.revivalcore.core.recipes.RVRecipe;

public interface IProcessCraftSystem<R extends RVRecipe> extends ICraftSystem<R>
{
	void setProcessing(boolean processing);
	
	void setRecipe(RVRecipe recipe);
	
	RVRecipe getRecipe();
	
	void setProcessTimer(short timer);
	
	void process();
	
	void resetProcessTimer();
	
	short getProcessTimer();
	
	boolean isProcessing();
	
	// For gui rendering
	default float getProgressionStage()
	{
		float f = this.getProcessTimer() / 250f;
		return f;
	}
	
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
