package com.revivalmodding.revivalcore.core.registry;

import java.util.HashMap;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;

import net.minecraft.inventory.EntityEquipmentSlot;

public class SuitRegistry 
{
	public static final HashMap<String, AbstractSuit> SUITS = new HashMap<String, AbstractSuit>();
	
	public static void putEntry(String name, AbstractSuit suit)
	{
		if(!canAddSuit(name, suit)) {
			RevivalCore.logger.error("Attempted to add suit {} with name {}, but suit is invalid, skipping...", suit, name);
			return;
		}
		SUITS.put(name, suit);
	}
	
	public static AbstractSuit getSuitByName(String name)
	{
		if(SUITS.containsKey(name)) {
			return SUITS.get(name);
		}
		return null;
	}
	
	private static boolean canAddSuit(String name, AbstractSuit suit)
	{
		return !SUITS.containsKey(name) && isSuitValid(suit);
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
