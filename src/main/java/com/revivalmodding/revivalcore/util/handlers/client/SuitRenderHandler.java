package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class SuitRenderHandler {

    public static final EntityEquipmentSlot[] ARMOR = {EntityEquipmentSlot.LEGS, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD};
    private static final ModelBiped SUIT_2D = new ModelBiped(0.6F);
    private static final ResourceLocation TEXTURE_2DTEST = new ResourceLocation(RevivalCore.MODID + ":textures/template.png");

    @SubscribeEvent
    public static void renderSuitOnPlayer(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        for(EntityEquipmentSlot slot : ARMOR) {
            ItemStack stack = player.getItemStackFromSlot(slot);
            if(stack.getItem() instanceof ItemSuit) {
                // TODO 3D Render
                ItemSuit suit = (ItemSuit)stack.getItem();
                ModelPlayer main = e.getRenderer().getMainModel();
                ResourceLocation texture = suit.get3DTexture();
                float partial = e.getPartialRenderTick();
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                boolean shouldSit = player.isRiding() && (player.getRidingEntity() != null && player.getRidingEntity().shouldRiderSit());
                boolean sneak = main.isSneak;
                SUIT_2D.swingProgress = main.swingProgress;
                float f = normalizeAndInterpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partial);
                float f1 = normalizeAndInterpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partial);
                float yaw = f1 - f;
                if (shouldSit && player.getRidingEntity() instanceof EntityLivingBase) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase) player.getRidingEntity();
                    f = normalizeAndInterpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partial);
                    yaw = f1 - f;
                    float f3 = MathHelper.wrapDegrees(yaw);
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
                    yaw = f1 - f;
                }
                float f7 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partial;
                float f8 = player.ticksExisted + partial;
                GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
                float f4 = prepareScale(player, partial);
                float f5 = 0.0F;
                float f6 = 0.0F;
                if (!player.isRiding()) {
                    f5 = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partial;
                    f6 = player.limbSwing - player.limbSwingAmount * (1.0F - partial);
                    if (player.isChild()) {
                        f6 *= 3.0F;
                    }
                    if (f5 > 1.0F) {
                        f5 = 1.0F;
                    }
                    yaw = f1 - f;
                }
                GlStateManager.enableAlpha();
                SUIT_2D.setLivingAnimations(player, f6, f5, partial);
                SUIT_2D.setRotationAngles(f6, f5, f8, yaw, f7, f4, player);
                Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
                GlStateManager.translate(0, -1.3, 0.08);
                GlStateManager.scale(1.8, 1.8, 1.8);
                doSuitRender2D(player, f6, f5, f8, yaw, f7, f4, slot);
                GlStateManager.disableRescaleNormal();
                GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
                GlStateManager.enableTexture2D();
                GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
            }
        }
    }

    private static void doSuitRender2D(Entity e, float limbSwing, float limbSwingAmount, float ticksExisted, float headYaw, float pitch, float scaleFactor, EntityEquipmentSlot slot) {
        SUIT_2D.setVisible(false);
        switch (slot) {
            case HEAD: {
                // too big smh
                //SUIT_2D.bipedHead.showModel = true;
                SUIT_2D.bipedHeadwear.showModel = true;
                break;
            }
            case CHEST: {
                SUIT_2D.bipedBody.showModel = true;
                SUIT_2D.bipedRightArm.showModel = true;
                SUIT_2D.bipedLeftArm.showModel = true;
                break;
            }
            case LEGS: {
                SUIT_2D.bipedRightLeg.showModel = true;
                SUIT_2D.bipedLeftLeg.showModel = true;
                break;
            }
        }
        SUIT_2D.render(e, limbSwing, limbSwingAmount, ticksExisted, headYaw, pitch, scaleFactor);
    }

    private static double interpolate(double prev, double current, double partial) {
        return prev + (current - prev) * partial;
    }

    private static float prepareScale(EntityPlayer player, float partial) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.scale(0.97F, 0.97F, 0.97F);
        GlStateManager.translate(0.0F, -1.5F, -0.0625F);
        return 0.0625F;
    }

    private static float normalizeAndInterpolateRotation(float prev, float current, float partial) {
        float f = current - prev;
        while (f < -180F) f += 360F;
        while (f >= 180.0F) f -= 360.0F;
        return prev + partial * f;
    }

    private static boolean isWearingSuitPart(EntityPlayer player) {
        for(EntityEquipmentSlot slot : ARMOR) {
            if(player.getItemStackFromSlot(slot).getItem() instanceof ItemSuit) {
                return true;
            }
        }
        return false;
    }

    private static void copyModelAngles(ModelRenderer from, ModelRenderer to) {
        to.rotateAngleX = from.rotateAngleX;
        to.rotateAngleY = from.rotateAngleY;
        to.rotateAngleZ = from.rotateAngleZ;
    }
}
