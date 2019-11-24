package com.revivalmodding.revivalcore.core.client.trail.renderers;

import com.revivalmodding.revivalcore.core.client.trail.Trail;
import com.revivalmodding.revivalcore.core.client.trail.TrailOptionalData;
import com.revivalmodding.revivalcore.core.client.trail.TrailPart;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TrailRendererSimple extends TrailRenderer {

    @Override
    public void renderTrail(EntityPlayer player, Trail trailToRender, @Nullable TrailOptionalData trailData, float partialTick) {
        AbstractSuit suit = AbstractSuit.getSuit(player);
        int color = suit != null ? suit.getTrailRGB().getRGB() : trailToRender.getColor();
        Vec3d lastRenderVec = null;
        double x = this.interpolate(player.posX, player.lastTickPosX, partialTick);
        double y = this.interpolate(player.posY, player.lastTickPosY, partialTick);
        double z = this.interpolate(player.posZ, player.lastTickPosZ, partialTick);
        Trail.TrailPos[] vertexData = trailToRender.getVertexData();
        for(int p = 0; p < vertexData.length; p++) {
            Trail.TrailPos trail = vertexData[p];
            if(trail == null) continue;
            int trailWidth = trailToRender.getWidth() - p;
            trailWidth = trailWidth <= 0 ? 1 : trailWidth;
            GlStateManager.glLineWidth(trailWidth);
            float a = trail.alpha;
            // TODO fix: First trail is never rendered
            if(lastRenderVec != null) {
                float r = ((color >> 16) & 255) / 255.0F;
                float g = ((color >>  8) & 255) / 255.0F;
                float b = (color & 255) / 255.0F;
                for(TrailPart part : TrailPart.values()) {
                    Vec3d partVec = part.offset().rotateYaw(-player.rotationYaw * 0.017453292F - ((float)Math.PI / 2.0F));
                    Vec3d renderVec = trail.add(partVec.x, partVec.y, partVec.z);
                    Vec3d last = lastRenderVec.add(partVec.x, partVec.y, partVec.z);
                    boolean specialRender = trailData != null && trailData.onTrailRender(this, renderVec, last, color, trailWidth, p, a, x, y, z);
                    if(!specialRender) {
                        this.drawLine(renderVec, last, trailWidth, r, g, b, a, x, y, z);
                    }
                }
            } else {

            }
            lastRenderVec = trail;
        }
    }
}
