package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketSendCapabilitiesToServer implements IMessage {

    private NBTTagCompound data;
    private UUID uuid;

    public PacketSendCapabilitiesToServer() {

    }

    public PacketSendCapabilitiesToServer(NBTTagCompound nbt, UUID uuid) {
        this.data = nbt;
        this.uuid = uuid;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
        ByteBufUtils.writeUTF8String(buf, uuid.toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf);
        this.uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    public static class Handler implements IMessageHandler<PacketSendCapabilitiesToServer, IMessage> {

        @Override
        public IMessage onMessage(PacketSendCapabilitiesToServer message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServer().addScheduledTask(() -> {
                EntityPlayer fromPlayer = player.world.getPlayerEntityByUUID(message.uuid);
                if(fromPlayer != null) {
                    CoreCapabilityImpl.getInstance(fromPlayer).fromNBT(message.data);
                }
            });
            return null;
        }
    }
}
