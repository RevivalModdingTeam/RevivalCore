package com.revivalcore.common.capabilities;

import com.revivalcore.api.SpeedAPI;
import com.revivalcore.common.capabilities.CapSpeedstersStorage.SpeedsterCapProvider;
import com.revivalcore.core.RevivalCore;
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

public class CapabilitySpeedster implements ISpeedsterCap {

    private EntityPlayer player;
    private float speed_level = 0.1f;
    private boolean isSpeedster = false;
    private boolean isPhasing = false;

    public CapabilitySpeedster() {

    }

    public CapabilitySpeedster(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (!isSpeedster()) {
            setSpeedLevel(0.1f);
            SpeedAPI.setSpeedFromCap(player);
        } else {
            if (getSpeedLevel() != player.capabilities.getWalkSpeed()) {
                SpeedAPI.setSpeedFromCap(player);
            }
        }
    }

    @Override
    public void sync() {
        NetworkManager.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
    }

    @Override
    public void setSpeedster(boolean speedster) {
        isSpeedster = speedster;
    }

    @Override
    public boolean isSpeedster() {
        return isSpeedster;
    }

    @Override
    public void setSpeedLevel(float level) {
        speed_level = level;
    }

    @Override
    public float getSpeedLevel() {
        return speed_level;
    }

    @Override
    public void setPhasing(boolean phase) {
        this.isPhasing = phase;
    }

    @Override
    public boolean isPhasing() {
        return isPhasing;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("speed_level", speed_level);
        nbt.setBoolean("is_speedster", isSpeedster);
        nbt.setBoolean("is_phasing", isPhasing);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        speed_level = nbt.getFloat("speed_level");
        isSpeedster = nbt.getBoolean("is_speedster");
        isPhasing = nbt.getBoolean("is_phasing");
    }


    @Mod.EventBusSubscriber(modid = RevivalCore.MODID)
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
                if (event.getObject() instanceof EntityPlayer)
                    event.addCapability(new ResourceLocation(RevivalCore.MODID, "speedsters_cap"), new SpeedsterCapProvider((EntityPlayer) event.getObject()));
        }

        @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            CapabilitySpeedster cap = event.getEntityLiving().getCapability(CapSpeedstersStorage.CAPABILITY, null);
            if (cap != null)
                cap.update();
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage storage = CapSpeedstersStorage.CAPABILITY.getStorage();

            ISpeedsterCap oldCap = get(event.getOriginal());
            ISpeedsterCap newCap = get(event.getEntityPlayer());

            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CapSpeedstersStorage.CAPABILITY, oldCap, null);
            storage.readNBT(CapSpeedstersStorage.CAPABILITY, newCap, null, nbt);
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
    public static ISpeedsterCap get(EntityPlayer player) {
        if (player.hasCapability(CapSpeedstersStorage.CAPABILITY, null)) {
            return player.getCapability(CapSpeedstersStorage.CAPABILITY, null);
        }
        throw new IllegalStateException("Missing Cap");
    }
}