package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public final class ImageHelper
{
    public static void drawFullScreenImage(Minecraft minecraft, ScaledResolution resolution, ResourceLocation imageLocation, boolean transparent)
    {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, 0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), 0, 0, 1, 1);

        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }

    public static void drawCustomSizedImage(Minecraft minecraft, ResourceLocation imageLocation, double startX, double startY, double width, double height, boolean transparent)
    {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        prepareShape(buffer, startX, startY, width, height, 0, 0, 1, 1);

        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }

    public static void drawImageWithUV(Minecraft minecraft, ResourceLocation imageLocation, double startX, double startY, double width, double height, double startU, double startV, double u, double v, boolean transparent)
    {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        if(u > 1) u = 1;
        if(v > 1) v = 1;

        prepareShape(buffer, startX, startY, width, height, startU, startV, u, v);

        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawImageWithUV(Minecraft minecraft, ResourceLocation imageLocation, int startX, int startY, double width, double height, double startU, double startV, double u, double v, boolean transparent)
    {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        if(u > 1) u = 1;
        if(v > 1) v = 1;

        prepareShape(buffer, startX, startY, width, height, startU, startV, u, v);

        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarHorizontally(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width * progress, height, 0, 0, 1, 1);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarHorizontally(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, double startU, double startV, double endU, double endV, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width * progress, height, startU, startV, endU, endV);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarVertically(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width, height * progress, 0, 0, 1, 1);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarVertically(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, double startU, double startV, double endU, double endV, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width, height * progress, startU, startV, endU, endV);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarHorizontallyInverted(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width * (1-progress), height, 0, 0, 1, 1);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarHorizontallyInverted(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, double startU, double startV, double endU, double endV, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width * (1-progress), height, startU, startV, endU, endV);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarVerticallyInverted(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width, height * (1-progress), 0, 0, 1, 1);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    public static void drawProgressionBarVerticallyInverted(Minecraft mc, ResourceLocation img, int x, int y, double width, double height, double startU, double startV, double endU, double endV, float progress, boolean transparent)
    {
        mc.getTextureManager().bindTexture(img);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        prepareShape(buffer, x, y, width, height * (1-progress), startU, startV, endU, endV);
        
        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    private static void prepareShape(BufferBuilder buffer, double startX, double startY, double width, double height, double startU, double startV, double endU, double endV)
    {
    	buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(startX, startY + height, 0).tex(startU, endV).endVertex();
        buffer.pos(startX + width, startY + height, 0).tex(endU, endV).endVertex();
        buffer.pos(startX + width, startY, 0).tex(endU, startV).endVertex();
        buffer.pos(startX, startY, 0).tex(startU, startV).endVertex();
    }
}
