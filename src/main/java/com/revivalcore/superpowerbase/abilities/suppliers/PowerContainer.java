package com.revivalcore.superpowerbase.abilities.suppliers;

import java.util.Collection;

import com.RevivalCore.capabilities.CapabilitySuperpower;
import com.RevivalCore.network.packets.PacketDispatcher;
import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.PowerBase.AbilityMap;
import com.revivalcore.superpowerbase.abilities.PowerBase.EnumAbilityContext;
import com.RevivalCore.superpowerbase.abilities.events.InitPowersEvent;
import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.INBTSerializable;

public class PowerContainer implements INBTSerializable<NBTTagCompound> {

    public final EntityLivingBase entity;
    public final PowerBase.EnumAbilityContext context;
    public IIPowerProvider provider;
    protected PowerBase.AbilityMap abilities;
    public EnumSync sync = EnumSync.NONE;

    public PowerContainer(EntityLivingBase entity, PowerBase.EnumAbilityContext context) {
        this.entity = entity;
        this.context = context;
        this.abilities = new PowerBase.AbilityMap();
    }

    public Collection<PowerBase> getAbilities() {
        return abilities.values();
    }

    public PowerBase getAbility(String key) {
        return this.abilities.get(key);
    }

    public String getKeyForAbility(PowerBase ability) {
        for (String s : this.abilities.keySet()) {
            if (this.abilities.get(s) == ability) {
                return s;
            }
        }

        return null;
    }

    public void onUpdate() {
        IIPowerProvider currentProvider = PowerBase.getAbilityProvider(this.entity, this.context);

        if (currentProvider != this.provider)
            this.switchProvider(currentProvider);

        for (PowerBase ab : getAbilities()) {
            ab.onUpdate();

            if (ab.sync != null && ab.sync != EnumSync.NONE) {
                sync = sync.add(ab.sync);
                ab.sync = EnumSync.NONE;
            }
        }

        if (sync != EnumSync.NONE) {
            sync();
            this.sync = EnumSync.NONE;
        }
    }

    public void switchProvider(IIPowerProvider provider) {
        for (PowerBase ab : getAbilities())
            if (ab.isUnlocked())
                ab.lastTick();

        if (this.provider != null)
            save();

        this.provider = provider;

        if (this.provider != null) {
            this.abilities = filterAbilities(provider.addDefaultAbilities(this.entity, new PowerBase.AbilityMap(), this.context));
            load();
        } else {
            this.abilities.clear();
        }

        this.sync = this.sync.add(EnumSync.EVERYONE);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.abilities.forEach((s, a) -> nbt.setTag(s, a.serializeNBT()));
        return nbt;
    }

    public NBTTagCompound serializeNBTSync() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.abilities.forEach((s, a) -> nbt.setTag(s, a.serializeNBTSync()));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (this.provider == null) {
            this.abilities = new PowerBase.AbilityMap();
            return;
        }

        this.abilities = this.provider.addDefaultAbilities(this.entity, new PowerBase.AbilityMap(), this.context);
        this.abilities.forEach((s, ability) -> {
            if (nbt.hasKey(s))
                ability.deserializeNBT(nbt.getCompoundTag(s));
        });
        this.abilities = filterAbilities(this.abilities);
    }

    public void deserializeNBTSync(NBTTagCompound nbt) {
        if (this.provider == null) {
            this.abilities = new PowerBase.AbilityMap();
            return;
        }
        this.abilities = this.provider.addDefaultAbilities(this.entity, new PowerBase.AbilityMap(), this.context);
        this.abilities.forEach((s, ability) -> {
            if (nbt.hasKey(s)) {
                ability.deserializeNBTSync(nbt.getCompoundTag(s));
            }
        });
        this.abilities = filterAbilities(this.abilities);
    }

    public PowerBase.AbilityMap filterAbilities(PowerBase.AbilityMap abilityList) {
    	PowerBase.AbilityMap l = new PowerBase.AbilityMap();
        abilityList.forEach((s, a) -> l.put(s, a));
        for (PowerBase ab : l.values())
            ab.context = context;
        MinecraftForge.EVENT_BUS.post(new InitPowersEvent.Pre(entity, l, context));
        for (PowerBase ab : l.values()) {
            ab.init(ImmutableMap.copyOf(l));
        }
        MinecraftForge.EVENT_BUS.post(new InitPowersEvent.Post(entity, l, context));
        return l;
    }

    public void save() {
        if (!entity.world.isRemote)
            entity.getCapability(CapabilitySuperpower.SUPERPOWER_CAP, null).getData().setTag(context.toString(), this.serializeNBT());
    }

    public void load() {
        this.deserializeNBT(entity.getCapability(CapabilitySuperpower.SUPERPOWER_CAP, null).getData().getCompoundTag(context.toString()));
    }

    public void sync() {
        if (entity.world.isRemote)
            return;
        if ((sync == EnumSync.SELF || sync == EnumSync.EVERYONE) && entity instanceof EntityPlayerMP)
            PacketDispatcher.sendTo(new MessageSyncPowerContainer(entity, this), (EntityPlayerMP) entity);
        if (sync == EnumSync.EVERYONE)
            ((WorldServer) entity.world).getEntityTracker().getTrackingPlayers(entity).forEach((p) -> PacketDispatcher.sendTo(new MessageSyncPowerContainer(entity, this), (EntityPlayerMP) p));
    }

}
