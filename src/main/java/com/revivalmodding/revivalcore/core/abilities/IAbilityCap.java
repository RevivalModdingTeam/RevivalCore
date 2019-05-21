package com.revivalmodding.revivalcore.core.abilities;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAbilityCap extends INBTSerializable<NBTTagCompound> {
	
	void setAbilities(AbilityBase[] abilities);
	
	AbilityBase[] getAbilities(EntityPlayer player);
	
	void addAbility(AbilityBase ability, EntityPlayer player);
	
	void removeAbility(AbilityBase ability, EntityPlayer player);
	
	boolean hasAbility(AbilityBase ability, EntityPlayer player);
	
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
		
		private AbilityBase[] abilities = new AbilityBase[0];
		
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
		public NBTTagCompound serializeNBT() {
			// TODO
			return new NBTTagCompound();
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			// TODO
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
}
