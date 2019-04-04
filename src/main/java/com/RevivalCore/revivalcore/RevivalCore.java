package com.RevivalCore.revivalcore;

import com.RevivalCore.network.NetworkManager;
import com.RevivalCore.proxy.IProxy;
import com.RevivalCore.tabs.CoreTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = RevivalCore.MODID, name = RevivalCore.NAME, version = RevivalCore.VERSION)
public class RevivalCore
{
    public static final String MODID = "rc";
    public static final String NAME = "Revival Core";
    public static final String VERSION = "1.0";

    @Instance
    public static RevivalCore instance;
    
    public static Logger logger;
    public static final CreativeTabs coretab = new CoreTab("coretab");
    
    @SidedProxy(clientSide = "com.RevivalCore.proxy.ClientProxy", serverSide = "com.RevivalCore.proxy.ServerProxy")
    public static IProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		proxy.preInit(event);
		NetworkManager.init();
		Registries.TileRegistry.init();
		// Working on disabling mod
		//if (Loader.isModLoaded("speedsterheroes")) {
		//
		//}
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

