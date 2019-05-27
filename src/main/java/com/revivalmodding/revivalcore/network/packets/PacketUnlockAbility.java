package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUnlockAbility implements IMessage {
	
	private AbilityBase ability;
	
	public PacketUnlockAbility() {
	}
	
	public PacketUnlockAbility(AbilityBase ability) {
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
	
	public static class Handler implements IMessageHandler<PacketUnlockAbility, IMessage> {
		
		@Override
		public IMessage onMessage(PacketUnlockAbility message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServer().addScheduledTask(() -> {
				IAbilityCap cap = IAbilityCap.Impl.get(player);
				AbilityBase ability = message.ability;
				if(!cap.containsAbility(cap.getUnlockedAbilities(), ability)) {
					if(cap.getLevel() >= ability.getAbilityPrice()) {
						cap.setLevel(cap.getLevel() - ability.getAbilityPrice());
						cap.unlockAbility(ability.getName());
					}
				}
				cap.sync(player);
			});
			return null;
		}
	}
}
