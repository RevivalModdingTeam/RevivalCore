package com.revivalmodding.revivalcore.core.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;

public class SHRModelRenderer extends ModelRenderer {

    private final EntityEquipmentSlot slot;

    public SHRModelRenderer(ModelBase model, EntityEquipmentSlot forSlot) {
        super(model);
        this.slot = forSlot;
    }

    public SHRModelRenderer(ModelBase model, int w, int h, EntityEquipmentSlot slot) {
        super(model, w, h);
        this.slot = slot;
    }

    public void render(float scale, EntityEquipmentSlot slot) {
        if(this.slot != slot) {
            return;
        }
        super.render(scale);
    }
}
