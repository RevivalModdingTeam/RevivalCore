package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class SuitRenderHandler {

    private static final EntityEquipmentSlot[] ARMOR = {EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.HEAD};

    // TODO: in future version implement 2D model
    @SubscribeEvent
    public static void renderSuitOnPlayer(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        for(EntityEquipmentSlot slot : ARMOR) {
            ItemStack stack = player.getItemStackFromSlot(slot);
            if(stack.getItem() instanceof ItemSuit) {
                ItemSuit suit = (ItemSuit)stack.getItem();


            }
        }
    }

    private static double interpolate(double prev, double current, double partial) {
        return prev + (current - prev) * partial;
    }

    private static float prepareScale(EntityPlayer player, float partial) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.scale(1.9F, 1.9F, 1.9F);
        GlStateManager.translate(0.0F, -1.5F, 0.0F);
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
}
