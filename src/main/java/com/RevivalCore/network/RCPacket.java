package com.RevivalCore.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public abstract class RCPacket<PACKET extends IMessage> implements IMessage, IMessageHandler<PACKET, PACKET>
{
	// some basic functions should go there
}
