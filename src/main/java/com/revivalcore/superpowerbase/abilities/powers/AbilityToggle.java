package com.revivalcore.superpowerbase.abilities.powers;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.suppliers.EnumSync;
import com.RevivalCore.util.triggers.RCriteriaTriggers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class AbilityToggle extends PowerBase {

    public AbilityToggle(EntityLivingBase entity) {
        super(entity);
    }

    @Override
    public void onUpdate() {
        if (isUnlocked()) {
            if (isEnabled()) {
                if (ticks == 0)
                    firstTick();
                ticks++;
                updateTick();

                if (hasCooldown()) {
                    if (getCooldown() >= getMaxCooldown())
                        setEnabled(false);
                    else
                        setCooldown(getCooldown() + 1);
                }
            } else {
                if (ticks != 0) {
                    lastTick();
                    ticks = 0;
                }

                if (hasCooldown()) {
                    if (getCooldown() > 0)
                        this.setCooldown(getCooldown() - 1);
                }
            }
        } else if (ticks != 0) {
            lastTick();
            ticks = 0;
        }

        if (this.dataManager.sync != null) {
            this.sync = this.sync.add(this.dataManager.sync);
            this.dataManager.sync = EnumSync.NONE;
        }
    }

    @Override
    public void onKeyPressed() {
        if (this.isUnlocked()) {
            this.action();
            for (PowerBase ability : getAbilities(entity).stream().filter(ability -> ability.getParentAbility() == this)
                    .toArray(PowerBase[]::new)) {
                ability.onKeyPressed();
            }
            if (entity instanceof EntityPlayerMP) {
                RCriteriaTriggers.EXECUTE_ABILITY.trigger((EntityPlayerMP) entity, this.getPowerEntries());
            }
        }
    }

    @Override
    public void onKeyReleased() {

    }

    @Override
    public AbilityType getAbilityType() {
        return AbilityType.TOGGLE;
    }

    @Override
    public abstract void updateTick();

    @Override
    public boolean action() {
        this.setEnabled(!isEnabled());
        return true;
    }

}
