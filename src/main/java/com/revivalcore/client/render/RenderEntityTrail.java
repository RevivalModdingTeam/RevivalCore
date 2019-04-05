package com.revivalcore.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import com.RevivalCore.superpowerbase.effects.EffectTrail;
import com.RevivalCore.superpowerbase.effects.EffectTrail.EntityTrail;
import com.RevivalCore.superpowerbase.models.ModelTrail;
import com.RevivalCore.util.handlers.SuperpowerHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class RenderEntityTrail extends RenderLivingBase<EntityTrail> {
    public float alpha;

    public RenderEntityTrail(RenderManager renderManager) {
        super(renderManager, new ModelTrail(0f, false), 0);
    }

    @Override
    public void doRender(EntityTrail entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.owner == Minecraft.getMinecraft().player && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            return;
        }

        List<EffectTrail> trailEffects = new ArrayList<>();
        for (EntityTrail trails : SuperpowerHandler.getSuperpowerCapability(entity.owner).getTrailEntities()) {
            for (EffectTrail effects : trails.effects) {
                if (!trailEffects.contains(effects)) {
                    trailEffects.add(effects);
                }
            }
        }

        Color color = new Color(0, 0, 0);
        boolean b = false;
        for (EffectTrail trails : trailEffects) {
            if (trails.type.getTrailRenderer().preRenderSpeedMirage(entity, trails, partialTicks)) {
                b = true;
                color = trails.color;
            }
        }

        if (!b) return;

        partialTicks = 1F;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        EntityPlayer player = null;

        if (entity.owner instanceof EntityPlayer)
            player = entity.owner;

        String skinType = player instanceof AbstractClientPlayer ? ((AbstractClientPlayer) player).getSkinType() : "default";
        this.mainModel = new ModelTrail(0F, skinType.equalsIgnoreCase("slim"));

        float abc = entity.swingProgress - entity.prevSwingProgress;
        this.mainModel.swingProgress = entity.prevSwingProgress + (abc < 0.0F ? abc++ : abc) * partialTicks;
        boolean shouldSit = entity.isRiding() && (entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
        this.mainModel.isRiding = shouldSit;
        this.mainModel.isChild = entity.isChild();
        ModelTrail model = (ModelTrail) this.mainModel;
        model.bipedBodyWear.showModel = player.isWearing(EnumPlayerModelParts.JACKET);
        model.bipedHeadwear.showModel = player.isWearing(EnumPlayerModelParts.HAT);
        model.bipedLeftArmwear.showModel = player.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
        model.bipedRightArmwear.showModel = player.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
        model.bipedRightLegwear.showModel = player.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
        model.bipedLeftLegwear.showModel = player.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);

        this.alpha = entity.alpha;

        try {
            float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
            float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
            float f2 = f1 - f;

            if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase) entity.getRidingEntity();
                f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
                f2 = f1 - f;
                float f3 = MathHelper.wrapDegrees(f2);

                if (f3 < -85.0F) {
                    f3 = -85.0F;
                }

                if (f3 >= 85.0F) {
                    f3 = 85.0F;
                }

                f = f1 - f3;

                if (f3 * f3 > 2500.0F) {
                    f += f3 * 0.2F;
                }
            }

            float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
            this.renderLivingAt(entity, x, y, z);
            float f8 = this.handleRotationFloat(entity, partialTicks);
            this.applyRotations(entity, f8, f, partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(entity, partialTicks);
            GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
            GlStateManager.translate(0.0F, 0.1F, 0.0F);
            float f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
            float f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);

            if (entity.isChild()) {
                f6 *= 3.0F;
            }

            if (f5 > 1.0F) {
                f5 = 1.0F;
            }

            GlStateManager.enableAlpha();
            this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
            this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, 0.0625F, entity);

            {
                boolean flag = this.setDoRenderBrightness(entity, partialTicks);

                float scale = entity.height / 1.8F;
                GlStateManager.translate(0F, -1.400494382F * scale + 1.400494382F, 0F);
                GlStateManager.scale(scale, scale, scale);
                this.renderModel(entity, f6, f5, f8, f2, f7, 0.0625F);
                this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, 0.0625F);

                GlStateManager.disableTexture2D();
                float progress = MathHelper.clamp(1F - (entity.ticksExisted + partialTicks) / 10F, 0F, 0.5F);
                progress = Math.max(progress - 0.3f, 0.1f);
                GlStateManager.color((float) color.getRed() / 255F, (float) color.getGreen() / 255F, (float) color.getBlue() / 255F, progress);
                this.renderModel(entity, f6, f5, f8, f2, f7, 0.0625F);
                this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, 0.0625F);
                GlStateManager.enableTexture2D();

                if (flag) {
                    this.unsetBrightness();
                }

                GlStateManager.depthMask(true);

            }

            GlStateManager.disableRescaleNormal();
        } catch (Exception exception) {
        }

        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.colorMask(true, true, true, true);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GlStateManager.popMatrix();

    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTrail entity) {
        if (entity.owner instanceof AbstractClientPlayer) {
            return ((AbstractClientPlayer) entity.owner).getLocationSkin();
        }
        return null;
    }


}
