package com.revivalmodding.revivalcore.core.common.suits;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

public abstract class ItemSuit extends ItemArmor
{
	public ItemSuit(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot) {
		super(material, index, slot);
		setTranslationKey(name);
		setRegistryName(name);
	}

	public abstract ResourceLocation get3DTexture();

	public abstract ModelBiped get3DModel();
}
