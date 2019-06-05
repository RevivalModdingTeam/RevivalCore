package com.revivalmodding.revivalcore.core.common.suits.donators;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemDonatorSuit extends ItemSuit implements IDonatorSuit {

    private final String donator;
    private final double amount;
    public static List<String> allowed = new ArrayList<>();

    public ItemDonatorSuit(String name, ArmorMaterial mat, int index, EntityEquipmentSlot slot, String donatorName, String uuid, double amount) {
        super(name, mat, index, slot);
        this.donator = donatorName;
        this.amount = amount;
        this.allowed.add(uuid); this.allowed.addAll(ModHelper.teamMembers);
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
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!this.allowed.contains(player.getUniqueID().toString())) {
                stack.setCount(0);
                PlayerHelper.sendMessage(player, "[Suit] This suit doesn't belong to you!", true);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        IDonatorSuit suit = (IDonatorSuit) this;
        tooltip.add("Donator Suit");
        tooltip.add("Thanks " + suit.getDonatorName() + " for donating " + suit.getDonatedAmount() + "$");
    }
}
