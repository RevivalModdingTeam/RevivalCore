package com.revivalmodding.revivalcore.core.common.tileentity;

import com.revivalmodding.revivalcore.core.common.events.RVItemCraftedEvent;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;

import net.minecraftforge.common.MinecraftForge;

public interface IProcessCraftSystem<R extends RVRecipe> extends ICraftSystem<R>
{
	void setProcessing(boolean processing);
	
	void setRecipe(RVRecipe recipe);
	
	RVRecipe getRecipe();
	
	void setProcessTimer(int timer);
	
	void process();
	
	void resetProcessTimer();
	
	int getProcessTimer();
	
	boolean isProcessing();
	
	// For gui rendering
	default float getProgressionStage()
	{
		float f = this.getProcessTimer() / (float)this.getRecipe().getCraftTime();
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
		RVItemCraftedEvent event = new RVItemCraftedEvent(te.getWorld(), this.getRecipe().constructResult(), te.getPos());
		MinecraftForge.EVENT_BUS.post(event);
	}
}
