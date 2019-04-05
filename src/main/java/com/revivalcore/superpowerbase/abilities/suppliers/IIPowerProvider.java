package com.revivalcore.superpowerbase.abilities.suppliers;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.PowerBase.AbilityMap;
import com.revivalcore.superpowerbase.abilities.PowerBase.EnumAbilityContext;

import net.minecraft.entity.EntityLivingBase;

public interface IIPowerProvider {

    PowerBase.AbilityMap addDefaultAbilities(EntityLivingBase entity, PowerBase.AbilityMap abilities, PowerBase.EnumAbilityContext context);

}
