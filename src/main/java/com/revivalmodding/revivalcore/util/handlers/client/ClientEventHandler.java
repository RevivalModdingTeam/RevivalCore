package com.revivalmodding.revivalcore.util.handlers.client;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler
{
    @SubscribeEvent
    public static void renderGuiOverlay(RenderGameOverlayEvent.Post event) {
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
            EntityPlayer player = Minecraft.getMinecraft().player;
            PlayerAbilityData data = CoreCapabilityImpl.getInstance(player).getAbilityData();
            Ability[] abilities = data.getActiveAbilities();
            ScaledResolution sr = event.getResolution();
            int idx = 0;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            for(int i = 0; i < abilities.length; i++) {
                Ability ability = abilities[i];
                if(ability == null) continue;
                int x = sr.getScaledWidth() - idx * 32 - 10;
                GlStateManager.disableTexture2D();
                ResourceLocation guiImg = ability.getGuiIcon();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder builder = tessellator.getBuffer();
                builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
                builder.pos(x, 42, 0).color(0.0F, 0.0F, 0.0F, 0.35F).endVertex();
                builder.pos(x + 32, 42, 0).color(0.0F, 0.0F, 0.0F, 0.35F).endVertex();
                builder.pos(x + 32, 10, 0).color(0.0F, 0.0F, 0.0F, 0.35F).endVertex();
                builder.pos(x, 10, 0).color(0.0F, 0.0F, 0.0F, 0.35F).endVertex();
                if(ability.hasCooldown()) {
                    float f = 1.0F - (ability.getCooldown() / (float)ability.cooldownLimit);
                    builder.pos(x, 42, 0).color(1.0F, 1.0F, 1.0F, 0.25F).endVertex();
                    builder.pos(x + 32, 42, 0).color(1.0F, 1.0F, 1.0F, 0.25F).endVertex();
                    builder.pos(x + 32, 42 - 32 * f, 0).color(1.0F, 1.0F, 1.0F, 0.25F).endVertex();
                    builder.pos(x, 42 - 32 * f, 0).color(1.0F, 1.0F, 1.0F, 0.25F).endVertex();
                }
                tessellator.draw();
                GlStateManager.enableTexture2D();
                builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                builder.pos(x + 4, 38, 0).tex(0, 1).endVertex();
                builder.pos(x + 24, 38, 0).tex(1, 1).endVertex();
                builder.pos(x + 24, 14, 0).tex(1, 0).endVertex();
                builder.pos(x + 4, 14, 0).tex(0, 0).endVertex();
                tessellator.draw();
                ++idx;
                KeyBinding keyBinding = i == 0 ? Keybinds.POWER1 : i == 1 ? Keybinds.POWER2 : Keybinds.POWER3;
                int color = 0xFFFFFFFF;
                if(ability.hasCooldown()) {
                    color = 0xFFBB0000;
                } else if(ability.isToggled()) {
                    color = 0xFF00CC00;
                }
                String key = keyBinding.getKeyDescription();
                renderer.drawStringWithShadow(key, x + (32 - renderer.getStringWidth(key)) / 2, 45, color);
            }
            GlStateManager.disableBlend();
        }
    }

    @SubscribeEvent
    public static void renderPlayerPre(RenderPlayerEvent.Pre e) {
        Random r = RevivalCore.getRandom();
        EntityPlayer player = e.getEntityPlayer();
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        GlStateManager.pushMatrix();
        if (powerData.isVibrating()) {
            GlStateManager.enableBlend();
            for (int i = 0; i < 1; i++) {
                GlStateManager.translate((r.nextFloat() - 1F) / 80, 0, (r.nextFloat() - 1F) / 80);
                GlStateManager.color(1, 1, 1, 0.4F);
            }
        }
    }

    @SubscribeEvent
    public static void renderPlayerPost(RenderPlayerEvent.Post e) {
        EntityPlayer player = e.getEntityPlayer();
        PlayerMetaPowerData powerData = CoreCapabilityImpl.getInstance(player).getMetaPowerData();
        if (powerData.isVibrating()) {
            GlStateManager.disableBlend();
        }
        GlStateManager.popMatrix();
    }
}
