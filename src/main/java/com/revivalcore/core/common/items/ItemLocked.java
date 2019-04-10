package com.revivalcore.core.common.items;

import com.revivalcore.util.RCTeam;
import com.revivalcore.util.helper.PlayerHelper;
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
        this.UUIDS.add(RCTeam.JOSIA50);
        this.UUIDS.add(RCTeam.TOMA);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (isSelected) {
                for(String s : UUIDS.toArray(new String[0])) {
                    if(!(s == player.getUniqueID().toString())) {
                        stack.setCount(0);
                        PlayerHelper.sendMessage(player, "[RCore] You can't use this item!", false);
                    }
                }
            }
        }
    }
}
