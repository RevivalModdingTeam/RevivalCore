package com.revivalmodding.revivalcore.core.common.tileentity;

import java.util.Set;

import javax.annotation.Nullable;

import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.core.registry.SuitMakerRecipeRegistry;
import com.revivalmodding.revivalcore.util.helper.RVHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
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
    private int processTime = 0;
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
    public int getProcessTimer()
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
    public void setProcessTimer(int timer)
    {
        this.processTime = timer;
    }

    @Override
    public int getSizeInventory()
    {
        return 21;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        // TODO: Fix - [!!!net.minecraft.nbt.NBTTagCompound@6f68d53b=>java.lang.StackOverflowError:null!!!]
        /*if(this.getRecipe() != null)
        {
            compound.setBoolean("isProcessing", isProcessing());
            compound.setInteger("processTime", getProcessTimer());
            compound.setTag("recipe", RVRecipe.writeRecipeToNBT(compound, getRecipe()));
            System.out.println(compound.getTag("recipe"));
        }*/
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
    	if(compound.hasKey("recipe"))
    	{
    		System.out.println("here");
    		setProcessing(compound.hasKey("isProcessing") ? compound.getBoolean("isProcessing") : false);
    		setProcessTimer(compound.hasKey("processTime") ? compound.getInteger("processTime") : 0);
    		setRecipe(compound.hasKey("recipe") ? RVRecipe.readRecipeFromNBT(compound) : null);
    		
            System.out.println(getRecipe());
    	}
    }

    @Override
    public void update()
    {
        if(this.isProcessing())
        {
        	if(world.isRemote)
        		RVHelper.spawnParticles(world, EnumParticleTypes.SMOKE_NORMAL, pos, 0.15, 4);
        	
            this.process();

            if(this.getProcessTimer() >= this.getRecipe().getCraftTime())
            {
                this.onProcessFinished(this);
            }
        }
    }
}