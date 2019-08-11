package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
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
        IMetaCap cap = CapabilityMeta.get(player);
        if (isActive()) {
            cap.setVibrating(!cap.isVibrating());
            cap.sync();
            toggleAbility();
        }
    }
}
