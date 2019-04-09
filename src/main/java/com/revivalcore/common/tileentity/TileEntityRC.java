package com.revivalcore.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public abstract class TileEntityRC extends TileEntity implements IInventory
{
    /**
     * @return instance of global variable for inventory
     */
    public abstract NonNullList<ItemStack> getInventory();
    
    public abstract void setInventory(NonNullList<ItemStack> inv);

    @Override
    public void clear()
    {
        this.getInventory().clear();
    }

    @Override
    public boolean isEmpty()
    {
        for(ItemStack stack : this.getInventory())
        {
            if(!stack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return index >= 0 && index < this.getSizeInventory() ? this.getInventory().get(index) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack stack = ItemStackHelper.getAndSplit(this.getInventory(), index, count);
        if(!stack.isEmpty())
        {
            this.markDirty();
        }
        return stack;
    }

    /**
     *  Called when itemstack is changed inside inventory (decrStackSize, setInventorySlotContents)
     */
    @Override
    public void markDirty()
    {
        super.markDirty();

        if(this instanceof ICraftSystem)
        {
            ((ICraftSystem)this).slotChanged(this);
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        if(this.getStackInSlot(index).isEmpty())
        {
            return ItemStack.EMPTY;
        }

        else
        {
            this.getInventory().set(index, ItemStack.EMPTY);
            return this.getStackInSlot(index);
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.getInventory().set(index, stack);
        if(!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }
    
    // same as setInvetorySlotContents but without the markDirty() call to prevent infinite loops
    public void addItemStackToInventory(int slotID, ItemStack stack)
    {
    	this.getInventory().set(slotID, stack);
    	if(stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
    	{
    		stack.setCount(this.getInventoryStackLimit());
    	}
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    // No idea what these 2 methods are supposed to do
    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean hasCustomName()
    {
        return true;
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
    	super.writeToNBT(compound);
    	ItemStackHelper.saveAllItems(compound, this.getInventory());
    	
    	if(this instanceof IProcessCraftSystem)
    	{
    		IProcessCraftSystem craftSystem = (IProcessCraftSystem)this;
    		compound.setBoolean("isProcessing", craftSystem.isProcessing());
    		compound.setByte("processTime", craftSystem.getProcessTimer());
    		//TODO: write recipe to NBT
    	}
    	
    	return compound;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
    	super.readFromNBT(compound);
    	this.setInventory(NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY));
    	ItemStackHelper.loadAllItems(compound, getInventory());
    	
    	if(this instanceof IProcessCraftSystem)
    	{
    		IProcessCraftSystem cs = (IProcessCraftSystem)this;
    		cs.setProcessing(compound.hasKey("isProcessing") ? compound.getBoolean("isProcessing") : false);
    		cs.setProcessTimer(compound.hasKey("processTimer") ? cs.getProcessTimer() : 0);
    		//TODO: read recipe from NBT
    	}
    }
}