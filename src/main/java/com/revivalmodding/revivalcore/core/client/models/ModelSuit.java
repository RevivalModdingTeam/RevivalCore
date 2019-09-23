package com.revivalmodding.revivalcore.core.client.models;

import com.revivalmodding.revivalcore.core.client.render.SHRModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelSuit extends ModelBase {

    public SHRModelRenderer headPart;
    public SHRModelRenderer bodyPart;
    public SHRModelRenderer rightArm;
    public SHRModelRenderer leftArm;
    public SHRModelRenderer rightLeg;
    public SHRModelRenderer leftLeg;
    public ModelBiped.ArmPose leftArmPose;
    public ModelBiped.ArmPose rightArmPose;
    public boolean isSneak;

    public ModelSuit(int texWidth, int texHeight) {
        this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.textureWidth = texWidth;
        this.textureHeight = texHeight;
    }

    public static <T extends SHRModelRenderer> T validateAndAssign(T part) {
        if (part == null) {
            throw new NullPointerException("Suit cannot have null part!");
        }
        return part;
    }

    /**
     * Must be called in order to render properly
     **/
    public void setParentModels(SHRModelRenderer head, SHRModelRenderer body, SHRModelRenderer rightArm, SHRModelRenderer leftArm, SHRModelRenderer rightLeg, SHRModelRenderer leftLeg) {
        this.headPart = validateAndAssign(head);
        this.bodyPart = validateAndAssign(body);
        this.rightArm = validateAndAssign(rightArm);
        this.leftArm = validateAndAssign(leftArm);
        this.rightLeg = validateAndAssign(rightLeg);
        this.leftLeg = validateAndAssign(leftLeg);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        throw new IllegalArgumentException("This function is not acceptable for suit render! Use the one with EntityEquipmentSlot parameter");
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slot) {
        if(headPart == null || bodyPart == null || rightArm == null || leftArm == null || rightLeg == null || leftLeg == null)
            throw new IllegalArgumentException("Suit part is null! Make sure to call ModelSuit::setParentsModels before rendering!");
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        if (this.isChild) {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            //this.headPart.render(scale, slot);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bodyPart.render(scale, slot);
            this.rightArm.render(scale, slot);
            this.leftArm.render(scale, slot);
            this.rightLeg.render(scale, slot);
            this.leftLeg.render(scale, slot);
        } else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.headPart.render(scale, slot);
            this.bodyPart.render(scale, slot);
            this.rightArm.render(scale, slot);
            this.leftArm.render(scale, slot);
            this.rightLeg.render(scale, slot);
            this.leftLeg.render(scale, slot);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.headPart.rotateAngleY = netHeadYaw * 0.017453292F;
        if (flag) {
            this.headPart.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.headPart.rotateAngleX = headPitch * 0.017453292F;
        }
        this.bodyPart.rotateAngleY = 0.0F;
        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }
        if (f < 1.0F) {
            f = 1.0F;
        }
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;
        if (this.isRiding) {
            this.rightArm.rotateAngleX += -((float) Math.PI / 5F);
            this.leftArm.rotateAngleX += -((float) Math.PI / 5F);
            this.rightLeg.rotateAngleX = -1.4137167F;
            this.rightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.rightLeg.rotateAngleZ = 0.07853982F;
            this.leftLeg.rotateAngleX = -1.4137167F;
            this.leftLeg.rotateAngleY = -((float) Math.PI / 10F);
            this.leftLeg.rotateAngleZ = -0.07853982F;
        }
        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.leftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.leftArm.rotateAngleY = 0.5235988F;
                break;
            case ITEM:
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.leftArm.rotateAngleY = 0.0F;
        }
        switch (this.rightArmPose) {
            case EMPTY:
                this.rightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.rightArm.rotateAngleY = -0.5235988F;
                break;
            case ITEM:
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.rightArm.rotateAngleY = 0.0F;
        }
        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            SHRModelRenderer modelRenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.bodyPart.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;
            if (enumhandside == EnumHandSide.LEFT) {
                this.bodyPart.rotateAngleY *= -1.0F;
            }
            this.rightArm.rotationPointZ = MathHelper.sin(this.bodyPart.rotateAngleY) * 5.0F;
            this.rightArm.rotationPointX = -MathHelper.cos(this.bodyPart.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointZ = -MathHelper.sin(this.bodyPart.rotateAngleY) * 5.0F;
            this.leftArm.rotationPointX = MathHelper.cos(this.bodyPart.rotateAngleY) * 5.0F;
            this.rightArm.rotateAngleY += this.bodyPart.rotateAngleY;
            this.leftArm.rotateAngleY += this.bodyPart.rotateAngleY;
            this.leftArm.rotateAngleX += this.bodyPart.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.headPart.rotateAngleX - 0.7F) * 0.75F;
            modelRenderer.rotateAngleX = (float) ((double) modelRenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelRenderer.rotateAngleY += this.bodyPart.rotateAngleY * 2.0F;
            modelRenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }
        if (this.isSneak) {
            this.bodyPart.rotateAngleX = 0.5F;
            this.rightArm.rotateAngleX += 0.4F;
            this.leftArm.rotateAngleX += 0.4F;
            this.rightLeg.rotationPointZ = 4.0F;
            this.leftLeg.rotationPointZ = 4.0F;
            this.rightLeg.rotationPointY = 9.0F;
            this.leftLeg.rotationPointY = 9.0F;
            this.headPart.rotationPointY = 1.0F;
        } else {
            this.bodyPart.rotateAngleX = 0.0F;
            this.rightLeg.rotationPointZ = 0.1F;
            this.leftLeg.rotationPointZ = 0.1F;
            this.rightLeg.rotationPointY = 12.0F;
            this.leftLeg.rotationPointY = 12.0F;
            this.headPart.rotationPointY = 0.0F;
        }
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.rightArm.rotateAngleY = -0.1F + this.headPart.rotateAngleY;
            this.leftArm.rotateAngleY = 0.1F + this.headPart.rotateAngleY + 0.4F;
            this.rightArm.rotateAngleX = -((float) Math.PI / 2F) + this.headPart.rotateAngleX;
            this.leftArm.rotateAngleX = -((float) Math.PI / 2F) + this.headPart.rotateAngleX;
        } else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
            this.rightArm.rotateAngleY = -0.1F + this.headPart.rotateAngleY - 0.4F;
            this.leftArm.rotateAngleY = 0.1F + this.headPart.rotateAngleY;
            this.rightArm.rotateAngleX = -((float) Math.PI / 2F) + this.headPart.rotateAngleX;
            this.leftArm.rotateAngleX = -((float) Math.PI / 2F) + this.headPart.rotateAngleX;
        }
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

    protected SHRModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }
}
