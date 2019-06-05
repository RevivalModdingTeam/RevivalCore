package com.revivalmodding.revivalcore.core.common.suits.donators;

import java.util.List;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDonatorSuit extends ItemSuit implements IDonatorSuit {
	
	private final String donator;
	private final double amount;
	
	public ItemDonatorSuit(String name, ArmorMaterial mat, int index, EntityEquipmentSlot slot, String donatorName, double amount) {
		super(name, mat, index, slot);
		this.donator = donatorName;
		this.amount = amount;
	}
	
	@Override
	public String getDonatorName() {
		return donator;
	}
	
	@Override
	public double getDonatedAmount() {
		return amount;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		IDonatorSuit suit = (IDonatorSuit)this;
		tooltip.add("Donator Suit");
		tooltip.add("Thanks " + suit.getDonatorName() + " for donating " + suit.getDonatedAmount() + "$");
	}
}
