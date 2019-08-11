package com.revivalmodding.revivalcore.meta.capability;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketCapSync;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
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

/**
 * Created by Josia50
 * on 10/04/2019.
 */

public class CapabilityMeta implements IMetaCap {

    private EntityPlayer player;
    private int metapowerid = -1;
    private double exhaustionlevel = 0.0;
    private boolean isPowerEnabled = false;
    private boolean isPowerMalfunction = false;
    private boolean powertempmalfunction = false;
    private int malfunctionlevel = 20;
    private boolean canVibrate = true;
    private boolean isVibrating = false;

    public CapabilityMeta() {

    }

    public CapabilityMeta(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (getexhaustionLevel() > 0) {
            setExhaustionLevel(getexhaustionLevel() - 0.1D);
        }

        if (getexhaustionLevel() > 10) {
            this.powertempmalfunction = true;
        } else {
            this.powertempmalfunction = false;
        }

        if (getexhaustionLevel() >= malfunctionlevel) {
            if (!isPowerMalfunction) {
                this.isPowerMalfunction = true;
                PlayerHelper.sendMessage(player, "You can't use your powers!, You're too exhausted!", true);
            }
        } else {
            if (isPowerMalfunction && getexhaustionLevel() < malfunctionlevel - 12) {
                this.isPowerMalfunction = false;
                PlayerHelper.sendMessage(player, "You can use your powers again!", true);
            }
        }
    }

    @Override
    public void sync() {
        NetworkManager.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
    }

    @Override
    public void setMetaPower(int metaPower) {
        if (!hasMetaPowers() || metaPower == -1) {
            this.metapowerid = metaPower;
        }
    }

    @Override
    public int getMetaPower() {
        return metapowerid;
    }

    @Override
    public boolean isPowerMalfunctioned(boolean disabled) {
        return disabled ? isPowerMalfunction : powertempmalfunction;
    }


    @Override
    public boolean hasMetaPowers() {
        if (getMetaPower() > -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isVibrating() {
        return isVibrating;
    }

    @Override
    public void canVibrate(boolean v) {
        canVibrate = v;
    }

    @Override
    public void setVibrating(boolean v) {
        isVibrating = v;
    }

    @Override
    public boolean canVibrate() {
        return canVibrate;
    }

    @Override
    public void setExhaustionLevel(double exhaustionLevel) {
        this.exhaustionlevel = exhaustionLevel;
    }

    @Override
    public double getexhaustionLevel() {
        return exhaustionlevel;
    }

    @Override
    public void setPowerEnabled(boolean enable) {
        this.isPowerEnabled = enable;
    }

    @Override
    public boolean isPowerEnabled() {
        return this.isPowerEnabled && !this.isPowerMalfunction;
    }

    @Override
    public void clear() {
        setMetaPower(-1);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("power_id", metapowerid);
        nbt.setDouble("exhaustion", exhaustionlevel);
        nbt.setBoolean("enabled", isPowerEnabled);
        nbt.setBoolean("power_malfunction", isPowerMalfunction);
        nbt.setBoolean("temp_malfunction", powertempmalfunction);
        nbt.setBoolean("can_vibrate", canVibrate);
        nbt.setBoolean("vibrating", isVibrating);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        metapowerid = nbt.getInteger("power_id");
        exhaustionlevel = nbt.getDouble("exhaustion");
        isPowerEnabled = nbt.getBoolean("enabled");
        isPowerMalfunction = nbt.getBoolean("power_malfunction");
        powertempmalfunction = nbt.getBoolean("temp_malfunction");
        canVibrate = nbt.getBoolean("can_vibrate");
        isVibrating = nbt.getBoolean("vibrating");
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
        throw new IllegalStateException("Missing Cap - RevivalCore");
    }
}