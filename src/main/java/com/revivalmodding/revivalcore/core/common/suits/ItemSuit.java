package com.revivalmodding.revivalcore.core.common.suits;

import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class ItemSuit extends ItemArmor
{
	public static final ResourceLocation EMPTY = new ResourceLocation("");

	public ItemSuit(String name, EntityEquipmentSlot slot) {
		super(ItemRegistry.SUIT_MATERIAL, 0, slot);
		setTranslationKey(name);
		setRegistryName(name);
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return EMPTY.toString();
	}

	@SideOnly(Side.CLIENT)
	public abstract ResourceLocation get3DTexture();
}
