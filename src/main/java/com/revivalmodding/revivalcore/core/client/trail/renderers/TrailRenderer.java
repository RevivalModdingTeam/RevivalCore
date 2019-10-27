package com.revivalmodding.revivalcore.core.client.trail.renderers;

import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public abstract class TrailRenderer {

    public abstract void renderTrail(EntityPlayer player, Trail trail, @Nullable TrailOptionalData trailData, float partialTick);

    public void drawLine(Vec3d start, Vec3d end, float width, float r, float g, float b, float a, double xPos, double yPos, double zPos) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        this.preRender(start, end, width, r, g, b, a);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.setTranslation(-xPos, -yPos, -zPos);
        GlStateManager.glLineWidth(width);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(start.x, start.y, start.z).color(r, g, b, a).endVertex();
        bufferBuilder.pos(end.x, end.y, end.z).color(r, g, b, a).endVertex();
        tessellator.draw();
        bufferBuilder.setTranslation(0, 0, 0);
        this.postRender(start, end, width, r, g, b, a);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
    }

    public void renderTrailIntoGUI() {

    }

    public void preRender(Vec3d start, Vec3d end, float width, float r, float g, float b, float a) {

    }

    public void postRender(Vec3d start, Vec3d end, float width, float r, float g, float b, float a) {

    }

    public double interpolate(double current, double previous, float partial) {
        return previous + (current - previous) * partial;
    }
}
