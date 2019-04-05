package com.revivalcore.superpowerbase.abilities.predicates;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.superpowerbase.abilities.suppliers.AbilityContainerSuperpower;
import com.RevivalCore.superpowerbase.abilities.suppliers.PowerContainer;
import com.RevivalCore.util.handlers.SuperpowerHandler;

import net.minecraft.util.text.TextComponentTranslation;

public class AbilityConditionLevel extends AbilityCondition {

    public AbilityConditionLevel(int level) {
        super((a) -> {
                    PowerContainer container = PowerBase.getAbilityContainer(PowerBase.EnumAbilityContext.SUPERPOWER, a.getEntity());
                    AbilityContainerSuperpower handler = container instanceof AbilityContainerSuperpower ? (AbilityContainerSuperpower) container : null;
                    return SuperpowerHandler.hasSuperpower(a.getEntity()) && SuperpowerHandler.getSuperpower(a.getEntity()).canLevelUp() && handler != null && handler.getLevel() >= level;
                },
                new TextComponentTranslation("lucraftcore.ability.condition.level", level));
    }
}
