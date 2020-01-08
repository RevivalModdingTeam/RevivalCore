package com.revivalmodding.revivalcore.core.capability.data;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.common.events.LevelUpEvent;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
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

    public void lockAbility(Ability ability) {
        unlockedAbilities.remove(ability);
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

    public boolean hasActiveAbility(Ability ability) {
        for(Ability a : this.getActiveAbilities()) {
            if(a != null && a.getName().equalsIgnoreCase(ability.getName())) {
                return true;
            }
        }
        return false;
    }

    public void toggleAbility(EntityPlayer player, int key) {
        if(this.hasActiveAbilityForKey(key)) {
            this.getActiveAbilities()[key].onUse(player);
        }
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

    public int getActiveAbilityCount() {
        int c = 0;
        for(Ability ability : this.getActiveAbilities()) {
            if(ability != null) c++;
        }
        return c;
    }

    public Ability[] getNonnullActiveAbilities() {
        Ability[] arr = new Ability[3];
        int index = 0;
        for (Ability ability : this.getActiveAbilities()) {
            if(ability != null) {
                arr[index] = ability;
                index++;
            }
        }
        return arr;
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

    public int getRequiredXPForLevel() {
        return this.getLevel() == 99 ? Integer.MAX_VALUE : 100 + (this.getLevel()+1)*25;
    }

    public void addXP(float xp) {
        this.xp += xp;
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
            Ability ability = Ability.fromString(unlocked.getStringTagAt(i));
            if(ability == null) continue;
            unlockedAbilities.add(ability);
        }
        NBTTagList active = tag.getTagList("active", Constants.NBT.TAG_STRING);
        this.activeAbilities = new Ability[3];
        for(int i = 0; i < active.tagCount(); i++) {
            Ability ability = Ability.fromString(active.getStringTagAt(i));
            if(ability == null) continue;
            activeAbilities[i] = ability;
        }
    }

    public void clear(boolean resetLevel) {
        this.lockAbilities();
        this.setActiveAbilities(new Ability[3]);
        if(resetLevel) {
            this.setLevel(0);
            this.setXP(0);
        }
    }

    public void update(EntityPlayer player) {
        for(Ability ability : this.getActiveAbilities()) {
            if(ability == null) continue;
            ability.onTick(player);
        }
        if(this.getXP() >= this.getRequiredXPForLevel() && this.getLevel() < 99) {
            this.setXP(0);
            this.setLevel(this.getLevel() + 1);
            MinecraftForge.EVENT_BUS.post(new LevelUpEvent(player, this.getLevel()));
            if(!player.world.isRemote) PlayerHelper.sendMessage(player, TextFormatting.BOLD.toString() + TextFormatting.GREEN + "LEVEL UP! [" + this.getLevel() + "]", true);
        }
    }
}
