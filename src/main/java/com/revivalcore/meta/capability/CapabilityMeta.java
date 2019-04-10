package com.revivalcore.meta.capability;

import com.revivalcore.RevivalCore;
import com.revivalcore.network.NetworkManager;
import com.revivalcore.network.packets.PacketCapSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CapabilityMeta implements IMetaCap {

    private EntityPlayer player;

    public CapabilityMeta() {

    }

    public CapabilityMeta(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
    }

    @Override
    public void sync() {
        NetworkManager.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
    }

    @Override
    public void makeSpeedster(boolean enable) {

    }

    @Override
    public String getMetaPower() {
        return null;
    }

    @Override
    public boolean hasMetaPowers() {
        return false;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }


    @Mod.EventBusSubscriber(modid = RevivalCore.MODID)
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
                if (event.getObject() instanceof EntityPlayer)
                    event.addCapability(new ResourceLocation(RevivalCore.MODID, "meta_cap"), new CapMetaStorage.MetaCapProvider((EntityPlayer) event.getObject()));
        }

        @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            CapabilityMeta cap = event.getEntityLiving().getCapability(CapMetaStorage.CAPABILITY, null);
            if (cap != null)
                cap.update();
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage storage = CapMetaStorage.CAPABILITY.getStorage();

            IMetaCap oldCap = get(event.getOriginal());
            IMetaCap newCap = get(event.getEntityPlayer());

            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CapMetaStorage.CAPABILITY, oldCap, null);
            storage.readNBT(CapMetaStorage.CAPABILITY, newCap, null, nbt);
            get(event.getEntityPlayer()).sync();
        }

        @SubscribeEvent
        public static void playerTracking(PlayerEvent.StartTracking event) {
            get(event.getEntityPlayer()).sync();
        }
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
    public static IMetaCap get(EntityPlayer player) {
        if (player.hasCapability(CapMetaStorage.CAPABILITY, null)) {
            return player.getCapability(CapMetaStorage.CAPABILITY, null);
        }
        throw new IllegalStateException("Missing Cap - SpeedsterHeroesReborn");
    }
}