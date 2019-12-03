package com.revivalmodding.revivalcore.core.client.render.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import java.util.function.Supplier;

public class RenderTrailEditor<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

    private final Supplier<TrailEditorModel> modelSupplier;

    public RenderTrailEditor(Supplier<TrailEditorModel> model) {
        this.modelSupplier = model;
    }

    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(0.5, 0.5, 0.5);
        this.modelSupplier.get().renderTrailEditor();
        GlStateManager.popMatrix();
    }

    public interface TrailEditorModel {

        void renderTrailEditor();
    }
}
