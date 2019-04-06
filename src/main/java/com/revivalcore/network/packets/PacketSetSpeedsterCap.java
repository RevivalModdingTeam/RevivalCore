package com.revivalcore.network.packets;

import com.revivalcore.common.capabilities.CapabilitySpeedster;
import com.revivalcore.common.capabilities.ISpeedsterCap;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketSetSpeedsterCap implements IMessage {



    public PacketSetSpeedsterCap() {
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketSetSpeedsterCap, IMessage> {
        @Override
        public IMessage onMessage(PacketSetSpeedsterCap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                ISpeedsterCap data = CapabilitySpeedster.get(player);
                if (data.isSpeedster()) {
                    data.setSpeedster(false);
                } else {
                    data.setSpeedster(true);
                }
                data.sync();
            });
            return null;
        }
    }

}
