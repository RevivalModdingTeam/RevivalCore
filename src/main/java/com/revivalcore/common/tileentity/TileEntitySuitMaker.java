package com.revivalcore.common.tileentity;

import java.util.Set;

import javax.annotation.Nullable;

import com.revivalcore.core.registry.SuitMakerRecipeRegistry;
import com.revivalcore.recipes.RVRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntitySuitMaker extends TileEntityRC implements IProcessCraftSystem<RVRecipe>, ITickable
{
    public static final TextComponentTranslation NAME = new TextComponentTranslation("container.suitMaker");
    protected NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(21, ItemStack.EMPTY);
    private boolean isProcessing;
    private byte processTime = 0;
    @Nullable
    public RVRecipe currRecipe = null;

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
    public void setInventory(NonNullList<ItemStack> inv)
    {
    	this.inventory = inv;
    }

    @Override
    public int getOutput()
    {
        return 0;
    }

    @Override
    public int getCraftingMatrixEnd()
    {
        return 21;
    }

    @Override
    public int getCraftingMatrixStart()
    {
        return 1;
    }

    @Override
    public Set<RVRecipe> getRegistry()
    {
        return SuitMakerRecipeRegistry.RECIPES;
    }
    
    @Override
    public void closeInventory(EntityPlayer player)
    {
    	InventoryHelper.dropInventoryItems(world, pos, this);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }
    
    @Override
    public void setProcessing(boolean processing)
    {
    	this.isProcessing = processing;
    }
    
    @Override
    public boolean isProcessing()
    {
    	return isProcessing;
    }
    
    @Override
    public byte getProcessTimer()
    {
    	return processTime;
    }
    
    @Override
    public RVRecipe getRecipe() 
    {
    	return currRecipe;
    }
    
    @Override
    public void process()
    {
    	++this.processTime;
    }
    
    @Override
    public void resetProcessTimer()
    {
    	this.processTime = 0;
    }
    
    @Override
    public void setRecipe(RVRecipe recipe)
    {
    	this.currRecipe = recipe;
    }
    
    @Override
    public void setProcessTimer(byte timer)
    {
    	this.processTime = timer;
    }
    
    @Override
    public int getSizeInventory()
    {
    	return 21;
    }
    
    @Override
    public void update()
    {
    	if(!isProcessing)
    		return;
    	
    	this.process();
    	
    	if(this.getProcessTimer() >= 250)
    	{
    		this.onProcessFinished(this);
    	}
    }
}