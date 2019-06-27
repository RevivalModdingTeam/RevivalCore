package com.revivalmodding.revivalcore.core.common.suits;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemSuit extends ItemArmor
{
	public ItemSuit(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot)
	{
		super(material, index, slot);
		setTranslationKey(name);
		setRegistryName(name);
	}
}
