package com.RevivalCore.proxies;

import com.RevivalCore.client.render.RenderSuitMaker;
import com.RevivalCore.common.tileentity.TileEntitySuitMaker;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
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

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
}

