package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.common.tileentity.IProcessCraftSystem;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncProcessTE implements IMessage {
    private BlockPos tilePos;
    private boolean processing;
    private int craftingTime;
    private RVRecipe recipe;

    public PacketSyncProcessTE() {
    }

    public PacketSyncProcessTE(IProcessCraftSystem te, BlockPos pos) {
        tilePos = pos;
        processing = te.isProcessing();
        craftingTime = te.getProcessTimer();
        recipe = te.getRecipe();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(tilePos.getX());
        buf.writeInt(tilePos.getY());
        buf.writeInt(tilePos.getZ());
        buf.writeBoolean(processing);
        buf.writeInt(craftingTime);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tilePos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        processing = buf.readBoolean();
        craftingTime = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketSyncProcessTE, IMessage> {
        @Override
        public IMessage onMessage(PacketSyncProcessTE message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                World world = Minecraft.getMinecraft().world;
                TileEntity tile = world.getTileEntity(message.tilePos);
                if (tile instanceof IProcessCraftSystem) {
                    IProcessCraftSystem te = (IProcessCraftSystem) tile;
                    te.setProcessing(message.processing);
                    te.setProcessTimer(message.craftingTime);
                    te.setRecipe(message.recipe);
                }
            });
            return null;
        }
    }
}
