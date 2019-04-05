package com.revivalcore.superpowerbase.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.revivalcore.superpowerbase.suitsets.SuitSet;

import java.util.Random;

public class ModelBipedSuitSet extends ModelBiped {

    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedLeftLegwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedBodyWear;

    public boolean smallArms;
    public SuitSet suitSet;
    public EntityEquipmentSlot slot;
    public ResourceLocation normalTex;
    public ResourceLocation lightsTex;
    public boolean vibrating;

    public ModelBipedSuitSet(float f, String normalTex, String lightTex, SuitSet hero, EntityEquipmentSlot slot, boolean hasSmallArms, boolean vibrating) {
        this(f, normalTex, lightTex, hero, slot, hasSmallArms, vibrating, 64, 64);
    }

    public ModelBipedSuitSet(float f, String normalTex, String lightTex, SuitSet hero, EntityEquipmentSlot slot, boolean hasSmallArms, boolean vibrating, int width, int height) {
        super(f, 0F, width, height);

        this.textureWidth = width;
        this.textureHeight = height;
        this.suitSet = hero;
        this.slot = slot;
        this.vibrating = vibrating;
        this.normalTex = new ResourceLocation(normalTex);
        if (lightTex != null)
            this.lightsTex = new ResourceLocation(lightTex);

        float scale = f + 0.5F;

        if (hasSmallArms) {
            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, f);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);

            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, f);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, scale);
            this.bipedLeftArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, scale);
            this.bipedRightArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        } else {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
            this.bipedLeftArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
            this.bipedRightArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        }

        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.bipedLeftLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.bipedRightLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedBody.addChild(bipedBodyWear);
        this.bipedRightArm.addChild(bipedRightArmwear);
        this.bipedLeftArm.addChild(bipedLeftArmwear);
        this.bipedRightLeg.addChild(bipedRightLegwear);
        this.bipedLeftLeg.addChild(bipedLeftLegwear);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        renderModel(entity, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entityIn) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entityIn);

        if (entityIn instanceof EntityArmorStand) {
            EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
            this.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
            this.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
            this.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
            this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
            this.bipedBody.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
            this.bipedBody.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
            this.bipedBody.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
            this.bipedLeftArm.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
            this.bipedLeftArm.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
            this.bipedLeftArm.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
            this.bipedRightArm.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
            this.bipedRightArm.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
            this.bipedRightArm.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
            this.bipedLeftLeg.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
            this.bipedLeftLeg.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
            this.bipedLeftLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
            this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
            this.bipedRightLeg.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
            this.bipedRightLeg.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
            this.bipedRightLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
            this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
            copyModelAngles(this.bipedHead, this.bipedHeadwear);
        }
    }

    public void renderModel(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setModelVisibility();

        renderSuit(entity, f, f1, f2, f3, f4, f5);

        if (vibrating) {
            for (int i = 0; i < 10; i++) {
                GlStateManager.pushMatrix();
                Random rand = new Random();
                GlStateManager.translate((rand.nextFloat() - 0.5F) / 15, 0, (rand.nextFloat() - 0.5F) / 15);
                GlStateManager.color(1, 1, 1, 0.3F);
                renderSuit(entity, f, f1, f2, f3, f4, f5);
                GlStateManager.popMatrix();
            }
        }
    }

    public void renderSuit(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.suitSet.bindArmorTexture(this.suitSet, entity, f, f1, f2, f3, f4, f5, normalTex, lightsTex, false, this.slot, this.smallArms);
        GlStateManager.enableBlend();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        super.render(entity, f, f1, f2, f3, f4, f5);

        if (suitSet.hasGlowyThings((EntityLivingBase) entity, slot)) {
            this.suitSet.bindArmorTexture(this.suitSet, entity, f, f1, f2, f3, f4, f5, normalTex, lightsTex, true, this.slot, this.smallArms);
            GlStateManager.pushMatrix();
            GlStateManager.color(1, 1, 1, suitSet.getGlowOpacity(this.suitSet, (EntityLivingBase) entity, slot));
            GlStateManager.disableLighting();
            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            super.render(entity, f, f1, f2, f3, f4, f5);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public void setModelVisibility() {
        this.setVisible(true);
    }
}
