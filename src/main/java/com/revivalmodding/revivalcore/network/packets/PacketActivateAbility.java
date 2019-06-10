package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
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

public class PacketActivateAbility implements IMessage {
	
	private AbilityBase ability;
	
	public PacketActivateAbility() {}
	
	public PacketActivateAbility(AbilityBase ability) {
		this.ability = ability;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, ability.getName());
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		ability = AbilityBase.getAbilityFromKey(ByteBufUtils.readUTF8String(buf));
	}
	
	public static class Handler implements IMessageHandler<PacketActivateAbility, IMessage> {
		
		@Override
		public IMessage onMessage(PacketActivateAbility message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(() -> {
				IAbilityCap cap = IAbilityCap.Impl.get(player);
				AbilityBase ability = message.ability;
				if(cap.getAbilities().size() < 3 && ability.canActivateAbility(player)) {
					cap.addAbility(ability);
					PlayerHelper.sendMessage(player, TextFormatting.GREEN + "You have activated ability: " + ability.getFullName(), true);
					MinecraftForge.EVENT_BUS.post(new AbilityEvent.Activate(ability, player));
				}
				cap.sync(player);
			});
			return null;
		}
	}
}
