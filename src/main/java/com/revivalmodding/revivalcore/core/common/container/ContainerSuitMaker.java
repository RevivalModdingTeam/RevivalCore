package com.revivalmodding.revivalcore.core.common.container;

import com.revivalmodding.revivalcore.core.common.tileentity.TileEntitySuitMaker;
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
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack.copy();
			
			//output
			if(index == 20)
			{
				if(!this.mergeItemStack(stack1, 21, 56, false))
				{
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(stack1, stack);
			}
			
			else if(index >= 0 && index <= 19)
			{
				if(!mergeItemStack(stack1, 21, 56, false))
				{
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(stack1, stack);
			}
			
			else if(index >= 21 && index <= 56)
			{
				if(!mergeItemStack(stack1, 0, 19, false))
				{
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(stack1, stack);
			}
		}
		
		return stack;
	}
	
	private void initSlots()
	{
		// Suit maker slots
		for(int y = 0; y < 4; ++y)
		{
			for(int x = 0; x < 5; ++x)
			{
				this.addSlotToContainer(new Slot(this.suitMaker, 1 + y*5 + x, 8 + 18 * x, 6 + 18 * y));
			}
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
