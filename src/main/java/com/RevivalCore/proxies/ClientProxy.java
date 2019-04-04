package com.RevivalCore.proxies;

import com.RevivalCore.client.render.RenderSuitMaker;
import com.RevivalCore.common.tileentity.TileEntitySuitMaker;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		registerRenders();
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{	
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) 
	{
	}

	private void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuitMaker.class, new RenderSuitMaker());
	}
}

