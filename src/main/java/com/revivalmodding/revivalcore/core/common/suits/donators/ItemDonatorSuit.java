package com.revivalmodding.revivalcore.core.common.suits.donators;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class ItemDonatorSuit extends ItemSuit {

    private final String donator;
    private final double amount;
    private final ISimpleSuit simpleSuit;

    public ItemDonatorSuit(String name, EntityEquipmentSlot slot, String donatorName, double amount, ISimpleSuit simpleSuit) {
        super(name, slot);
        this.donator = donatorName;
        this.amount = amount;
        this.simpleSuit = simpleSuit;
        this.setCreativeTab(RevivalCore.coretab);
    }

    @Override
    public ResourceLocation get3DTexture() {
        return simpleSuit.getDonatorSuitTexture();
    }

    public String getDonatorName() {
        return donator;
    }

    public double getDonatedAmount() {
        return amount;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Donator Suit");
        tooltip.add("Thanks " + donator + " for donating " + amount + "$");
    }
}
