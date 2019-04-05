package com.revivalcore.superpowerbase;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RenderSuperpowerLayerEvent extends Event {

    private EntityPlayer player;
    private RenderPlayer renderPlayer;
    private float limbSwing;
    private float limbSwingAmount;
    private float partialTicks;
    private float ageInTicks;
    private float netHeadYaw;
    private float headPitch;
    private float scale;

    public RenderSuperpowerLayerEvent(EntityPlayer player, RenderPlayer renderPlayer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.player = player;
        this.renderPlayer = renderPlayer;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.partialTicks = partialTicks;
        this.ageInTicks = ageInTicks;
        this.netHeadYaw = netHeadYaw;
        this.headPitch = headPitch;
        this.scale = scale;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public RenderPlayer getRenderPlayer() {
        return renderPlayer;
    }

    public float getLimbSwing() {
        return limbSwing;
    }

    public float getLimbSwingAmount() {
        return limbSwingAmount;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public float getAgeInTicks() {
        return ageInTicks;
    }

    public float getNetHeadYaw() {
        return netHeadYaw;
    }

    public float getHeadPitch() {
        return headPitch;
    }

    public float getScale() {
        return scale;
    }
}
