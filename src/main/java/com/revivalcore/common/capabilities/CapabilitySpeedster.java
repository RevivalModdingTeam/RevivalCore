package com.revivalcore.common.capabilities;

import com.revivalcore.common.capabilities.CapSpeedstersStorage.SpeedsterCapProvider;
import com.revivalcore.network.NetworkManager;
import com.revivalcore.network.packets.PacketCapSync;
import com.revivalcore.core.RevivalCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CapabilitySpeedster implements ISpeedsterCap {

    @CapabilityInject(ISpeedsterCap.class)
    public static final Capability<ISpeedsterCap> CAPABILITY = null;

    private EntityPlayer player;
    private float speed_level = 1;

    public CapabilitySpeedster() {

    }

    public CapabilitySpeedster(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void sync() {
        if(player.world.isRemote) {
            throw new IllegalStateException("We don't need to sync client to server");
        }else {
            NetworkManager.INSTANCE.sendToAll(new PacketCapSync(player, serializeNBT()));
        }
    }

    @Override
    public void setSpeedLevel(float level) {
        this.speed_level = level;
    }

    @Override
    public float getSpeedLevel() {
        return this.speed_level;
    }

    @Mod.EventBusSubscriber(modid = RevivalCore.MODID) // TODO change to SHR  later
    public static class Event {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer)
                event.addCapability(new ResourceLocation(RevivalCore.MODID, "speedster_cap"), new SpeedsterCapProvider((EntityPlayer) event.getObject()));
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage storage = CapSpeedstersStorage.CAP.getStorage();

            ISpeedsterCap oldCap = get(event.getOriginal());
            ISpeedsterCap newCap = get(event.getEntityPlayer());

            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CapSpeedstersStorage.CAP, oldCap, null);
            storage.readNBT(CapSpeedstersStorage.CAP, newCap, null, nbt);
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
        if (player.hasCapability(CapSpeedstersStorage.CAP, null)) {
            return player.getCapability(CapSpeedstersStorage.CAP, null);
        }
        throw new IllegalStateException("Missing Speedster capability: " + player + ", please report this!");
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("speed_level", speed_level);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        speed_level = nbt.getFloat("speed_level");
    }
}
