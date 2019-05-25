package com.revivalmodding.revivalcore.core.abilities;

import java.util.ArrayList;
import java.util.List;

import com.revivalmodding.revivalcore.RevivalCore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

//TODO: attach to player when it's ready
public interface IAbilityCap extends INBTSerializable<NBTTagCompound> {
	
	void setAbilities(AbilityBase[] abilities);
	
	AbilityBase[] getAbilities(EntityPlayer player);
	
	void addAbility(AbilityBase ability, EntityPlayer player);
	
	void removeAbility(AbilityBase ability, EntityPlayer player);
	
	boolean hasAbility(AbilityBase ability, EntityPlayer player);
	
	void setUnlockedAbilities(String[] abilityKeys);
	
	void unlockAbility(String key);
	
	void lockAbilities();
	
	AbilityBase[] getUnlockedAbilities(String[] keys);
	
	String[] getUnlockedAbilityKeys();
	
	public class Storage implements IStorage<IAbilityCap> {
		
		@Override
		public NBTBase writeNBT(Capability<IAbilityCap> capability, IAbilityCap instance, EnumFacing side) {
			return instance.serializeNBT();
		}
		
		@Override
		public void readNBT(Capability<IAbilityCap> capability, IAbilityCap instance, EnumFacing side, NBTBase nbt) {
			instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
		}
	}
	
	public class Impl implements IAbilityCap {
		
		// all curently active abilities
		private AbilityBase[] abilities = new AbilityBase[0];
		
		// all abilities player can unlock
		private String[] unlockedAbilities = new String[0];
		
		@Override
		public void addAbility(AbilityBase ability, EntityPlayer player) {
			if(!this.hasAbility(ability, player)) {
				AbilityBase[] abilities = this.getAbilities(player);
				abilities[abilities.length] = ability;
				this.setAbilities(abilities);
			}
		}
		
		@Override
		public void setAbilities(AbilityBase[] abilities) {
			this.abilities = abilities;
		}
		
		@Override
		public AbilityBase[] getAbilities(EntityPlayer player) {
			return abilities;
		}
		
		@Override
		public boolean hasAbility(AbilityBase ability, EntityPlayer player) {
			for(AbilityBase a : this.getAbilities(player)) {
				if(ability.getName().equalsIgnoreCase(a.getName())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void removeAbility(AbilityBase ability, EntityPlayer player) {
			if(this.hasAbility(ability, player)) {
				ArrayList<AbilityBase> list = new ArrayList<>();
				for(AbilityBase a : this.getAbilities(player)) {
					if(!ability.getName().equalsIgnoreCase(a.getName())) {
						list.add(a);
					}
				}
				
				this.setAbilities(list.toArray(new AbilityBase[0]));
			}
		}
		
		@Override
		public AbilityBase[] getUnlockedAbilities(String[] keys) {
			ArrayList<AbilityBase> list = new ArrayList<>();
			for(String s : keys) {
				AbilityBase base = AbilityBase.getAbilityFromKey(s);
				if(base != null) {
					list.add(base);
				}
			}
			return list.toArray(new AbilityBase[0]);
		}
		
		@Override
		public void unlockAbility(String key) {
			if(AbilityBase.getAbilityFromKey(key) != null) {
				unlockedAbilities[unlockedAbilities.length] = key;
			} else RevivalCore.logger.error("Couldn't unlock ability from key '{}', ability doesn't exist!", key);
		}
		
		@Override
		public String[] getUnlockedAbilityKeys() {
			return unlockedAbilities;
		}
		
		@Override
		public void lockAbilities() {
			this.unlockedAbilities = new String[0];
		}
		
		@Override
		public void setUnlockedAbilities(String[] abilityKeys) {
			this.unlockedAbilities = abilityKeys;
		}
		
		@Override
		public NBTTagCompound serializeNBT() {
			NBTTagCompound c = new NBTTagCompound();
			NBTTagList active = new NBTTagList();
			NBTTagList unlocked = new NBTTagList();
			
			for(String unl : unlockedAbilities) {
				unlocked.appendTag(AbilityBase.writeNBT(unl));
			}
			
			for(AbilityBase act : abilities) {
				active.appendTag(AbilityBase.writeNBT(act.getName()));
			}
			
			c.setTag("activeAbilities", active);
			c.setTag("unlockedAbilities", unlocked);
			return c;
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			if(nbt.hasKey("activeAbilities") && nbt.hasKey("unlockedAbilities")) {
				NBTTagList active = nbt.getTagList("activeAbilities", Constants.NBT.TAG_COMPOUND);
				NBTTagList unlocked = nbt.getTagList("unlockedAbilities", Constants.NBT.TAG_COMPOUND);
				abilities = new AbilityBase[0];
				unlockedAbilities = new String[0];
				
				for(int a = 0; a < active.tagCount(); a++) {
					abilities[abilities.length] = AbilityBase.getAbilityFromKey(active.getCompoundTagAt(a).getString("name"));
				}
				
				for(int u = 0; u < unlocked.tagCount(); u++) {
					unlockedAbilities[unlockedAbilities.length] = unlocked.getCompoundTagAt(u).getString("name");
				}
			}
			else {
				abilities = new AbilityBase[0];
				unlockedAbilities = new String[0];
				RevivalCore.logger.error("Couldn't load ability data from NBT");
			}
		}
		
		public static IAbilityCap get(EntityPlayer player) {
			if(player.hasCapability(IAbilityCap.Provider.ABILITIES, null)) {
				return player.getCapability(IAbilityCap.Provider.ABILITIES, null);
			}
			return null;
		}
	}
	
	public class Provider implements ICapabilitySerializable<NBTTagCompound> {
		
		@CapabilityInject(IAbilityCap.class)
		public static final Capability<IAbilityCap> ABILITIES = null;
		
		private IAbilityCap instance = ABILITIES.getDefaultInstance();
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == ABILITIES;
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == ABILITIES ? ABILITIES.<T>cast(this.instance) : null;
		}
		
		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound)ABILITIES.getStorage().writeNBT(ABILITIES, this.instance, null);
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			ABILITIES.getStorage().readNBT(ABILITIES, this.instance, null, nbt);
		}
	}
	
	public static class Events {
		
	}
}
