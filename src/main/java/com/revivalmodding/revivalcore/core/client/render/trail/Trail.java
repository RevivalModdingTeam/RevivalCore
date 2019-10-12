package com.revivalmodding.revivalcore.core.client.render.trail;

import com.google.common.base.Preconditions;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class Trail {

    private int length;
    private int width;
    private int color;
    private Vec3d[] points;

    private Trail() {
    }

    public void updateTrail(EntityPlayer player) {
        this.addPoint(new Vec3d(player.getPosition()));
    }

    // TODO trails are not getting removed soon enough, alpha value ignored?
    public void renderTrail(EntityPlayer player, float partialTick) {
        AbstractSuit suit = AbstractSuit.getSuit(player);
        int color = suit != null ? suit.getTrailRGB().getRGB() : this.color;
        Vec3d lastRenderVec = null;
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTick;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTick;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTick;
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.glLineWidth(this.width);
        for(int p = 0; p < points.length; p++) {
            Vec3d vec = points[p];
            if(vec == null) continue;
            float a = 255 * (1 - p/(float)this.length) + 0.1F;
            if(a > 1.0F) a = 1.0F;
            if(lastRenderVec != null) {
                float r = (color >> 16) & 15;
                float g = (color >>  8) & 15;
                float b = color & 15;
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferBuilder = tessellator.getBuffer();
                bufferBuilder.setTranslation(-x, -y, -z);
                for(TrailPart part : TrailPart.values()) {
                    Vec3d renderVec = vec.add(part.offset());
                    Vec3d last = lastRenderVec.add(part.offset());
                    bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                    bufferBuilder.pos(renderVec.x, renderVec.y, renderVec.z).color(r, g, b, a).endVertex();
                    bufferBuilder.pos(last.x, last.y, last.z).color(r, g, b, a).endVertex();
                    tessellator.draw();
                }
                bufferBuilder.setTranslation(0, 0, 0);
            }
            lastRenderVec = vec;
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
    }

    // TODO apply random small offset
    private double interpolateAndOffset(double current, double previous, float partial) {
        return current - (current - previous) * partial;
    }

    private void addPoint(Vec3d vec3d) {
        for(int i = this.length - 2; i > 0; i--) {
            points[i] = points[i-1];
        }
        points[0] = vec3d;
    }

    public static void writeTrailToNBT(Trail trail, NBTTagCompound nbt) {
        NBTTagCompound nbt1 = new NBTTagCompound();
        nbt1.setInteger("length", trail.length);
        nbt1.setInteger("width", trail.width);
        nbt1.setInteger("color", trail.color);
        nbt.setTag("trail", nbt1);
    }

    public static Trail readTrailFromNBT(NBTTagCompound nbt) {
        if(!nbt.hasKey("trail")) {
            RevivalCore.logger.error("Error when parsing NBT, didn't find any trail data! Creating new trail..");
            Trail trail = new Trail();
            trail.width = 1; trail.length = 1; trail.color = 0xFFFFFF; trail.points = new Vec3d[0];
            return trail;
        }
        NBTTagCompound trailData = nbt.getCompoundTag("trail");
        Trail trail = new Trail();
        trail.length = trailData.getInteger("length");
        trail.width = trailData.getInteger("width");
        trail.color = trailData.getInteger("color");
        trail.points = new Vec3d[trail.length];
        return trail;
    }

    public static class TrailBuilder {

        private int color, width, length;

        private TrailBuilder() {}

        public static TrailBuilder create() {
            return new TrailBuilder();
        }

        public TrailBuilder trailLength(int length) {
            this.length = length;
            return this;
        }

        public TrailBuilder trailWidth(int width) {
            this.width = width;
            return this;
        }

        public TrailBuilder color(int color) {
            this.color = color;
            return this;
        }

        public TrailBuilder color(int red, int green, int blue) {
            return this.color((red&0xff)<<16|(green&0xff)<<8|(blue&0xff));
        }

        public TrailBuilder color(float r, float g, float b) {
            return this.color((int)(this.normalizeColor(r)*255), (int)(this.normalizeColor(g)*255), (int)(this.normalizeColor(b)*255));
        }

        public Trail build() {
            Preconditions.checkState(length>0, "Trail length must be >0!");
            Preconditions.checkState(width>0, "Trail width must be >0!");
            Trail trail = new Trail();
            trail.color = this.color;
            trail.length = this.length;
            trail.width = this.width;
            trail.points = new Vec3d[this.length];
            return trail;
        }

        private float normalizeColor(float f) {
            return f > 1 ? 1 : f < 0 ? 0 : f;
        }
    }
}
