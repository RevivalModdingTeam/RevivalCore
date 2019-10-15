package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.common.events.AbilityEvent;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDeactivateAbility implements IMessage {
	
	private Ability ability;
	
	public PacketDeactivateAbility() {
	}
	
	public PacketDeactivateAbility(Ability ability) {
		this.ability = ability;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, ability.getName());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		ability = Ability.getAbilityFromKey(ByteBufUtils.readUTF8String(buf));
	}
	
	public static class Handler implements IMessageHandler<PacketDeactivateAbility, IMessage> {
		
		@Override
		public IMessage onMessage(PacketDeactivateAbility message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(() -> {
				ICoreCapability coreCapability = CoreCapabilityImpl.getInstance(player);
				PlayerAbilityData data = coreCapability.getAbilityData();
				Ability ability = message.ability;
				if(cap.containsAbility(cap.getAbilities(), ability)) {
					cap.removeAbility(ability);
					ability.onAbilityDeactivated(player);
					PlayerHelper.sendMessage(player, TextFormatting.RED + "You have deactivated ability: " + ability.getFullName(), true);
					MinecraftForge.EVENT_BUS.post(new AbilityEvent.Deactivate(ability, player));
				}
				cap.sync(player);
			});
			return null;
		}
	}
}
