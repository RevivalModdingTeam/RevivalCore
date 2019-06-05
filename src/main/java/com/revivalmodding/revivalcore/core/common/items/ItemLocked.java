package com.revivalmodding.revivalcore.core.common.items;

import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemLocked extends Item {

    public static final List<String> UUIDS = new ArrayList<String>();

    public ItemLocked() {
        UUIDS.addAll(ModHelper.teamMembers);
    }

    public static void addAllowedUser(String uuid) {
        UUIDS.add(uuid);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (isSelected) {
                if (!isUserAllowed(player)) {
                    stack.setCount(0);
                    PlayerHelper.sendMessage(player, "[RevivalCore] You can't use this item!", true);
                }
            }
        }
    }

    public static boolean isUserAllowed(EntityPlayer player) {
        if(UUIDS.contains(player.getUniqueID().toString())) {
            return true;
        }
        return false;
    }

    public static void setAllowedUUID(String uuid) {
        UUIDS.add(uuid);
    }

    public static void setAllowedUUID(List l) {
        UUIDS.addAll(l);
    }
}
