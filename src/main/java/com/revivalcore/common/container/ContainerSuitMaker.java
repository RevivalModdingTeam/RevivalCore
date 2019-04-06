package com.revivalcore.common.container;

import com.revivalcore.common.tileentity.TileEntitySuitMaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
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
		// Suit maker slots
		for(int x = 0; x < 4; ++x)
		{
			this.addSlotToContainer(new Slot(suitMaker, 1+x, 8 + 18 * x, 6));
		}
		for(int x = 0; x < 4; ++x)
		{
			this.addSlotToContainer(new Slot(suitMaker, 5+x, 8 + 18 * x, 24));
		}
		for(int x = 0; x < 4; ++x)
		{
			this.addSlotToContainer(new Slot(suitMaker, 9+x, 8 + 18 * x, 42));
		}
		
		// output slot
		this.addSlotToContainer(new Slot(suitMaker, 0, 140, 33)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
		});
		
		//player inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.suitMaker);
	}
}
