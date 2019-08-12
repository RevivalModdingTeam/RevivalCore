package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.events.LevelUpEvent;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketSyncAbilities;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public interface IAbilityCap extends INBTSerializable<NBTTagCompound> {
	
	void setAbilities(List<AbilityBase> abilities);
	
	List<AbilityBase> getAbilities();
	
	void addAbility(AbilityBase ability);
	
	void removeAbility(AbilityBase ability);
	
	boolean hasAbility(AbilityBase ability);
	
	boolean containsAbility(List<AbilityBase> list, AbilityBase ability);
	
	void setUnlockedAbilities(List<AbilityBase> abilityKeys);
	
	void unlockAbility(String key);
	
	void lockAbility(String key);
	
	void lockAbilities();
	
	List<AbilityBase> getUnlockedAbilities();
	
	void setLevel(int level);
	
	void setXP(double xp);
	
	void addXP(double amount);
	
	int getLevel();
	
	double getXP();
	
	void reset(boolean unlocked, boolean active, boolean xp, boolean level);
	
	void resetAll();
	
	void sync(EntityPlayerMP player);
	
	class Storage implements IStorage<IAbilityCap> {
		
		@Override
		public NBTBase writeNBT(Capability<IAbilityCap> capability, IAbilityCap instance, EnumFacing side) {
			return instance.serializeNBT();
		}
		
		@Override
		public void readNBT(Capability<IAbilityCap> capability, IAbilityCap instance, EnumFacing side, NBTBase nbt) {
			instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound)nbt : new NBTTagCompound());
		}
	}
	
	class Impl implements IAbilityCap {
		
		// all curently active abilities
		private List<AbilityBase> abilities = new ArrayList<>();
		// all abilities player can unlock
		private List<AbilityBase> unlockedAbilities = new ArrayList<>();
		private int level;
		private double xp;
		
		@Override
		public void addAbility(AbilityBase ability) {
			if(!this.hasAbility(ability)) {
				abilities.add(ability);
			}
		}
		
		@Override
		public void setAbilities(List<AbilityBase> abilities) {
			this.abilities = abilities;
		}
		
		@Override
		public List<AbilityBase> getAbilities() {
			return abilities;
		}
		
		@Override
		public boolean hasAbility(AbilityBase ability) {
			for(AbilityBase a : this.getAbilities()) {
				if(ability.getName().equalsIgnoreCase(a.getName())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void removeAbility(AbilityBase ability) {
			if(this.hasAbility(ability)) {
				abilities.remove(abilities.indexOf(ability));
			}
		}
		
		@Override
		public List<AbilityBase> getUnlockedAbilities() {
			return unlockedAbilities;
		}
		
		@Override
		public void unlockAbility(String key) {
			if(AbilityBase.getAbilityFromKey(key) != null && !AbilityBase.hasAbility(AbilityBase.getAbilityFromKey(key), unlockedAbilities)) {
				unlockedAbilities.add(AbilityBase.getAbilityFromKey(key));
			} else RevivalCore.logger.error("Couldn't unlock ability from key '{}', ability doesn't exist!", key);
		}
		
		@Override
		public void lockAbility(String key) {
			AbilityBase a = AbilityBase.getAbilityFromKey(key);
			if(a != null) {
				if(AbilityBase.hasAbility(a, unlockedAbilities)) {
					unlockedAbilities.remove(a);
				}
			}
		}
		
		@Override
		public void lockAbilities() {
			this.unlockedAbilities.clear();
		}
		
		@Override
		public void setUnlockedAbilities(List<AbilityBase> abilities) {
			this.unlockedAbilities = abilities;
		}
		
		@Override
		public void setLevel(int level) {
			this.level = level;
		}
		
		@Override
		public void setXP(double xp) {
			this.xp = xp;
		}
		
		@Override
		public int getLevel() {
			return level;
		}
		
		@Override
		public double getXP() {
			return xp;
		}
		
		@Override
		public void addXP(double amount) {
			this.xp += amount;
		}
		
		@Override
		public boolean containsAbility(List<AbilityBase> list, AbilityBase ability) {
			if(list.isEmpty()) {
				return false;
			}
			
			for(AbilityBase ab : list) {
				if(ab != null && ab.getName().equalsIgnoreCase(ability.getName())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void reset(boolean unlocked, boolean active, boolean xp, boolean level) {
			if(unlocked) {
				unlockedAbilities.clear();
			}
			if(active) {
				abilities.clear();
			}
			if(xp) {
				this.xp = 0;
			}
			if(level) {
				this.level = 0;
			}
		}
		
		@Override
		public void resetAll() {
			abilities.clear();
			unlockedAbilities.clear();
			xp = 0;
			level = 0;
		}
		
		@Override
		public void sync(EntityPlayerMP player) {
			if(this.getLevel() > 99) setLevel(99);
			NetworkManager.INSTANCE.sendTo(new PacketSyncAbilities(this.serializeNBT(), player), player);
		}
		
		@Override
		public NBTTagCompound serializeNBT() {
			NBTTagCompound c = new NBTTagCompound();
			NBTTagList active = new NBTTagList();
			NBTTagList unlocked = new NBTTagList();
			
			if(!unlockedAbilities.isEmpty()) {
				for(AbilityBase unl : unlockedAbilities) {
					if(unl != null) {
						unlocked.appendTag(AbilityBase.writeNBT(unl.getName()));
					}
				}
			}
			
			if(!abilities.isEmpty()) {
				for(AbilityBase act : abilities) {
					if(act != null) {
						active.appendTag(AbilityBase.writeNBT(act.getName()));
					}
				}
			}
			
			c.setTag("activeAbilities", active);
			c.setTag("unlockedAbilities", unlocked);
			c.setInteger("capLevel", level);
			c.setDouble("capXP", xp);
			return c;
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			abilities.clear();
			unlockedAbilities.clear();
			if(nbt.hasKey("activeAbilities") && nbt.hasKey("unlockedAbilities")) {
				NBTTagList active = nbt.getTagList("activeAbilities", Constants.NBT.TAG_COMPOUND);
				NBTTagList unlocked = nbt.getTagList("unlockedAbilities", Constants.NBT.TAG_COMPOUND);
				
				for(int a = 0; a < active.tagCount(); a++) {
					abilities.add(AbilityBase.getAbilityFromKey(active.getCompoundTagAt(a).getString("abilityID")));
				}
				
				for(int u = 0; u < unlocked.tagCount(); u++) {
					unlockedAbilities.add(AbilityBase.getAbilityFromKey(unlocked.getCompoundTagAt(u).getString("abilityID")));
				}
			}
			else {
				RevivalCore.logger.error("Couldn't load ability data from NBT");
			}
			level = nbt.getInteger("capLevel");
			xp = nbt.getDouble("capXP");
		}
		
		public static IAbilityCap get(EntityPlayer player) {
			if(player.hasCapability(IAbilityCap.Provider.ABILITIES, null)) {
				return player.getCapability(IAbilityCap.Provider.ABILITIES, null);
			}
			return null;
		}
		
		public static int getRequiredXPForNewLevel(IAbilityCap cap) {
			return cap.getLevel() == 99 ? Integer.MAX_VALUE : 100 + (cap.getLevel()+1)*25;
		}
	}
	
	class Provider implements ICapabilitySerializable<NBTTagCompound> {
		
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
	
	@EventBusSubscriber
	class Events {

		@SubscribeEvent
		public static void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
			if(e.player instanceof EntityPlayerMP) {
				IAbilityCap.Impl.get((EntityPlayerMP)e.player).sync((EntityPlayerMP)e.player);
			}
		}
		
		@SubscribeEvent
		public static void changedDimension(PlayerEvent.PlayerChangedDimensionEvent e) {
			if(e.player instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP)e.player;
				IAbilityCap.Impl.get(player).sync(player);
			}
		}
		
		@SubscribeEvent
		public static void loggedIn(PlayerEvent.PlayerLoggedInEvent e) {
			if(e.player instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP)e.player;
				IAbilityCap.Impl.get(player).sync(player);
			}
		}
		
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
			IAbilityCap cap = IAbilityCap.Impl.get(e.player);
			if(cap != null) {
				if(!cap.getAbilities().isEmpty()) {
					for(AbilityBase activeAbility : cap.getAbilities()) {
						if(activeAbility != null) {
							activeAbility.update(e.player);
						}
					}
				}
				
				if(cap.getXP() >= Impl.getRequiredXPForNewLevel(cap)) {
					cap.setXP(0);
					cap.setLevel(cap.getLevel() + 1);
					MinecraftForge.EVENT_BUS.post(new LevelUpEvent(e.player, cap));
					PlayerHelper.sendMessage(e.player, "You are now level " + cap.getLevel(), true);
				}
			}
		}
		
		@SubscribeEvent
		public static void attachAbility(AttachCapabilitiesEvent<Entity> e) {
			if(e.getObject() instanceof EntityPlayer) {
				e.addCapability(new ResourceLocation(RevivalCore.MODID, "abilityCap"), new IAbilityCap.Provider());
			}
		}
	}
}
