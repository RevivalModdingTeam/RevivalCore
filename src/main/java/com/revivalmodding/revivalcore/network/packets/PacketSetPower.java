package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.core.common.events.PowerToggleEvent;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
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
                ICoreCapability data = CoreCapabilityImpl.getInstance(player);
                PlayerMetaPowerData powerData = data.getMetaPowerData();
                powerData.setPowerActivated(!powerData.isPowerActivated());
                PlayerHelper.sendMessage(player, powerData.isPowerActivated() ? "Powers activated!" : "Powers deactivated!", true);
                MinecraftForge.EVENT_BUS.post(new PowerToggleEvent(player, powerData.isPowerActivated()));
                data.sync();
            });
            return null;
        }
    }

}
