package com.revivalmodding.revivalcore.core.common.suits;

import com.revivalmodding.revivalcore.core.common.items.CoreItems;
import net.minecraft.entity.player.EntityPlayer;

public class SuitDebug extends AbstractSuit
{
	public SuitDebug()
	{
		super("debug");
	}
	
	@Override
	public ItemSuit getHelmet()
	{
		return CoreItems.suit_head;
	}
	
	@Override
	public ItemSuit getChest()
	{
		return CoreItems.suit_body;
	}
	
	@Override
	public ItemSuit getLeggings() 
	{
		return CoreItems.suit_legs;
	}
	
	@Override
	public ItemSuit getBoots()
	{
		return CoreItems.suit_boots;
	}
	
	@Override
	public void handleEffects(EntityPlayer player)
	{
		if(!isSuitComplete(player)) {
			return;
		}
	}
}
