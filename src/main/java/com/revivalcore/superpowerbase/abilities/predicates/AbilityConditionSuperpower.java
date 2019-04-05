package com.revivalcore.superpowerbase.abilities.predicates;


import com.revivalcore.superpowerbase.Superpowerbase;
import com.RevivalCore.util.handlers.SuperpowerHandler;

import net.minecraft.util.text.TextComponentTranslation;

public class AbilityConditionSuperpower extends AbilityCondition {

    public AbilityConditionSuperpower(Superpowerbase superpower) {
        super((a) -> (SuperpowerHandler.hasSuperpower(a.getEntity(), superpower)), new TextComponentTranslation("lucraftcore.ability.condition.superpower", superpower.getDisplayName()));
    }

}
