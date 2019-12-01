package com.revivalmodding.revivalcore.core.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class OriginalModelSuit extends ModelBase {

    protected final EntityEquipmentSlot slot;
    public boolean isSneak;
    public ModelBiped.ArmPose leftArmPose;
    public ModelBiped.ArmPose rightArmPose;
    protected ModelRenderer bipedHead;
    protected ModelRenderer bipedBody;
    protected ModelRenderer bipedRightArm;
    protected ModelRenderer bipedLeftArm;
    protected ModelRenderer bipedRightLeg;
    protected ModelRenderer bipedLeftLeg;
    private boolean modelPrepared;

    public OriginalModelSuit(EntityEquipmentSlot slot) {
        ModelRenderer EMPTY = new ModelRenderer(this);
        this.slot = slot;
        this.modelPrepared = false;

        this.bipedHead = EMPTY;
        this.bipedBody = EMPTY;
        this.bipedRightArm = EMPTY;
        this.bipedLeftArm = EMPTY;
        this.bipedRightLeg = EMPTY;
        this.bipedLeftLeg = EMPTY;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();

        if (this.isChild) {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
        } else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            this.bipedHead.render(scale);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
        }

        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag) {
            this.bipedHead.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
        }

        this.bipedBody.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;
        float f = 1.0F;

        if (flag) {
            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding) {
            this.bipedRightArm.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;

        switch (this.leftArmPose) {
            case EMPTY:
                this.bipedLeftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedLeftArm.rotateAngleY = 0.5235988F;
                break;
            case ITEM:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.bipedRightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedRightArm.rotateAngleY = -0.5235988F;
                break;
            case ITEM:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.bipedRightArm.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.bipedBody.rotateAngleY *= -1.0F;
            }

            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        if (this.isSneak) {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
        } else {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }
    }

    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
    }

    protected EnumHandSide getMainHand(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        } else {
            return EnumHandSide.RIGHT;
        }
    }

    // TODO
    protected ModelBiped.ArmPose getArmPose(Entity entity) {
        return ModelBiped.ArmPose.EMPTY;
    }
}
