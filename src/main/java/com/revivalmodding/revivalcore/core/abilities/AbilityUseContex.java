package com.revivalmodding.revivalcore.core.abilities;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Wraps EntityPlayer and Ability into one Object for better interaction
 *
 * Created by Toma, 8.1.2020
 */
public class AbilityUseContex {

    private Ability ability;
    private EntityPlayer player;

    private AbilityUseContex(Ability ability, EntityPlayer player) {
        this.ability = ability;
        this.player = player;
    }

    public Ability getAbility() {
        return ability;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public static AbilityUseContex newContex(Ability ability, EntityPlayer player) {
        return new AbilityUseContex(ability, player);
    }
}
