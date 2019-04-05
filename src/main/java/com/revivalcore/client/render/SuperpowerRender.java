package com.revivalcore.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.RevivalCore.capabilities.ISuperpowerbaseCap;
import com.RevivalCore.superpowerbase.Superpowerbase;
import com.RevivalCore.superpowerbase.abilities.suppliers.PowerContainer;
import com.RevivalCore.superpowerbase.effects.EffectHUD;
import com.RevivalCore.superpowerbase.effects.EffectHandler;
import com.RevivalCore.superpowerbase.effects.EffectSkinChange;
import com.RevivalCore.superpowerbase.effects.EffectTrail;
import com.RevivalCore.superpowerbase.events.RenderModelEvent;
import com.RevivalCore.util.handlers.SuperpowerHandler;

public class SuperpowerRender {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static boolean overrideSkin = true;

    @SubscribeEvent(receiveCanceled = true)
    public void onSetRotationAngels(RenderModelEvent.SetRotationAngels e) {
        if (overrideSkin && e.getEntity() instanceof EntityPlayer && e.model instanceof ModelPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            for (EffectSkinChange skinChange : EffectHandler.getEffectsByClass(player, EffectSkinChange.class)) {
                if (EffectHandler.canEffectBeDisplayed(skinChange, player)) {
                    mc.renderEngine.bindTexture(skinChange.texture);
                }
            }
        }
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent e) {
        e.getPlayer().refreshDisplayName();
    }

    @SubscribeEvent
    public void onRenderSpecificHand(RenderSpecificHandEvent e) {
        Superpowerbase superpower = SuperpowerHandler.getSuperpower(mc.player);
        if (superpower != null && superpower.getPlayerRenderer() != null)
            superpower.getPlayerRenderer().onRenderSpecificHandEvent(e);
    }

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent e) {
        Superpowerbase superpower = SuperpowerHandler.getSuperpower(mc.player);
        if (superpower != null && superpower.getPlayerRenderer() != null)
            superpower.getPlayerRenderer().onRenderHandEvent(e);
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent e) {
        Superpowerbase superpower = SuperpowerHandler.getSuperpower(mc.player);
        if (superpower != null && superpower.getPlayerRenderer() != null)
            superpower.getPlayerRenderer().onRenderGameOverlay(e);
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post e) {
        List<EffectTrail> trailEffects = new ArrayList<>();
        for (EffectTrail.EntityTrail trails : SuperpowerHandler.getSuperpowerCapability(e.getEntityPlayer()).getTrailEntities()) {
            for (EffectTrail effects : trails.effects) {
                if (!trailEffects.contains(effects)) {
                    trailEffects.add(effects);
                }
            }
        }

        for (EffectTrail trails : trailEffects) {
            trails.type.getTrailRenderer().renderTrail(e.getEntityPlayer(), trails, SuperpowerHandler.getSuperpowerCapability(e.getEntityPlayer()).getTrailEntities(), e.getPartialRenderTick());
        }
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent.Post e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.HELMET && this.mc.gameSettings.thirdPersonView == 0) {
            List<EffectHUD> effects = EffectHandler.getEffectsByClass(Minecraft.getMinecraft().player, EffectHUD.class);

            if (effects.size() <= 0)
                return;

            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableAlpha();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            ScaledResolution scaledRes = e.getResolution();

            for (EffectHUD hud : effects) {
                if (EffectHandler.canEffectBeDisplayed(hud, Minecraft.getMinecraft().player)) {

                    this.mc.getTextureManager().bindTexture(hud.texture);

                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                    bufferbuilder.pos(0.0D, (double) scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
                    bufferbuilder.pos((double) scaledRes.getScaledWidth(), (double) scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
                    bufferbuilder.pos((double) scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
                    bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
                    tessellator.draw();
                }
            }

            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {

            List<EffectTrail> trailEffects = new ArrayList<>();
            for (EffectTrail trails : EffectHandler.getEffectsByClass((EntityPlayer) e.getEntityLiving(), EffectTrail.class)) {
                if (EffectHandler.canEffectBeDisplayed(trails, (EntityPlayer) e.getEntityLiving())) {
                    trailEffects.add(trails);
                }
            }

            if (trailEffects.size() > 0) {

                ISuperpowerbaseCap cap = SuperpowerHandler.getSuperpowerCapability((EntityPlayer) e.getEntityLiving());
                LinkedList<EffectTrail.EntityTrail> list = cap.getTrailEntities();

                if (list.size() == 0) {
                    EffectTrail.EntityTrail trail = new EffectTrail.EntityTrail(e.getEntityLiving().getEntityWorld(), (EntityPlayer) e.getEntityLiving(), trailEffects.toArray(new EffectTrail[trailEffects.size()]));
                    cap.addTrailEntity(trail);
                    e.getEntityLiving().getEntityWorld().spawnEntity(trail);
                } else if (e.getEntityLiving().getDistance(list.getLast()) >= e.getEntityLiving().width * 1.1F) {
                    EffectTrail.EntityTrail trail = new EffectTrail.EntityTrail(e.getEntityLiving().getEntityWorld(), (EntityPlayer) e.getEntityLiving(), trailEffects.toArray(new EffectTrail[trailEffects.size()]));
                    cap.addTrailEntity(trail);
                    e.getEntityLiving().getEntityWorld().spawnEntity(trail);
                }
            }
        }
    }

    public interface ISuperpowerRenderer {

        @SideOnly(Side.CLIENT)
        void onRenderPlayer(RenderLivingBase<?> renderer, Minecraft mc, EntityPlayer player, Superpowerbase superpower, PowerContainer handler,
                            float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float mcScale);

        default void applyColor() {
            GlStateManager.color(1, 1, 1);
        }

        default void onRenderHandEvent(RenderHandEvent e) {
        }

        default void onRenderSpecificHandEvent(RenderSpecificHandEvent e) {
        }

        default void onRenderGameOverlay(RenderGameOverlayEvent e) {
        }

    }
}
