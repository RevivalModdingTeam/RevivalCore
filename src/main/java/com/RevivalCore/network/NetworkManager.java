package com.RevivalCore.network;

import java.util.HashSet;
import java.util.Set;

import com.RevivalCore.revivalcore.RevivalCore;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager 
{
	public static final Set<RCPacket> SERVER_PACKETS = new HashSet<RCPacket>();
	public static final Set<RCPacket> CLIENT_PACKETS = new HashSet<RCPacket>();
	
	private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RevivalCore.MODID);
	private static int ID = -1;
	
	public static void initialize()
	{
		for(RCPacket cpacket : CLIENT_PACKETS)
		{
			registerCPacket(cpacket.getClass());
		}
		
		for(RCPacket spacket : SERVER_PACKETS)
		{
			registerSPacket(spacket.getClass());
		}
	}
	
	public static void sendToClient(RCPacket packet, EntityPlayerMP player)
	{
		INSTANCE.sendTo(packet, player);
	}
	
	public static void sendToAllClients(RCPacket packet)
	{
		INSTANCE.sendToAll(packet);
	}
	
	public static void sendToClientsAround(RCPacket packet, TargetPoint target)
	{
		INSTANCE.sendToAllAround(packet, target);
	}
	
	public static void sendToClientsAround(RCPacket packet, int dimension, BlockPos pos, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, pos.getX(), pos.getY(), pos.getZ(), range));
	}
	
	public static void sendToClientsAround(RCPacket packet, int dimension, double x, double y, double z, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(dimension, x, y, z, range));
	}
	
	public static void sendToClientsAround(RCPacket packet, EntityPlayerMP player, double range)
	{
		INSTANCE.sendToAllAround(packet, new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, range));
	}
	
	public static void sendToDimension(RCPacket packet, int dimension)
	{
		INSTANCE.sendToDimension(packet, dimension);
	}
	
	public static void sendToDimension(RCPacket packet, EntityPlayerMP player)
	{
		INSTANCE.sendToDimension(packet, player.dimension);
	}
	
	public static void sendToServer(RCPacket packet)
	{
		INSTANCE.sendToServer(packet);
	}	
	
	public static void registerClientPacket(RCPacket packet)
	{
		CLIENT_PACKETS.add(packet);
	}
	
	public static void registerServerPacket(RCPacket packet)
	{
		SERVER_PACKETS.add(packet);
	}
	
	private static void registerCPacket(Class packetClass)
	{
		INSTANCE.registerMessage(packetClass, packetClass, getPacketID(), Side.CLIENT);
	}
	
	private static void registerSPacket(Class packetClass)
	{
		INSTANCE.registerMessage(packetClass, packetClass, getPacketID(), Side.SERVER);
	}
	
	private static int getPacketID()
	{
		ID++;
		return ID;
	}
}
