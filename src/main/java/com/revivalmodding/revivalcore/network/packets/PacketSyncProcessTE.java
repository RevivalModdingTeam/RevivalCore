package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.core.common.tileentity.IProcessCraftSystem;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.network.NetworkManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncProcessTE implements IMessage
{
	private BlockPos tilePos;
	private boolean processing;
	private int craftingTime;
	private RVRecipe recipe;
	
	public PacketSyncProcessTE() {
	}
	
	protected PacketSyncProcessTE(IProcessCraftSystem te, BlockPos pos) {
		tilePos = pos;
		processing = te.isProcessing();
		craftingTime = te.getProcessTimer();
		recipe = te.getRecipe();
	}
	
	public static void sync(IProcessCraftSystem te, BlockPos pos) {
		NetworkManager.INSTANCE.sendToAll(new PacketSyncProcessTE(te, pos));
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(tilePos.getX());
		buf.writeInt(tilePos.getY());
		buf.writeInt(tilePos.getZ());
		buf.writeBoolean(processing);
		buf.writeInt(craftingTime);
		System.out.println(recipe.getName());
		ByteBufUtils.writeUTF8String(buf, recipe.getName());
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		tilePos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		processing = buf.readBoolean();
		craftingTime = buf.readInt();
		recipe = RVRecipe.getRecipeFromName(ByteBufUtils.readUTF8String(buf));
	}
	
	public static class Handler implements IMessageHandler<PacketSyncProcessTE, IMessage>
	{
		@Override
		public IMessage onMessage(PacketSyncProcessTE message, MessageContext ctx)
		{
			if(ctx.side.isClient()) {
				Minecraft.getMinecraft().addScheduledTask(() -> handle(message));
			}
			return null;
		}
		
		private static void handle(PacketSyncProcessTE packet)
		{
			World world = Minecraft.getMinecraft().player.world;
			TileEntity tile = world.getTileEntity(packet.tilePos);
			if(tile instanceof IProcessCraftSystem) {
				IProcessCraftSystem te = (IProcessCraftSystem)tile;
				te.setProcessing(packet.processing);
				te.setProcessTimer(packet.craftingTime);
				te.setRecipe(packet.recipe);
			}
		}
	}
}
