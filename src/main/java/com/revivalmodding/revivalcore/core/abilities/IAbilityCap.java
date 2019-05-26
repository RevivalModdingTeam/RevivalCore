package com.revivalmodding.revivalcore.core.abilities;

import java.util.ArrayList;
import java.util.List;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.network.NetworkManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IAbilityCap extends INBTSerializable<NBTTagCompound> {
	
	void setAbilities(List<AbilityBase> abilities);
	
	List<AbilityBase> getAbilities(EntityPlayer player);
	
	void addAbility(AbilityBase ability, EntityPlayer player);
	
	void removeAbility(AbilityBase ability, EntityPlayer player);
	
	boolean hasAbility(AbilityBase ability, EntityPlayer player);
	
	void setUnlockedAbilities(List<AbilityBase> abilityKeys);
	
	void unlockAbility(String key);
	
	void lockAbilities();
	
	List<AbilityBase> getUnlockedAbilities();
	
	void sync(EntityPlayerMP player);
	
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
		private List<AbilityBase> abilities = new ArrayList<>();
		
		// all abilities player can unlock
		private List<AbilityBase> unlockedAbilities = new ArrayList<>();
		
		@Override
		public void addAbility(AbilityBase ability, EntityPlayer player) {
			if(!this.hasAbility(ability, player)) {
				abilities.add(ability);
			}
		}
		
		@Override
		public void setAbilities(List<AbilityBase> abilities) {
			this.abilities = abilities;
		}
		
		@Override
		public List<AbilityBase> getAbilities(EntityPlayer player) {
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
				abilities.remove(abilities.indexOf(ability));
			}
		}
		
		@Override
		public List<AbilityBase> getUnlockedAbilities() {
			return unlockedAbilities;
		}
		
		@Override
		public void unlockAbility(String key) {
			if(AbilityBase.getAbilityFromKey(key) != null) {
				unlockedAbilities.add(AbilityBase.getAbilityFromKey(key));
			} else RevivalCore.logger.error("Couldn't unlock ability from key '{}', ability doesn't exist!", key);
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
		public void sync(EntityPlayerMP player) {
			NetworkManager.INSTANCE.sendTo(new PacketSync(this.serializeNBT()), player);
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
			return c;
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
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
	
	@EventBusSubscriber
	public static class Events {
		
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
			if(cap != null && !cap.getAbilities(e.player).isEmpty()) {
				for(AbilityBase activeAbility : cap.getAbilities(e.player)) {
					if(activeAbility != null) {
						activeAbility.update(e.player);
					}
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
	
	public class PacketSync implements IMessage {
		
		private NBTTagCompound comp;
		
		public PacketSync() {}
		
		public PacketSync(NBTTagCompound comp) {
			this.comp = comp;
		}
		
		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeTag(buf, comp);
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			comp = ByteBufUtils.readTag(buf);
		}
		
		public static class Handler implements IMessageHandler<PacketSync, IMessage> {
			
			@Override
			public IMessage onMessage(PacketSync message, MessageContext ctx) {
				if(ctx.side.isClient()) {
					Minecraft.getMinecraft().addScheduledTask(() -> sync(message));
				}
				return null;
			}
			
			public static void sync(PacketSync p) {
				IAbilityCap.Impl.get(Minecraft.getMinecraft().player).deserializeNBT(p.comp);
			}
		}
	}
}
