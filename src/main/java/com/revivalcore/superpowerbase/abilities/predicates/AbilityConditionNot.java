package com.revivalcore.superpowerbase.abilities.predicates;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbilityConditionNot extends AbilityCondition {

    public AbilityConditionNot(AbilityCondition... conditions) {
        super((a) -> {
            for (AbilityCondition c : conditions)
                if (c != null && c.test(a))
                    return false;
            return true;
        }, text(conditions));
    }

    private static ITextComponent text(AbilityCondition... conditions) {
        ITextComponent text = new TextComponentTranslation("lucraftcore.ability.condition.requires_not");
        List<AbilityCondition> list = Arrays.asList(conditions).stream().filter((c) -> c != null).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            AbilityCondition c = list.get(i);
            text.appendText("\n      ").appendSibling(c.displayText.createCopy());
            if (i == conditions.length - 1)
                text.appendText(" ").appendSibling(new TextComponentTranslation("lucraftcore.ability.condition.and"));
        }

        return text;
    }

}

