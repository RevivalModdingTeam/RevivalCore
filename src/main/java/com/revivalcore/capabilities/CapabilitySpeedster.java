package com.revivalcore.capabilities;

import com.revivalcore.capabilities.CapSpeedstersStorage.SpeedsterCapProvider;
import com.revivalcore.network.NetworkManager;
import com.revivalcore.network.packets.PacketCapSync;
import com.revivalcore.core.RevivalCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CapabilitySpeedster implements ISpeedsterCap {

    private EntityPlayer player;

    public CapabilitySpeedster() {

    }

    public CapabilitySpeedster(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void sync() {
        NetworkManager.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
    }

    @Mod.EventBusSubscriber(modid = RevivalCore.MODID) // TODO change to SHR  later
    public static class Event {
        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer)
                event.addCapability(new ResourceLocation(RevivalCore.MODID, "speedster_cap"), new SpeedsterCapProvider((EntityPlayer) event.getObject()));
        }

     /*   @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            CapabilitySpeedster cap = event.getEntityLiving().getCapability(CapSpeedstersStorage.CAP, null);
            if (cap != null)
                cap.update();
        }*/
    }

    @SubscribeEvent
    public static void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onDeathEvent(LivingDeathEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            get((EntityPlayer) e.getEntityLiving()).sync();
        }
    }

    @Nonnull
    public static ISpeedsterCap get(EntityPlayer player) {
        if (player.hasCapability(CapSpeedstersStorage.CAP, null)) {
            return player.getCapability(CapSpeedstersStorage.CAP, null);
        }
        throw new IllegalStateException("Missing Speedster capability: " + player + ", please report this!");
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
