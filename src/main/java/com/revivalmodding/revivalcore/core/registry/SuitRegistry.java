package com.revivalmodding.revivalcore.core.registry;

import java.util.HashMap;
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
		if(!isSuitValid(suit)) {
			RevivalCore.logger.error("Attempted to add suit {} with name {}, but suit is invalid, skipping...", suit, suit.getSuitName());
			return;
		}
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
	
	private static boolean isSuitValid(AbstractSuit suit)
	{
		boolean b = true;
		for(int i = 0; i < suit.getSet().length; ++i) {
			ItemSuit suitItem = suit.getSet()[i];
			int eqSlotIndex = EntityEquipmentSlot.values().length - i - 1;
			if(!suitItem.getEquipmentSlot().equals(EntityEquipmentSlot.values()[eqSlotIndex])) {
				b = !b;
				break;
			}
		}
		return b;
	}
}
