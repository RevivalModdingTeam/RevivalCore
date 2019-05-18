package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.common.tileentity.IProcessCraftSystem;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncProcessTileEntity implements IMessage
{
	private BlockPos pos;
	private boolean processing;
	private int timeProcessed;
	
	public PacketSyncProcessTileEntity() {}
	
	public PacketSyncProcessTileEntity(BlockPos tePos, boolean processin, int time) {
		this.pos = tePos;
		this.processing = processin;
		this.timeProcessed = time;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeBoolean(processing);
		buf.writeInt(timeProcessed);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		processing = buf.readBoolean();
		timeProcessed = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<PacketSyncProcessTileEntity, IMessage>
	{
		static void handle(PacketSyncProcessTileEntity p) {
			TileEntity te = Minecraft.getMinecraft().player.world.getTileEntity(p.pos);
			if(te instanceof IProcessCraftSystem) {
				IProcessCraftSystem ipcs = (IProcessCraftSystem)te;
				ipcs.setProcessing(p.processing);
				ipcs.setProcessTimer(p.timeProcessed);
			}
		}
		
		@Override
		public IMessage onMessage(PacketSyncProcessTileEntity message, MessageContext ctx)
		{
			if(ctx.side.isClient()) {
				Minecraft.getMinecraft().addScheduledTask(() -> handle(message));
			}
			return null;
		}
	}
}
