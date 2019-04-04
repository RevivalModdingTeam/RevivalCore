package com.revivalcore.network.packets;

import com.revivalcore.capabilities.CapabilitySpeedster;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketCapSync implements IMessage {

    private EntityPlayer player;
    private NBTTagCompound data;

    public PacketCapSync() {
    }

    public PacketCapSync(EntityPlayer player, NBTTagCompound data) {
        this.player = player;
        this.data = data;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
        ByteBufUtils.writeUTF8String(buf, player.getGameProfile().getId().toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (Minecraft.getMinecraft().player == null) return;
        data = ByteBufUtils.readTag(buf);
        player = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(UUID.fromString(ByteBufUtils.readUTF8String(buf)));
    }

    public static class Handler implements IMessageHandler<PacketCapSync, IMessage> {

        @Override
        public IMessage onMessage(PacketCapSync message, MessageContext ctx) {
            EntityPlayer player = message.player;
            if (player != null) Minecraft.getMinecraft().addScheduledTask(() -> CapabilitySpeedster.get(player).deserializeNBT(message.data));
            return null;
        }
    }

}
