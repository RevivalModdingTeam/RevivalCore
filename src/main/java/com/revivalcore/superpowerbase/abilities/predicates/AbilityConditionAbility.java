package com.revivalcore.superpowerbase.abilities.predicates;

import com.revivalcore.superpowerbase.abilities.PowerBase;

import net.minecraft.util.text.TextComponentTranslation;

public class AbilityConditionAbility extends AbilityCondition {

    public AbilityConditionAbility(PowerBase ability) {
        super((a) -> (ability.isUnlocked() && ability.isEnabled()), new TextComponentTranslation("lucraftcore.ability.condition.ability", ability.getDisplayName()));
    }
}
