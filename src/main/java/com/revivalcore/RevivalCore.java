package com.revivalcore;

import com.revivalcore.core.common.events.RVRecipeRegistryEvent;
import com.revivalcore.core.registry.Registries;
import com.revivalcore.core.registry.SuitMakerRecipeRegistry;
import com.revivalcore.core.tabs.CoreTab;
import com.revivalcore.meta.capability.CapMetaStorage;
import com.revivalcore.meta.capability.CapabilityMeta;
import com.revivalcore.meta.capability.IMetaCap;
import com.revivalcore.network.NetworkManager;
import com.revivalcore.proxy.IProxy;
import com.revivalcore.util.handlers.GuiHandlerRV;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = RevivalCore.MODID, name = RevivalCore.NAME, version = RevivalCore.VERSION, updateJSON = RevivalCore.UPDATEURL)
public class RevivalCore {
    public static final String MODID = "revivalcore";
    public static final String NAME = "Revival Core";
    public static final String VERSION = "0.0.4";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/revivalmodding/RevivalCore/master/update.json";

    @Instance
    public static RevivalCore instance;
    public static final CreativeTabs coretab = new CoreTab("coretab");
    public static Logger logger;

    @SidedProxy(clientSide = "com.revivalcore.proxy.ClientProxy", serverSide = "com.revivalcore.proxy.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        NetworkManager.init();
        Registries.TileRegistry.init();
        SuitMakerRecipeRegistry.init();
        CapabilityManager.INSTANCE.register(IMetaCap.class, new CapMetaStorage(), CapabilityMeta::new);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerRV());
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.post(new RVRecipeRegistryEvent.SuitMakerRecipeRegistryEvent(SuitMakerRecipeRegistry.RECIPES));
        proxy.init(event);
    }

    @EventHandler
    public static void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }


}

