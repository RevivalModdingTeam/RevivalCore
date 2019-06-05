package com.revivalmodding.revivalcore.core.common.suits;

import java.util.List;

import com.revivalmodding.revivalcore.core.common.suits.donators.IDonatorSuit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSuit extends ItemArmor
{
	public ItemSuit(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot)
	{
		super(material, index, slot);
		setTranslationKey(name);
		setRegistryName(name);
	}
}
