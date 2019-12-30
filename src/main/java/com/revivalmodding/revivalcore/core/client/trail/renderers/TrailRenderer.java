package com.revivalmodding.revivalcore.core.client.trail.renderers;

import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public abstract class TrailRenderer {

    public abstract void renderTrail(EntityPlayer player, Trail trail, @Nullable TrailOptionalData trailData, float partialTick);

    public void drawLine(Vec3d start, Vec3d end, float width, float r1, float g1, float b1, float r2, float g2, float b2, float a, double xPos, double yPos, double zPos) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.disableLighting();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.setTranslation(-xPos, -yPos, -zPos);
        GlStateManager.glLineWidth(width);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(start.x, start.y, start.z).color(r1, g1, b1, a).endVertex();
        bufferBuilder.pos(end.x, end.y, end.z).color(r2, g2, b2, a).endVertex();
        tessellator.draw();
        bufferBuilder.setTranslation(0, 0, 0);
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
    }

    public void drawLine(Vec3d start, Vec3d end, float width, float r, float g, float b, float a, double xPos, double yPos, double zPos) {
        this.drawLine(start, end, width, r, g, b, r, g, b, a, xPos, yPos, zPos);
    }

    public void renderTrailIntoGUI(Trail trail, @Nullable TrailOptionalData optionalData, int x, int y, int w, int h) {
        ImageHelper.drawColorShape(x, y, w, h, 0.2F, 0.2F, 0.2F);
        for(int i = 0; i < trail.getLength(); i++) {
            int modifier = 10;
            int separator = x + (int)(w * ((float)i / trail.getLength()));
            int j = i + 1;
            int next = x + (int)(w * ((float)j / trail.getLength()));
            int color = optionalData != null && optionalData.stageColors != null && i < optionalData.stageColors.length ? optionalData.stageColors[i] : trail.getColor();
            int color1 = optionalData != null && optionalData.stageColors != null && i + 1 < optionalData.stageColors.length ? optionalData.stageColors[i + 1] : color;
            float r1 = ((color >> 16) & 255) / 255F;
            float g1 = ((color >>  8) & 255) / 255F;
            float b1 = (color & 255) / 255F;
            float r2 = ((color1 >> 16) & 255) / 255.0F;
            float g2 = ((color1 >> 8) & 255) / 255.0F;
            float b2 = ((color1) & 255) / 255.0F;
            GlStateManager.glLineWidth(trail.getWidth() - i);
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            builder.pos(separator, (y + h / 2) + (i % 2 == 0 ? modifier : -modifier), 0).color(r1, g1, b1, 1.0F).endVertex();
            builder.pos(next, (y + h / 2) + (i % 2 == 0 ? -modifier : modifier), 0).color(r2, g2, b2, 1.0F).endVertex();
            tessellator.draw();
            GlStateManager.shadeModel(GL11.GL_FLAT);
            if(i == 0) continue;
            ImageHelper.drawColorLine(separator, y - 1, separator, y + h + 1, 1.0F, 0.0F, 0.0F, 2);
        }
        for(int i = 0; i < trail.getLength(); i++) {
            // Because of font renderer weirdness ¯\_(ツ)_/¯
            int separator = x + (int)(w * ((float)i / trail.getLength()));
            Minecraft.getMinecraft().fontRenderer.drawString(i + "", separator, y - 10, 0x121212);
        }
    }

    public double interpolate(double current, double previous, float partial) {
        return previous + (current - previous) * partial;
    }
}
