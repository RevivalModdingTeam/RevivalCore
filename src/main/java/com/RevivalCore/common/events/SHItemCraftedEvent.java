package com.RevivalCore.common.events;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

// TODO: Not called yet
public class SHItemCraftedEvent extends Event
{
	private World world;
	private Item item;
	
	public SHItemCraftedEvent(World world, Item craftedItem) 
	{
		this.world = world;
		this.item = craftedItem;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public Item getCraftedItem()
	{
		return item;
	}
}
