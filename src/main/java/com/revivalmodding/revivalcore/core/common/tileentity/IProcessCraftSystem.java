package com.revivalmodding.revivalcore.core.common.tileentity;

import com.revivalmodding.revivalcore.core.common.events.RVItemCraftedEvent;
import com.revivalmodding.revivalcore.core.recipes.RVIngredient;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketSyncProcessTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
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
		float f = this.getRecipe() != null ? this.getProcessTimer() / (float)this.getRecipe().getCraftTime() : 0f;
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
		if(!te.getWorld().isRemote) {
			this.consumeIngredients(this.getRecipe(), te);
			te.addItemStackToInventory(getOutput(), this.getRecipe().constructResult());
			RVItemCraftedEvent event = new RVItemCraftedEvent(te.getWorld(), this.getRecipe().constructResult(), te.getPos());
			MinecraftForge.EVENT_BUS.post(event);
		}
	}
	
	default boolean shouldProcess(TileEntityRC te, RVRecipe recipe)
	{
		if(this.isProcessing() && recipe != null) {
			for(RVIngredient ingredient : recipe.getIngredients()) {
				if(!te.hasStackInSlot(ingredient.ingredient, ingredient.index)) {
					this.setProcessing(false);
					this.setProcessTimer(0);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	default void sync(TileEntity te) {
		NetworkManager.INSTANCE.sendToAll(new PacketSyncProcessTileEntity(te.getPos(), this.isProcessing(), this.getProcessTimer(), this.getRecipe()));
	}
}
