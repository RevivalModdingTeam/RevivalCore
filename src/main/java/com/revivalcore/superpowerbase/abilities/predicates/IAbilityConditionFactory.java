package com.revivalcore.superpowerbase.abilities.predicates;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.google.gson.JsonObject;


import java.util.List;

public interface IAbilityConditionFactory {

    AbilityCondition parse(JsonObject json, PowerBase ability, PowerBase.AbilityMap abilities);

}
