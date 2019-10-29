package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class PacketSyncAbilities implements IMessage {

    private NBTTagCompound comp;
    private UUID player;

    public PacketSyncAbilities() {}

    public PacketSyncAbilities(NBTTagCompound comp, UUID uuid) {
        this.comp = comp;
        this.player = uuid;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, comp);
        ByteBufUtils.writeUTF8String(buf, player.toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        comp = ByteBufUtils.readTag(buf);
        player = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    public static class Handler implements IMessageHandler<PacketSyncAbilities, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketSyncAbilities message, MessageContext ctx) {
            if(ctx.side.isClient()) {
                EntityPlayer receiver = Minecraft.getMinecraft().player;
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer receivedFrom = receiver.world.getPlayerEntityByUUID(message.player);
                    if(receivedFrom != null) {
                        CoreCapabilityImpl.getInstance(receivedFrom).fromNBT(message.comp);
                    }
                });
            }
            return null;
        }
    }
}
