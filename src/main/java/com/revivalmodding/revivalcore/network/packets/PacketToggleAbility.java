package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleAbility implements IMessage {
	
	int id;
	
	public PacketToggleAbility() {
	}
	
	public PacketToggleAbility(int keyID) {
		this.id = keyID;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<PacketToggleAbility, IMessage> {
		
		@Override
		public IMessage onMessage(PacketToggleAbility message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(() -> {
				IAbilityCap cap = IAbilityCap.Impl.get(player);
				if(message.id < cap.getAbilities().size()) {
					AbilityBase ability = cap.getAbilities().get(message.id);
					if(ability != null) {
						ability.toggleAbility();
						PlayerHelper.sendMessage(player, TextFormatting.GREEN + "You have used/toggled " + ability.getFullName().toLowerCase(), true);
					}
				} else {
					PlayerHelper.sendMessage(player, TextFormatting.RED + "No active ability for slot " + message.id, true);
				}
			});
			return null;
		}
	}
}
