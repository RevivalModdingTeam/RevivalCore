package com.revivalmodding.revivalcore.core.client.models;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderTrailEditor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Psychokiller
 * Added by Toma, 12.2.2019
 */
public class ModelTrailEditorBasic extends ModelBase implements RenderTrailEditor.TrailEditorModel {

    public static final ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID, "textures/blocks/trail_editor_basic.png");
    private final ModelRenderer Lightning;
    private final ModelRenderer Pedestal;
    private final ModelRenderer Holder;

    public ModelTrailEditorBasic() {
        textureWidth = 16;
        textureHeight = 16;

        Lightning = new ModelRenderer(this);
        Lightning.setRotationPoint(0.0F, 10.7733F, -13.0F);
        setRotationAngle(Lightning, -0.8727F, 0.0F, 0.0F);

        ModelRenderer bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.954F, -6.9574F, 3.0F, 0, 2, 0, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.72F, -5.04F, 3.0F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -4.92F, 3.0F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -4.3333F, 3.0F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -4.44F, 3.0F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.922F, -5.48F, 3.0F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightningPoint2 = new ModelRenderer(this);
        lightningPoint2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightningPoint2, 0.0F, 0.0F, 0.5236F);
        Lightning.addChild(lightningPoint2);
        lightningPoint2.cubeList.add(new ModelBox(lightningPoint2, 0, 0, -2.9867F, -4.2667F, 3.0F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_point0 = new ModelRenderer(this);
        lightning_point0.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightning_point0);
        lightning_point0.cubeList.add(new ModelBox(lightning_point0, 0, 0, -0.9522F, -6.3223F, 3.0F, -1, 2, 1, 0.0F, false));

        ModelRenderer lightningFill = new ModelRenderer(this);
        lightningFill.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningFill);

        ModelRenderer lightningFill2 = new ModelRenderer(this);
        lightningFill2.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightningFill.addChild(lightningFill2);
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -0.2533F, -11.0F, 3.0F, 2, 2, 1, 0.0F, true));
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -0.2533F, -10.0F, 3.0F, 1, 2, 1, 0.0F, true));
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -1.2533F, -18.0F, 3.0F, 1, 2, 1, 0.0F, true));
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -0.2533F, -11.0F, 3.0F, 1, 2, 1, 0.0F, true));
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -0.954F, -9.0018F, 3.08F, 1, 2, 1, 0.0F, true));
        lightningFill2.cubeList.add(new ModelBox(lightningFill2, 0, 0, -0.2533F, -12.9467F, 2.9467F, 3, 2, 1, 0.0F, true));

        ModelRenderer lightningpart4 = new ModelRenderer(this);
        lightningpart4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightningpart4, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(lightningpart4);
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, -2, 0, 5.0033F, -4.0F, 3.0F, 5, 1, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, -2, 0, 7.0033F, -4.0F, 3.0F, 5, 1, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.2784F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.3082F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.3544F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.3313F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.3775F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.4699F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.5622F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.6546F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.68F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.68F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 4.4533F, -3.2267F, 3.0F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightningpart3 = new ModelRenderer(this);
        lightningpart3.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningpart3);
        lightningpart3.cubeList.add(new ModelBox(lightningpart3, -4, 0, -1.0794F, -12.7582F, 3.0F, 4, 0, 1, 0.0F, false));
        lightningpart3.cubeList.add(new ModelBox(lightningpart3, -4, 0, -4.0794F, -12.7582F, 3.0F, 4, 0, 1, 0.0F, false));

        ModelRenderer lightning2 = new ModelRenderer(this);
        lightning2.setRotationPoint(-1.1733F, -26.3333F, 0.0F);
        setRotationAngle(lightning2, 0.0F, 0.0F, 3.1416F);
        Lightning.addChild(lightning2);

        ModelRenderer bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.954F, -6.9574F, 3.0F, 0, 2, 0, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.72F, -5.04F, 3.0F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.9467F, -4.92F, 3.0F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.9467F, -4.3333F, 3.0F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.9467F, -4.44F, 3.0F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.922F, -5.48F, 3.0F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_Point3 = new ModelRenderer(this);
        lightning_Point3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightning_Point3, 0.0F, 0.0F, 0.5236F);
        lightning2.addChild(lightning_Point3);
        lightning_Point3.cubeList.add(new ModelBox(lightning_Point3, 0, 0, -2.9867F, -4.2667F, 3.0F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_Point4 = new ModelRenderer(this);
        lightning_Point4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_Point4);
        lightning_Point4.cubeList.add(new ModelBox(lightning_Point4, 0, 0, -0.9522F, -6.3223F, 3.0F, -1, 2, 1, 0.0F, false));

        ModelRenderer lightning_Fill3 = new ModelRenderer(this);
        lightning_Fill3.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_Fill3);

        ModelRenderer lightning_Fill4 = new ModelRenderer(this);
        lightning_Fill4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning_Fill3.addChild(lightning_Fill4);
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2533F, -11.0F, 2.9467F, 2, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2533F, -11.0F, 3.0F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2533F, -11.0F, 3.0F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.954F, -12.0018F, 2.9733F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.954F, -9.0018F, 3.0F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.3674F, -10.0018F, 3.0F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -1.3274F, -15.4684F, 3.0F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2533F, -12.9467F, 3.0533F, 3, 2, 1, 0.0F, true));

        ModelRenderer lightning_part_2 = new ModelRenderer(this);
        lightning_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightning_part_2, 0.0F, 0.0F, -1.0472F);
        lightning2.addChild(lightning_part_2);
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, -2, 0, 7.0033F, -4.0F, 3.0F, 5, 1, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, -2, 0, 5.0033F, -4.0F, 3.0F, 5, 1, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.2784F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.3082F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.3544F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.3544F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.3313F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.3775F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.4699F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.5622F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.6546F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.68F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.68F, -2.9867F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.4533F, -3.2267F, 3.0F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightning_part_3 = new ModelRenderer(this);
        lightning_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_part_3);
        lightning_part_3.cubeList.add(new ModelBox(lightning_part_3, -4, 0, -5.0794F, -12.7582F, 3.0F, 4, 0, 1, 0.0F, false));
        lightning_part_3.cubeList.add(new ModelBox(lightning_part_3, -4, 0, -1.0794F, -12.7582F, 3.0F, 4, 0, 1, 0.0F, false));

        ModelRenderer bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-1.1733F, -26.3467F, 7.0F);
        setRotationAngle(bone4, 0.0F, 3.1416F, 0.0F);
        Lightning.addChild(bone4);

        ModelRenderer lightning_shit2 = new ModelRenderer(this);
        lightning_shit2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone4.addChild(lightning_shit2);
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4651F, 12.7634F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4189F, 12.79F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5478F, 13.7616F, 3.0F, 1, -1, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.442F, 12.7767F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3958F, 12.8034F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3035F, 12.8567F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2111F, 12.91F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1187F, 12.9634F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0933F, 13.0F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0933F, 13.0F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -4.32F, 13.3333F, 3.0F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0667F, 13.5733F, 3.0F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 12.9481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 12.9481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 12.9748F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 12.9481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 12.9481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2819F, 13.2148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2552F, 13.2414F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2286F, 13.3214F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3086F, 13.1614F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3086F, 13.1881F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3619F, 13.0814F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3352F, 13.1081F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2019F, 13.3481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1752F, 13.3481F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1752F, 13.4014F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1486F, 13.4281F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1486F, 13.4548F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1219F, 13.4814F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1219F, 13.4814F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0952F, 13.5348F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3619F, 13.0814F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0552F, 13.5614F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4952F, 12.8533F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4686F, 12.88F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4419F, 12.9067F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 12.7614F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5467F, 12.7881F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 12.7881F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, 1.0472F);
        bone4.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 0, 0, 8.2914F, 11.1881F, 2.9993F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, 1.0472F);
        bone4.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, 8.2914F, 11.1881F, 2.9993F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightningshit = new ModelRenderer(this);
        lightningshit.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningshit);
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4651F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4189F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5478F, -11.7657F, 3.0F, 1, -1, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.442F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3958F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3035F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2111F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1187F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0933F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0933F, -12.76F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -4.32F, -13.0F, 3.0F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0667F, -12.7733F, 3.0F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2819F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2552F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2286F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3086F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3086F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3619F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3352F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2019F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1752F, -12.7881F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1752F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1486F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1486F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1219F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1219F, -12.7881F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0952F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3619F, -12.8148F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0552F, -12.7614F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4952F, -12.7614F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4686F, -12.7881F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4419F, -12.8148F, 2.9993F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.7614F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5467F, -12.7614F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.7614F, 2.9993F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.7881F, 2.9993F, 1, 0, 1, 0.0F, false));

        ModelRenderer dsa = new ModelRenderer(this);
        dsa.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(dsa, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(dsa);
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 8.3651F, -11.0342F, 2.9993F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 8.2908F, -11.1628F, 2.9993F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 8.2908F, -11.1628F, 2.9993F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 8.3841F, -11.1362F, 2.9993F, 0, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 8.2815F, -11.184F, 3.0F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 8.2914F, -11.1614F, 2.9993F, 1, 0, 1, 0.0F, false));

        Pedestal = new ModelRenderer(this);
        Pedestal.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer halfThePedestal = new ModelRenderer(this);
        halfThePedestal.setRotationPoint(0.0F, 0.0F, 0.0F);
        Pedestal.addChild(halfThePedestal);

        ModelRenderer feetThing = new ModelRenderer(this);
        feetThing.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feetThing, 0.0F, 0.7854F, 0.0F);
        halfThePedestal.addChild(feetThing);
        feetThing.cubeList.add(new ModelBox(feetThing, 0, 4, -1.0844F, -3.0F, -15.0F, 1, 3, 1, 0.0F, false));
        feetThing.cubeList.add(new ModelBox(feetThing, 0, 4, -1.0844F, -14.0F, -12.7333F, 1, 3, 1, 0.0F, false));

        ModelRenderer feet_Thing2 = new ModelRenderer(this);
        feet_Thing2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing2, -0.2618F, 0.7854F, 0.0F);
        halfThePedestal.addChild(feet_Thing2);
        feet_Thing2.cubeList.add(new ModelBox(feet_Thing2, 0, 4, -1.0844F, 1.0133F, -15.2682F, 1, 0, 1, 0.0F, false));

        ModelRenderer feet_Thing3 = new ModelRenderer(this);
        feet_Thing3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing3, -1.5708F, 0.7854F, 0.0F);
        halfThePedestal.addChild(feet_Thing3);
        feet_Thing3.cubeList.add(new ModelBox(feet_Thing3, 0, 4, -1.0844F, 12.76F, -3.9067F, 1, 2, 1, 0.0F, false));

        ModelRenderer otherthing = new ModelRenderer(this);
        otherthing.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(otherthing, 0.0F, 1.5708F, 0.0F);
        halfThePedestal.addChild(otherthing);
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, -0.8682F, -3.0F, -10.72F, 11, 3, 11, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, -7.9867F, -12.9945F, -8.72F, 15, 3, 9, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, 8.2559F, -3.0F, -9.7895F, 1, 0, 1, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, -9.2559F, -3.0F, -9.7895F, 1, 0, 1, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, -9.0F, -4.0F, -9.72F, 18, 1, 10, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, 8.0F, -13.0F, -8.72F, 1, 10, 9, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, 8.0F, -13.0F, -8.72F, 1, 10, 9, 0.0F, false));
        otherthing.cubeList.add(new ModelBox(otherthing, 0, 4, -9.0133F, -13.0F, -8.72F, 1, 10, 9, 0.0F, false));

        ModelRenderer thing2 = new ModelRenderer(this);
        thing2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thing2, -0.2618F, 0.7854F, 0.0F);
        halfThePedestal.addChild(thing2);
        thing2.cubeList.add(new ModelBox(thing2, 0, 4, -1.0844F, -6.88F, -15.2682F, 1, 7, 8, 0.0F, false));

        ModelRenderer pedestal2 = new ModelRenderer(this);
        pedestal2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(pedestal2, 0.0F, 3.1416F, 0.0F);
        halfThePedestal.addChild(pedestal2);

        ModelRenderer half_The_Pedestal3 = new ModelRenderer(this);
        half_The_Pedestal3.setRotationPoint(0.0F, 0.0F, 0.0F);
        pedestal2.addChild(half_The_Pedestal3);

        ModelRenderer feet_Thing7 = new ModelRenderer(this);
        feet_Thing7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing7, 0.0F, 0.7854F, 0.0F);
        half_The_Pedestal3.addChild(feet_Thing7);

        ModelRenderer feet_Thing8 = new ModelRenderer(this);
        feet_Thing8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing8, -0.2618F, 0.7854F, 0.0F);
        half_The_Pedestal3.addChild(feet_Thing8);

        ModelRenderer feet_Thing9 = new ModelRenderer(this);
        feet_Thing9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing9, -1.5708F, 0.7854F, 0.0F);
        half_The_Pedestal3.addChild(feet_Thing9);

        ModelRenderer other_thing3 = new ModelRenderer(this);
        other_thing3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing3, 0.0F, 1.5708F, 0.0F);
        half_The_Pedestal3.addChild(other_thing3);

        ModelRenderer half_The_Pedestal4 = new ModelRenderer(this);
        half_The_Pedestal4.setRotationPoint(0.0F, 0.0F, 0.0F);
        pedestal2.addChild(half_The_Pedestal4);

        ModelRenderer feet_Thing10 = new ModelRenderer(this);
        feet_Thing10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing10, 0.0F, -0.7854F, 0.0F);
        half_The_Pedestal4.addChild(feet_Thing10);
        feet_Thing10.cubeList.add(new ModelBox(feet_Thing10, 0, 4, -0.3298F, -3.0F, -15.0F, 1, 3, 1, 0.0F, true));
        feet_Thing10.cubeList.add(new ModelBox(feet_Thing10, 0, 4, -0.3298F, -14.0F, -12.7333F, 1, 3, 1, 0.0F, true));

        ModelRenderer feet_Thing11 = new ModelRenderer(this);
        feet_Thing11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing11, -0.2618F, -0.7854F, 0.0F);
        half_The_Pedestal4.addChild(feet_Thing11);
        feet_Thing11.cubeList.add(new ModelBox(feet_Thing11, 0, 4, -0.3298F, 1.0133F, -15.2682F, 1, 0, 1, 0.0F, true));

        ModelRenderer feet_Thing12 = new ModelRenderer(this);
        feet_Thing12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing12, -1.5708F, -0.7854F, 0.0F);
        half_The_Pedestal4.addChild(feet_Thing12);
        feet_Thing12.cubeList.add(new ModelBox(feet_Thing12, 0, 4, -0.3298F, 12.76F, -3.9067F, 1, 2, 1, 0.0F, true));

        ModelRenderer other_thing4 = new ModelRenderer(this);
        other_thing4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing4, 0.0F, -1.5708F, 0.0F);
        half_The_Pedestal4.addChild(other_thing4);
        other_thing4.cubeList.add(new ModelBox(other_thing4, 0, 4, -10.1318F, -3.0F, -10.72F, 11, 3, 11, 0.0F, true));

        ModelRenderer thing = new ModelRenderer(this);
        thing.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thing, -0.2618F, 2.3562F, 0.0F);
        halfThePedestal.addChild(thing);
        thing.cubeList.add(new ModelBox(thing, 0, 4, -0.3282F, -6.88F, -15.2682F, 1, 7, 8, 0.0F, false));

        ModelRenderer bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
        halfThePedestal.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 4, -9.72F, -13.0F, -8.0133F, 1, 10, 16, 0.0F, false));

        ModelRenderer half_The_Pedestal2 = new ModelRenderer(this);
        half_The_Pedestal2.setRotationPoint(0.0F, 0.0F, 0.0F);
        halfThePedestal.addChild(half_The_Pedestal2);

        ModelRenderer feet_Thing4 = new ModelRenderer(this);
        feet_Thing4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing4, 0.0F, -0.7854F, 0.0F);
        half_The_Pedestal2.addChild(feet_Thing4);

        ModelRenderer feet_Thing5 = new ModelRenderer(this);
        feet_Thing5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing5, -0.2618F, -0.7854F, 0.0F);
        half_The_Pedestal2.addChild(feet_Thing5);

        ModelRenderer feet_Thing6 = new ModelRenderer(this);
        feet_Thing6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing6, -1.5708F, -0.7854F, 0.0F);
        half_The_Pedestal2.addChild(feet_Thing6);

        ModelRenderer other_thing2 = new ModelRenderer(this);
        other_thing2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing2, 0.0F, -1.5708F, 0.0F);
        half_The_Pedestal2.addChild(other_thing2);

        ModelRenderer spiky1 = new ModelRenderer(this);
        spiky1.setRotationPoint(0.0F, 0.0F, 0.0F);
        halfThePedestal.addChild(spiky1);

        ModelRenderer half_The_Pedestal5 = new ModelRenderer(this);
        half_The_Pedestal5.setRotationPoint(0.0F, 0.0F, 0.0F);
        Pedestal.addChild(half_The_Pedestal5);

        ModelRenderer feet_Thing13 = new ModelRenderer(this);
        feet_Thing13.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing13, 0.0F, -0.7854F, 0.0F);
        half_The_Pedestal5.addChild(feet_Thing13);
        feet_Thing13.cubeList.add(new ModelBox(feet_Thing13, 0, 4, -0.3298F, -3.0F, -15.0F, 1, 3, 1, 0.0F, true));
        feet_Thing13.cubeList.add(new ModelBox(feet_Thing13, 0, 4, -0.3298F, -14.0F, -12.7333F, 1, 3, 1, 0.0F, true));

        ModelRenderer feet_Thing14 = new ModelRenderer(this);
        feet_Thing14.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing14, -0.2618F, -0.7854F, 0.0F);
        half_The_Pedestal5.addChild(feet_Thing14);
        feet_Thing14.cubeList.add(new ModelBox(feet_Thing14, 0, 4, -0.3298F, 1.0133F, -15.2682F, 1, 0, 1, 0.0F, true));

        ModelRenderer feet_Thing15 = new ModelRenderer(this);
        feet_Thing15.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing15, -1.5708F, -0.7854F, 0.0F);
        half_The_Pedestal5.addChild(feet_Thing15);
        feet_Thing15.cubeList.add(new ModelBox(feet_Thing15, 0, 4, -0.3298F, 12.76F, -3.9067F, 1, 2, 1, 0.0F, true));

        ModelRenderer other_thing5 = new ModelRenderer(this);
        other_thing5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing5, 0.0F, -1.5708F, 0.0F);
        half_The_Pedestal5.addChild(other_thing5);
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -10.1318F, -3.0F, -10.72F, 11, 3, 11, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -8.0F, -12.9945F, -8.72F, 15, 3, 9, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -9.2559F, -3.0F, -9.7895F, 1, 0, 1, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, 8.2559F, -3.0F, -9.7895F, 1, 0, 1, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -9.0F, -4.0F, -9.72F, 18, 1, 10, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -9.0F, -13.0F, -8.72F, 1, 10, 9, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, -9.0F, -13.0F, -8.72F, 1, 10, 9, 0.0F, true));
        other_thing5.cubeList.add(new ModelBox(other_thing5, 0, 4, 8.0133F, -13.0F, -8.72F, 1, 10, 9, 0.0F, true));

        ModelRenderer thing3 = new ModelRenderer(this);
        thing3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thing3, -0.2618F, -0.7854F, 0.0F);
        half_The_Pedestal5.addChild(thing3);
        thing3.cubeList.add(new ModelBox(thing3, 0, 4, -0.3298F, -6.88F, -15.2682F, 1, 7, 8, 0.0F, true));

        ModelRenderer pedestal3 = new ModelRenderer(this);
        pedestal3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(pedestal3, 0.0F, -3.1416F, 0.0F);
        half_The_Pedestal5.addChild(pedestal3);

        ModelRenderer half_The_Pedestal6 = new ModelRenderer(this);
        half_The_Pedestal6.setRotationPoint(0.0F, 0.0F, 0.0F);
        pedestal3.addChild(half_The_Pedestal6);

        ModelRenderer feet_Thing16 = new ModelRenderer(this);
        feet_Thing16.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing16, 0.0F, -0.7854F, 0.0F);
        half_The_Pedestal6.addChild(feet_Thing16);

        ModelRenderer feet_Thing17 = new ModelRenderer(this);
        feet_Thing17.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing17, -0.2618F, -0.7854F, 0.0F);
        half_The_Pedestal6.addChild(feet_Thing17);

        ModelRenderer feet_Thing18 = new ModelRenderer(this);
        feet_Thing18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing18, -1.5708F, -0.7854F, 0.0F);
        half_The_Pedestal6.addChild(feet_Thing18);

        ModelRenderer other_thing6 = new ModelRenderer(this);
        other_thing6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing6, 0.0F, -1.5708F, 0.0F);
        half_The_Pedestal6.addChild(other_thing6);

        ModelRenderer half_The_Pedestal7 = new ModelRenderer(this);
        half_The_Pedestal7.setRotationPoint(0.0F, 0.0F, 0.0F);
        pedestal3.addChild(half_The_Pedestal7);

        ModelRenderer feet_Thing19 = new ModelRenderer(this);
        feet_Thing19.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing19, 0.0F, 0.7854F, 0.0F);
        half_The_Pedestal7.addChild(feet_Thing19);
        feet_Thing19.cubeList.add(new ModelBox(feet_Thing19, 0, 4, -1.0844F, -3.0F, -15.0F, 1, 3, 1, 0.0F, false));
        feet_Thing19.cubeList.add(new ModelBox(feet_Thing19, 0, 4, -1.0844F, -14.0F, -12.7333F, 1, 3, 1, 0.0F, false));

        ModelRenderer feet_Thing20 = new ModelRenderer(this);
        feet_Thing20.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing20, -0.2618F, 0.7854F, 0.0F);
        half_The_Pedestal7.addChild(feet_Thing20);
        feet_Thing20.cubeList.add(new ModelBox(feet_Thing20, 0, 4, -1.0844F, 1.0133F, -15.2682F, 1, 0, 1, 0.0F, false));

        ModelRenderer feet_Thing21 = new ModelRenderer(this);
        feet_Thing21.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing21, -1.5708F, 0.7854F, 0.0F);
        half_The_Pedestal7.addChild(feet_Thing21);
        feet_Thing21.cubeList.add(new ModelBox(feet_Thing21, 0, 4, -1.0844F, 12.76F, -3.9067F, 1, 2, 1, 0.0F, false));

        ModelRenderer other_thing7 = new ModelRenderer(this);
        other_thing7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing7, 0.0F, 1.5708F, 0.0F);
        half_The_Pedestal7.addChild(other_thing7);
        other_thing7.cubeList.add(new ModelBox(other_thing7, 0, 4, -0.8682F, -3.0F, -10.72F, 11, 3, 11, 0.0F, false));

        ModelRenderer thing4 = new ModelRenderer(this);
        thing4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thing4, -0.2618F, -2.3562F, 0.0F);
        half_The_Pedestal5.addChild(thing4);
        thing4.cubeList.add(new ModelBox(thing4, 0, 4, -1.086F, -6.88F, -15.2682F, 1, 7, 8, 0.0F, true));

        ModelRenderer bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        half_The_Pedestal5.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 4, 8.72F, -13.0F, -8.0133F, 1, 10, 16, 0.0F, true));

        ModelRenderer half_The_Pedestal8 = new ModelRenderer(this);
        half_The_Pedestal8.setRotationPoint(0.0F, 0.0F, 0.0F);
        half_The_Pedestal5.addChild(half_The_Pedestal8);

        ModelRenderer feet_Thing22 = new ModelRenderer(this);
        feet_Thing22.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing22, 0.0F, 0.7854F, 0.0F);
        half_The_Pedestal8.addChild(feet_Thing22);

        ModelRenderer feet_Thing23 = new ModelRenderer(this);
        feet_Thing23.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing23, -0.2618F, 0.7854F, 0.0F);
        half_The_Pedestal8.addChild(feet_Thing23);

        ModelRenderer feet_Thing24 = new ModelRenderer(this);
        feet_Thing24.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(feet_Thing24, -1.5708F, 0.7854F, 0.0F);
        half_The_Pedestal8.addChild(feet_Thing24);

        ModelRenderer other_thing8 = new ModelRenderer(this);
        other_thing8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(other_thing8, 0.0F, 1.5708F, 0.0F);
        half_The_Pedestal8.addChild(other_thing8);

        ModelRenderer spiky_2 = new ModelRenderer(this);
        spiky_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        half_The_Pedestal5.addChild(spiky_2);

        Holder = new ModelRenderer(this);
        Holder.setRotationPoint(-1.1733F, -30.3333F, 0.0F);
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, 0.623F, 39.8324F, -4.5F, 1, 2, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, 0.623F, 39.8324F, 1.5F, 1, 2, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, -1.377F, 34.8324F, 1.5F, 3, 0, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, 0.92F, 33.8324F, 1.1851F, 0, 1, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, -3.377F, 34.8324F, 1.5F, 3, 0, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, 0.623F, 36.8324F, 1.5F, 1, 2, 1, 0.0F, true));
        Holder.cubeList.add(new ModelBox(Holder, 12, 0, 0.623F, 35.8324F, 1.5F, 1, 2, 1, 0.0F, true));
    }

    @Override
    public void renderTrailEditor() {
        Lightning.render(1F);
        Pedestal.render(1F);
        Holder.render(1F);
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
