package com.revivalcore.common.container;

import com.revivalcore.common.tileentity.TileEntitySuitMaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSuitMaker extends Container
{
	private final InventoryPlayer playerInv;
	private final TileEntitySuitMaker suitMaker;
	
	public ContainerSuitMaker(InventoryPlayer playerInv, TileEntitySuitMaker te) 
	{
		this.playerInv = playerInv;
		this.suitMaker = te;
		
		this.initSlots();
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(index < this.suitMaker.getSizeInventory())
            {
                if(!this.mergeItemStack(itemstack1, this.suitMaker.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.mergeItemStack(itemstack1, 0, this.suitMaker.getSizeInventory(), false))
            {
                return ItemStack.EMPTY;
            }

            if(itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
	}
	
	private void initSlots()
	{
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
}
