package com.revivalmodding.revivalcore.core.abilities;

import net.minecraft.entity.player.EntityPlayer;

public interface AbilityConsumer {

    void apply(Ability ability, EntityPlayer player);
}
