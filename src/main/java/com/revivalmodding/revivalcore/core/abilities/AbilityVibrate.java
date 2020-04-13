package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AbilityVibrate extends Ability {

    private static final ResourceLocation ICON = new ResourceLocation(RevivalCore.MODID + ":textures/icons/abilityvibrate.png");
    private final String[] description;

    public AbilityVibrate() {
        super("vibrate");
        // TODO better description
        this.description = new String[] {"This ability is disabled!", "Will be unlocked in some amount of time!"};
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
        ICoreCapability coreCapability = CoreCapabilityImpl.getInstance(player);
        PlayerMetaPowerData cap = coreCapability.getMetaPowerData();
        if (isActive()) {
            cap.setVibratingState(!cap.isVibrating());
            coreCapability.sync();
            toggleAbility();
        }
    }

    @Override
    public boolean canActivateAbility(EntityPlayer player) {
        return CoreCapabilityImpl.getInstance(player).getMetaPowerData().canVibrate();
    }

    @Nullable
    @Override
    public String[] getHoveredDescription() {
        return description;
    }
}
