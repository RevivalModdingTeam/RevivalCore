package com.revivalcore.superpowerbase.effects;

import com.RevivalCore.revivalcore.RCConfig;
import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EffectNameChange extends Effect {

    public ITextComponent name;

    @Override
    public void readSettings(JsonObject json) {
        this.name = ITextComponent.Serializer.jsonToComponent(JsonUtils.getJsonObject(json, "name").toString());
    }

    public static class EventHandler {

        @SubscribeEvent
        public void onName(PlayerEvent.NameFormat e) {
            if (e.getEntityPlayer().world.isRemote && !RConfig.RCConfig.nameChangeEffect)
                return;

            if (!e.getEntityPlayer().world.isRemote && !RCConfig.superpowers.nameChangeEffect)
                return;

            for (EffectNameChange nameChange : EffectHandler.getEffectsByClass(e.getEntityPlayer(), EffectNameChange.class)) {
                if (EffectHandler.canEffectBeDisplayed(nameChange, e.getEntityPlayer())) {
                    e.setDisplayname(nameChange.name.getFormattedText());
                    return;
                }
            }
        }

        @SubscribeEvent
        public void onLivingUpdate(TickEvent.PlayerTickEvent e) {
            if (e.phase == TickEvent.Phase.START && e.player.ticksExisted % 100 == 0) {
                e.player.refreshDisplayName();
            }
        }

    }

}
