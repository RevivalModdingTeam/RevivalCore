package com.psychokiller.proxies;

import com.psychokiller.client.render.RenderSuitMaker;
import com.psychokiller.common.tileentity.TileEntitySuitMaker;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

