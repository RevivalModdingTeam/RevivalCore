package com.revivalmodding.revivalcore.core.client.trail;

import com.google.common.base.Preconditions;
import com.revivalmodding.revivalcore.RevivalCore;
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
        boolean canRenderTrail = player.posX != player.lastTickPosX || player.posY != player.lastTickPosY || player.posZ != player.lastTickPosZ;
        this.addPoint(canRenderTrail ? new TrailPos(this.offset(player.posX), this.offset(player.posY), this.offset(player.posZ)) : null);
    }

    private double offset(double base) {
        return base + (RevivalCore.getRandom().nextDouble()/10)-(RevivalCore.getRandom().nextDouble()/10);
    }

    private void addPoint(@Nullable TrailPos trailPos) {
        try {
            for(int i = this.length - 1; i > 0; i--) {
                boolean flag = points[i-1] == null;
                if(flag) {
                    points[i] = null;
                } else {
                    float alpha = 1 - ((i-1) / (float)this.length) + 0.1F;
                    points[i] = points[i-1].withAlpha(alpha > 1F ? 1.0F : alpha);
                }
            }
            points[0] = trailPos;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTrailToNBT(NBTTagCompound nbt) {
        NBTTagCompound nbt1 = new NBTTagCompound();
        nbt1.setInteger("length", this.length);
        nbt1.setInteger("width", this.width);
        nbt1.setInteger("color", this.color);
        nbt.setTag("trail", nbt1);
    }

    public void readTrailFromNBT(NBTTagCompound nbt) {
        if(!nbt.hasKey("trail")) {
            RevivalCore.logger.error("Error when parsing NBT, didn't find any trail data! Creating new trail..");
            this.width = 6; this.length = 6; this.color = 0xFFCC00; this.points = new TrailPos[6];
            return;
        }
        NBTTagCompound trailData = nbt.getCompoundTag("trail");
        this.length = trailData.getInteger("length");
        this.width = trailData.getInteger("width");
        this.color = trailData.getInteger("color");
        this.points = new TrailPos[this.length];
    }

    public static Trail createDefaultTrail() {
        Trail trail = new Trail();
        trail.length = 6;
        trail.width = 6;
        trail.color = 0xFFCC00;
        trail.points = new TrailPos[6];
        return trail;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getColor() {
        return color;
    }

    public TrailPos[] getVertexData() {
        return points;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setColor(int color) {
        this.color = color;
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
            return this.color(TrailColorHelper.convertToInt(red, green, blue));
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

    public static class TrailColorHelper {

        public static int convertToInt(int r, int g, int b) {
            return (r&0xff)<<16|(g&0xff)<<8|(b&0xff);
        }

        public static int convertToInt(float r, float g, float b) {
            return convertToInt((int)(r * 255), (int)(g * 255), (int)(b * 255));
        }
    }
}
