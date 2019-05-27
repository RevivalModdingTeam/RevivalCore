package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAbilityToggled implements IMessage {
	
	private AbilityBase ability;
	
	public PacketAbilityToggled() {
	}
	
	public PacketAbilityToggled(AbilityBase ability) {
		this.ability = ability;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, ability.getName());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		ability = AbilityBase.getAbilityFromKey(ByteBufUtils.readUTF8String(buf));
		if(ability == null) {
			throw new IllegalArgumentException("Received null ability");
		}
	}
	
	public static class Handler implements IMessageHandler<PacketAbilityToggled, IMessage> {
		
		@Override
		public IMessage onMessage(PacketAbilityToggled message, MessageContext ctx) {
			if(ctx.side.isClient()) {
				Minecraft.getMinecraft().addScheduledTask(() -> message.ability.toggleAbility());
			}
			return null;
		}
	}
}
