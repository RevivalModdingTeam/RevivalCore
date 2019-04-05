package com.revivalcore.superpowerbase.abilities.suppliers;

import java.util.function.Function;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.PowerBase.EnumAbilityContext;

import net.minecraft.entity.EntityLivingBase;

public class PowerSupplier {

    public Function<EntityLivingBase, IIPowerProvider> providerSupplier;
    public PowerSupplier.AbilityContainerFactory containerFactory;

    public PowerSupplier(Function<EntityLivingBase, IIPowerProvider> providerSupplier, PowerSupplier.AbilityContainerFactory containerFactory) {
        this.providerSupplier = providerSupplier;
        this.containerFactory = containerFactory;
    }

    public interface AbilityContainerFactory {

        PowerContainer create(EntityLivingBase player, PowerBase.EnumAbilityContext context);

    }

}
