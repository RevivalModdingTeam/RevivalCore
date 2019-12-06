package com.revivalmodding.revivalcore.core.client.render.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTrailEditor<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

    private final TrailEditorModel model;

    public RenderTrailEditor(TrailEditorModel model) {
        this.model = model;
    }

    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        this.bindTexture(this.model.getTexture());
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5, 1.2, 0.5);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.scale(0.05, 0.05, 0.05);
        this.model.renderTrailEditor();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    public interface TrailEditorModel {

        void renderTrailEditor();

        ResourceLocation getTexture();
    }
}
