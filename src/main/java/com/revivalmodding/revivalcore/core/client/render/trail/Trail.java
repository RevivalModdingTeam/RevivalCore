package com.revivalmodding.revivalcore.core.client.render.trail;

import com.google.common.base.Preconditions;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class Trail {

    private int length;
    private int width;
    private int color;
    private ArrayList<Vec3d> points;

    private Trail() {
    }

    public void updateTrail(EntityPlayer player, float partialTicks) {
        points.remove(length-1);
        points.add(0, new Vec3d(
                this.interpolateAndOffset(player.posX, player.lastTickPosX, partialTicks),
                this.interpolateAndOffset(player.posY, player.lastTickPosY, partialTicks),
                this.interpolateAndOffset(player.posZ, player.lastTickPosZ, partialTicks)
        ));
    }

    // TODO finish
    public void renderTrail(EntityPlayer player) {
        AbstractSuit suit = AbstractSuit.getSuit(player);
        int color = suit != null ? suit.getTrailRGB().getRGB() : this.color;
        Vec3d lastRenderVec = null;
        for(int p = 0; p < points.size(); p++) {
            Vec3d vec = points.get(p);
            float a = 255 * (1 - p/(float)this.length) + 0.1F;
            if(a > 1.0F) a = 1.0F;
            if(lastRenderVec != null) {
                float r = (color >> 16) & 15;
                float g = (color >>  8) & 15;
                float b = color & 15;
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferBuilder = tessellator.getBuffer();
                for(TrailPart part : TrailPart.values()) {
                    Vec3d renderVec = vec.add(part.offset());
                    Vec3d last = lastRenderVec.add(part.offset());
                    bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                    bufferBuilder.pos(renderVec.x, renderVec.y, renderVec.z).color(r, g, b, a).endVertex();
                    bufferBuilder.pos(last.x, last.y, last.z).color(r, g, b, a).endVertex();
                    tessellator.draw();
                }
            }
            lastRenderVec = vec;
        }
    }

    // TODO apply random small offset
    private double interpolateAndOffset(double current, double previous, float partial) {
        return current - (current - previous) * partial;
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
            trail.width = 1; trail.length = 1; trail.color = 0xFFFFFF; trail.points = new ArrayList<>(1);
            return trail;
        }
        NBTTagCompound trailData = nbt.getCompoundTag("trail");
        Trail trail = new Trail();
        trail.length = trailData.getInteger("length");
        trail.width = trailData.getInteger("width");
        trail.color = trailData.getInteger("color");
        trail.points = new ArrayList<>(trail.length);
        return trail;
    }

    public static class TrailBuilder {

        int color, width, length;

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
            trail.points = new ArrayList<>(this.length);
            return trail;
        }

        private float normalizeColor(float f) {
            return f > 1 ? 1 : f < 0 ? 0 : f;
        }
    }
}
