package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketSyncAbilities implements IMessage {

    private NBTTagCompound comp;
    private EntityPlayer player;

    public PacketSyncAbilities() {}

    public PacketSyncAbilities(NBTTagCompound comp, EntityPlayer player) {
        this.comp = comp;
        this.player = player;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, comp);
        ByteBufUtils.writeUTF8String(buf, player.getGameProfile().getId().toString());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        comp = ByteBufUtils.readTag(buf);
        player = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(UUID.fromString(ByteBufUtils.readUTF8String(buf)));
    }

    public static class Handler implements IMessageHandler<PacketSyncAbilities, IMessage> {

        @Override
        public IMessage onMessage(PacketSyncAbilities message, MessageContext ctx) {
            EntityPlayer player = message.player;
            if(player != null) {
                Minecraft.getMinecraft().addScheduledTask(() -> {IAbilityCap.Impl.get(player).deserializeNBT(message.comp);});
            }
            return null;
        }
    }
}
