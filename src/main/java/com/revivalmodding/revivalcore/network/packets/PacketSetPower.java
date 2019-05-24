package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetPower implements IMessage {


    public PacketSetPower() {
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketSetPower, IMessage> {
        @Override
        public IMessage onMessage(PacketSetPower message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                IMetaCap data = CapabilityMeta.get(player);
                if (data.isPowerEnabled()) {
                    data.setPowerEnabled(false);
                    PlayerHelper.sendMessage(player, "Power Disabled!", true);
                } else {
                    data.setPowerEnabled(true);
                    PlayerHelper.sendMessage(player, "Power Enabled!", true);
                }
                data.sync();
            });
            return null;
        }
    }

}
