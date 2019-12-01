package com.revivalmodding.revivalcore.core.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelSuit extends ModelPlayer {

	public boolean smallArms;
	private static ModelBiped cachedModel;

	public ModelRenderer bipedLeftArmwear;
	public ModelRenderer bipedRightArmwear;
	public ModelRenderer bipedLeftLegwear;
	public ModelRenderer bipedRightLegwear;
	public ModelRenderer bipedBodyWear;

	public ModelSuit(float f, boolean hasSmallArms) {
		super(f, hasSmallArms);

		this.textureWidth = 64;
		this.textureHeight = 64;
		
		float scale = f + 0.5F;

        if (hasSmallArms) {
            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, f);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);

            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, f);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
            this.bipedLeftArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
            this.bipedRightArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        } else {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, f);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            this.bipedLeftArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            this.bipedRightArmwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        }

        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedRightLegwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedBody.addChild(bipedBodyWear);
        this.bipedRightArm.addChild(bipedRightArmwear);
        this.bipedLeftArm.addChild(bipedLeftArmwear);
        this.bipedRightLeg.addChild(bipedRightLegwear);
        this.bipedLeftLeg.addChild(bipedLeftLegwear);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        if (entityIn instanceof EntityArmorStand) {
            EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
            this.bipedHead.rotateAngleX = 0.019F * entityarmorstand.getHeadRotation().getX();
            this.bipedHead.rotateAngleY = 0.019F * entityarmorstand.getHeadRotation().getY();
            this.bipedHead.rotateAngleZ = 0.019F * entityarmorstand.getHeadRotation().getZ();
            this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
            this.bipedBody.rotateAngleX = 0.019F * entityarmorstand.getBodyRotation().getX();
            this.bipedBody.rotateAngleY = 0.019F * entityarmorstand.getBodyRotation().getY();
            this.bipedBody.rotateAngleZ = 0.019F * entityarmorstand.getBodyRotation().getZ();
            this.bipedLeftArm.rotateAngleX = 0.019F * entityarmorstand.getLeftArmRotation().getX();
            this.bipedLeftArm.rotateAngleY = 0.019F * entityarmorstand.getLeftArmRotation().getY();
            this.bipedLeftArm.rotateAngleZ = 0.019F * entityarmorstand.getLeftArmRotation().getZ();
            this.bipedRightArm.rotateAngleX = 0.019F * entityarmorstand.getRightArmRotation().getX();
            this.bipedRightArm.rotateAngleY = 0.019F * entityarmorstand.getRightArmRotation().getY();
            this.bipedRightArm.rotateAngleZ = 0.019F * entityarmorstand.getRightArmRotation().getZ();
            this.bipedLeftLeg.rotateAngleX = 0.019F * entityarmorstand.getLeftLegRotation().getX();
            this.bipedLeftLeg.rotateAngleY = 0.019F * entityarmorstand.getLeftLegRotation().getY();
            this.bipedLeftLeg.rotateAngleZ = 0.019F * entityarmorstand.getLeftLegRotation().getZ();
            this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
            this.bipedRightLeg.rotateAngleX = 0.019F * entityarmorstand.getRightLegRotation().getX();
            this.bipedRightLeg.rotateAngleY = 0.019F * entityarmorstand.getRightLegRotation().getY();
            this.bipedRightLeg.rotateAngleZ = 0.019F * entityarmorstand.getRightLegRotation().getZ();
            this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
            copyModelAngles(this.bipedHead, this.bipedHeadwear);
        }

        if(entityIn instanceof EntityZombie) {
            boolean flag = entityIn instanceof EntityZombie && ((EntityZombie)entityIn).isArmsRaised();
            float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
            this.bipedRightArm.rotateAngleX = f2;
            this.bipedLeftArm.rotateAngleX = f2;
            this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
            this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }

        if (cachedModel != null) {
            copyModelAngles(cachedModel.bipedHead, this.bipedHead);
            copyModelAngles(cachedModel.bipedBody, this.bipedBody);
            copyModelAngles(cachedModel.bipedRightArm, this.bipedRightArm);
            copyModelAngles(cachedModel.bipedLeftArm, this.bipedLeftArm);
            copyModelAngles(cachedModel.bipedRightLeg, this.bipedRightLeg);
            copyModelAngles(cachedModel.bipedLeftLeg, this.bipedLeftLeg);

            cachedModel = null;
        }
    }

    @Override
    public void setModelAttributes(ModelBase model) {

        this.textureWidth = 64;
        this.textureHeight = 64;

        super.setModelAttributes(model);

        if (model instanceof ModelBiped) {
            cachedModel = (ModelBiped) model;
        }
    }



    public void setModelVisibility() {
        this.setVisible(true);
    }
}