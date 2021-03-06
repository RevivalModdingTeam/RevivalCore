package com.revivalmodding.revivalcore.core;

import com.revivalmodding.revivalcore.RevivalCore;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = RevivalCore.MODID, name = RevivalCore.NAME + " Config")
public class RCoreConfig {

    public static final RCore revivalCore = new RCore();

    @Config.LangKey("config.tab.revivalcore")
    public static class RCore {
        @Config.LangKey("config.rcore.updatechecker")
        @Config.Comment("Toggle the update checker")
        public boolean updatechecker = true;

        // TODO implement
        /*@Config.LangKey("config.rcore.3dsuits")
        public boolean use3DSuits = false;*/
    }

    @Mod.EventBusSubscriber
    public static class Event {
        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent e) {
            if (e.getModID().equals(RevivalCore.MODID))
                ConfigManager.sync(RevivalCore.MODID, Type.INSTANCE);
        }
    }
}