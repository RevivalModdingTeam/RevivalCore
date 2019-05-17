package com.revivalmodding.revivalcore.core.common.items;

import java.util.ArrayList;
import java.util.List;

import com.revivalmodding.revivalcore.util.RCTeam;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLocked extends Item {

    public static final List<String> UUIDS = new ArrayList<String>();

    public ItemLocked() {
        this.UUIDS.add(RCTeam.JOSIA50);
        this.UUIDS.add(RCTeam.TOMA);
        this.UUIDS.add(RCTeam.TUD);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (isSelected) {
                for(String s : UUIDS) {
                    if(!(player.getUniqueID().toString().equals(s))) {
                        stack.setCount(0);
                        PlayerHelper.sendMessage(player, "[RevivalCore] You can't use this item!", false);
                    }
                }
            }
        }
    }
}
