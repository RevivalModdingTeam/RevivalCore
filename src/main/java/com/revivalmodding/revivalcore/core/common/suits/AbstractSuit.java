package com.revivalmodding.revivalcore.core.common.suits;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class AbstractSuit
{
	private final String name;
	
	public AbstractSuit(String name)
	{
		this.name = name;
	}
	
	public abstract ItemSuit getHelmet();
	
	public abstract ItemSuit getChest();
	
	public abstract ItemSuit getLeggings();
	
	public abstract ItemSuit getBoots();
	
	public abstract void handleEffects(EntityPlayer player);
	
	public final void tick(EntityPlayer player)
	{
		this.handleEffects(player);
	}
	
	public final ItemSuit[] getSet()
	{
		return new ItemSuit[] {this.getHelmet(), this.getChest(), this.getLeggings(), this.getBoots()};
	}
	
	public final boolean isSuitComplete(EntityPlayer player)
	{
		return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this.getHelmet() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this.getChest() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == this.getLeggings() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == this.getBoots();
	}
	
	/**
	 * <li>x - Red
	 * <li>y - Green
	 * <li>z - Blue
	 * @return RGB for trail
	 */
	@Nullable
	public Vector3f getTrailRGB()
	{
		return null;
	}
}
