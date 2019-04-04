package com.RevivalCore.network;

import com.RevivalCore.revivalcore.RevivalCore;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkManager 
{
	private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RevivalCore.MODID);
	private static int id = -1;
	
	public static void init()
	{
		//INSTANCE.registerMessage();
	}
}
