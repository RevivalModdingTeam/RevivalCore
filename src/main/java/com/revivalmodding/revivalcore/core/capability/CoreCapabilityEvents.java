package com.revivalmodding.revivalcore.core.capability;

import com.revivalmodding.revivalcore.RevivalCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class CoreCapabilityEvents {

    @SubscribeEvent
    public static void clonePlayer(net.minecraftforge.event.entity.player.PlayerEvent.Clone e) {
        ICoreCapability oldCap = CoreCapabilityImpl.getInstance(e.getOriginal());
        ICoreCapability newCap = CoreCapabilityImpl.getInstance(e.getEntityPlayer());
        Capability.IStorage dataStorage = CoreCapabilityProvider.DATA.getStorage();
        dataStorage.readNBT(CoreCapabilityProvider.DATA, newCap, null, dataStorage.writeNBT(CoreCapabilityProvider.DATA, oldCap, null));
        newCap.sync();
    }

    @SubscribeEvent
    public static void changeDimension(PlayerEvent.PlayerChangedDimensionEvent e) {
        CoreCapabilityImpl.getInstance(e.player).sync();
    }

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent e) {
        CoreCapabilityImpl.getInstance(e.player).sync();
    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent e) {
        CoreCapabilityImpl.getInstance(e.player).tick();
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if(e.getObject() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getObject();
            e.addCapability(new ResourceLocation(RevivalCore.MODID, "core_cap"), new CoreCapabilityProvider(player));
        }
    }
}
