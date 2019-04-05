package com.revivalcore.superpowerbase.abilities.predicates;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;

public class AbilityConditionHeldItem extends AbilityCondition {

    public AbilityConditionHeldItem(Item item, EnumHand hand) {
        super((a) -> a.getEntity().getHeldItem(hand).getItem() == item, new TextComponentTranslation("lucraftcore.ability.condition.held_item", item.getItemStackDisplayName(new ItemStack(item)), new TextComponentTranslation(hand == EnumHand.MAIN_HAND ? "lucraftcore.ability.condition.main_hand" : "lucraftcore.ability.condition.off_hand")));
    }

}
