package com.revivalcore.superpowerbase.abilities.predicates;

import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextComponentTranslation;


public class AbilityConditionPotionWeakness extends AbilityCondition {

    public Potion potion;

    public AbilityConditionPotionWeakness(Potion potion) {
        super(ability -> !ability.getEntity().isPotionActive(potion), new TextComponentTranslation("lucraftcore.ability.condition.potion_weakness", new TextComponentTranslation(potion.getName())));
        this.potion = potion;
    }
}

