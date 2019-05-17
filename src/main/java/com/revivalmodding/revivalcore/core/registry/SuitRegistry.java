package com.revivalmodding.revivalcore.core.registry;

import java.util.HashSet;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;

import net.minecraft.inventory.EntityEquipmentSlot;

public class SuitRegistry
{
	public static final HashSet<AbstractSuit> SUITS = new HashSet<AbstractSuit>();
	
	public static void putEntry(AbstractSuit suit)
	{
		SUITS.add(suit);
	}
	
	public static AbstractSuit getSuitByName(String name)
	{
		if(name == null || name.isEmpty())
			return null;
		
		for(AbstractSuit suit : SUITS) {
			if(suit.getSuitName().equalsIgnoreCase(name)) {
				return suit;
			}
		}
		
		return null;
	}
}
