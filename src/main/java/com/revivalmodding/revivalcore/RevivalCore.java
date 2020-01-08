package com.revivalmodding.revivalcore;

import com.revivalmodding.revivalcore.command.CommandSuperpowers;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityStorage;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.common.events.RCRegistryEvent;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.core.tabs.CoreTab;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.proxy.IProxy;
import com.revivalmodding.revivalcore.util.handlers.GuiHandlerRV;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = RevivalCore.MODID, name = RevivalCore.NAME, version = RevivalCore.VERSION, updateJSON = RevivalCore.UPDATEURL)
public class RevivalCore {
    public static final String MODID = "revivalcore";
    public static final String NAME = "Revival Core";
    public static final String VERSION = "0.3.8";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/RevivalModdingTeam/RevivalModding-ModBugs/master/update/updatecore.json";
    private static final Random random = new Random();

    @Instance
    public static RevivalCore instance;
    public static final CreativeTabs coretab = new CoreTab("coretab");
    public static Logger logger;

    @SidedProxy(clientSide = "com.revivalmodding.revivalcore.proxy.ClientProxy", serverSide = "com.revivalmodding.revivalcore.proxy.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        NetworkManager.init();
        Registries.TileRegistry.init();
        CapabilityManager.INSTANCE.register(ICoreCapability.class, new CoreCapabilityStorage(), CoreCapabilityImpl::new);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerRV());
        ModHelper.startupChecks();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.post(new RCRegistryEvent.SuitMakerRecipeRegistryEvent());
        MinecraftForge.EVENT_BUS.post(new RCRegistryEvent.AbilityRegistryEvent());
        proxy.init(event);
    }

    @EventHandler
    public static void postinit(FMLPostInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.post(new RCRegistryEvent.SuitRegistryEvent());
        proxy.postInit(event);
    }
    
    @EventHandler
    public static void serverStarting(FMLServerStartingEvent e) {
    	e.registerServerCommand(new CommandSuperpowers());
    }

    public static Random getRandom() {
        return random;
    }
}

