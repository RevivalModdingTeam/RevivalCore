package com.revivalcore.superpowerbase.abilities.predicates;

import com.RevivalCore.util.items.OpenableArmor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextComponentTranslation;

public class AbilityConditionOpenArmor extends AbilityCondition {

    public AbilityConditionOpenArmor(EntityEquipmentSlot slot) {
        super((a) -> !a.getEntity().getItemStackFromSlot(slot).isEmpty() && a.getEntity().getItemStackFromSlot(slot).getItem() instanceof OpenableArmor.IOpenableArmor && ((OpenableArmor.IOpenableArmor) a.getEntity().getItemStackFromSlot(slot).getItem()).isArmorOpen(a.getEntity(), a.getEntity().getItemStackFromSlot(slot)), new TextComponentTranslation("lucraftcore.ability.condition.open_armor", new TextComponentTranslation("lucraftcore.ability.condition.open_armor." + slot.getName().toLowerCase())));
    }
}

