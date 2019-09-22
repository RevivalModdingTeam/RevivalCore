package com.revivalmodding.revivalcore.core.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelHeadTest extends ModelBiped {

    public ModelRenderer headbase;
    public ModelRenderer head101;
    public ModelRenderer head102;
    public ModelRenderer head24;
    public ModelRenderer head26;
    public ModelRenderer head29;
    public ModelRenderer head28;
    public ModelRenderer head27;
    public ModelRenderer head25;
    public ModelRenderer head46;
    public ModelRenderer head47;
    public ModelRenderer head48;
    public ModelRenderer head50;
    public ModelRenderer head49;
    public ModelRenderer head51;

    public ModelHeadTest() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.head24 = new ModelRenderer(this, 85, 95);
        this.head24.setRotationPoint(1.6F, 0.0F, 0.6F);
        this.head24.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head24, 0.0F, 0.7285004297824331F, 0.0F);
        this.head25 = new ModelRenderer(this, 87, 91);
        this.head25.setRotationPoint(-3.2F, 0.0F, 0.6F);
        this.head25.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head25, 0.0F, 0.7285004297824331F, 0.0F);
        this.head102 = new ModelRenderer(this, 85, 68);
        this.head102.setRotationPoint(0.08F, 4.05F, -0.01F);
        this.head102.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head102, 0.0F, 0.0F, 0.9560913642424937F);
        this.headbase = new ModelRenderer(this, 96, 71);
        this.headbase.setRotationPoint(0.0F, -8.0F, -2.0F);
        this.headbase.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.head27 = new ModelRenderer(this, 84, 95);
        this.head27.setRotationPoint(-2.2F, 0.9F, 0.6F);
        this.head27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(head27, 0.0F, 0.7285004297824331F, 0.8651597102135892F);
        this.head28 = new ModelRenderer(this, 85, 95);
        this.head28.setRotationPoint(-2.3F, 1.8F, 0.6F);
        this.head28.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(head28, 0.0F, 0.7285004297824331F, -0.5918411493512771F);
        this.head26 = new ModelRenderer(this, 85, 95);
        this.head26.setRotationPoint(1.1F, 1.9F, 0.6F);
        this.head26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(head26, 0.0F, 0.7285004297824331F, -0.8651597102135892F);
        this.head46 = new ModelRenderer(this, 28, 70);
        this.head46.setRotationPoint(2.8F, 1.4F, 4.3F);
        this.head46.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head46, -0.4553564018453205F, 0.6373942428283291F, -0.27314402793711257F);
        this.head50 = new ModelRenderer(this, 32, 74);
        this.head50.setRotationPoint(-3.5F, 2.6F, 4.9F);
        this.head50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(head50, -1.5707963267948966F, 0.0F, 0.7285004297824331F);
        this.head47 = new ModelRenderer(this, 27, 70);
        this.head47.setRotationPoint(3.5F, 2.5F, 4.8F);
        this.head47.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(head47, -1.5707963267948966F, 0.0F, 0.7285004297824331F);
        this.head101 = new ModelRenderer(this, 83, 69);
        this.head101.setRotationPoint(-0.6F, 4.85F, -0.01F);
        this.head101.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head101, 0.0F, 0.0F, -0.9560913642424937F);
        this.head51 = new ModelRenderer(this, 29, 70);
        this.head51.setRotationPoint(-3.6F, 3.1F, 4.0F);
        this.head51.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head51, -0.4553564018453205F, -0.6373942428283291F, 0.27314402793711257F);
        this.head49 = new ModelRenderer(this, 29, 70);
        this.head49.setRotationPoint(-3.6F, 1.3F, 3.8F);
        this.head49.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head49, -0.4553564018453205F, -0.6373942428283291F, 0.27314402793711257F);
        this.head29 = new ModelRenderer(this, 85, 65);
        this.head29.setRotationPoint(1.0F, 1.0F, 0.6F);
        this.head29.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(head29, 0.0F, 0.7285004297824331F, 0.5918411493512771F);
        this.head48 = new ModelRenderer(this, 28, 72);
        this.head48.setRotationPoint(2.8F, 3.3F, 4.6F);
        this.head48.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(head48, -0.4553564018453205F, 0.6373942428283291F, -0.27314402793711257F);
        this.headbase.addChild(this.head24);
        this.headbase.addChild(this.head25);
        this.headbase.addChild(this.head102);
        this.headbase.addChild(this.head27);
        this.headbase.addChild(this.head28);
        this.headbase.addChild(this.head26);
        this.headbase.addChild(this.head46);
        this.headbase.addChild(this.head50);
        this.headbase.addChild(this.head47);
        this.headbase.addChild(this.head101);
        this.headbase.addChild(this.head51);
        this.headbase.addChild(this.head49);
        this.headbase.addChild(this.head29);
        this.headbase.addChild(this.head48);
        this.bipedHead.addChild(headbase);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            this.headbase.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
        }
        else
        {
            if (entityIn.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.headbase.render(scale);
        }

        GlStateManager.popMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
