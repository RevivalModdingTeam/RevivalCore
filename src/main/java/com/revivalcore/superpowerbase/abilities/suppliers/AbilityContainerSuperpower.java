package com.revivalcore.superpowerbase.abilities.suppliers;

import com.revivalcore.superpowerbase.Superpowerbase;
import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.superpowerbase.toasts.SuperpowerLevelUpToast;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AbilityContainerSuperpower extends PowerContainer {

    private int xp;
    private int level;
    private Superpowerbase superpower;

    public AbilityContainerSuperpower(EntityLivingBase entity) {
        super(entity, PowerBase.EnumAbilityContext.SUPERPOWER);
    }

    @Override
    public void onUpdate() {
        this.superpower = this.provider == null ? null : this.provider instanceof Superpowerbase ? (Superpowerbase) this.provider : null;
        super.onUpdate();
    }

    public int getLevel() {
        if (superpower != null && superpower.canLevelUp())
            return MathHelper.clamp(level, 1, superpower.getMaxLevel());
        else
            return 0;
    }

    public void setLevel(int level) {
        if (superpower != null && superpower.canLevelUp()) {
            this.level = MathHelper.clamp(level, 1, superpower.getMaxLevel());
            this.sync = this.sync.add(EnumSync.SELF);
        }
    }

    public int getXP() {
        if (superpower != null && superpower.canLevelUp())
            return MathHelper.clamp(xp, 0, superpower.getXPForLevel(getLevel() + 1));
        else
            return 0;
    }

    public void setXP(int xp) {
        if (superpower != null && superpower.canLevelUp()) {
            this.xp = MathHelper.clamp(xp, 0, superpower.getXPForLevel(getLevel() + 1));
            this.sync = this.sync.add(EnumSync.SELF);
        }
    }

    public void addXP(int xp) {
        addXP(xp, true);
    }

    public void addXP(int xp, boolean showMessage) {
        if (xp > 0 && superpower != null && superpower.canLevelUp() && level < superpower.getMaxLevel()) {
            int max = superpower.getXPForLevel(level + 1);
            this.setXP(getXP() + xp);
            int tB = getXP();

            if (getXP() >= max) {
                levelUp();
                addXP(tB - max, false);
            }
        }
    }

    public void levelUp() {
        if (superpower != null && superpower.canLevelUp() && level < superpower.getMaxLevel()) {
            this.setLevel(getLevel() + 1);
            this.setXP(0);
            if (this.entity.world.isRemote)
                sendLevelUpMessage(getLevel());

            this.sync = this.sync.add(EnumSync.SELF);
        }
    }

    @SideOnly(Side.CLIENT)
    public void sendLevelUpMessage(int level) {
        Minecraft.getMinecraft().getToastGui().add(new SuperpowerLevelUpToast());
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("Level", this.level);
        nbt.setInteger("XP", this.xp);
        return nbt;
    }

    @Override
    public NBTTagCompound serializeNBTSync() {
        NBTTagCompound nbt = super.serializeNBTSync();
        nbt.setInteger("Level", this.level);
        nbt.setInteger("XP", this.xp);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        this.level = nbt.getInteger("Level");
        this.xp = nbt.getInteger("XP");
    }

    @Override
    public void deserializeNBTSync(NBTTagCompound nbt) {
        super.deserializeNBTSync(nbt);
        this.level = nbt.getInteger("Level");
        this.xp = nbt.getInteger("XP");
    }
}
