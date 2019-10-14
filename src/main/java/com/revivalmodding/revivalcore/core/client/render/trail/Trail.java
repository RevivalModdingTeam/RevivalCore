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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class Trail {

    protected int length;
    protected int width;
    protected int color;
    protected TrailPos[] points;

    private Trail() {
    }

    public void updateTrail(EntityPlayer player) {
        this.addPoint(new TrailPos(this.offset(player.posX), this.offset(player.posY), this.offset(player.posZ)));
    }

    // TODO implement the optional data
    public void renderTrail(EntityPlayer player, float partialTick, @Nullable TrailOptionalData trailOptionalData) {
        AbstractSuit suit = AbstractSuit.getSuit(player);
        int color = suit != null ? suit.getTrailRGB().getRGB() : this.color;
        Vec3d lastRenderVec = null;
        double x = this.interpolate(player.posX, player.lastTickPosX, partialTick);
        double y = this.interpolate(player.posY, player.lastTickPosY, partialTick);
        double z = this.interpolate(player.posZ, player.lastTickPosZ, partialTick);
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ZERO);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.setTranslation(-x, -y, -z);
        for(int p = 0; p < points.length; p++) {
            TrailPos trail = points[p];
            if(trail == null) continue;
            int trailWidth = this.width - p;
            trailWidth = trailWidth <= 0 ? 1 : trailWidth;
            GlStateManager.glLineWidth(trailWidth);
            float a = trail.alpha;
            if(lastRenderVec != null) {
                float r = (color >> 16) & 15;
                float g = (color >>  8) & 15;
                float b = color & 15;
                for(TrailPart part : TrailPart.values()) {
                    bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                    Vec3d partVec = part.offset().rotateYaw(-player.rotationYaw * 0.017453292F - ((float)Math.PI / 2.0F));
                    Vec3d renderVec = trail.add(partVec.x, partVec.y, partVec.z);
                    Vec3d last = lastRenderVec.add(partVec.x, partVec.y, partVec.z);
                    bufferBuilder.pos(renderVec.x, renderVec.y, renderVec.z).color(r, g, b, a).endVertex();
                    bufferBuilder.pos(last.x, last.y, last.z).color(r, g, b, a).endVertex();
                    tessellator.draw();
                }
            }
            lastRenderVec = trail;
        }
        bufferBuilder.setTranslation(0, 0, 0);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
        GlStateManager.disableAlpha();
    }

    private double offset(double base) {
        return base + (RevivalCore.getRandom().nextDouble()/6)-(RevivalCore.getRandom().nextDouble()/6);
    }

    private double interpolate(double current, double previous, float partial) {
        return previous + (current - previous) * partial;
    }

    private void addPoint(TrailPos trailPos) {
        try {
            for(int i = this.length - 1; i > 0; i--) {
                if(points[i-1] == null) continue;
                float alpha = 1 - ((i-1) / (float)this.length) + 0.1F;
                points[i] = points[i-1].withAlpha(alpha > 1F ? 1.0F : alpha);
            }
            points[0] = trailPos;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            trail.width = 1; trail.length = 1; trail.color = 0xFFFFFF; trail.points = new TrailPos[0];
            return trail;
        }
        NBTTagCompound trailData = nbt.getCompoundTag("trail");
        Trail trail = new Trail();
        trail.length = trailData.getInteger("length");
        trail.width = trailData.getInteger("width");
        trail.color = trailData.getInteger("color");
        trail.points = new TrailPos[trail.length];
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
            trail.points = new TrailPos[this.length];
            return trail;
        }

        private float normalizeColor(float f) {
            return f > 1 ? 1 : f < 0 ? 0 : f;
        }
    }

    public class TrailPos extends Vec3d {

        public float alpha;

        public TrailPos(BlockPos pos) {
            this(pos, 1.0F);
        }

        public TrailPos(BlockPos pos, float a) {
            this(pos.getX(), pos.getY(), pos.getZ(), a);
        }

        public TrailPos(double x, double y, double z) {
            this(x, y, z, 1.0F);
        }

        public TrailPos(double x, double y, double z, float a) {
            super(x, y, z);
            this.alpha = a;
        }

        public TrailPos withAlpha(float a) {
            this.alpha = a;
            return this;
        }
    }
}
