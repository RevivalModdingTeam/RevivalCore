package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.Ability;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class PlayerAbilityData {

    private List<Ability> unlockedAbilities = new ArrayList<>();
    private Ability[] activeAbilities = new Ability[3];
    private int playerLevel;
    private float xp;

    // Ability locking/unlocking =======================================================================================

    public void unlockAbility(Ability abilityBase) {
        if(unlockedAbilities.contains(abilityBase)) {
            RevivalCore.logger.error("Attempted to unlock already unlocked ability!");
            return;
        }
        unlockedAbilities.add(abilityBase);
    }

    public void setUnlockedAbilities(List<Ability> list) {
        this.unlockedAbilities = list;
    }

    public void lockAbilities() {
        this.setUnlockedAbilities(new ArrayList<>());
    }

    public List<Ability> getUnlockedAbilities() {
        return unlockedAbilities;
    }

    // Active abilities manager ========================================================================================

    public Ability[] getActiveAbilities() {
        return activeAbilities;
    }

    public void setActiveAbilities(Ability[] abilities) {
        if(abilities.length > 3) {
            RevivalCore.logger.error("Cannot assing ability array of length {}, maximum is 3!", abilities.length);
            abilities = new Ability[3];
        }
        this.activeAbilities = abilities;
    }

    public boolean hasActiveAbilityForKey(int key) {
        return key >= 0 && key < activeAbilities.length && activeAbilities[key] != null;
    }

    public boolean activateAbility(Ability ability) {
        for(int i = 0; i < activeAbilities.length; i++) {
            if(!hasActiveAbilityForKey(i)) {
                activeAbilities[i] = ability;
                return true;
            }
        }
        return false;
    }

    public boolean deactivateAbility(Ability ability) {
        for(int i = 0; i < this.activeAbilities.length; i++) {
            Ability a = this.activeAbilities[i];
            if(a.getName().equals(ability.getName())) {
                this.activeAbilities[i] = null;
                return true;
            }
        }
        return false;
    }

    // Level and XP manager ============================================================================================

    public void setLevel(int level) {
        this.playerLevel = level;
    }

    public void setXP(float xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return playerLevel;
    }

    public float getXP() {
        return xp;
    }

    // =================================================================================================================

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("playerLevel", playerLevel);
        tag.setFloat("xp", xp);
        NBTTagList unlocked = new NBTTagList();
        for(int i = 0; i < unlockedAbilities.size(); i++) {
            Ability ability = this.unlockedAbilities.get(i);
            NBTTagString unlockedAbilityRegistryName = new NBTTagString(ability.getName());
            unlocked.appendTag(unlockedAbilityRegistryName);
        }
        NBTTagList active = new NBTTagList();
        for(int i = 0; i < activeAbilities.length; i++) {
            Ability ability = activeAbilities[i];
            if(ability == null) continue;
            unlocked.appendTag(new NBTTagString(ability.getName()));
        }
        tag.setTag("unlocked", unlocked);
        tag.setTag("active", active);
        nbt.setTag("playerAbilityData", tag);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagCompound tag = nbt.hasKey("playerAbilityData") ? nbt.getCompoundTag("playerAbilityData") : new NBTTagCompound();
        this.playerLevel = tag.getInteger("playerLevel");
        this.xp = tag.getFloat("xp");
        NBTTagList unlocked = tag.getTagList("unlocked", Constants.NBT.TAG_STRING);
        this.unlockedAbilities = new ArrayList<>();
        for(int i = 0; i < unlocked.tagCount(); i++) {
            Ability ability = Ability.getAbilityFromKey(unlocked.getStringTagAt(i));
            if(ability == null) continue;
            unlockedAbilities.add(ability);
        }
        NBTTagList active = tag.getTagList("active", Constants.NBT.TAG_STRING);
        this.activeAbilities = new Ability[3];
        for(int i = 0; i < active.tagCount(); i++) {
            Ability ability = Ability.getAbilityFromKey(active.getStringTagAt(i));
            if(ability == null) continue;
            activeAbilities[i] = ability;
        }
    }
}
