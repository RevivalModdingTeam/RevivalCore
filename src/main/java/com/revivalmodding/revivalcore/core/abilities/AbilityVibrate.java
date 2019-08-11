package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.util.handlers.client.ClientEventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class AbilityVibrate extends AbilityBase {

    private static final ResourceLocation ICON = new ResourceLocation("");

    public AbilityVibrate() {
        super("vibrate");
    }

    @Nonnull
    @Override
    public ResourceLocation getIcon() {
        return ICON;
    }

    @Override
    public int getAbilityPrice() {
        return 3;
    }

    @Override
    public String getFullName() {
        return "Ability Vibrate";
    }

    @Override
    public void update(EntityPlayer player) {
        if(isActive()) {
            ClientEventHandler.vibrating = !ClientEventHandler.vibrating;
            toggleAbility();
        }
    }
}
