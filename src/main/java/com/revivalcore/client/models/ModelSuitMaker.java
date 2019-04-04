package com.revivalcore.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSuitMaker extends ModelBase {
    private final ModelRenderer Table;
    private final ModelRenderer Mask;
    private final ModelRenderer bone;

    public ModelSuitMaker() {
        textureWidth = 16;
        textureHeight = 16;

        Table = new ModelRenderer(this);
        Table.setRotationPoint(0.0F, 24.0F, 0.0F);
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -10.0F, 7.0F, 38, 10, 2, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, 18.0F, -6.0F, -6.0F, 1, 2, 13, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -6.0F, -6.0F, 1, 2, 13, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -4.0F, -6.0F, 38, 4, 1, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -4.0F, -5.0F, 38, 1, 12, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, 18.0F, -10.0F, -6.0F, 1, 4, 2, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -10.0F, -6.0F, 1, 4, 2, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, -19.0F, -11.0F, -6.0F, 38, 1, 15, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, 6.0F, -12.0F, 1.0F, 3, 1, 1, 0.0F, false));
        Table.cubeList.add(new ModelBox(Table, 0, 0, 9.0F, -12.0F, 0.0F, 3, 1, 1, 0.0F, false));

        Mask = new ModelRenderer(this);
        Mask.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(Mask, 0.0F, -0.6109F, 0.0F);
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -11.0F, -16.0F, 6.0F, 5, 5, 5, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -25.0F, 4.0F, 9, 9, 9, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -6.0F, -20.0F, 3.0F, 2, 4, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -19.0F, 3.0F, 2, 3, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -20.0F, 3.0F, 3, 3, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -7.0F, -20.0F, 3.0F, 3, 3, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -10.0F, -25.0F, 3.0F, 3, 7, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -25.0F, 3.0F, 9, 3, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -21.0F, 3.0F, 3, 1, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -7.0F, -21.0F, 3.0F, 3, 1, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -5.0F, -22.0F, 3.0F, 1, 1, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -22.0F, 3.0F, 1, 1, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -26.0F, 3.0F, 9, 1, 10, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -14.0F, -26.0F, 3.0F, 1, 10, 11, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -4.0F, -26.0F, 3.0F, 1, 10, 10, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -14.0F, -26.0F, 13.0F, 11, 10, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -14.0F, -27.0F, 6.0F, 1, 1, 5, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -14.0F, -29.0F, 7.0F, 1, 2, 3, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -14.0F, -31.0F, 8.0F, 1, 2, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -13.0F, -28.0F, 8.0F, 1, 2, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -5.0F, -28.0F, 8.0F, 1, 2, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -4.0F, -31.0F, 8.0F, 1, 2, 1, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -4.0F, -29.0F, 7.0F, 1, 2, 3, 0.0F, false));
        Mask.cubeList.add(new ModelBox(Mask, 0, 0, -4.0F, -27.0F, 6.0F, 1, 1, 5, 0.0F, false));

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -18.0F, -10.0F, -6.0F, 36, 6, 1, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Table.render(f5);
        Mask.render(f5);
        bone.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}