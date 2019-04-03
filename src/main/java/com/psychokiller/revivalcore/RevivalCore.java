package com.psychokiller.revivalcore;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Logger;

import com.psychokiller.common.tileentity.TileEntitySuitMaker;
import com.psychokiller.proxies.IProxy;

@Mod(modid = RevivalCore.MODID, name = RevivalCore.NAME, version = RevivalCore.VERSION)
public class RevivalCore
{
    public static final String MODID = "rc";
    public static final String NAME = "Revival Core";
    public static final String VERSION = "1.0";

    @Instance
    public static RevivalCore instance;
    
    public static Logger logger;
    
    @SidedProxy(clientSide = "com.psychokiller.proxies.ClientProxy", serverSide = "com.psychokiller.proxies.ServerProxy")
    public static IProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		Registries.Registry.registerTileEntity(TileEntitySuitMaker.class, "suit_maker");
		proxy.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public static void postinit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}


}

