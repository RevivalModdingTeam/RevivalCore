package com.revivalmodding.revivalcore.core.client.render;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.revivalmodding.revivalcore.util.handlers.client.SuitRenderHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class LayerSuitBase<E extends EntityLivingBase> implements LayerRenderer<E> {

    private final RenderLivingBase<?> renderer;
    @Nullable
    private final AbstractSuit suit;
    public ModelBiped mainModel;

    public LayerSuitBase(RenderLivingBase<?> renderer) {
        this(renderer, null);
    }

    public LayerSuitBase(RenderLivingBase<?> renderer, AbstractSuit suit) {
        this.renderer = renderer;
        this.initModel();
        this.suit = suit;
    }

    public void initModel() {
        mainModel = new ModelBiped(0.4F);
    }

    @Override
    public void doRenderLayer(E entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        for (EntityEquipmentSlot slot : SuitRenderHandler.ARMOR) {
            this.renderLayerPart(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, slot);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private void renderLayerPart(E entity, float limbSwing, float limbSwingAmount, float partial, float age, float headYaw, float headPitch, float scale, EntityEquipmentSlot slot) {
        if(suit != null) {
            // For entities with defined suits
            mainModel.setModelAttributes(this.renderer.getMainModel());
            mainModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partial);
            this.setVisibleParts(slot);
            this.renderer.bindTexture(this.suit.getHelmet().get3DTexture());
            mainModel.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
            return;
        }
        // For players
        ItemStack stack = entity.getItemStackFromSlot(slot);
        if (!stack.isEmpty() && stack.getItem() instanceof ItemSuit) {
            ItemSuit suit = (ItemSuit) stack.getItem();
            mainModel.setModelAttributes(this.renderer.getMainModel());
            mainModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partial);
            this.setVisibleParts(slot);
            this.renderer.bindTexture(suit.get3DTexture());
            mainModel.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
        }
    }

    private void setVisibleParts(EntityEquipmentSlot slot) {
        mainModel.setVisible(false);
        switch (slot) {
            case HEAD: {
                mainModel.bipedHead.showModel = true;
                mainModel.bipedHeadwear.showModel = true;
                break;
            }

            case CHEST: {
                mainModel.bipedBody.showModel = true;
                mainModel.bipedRightArm.showModel = true;
                mainModel.bipedLeftArm.showModel = true;
                break;
            }

            case LEGS: {
                mainModel.bipedRightLeg.showModel = true;
                mainModel.bipedLeftLeg.showModel = true;
                break;
            }
        }
    }
}
