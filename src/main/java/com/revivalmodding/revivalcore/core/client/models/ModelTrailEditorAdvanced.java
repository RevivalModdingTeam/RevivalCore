package com.revivalmodding.revivalcore.core.client.models;

import com.revivalmodding.revivalcore.core.client.render.tileentity.RenderTrailEditor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author Psychokiller
 * Added by Toma, 12.2.2019
 */
public class ModelTrailEditorAdvanced extends ModelBase implements RenderTrailEditor.TrailEditorModel {

    private final ModelRenderer Lightning;
    private final ModelRenderer HalfPedestal;
    private final ModelRenderer Half_Pedestal2;
    private final ModelRenderer FootHalf;
    private final ModelRenderer Foot_Half2;
    private final ModelRenderer extras;

    public ModelTrailEditorAdvanced() {
        textureWidth = 16;
        textureHeight = 16;

        Lightning = new ModelRenderer(this);
        Lightning.setRotationPoint(0.0F, 0.0F, -11.0F);
        setRotationAngle(Lightning, -0.8727F, 0.0F, 0.0F);

        ModelRenderer bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(bone);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.954F, -6.3146F, 3.766F, 0, 2, 0, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.72F, -4.3972F, 3.766F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -4.2772F, 3.766F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -3.6905F, 3.766F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.9467F, -3.7972F, 3.766F, 0, 0, 1, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 0, -0.922F, -4.8372F, 3.766F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_Point2 = new ModelRenderer(this);
        lightning_Point2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightning_Point2, 0.0F, 0.0F, 0.5236F);
        Lightning.addChild(lightning_Point2);
        lightning_Point2.cubeList.add(new ModelBox(lightning_Point2, 0, 0, -2.6653F, -3.71F, 3.766F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightningPoint = new ModelRenderer(this);
        lightningPoint.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningPoint);
        lightningPoint.cubeList.add(new ModelBox(lightningPoint, 0, 0, -0.9522F, -5.6796F, 3.766F, -1, 2, 1, 0.0F, false));

        ModelRenderer lightningFill = new ModelRenderer(this);
        lightningFill.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningFill);

        ModelRenderer lightning_Fill2 = new ModelRenderer(this);
        lightning_Fill2.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightningFill.addChild(lightning_Fill2);
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -0.2533F, -10.3572F, 3.766F, 2, 2, 1, 0.0F, true));
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -0.2533F, -9.3572F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -1.2533F, -17.3572F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -0.2533F, -10.3572F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -0.954F, -8.359F, 3.846F, 1, 2, 1, 0.0F, true));
        lightning_Fill2.cubeList.add(new ModelBox(lightning_Fill2, 0, 0, -0.2533F, -12.3039F, 3.7127F, 3, 2, 1, 0.0F, true));

        ModelRenderer lightningpart4 = new ModelRenderer(this);
        lightningpart4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightningpart4, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(lightningpart4);
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, -2, 0, 4.4467F, -3.6786F, 3.766F, 5, 1, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, -2, 0, 6.4467F, -3.6786F, 3.766F, 5, 1, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.7218F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.7515F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.7977F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.7746F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.8208F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 2.9132F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.0056F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.0979F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.1233F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.1233F, -2.6653F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningpart4.cubeList.add(new ModelBox(lightningpart4, 0, 0, 3.8967F, -2.9053F, 3.766F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightningpart3 = new ModelRenderer(this);
        lightningpart3.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningpart3);
        lightningpart3.cubeList.add(new ModelBox(lightningpart3, -4, 0, -1.0794F, -12.1154F, 3.766F, 4, 0, 1, 0.0F, false));
        lightningpart3.cubeList.add(new ModelBox(lightningpart3, -4, 0, -4.0794F, -12.1154F, 3.766F, 4, 0, 1, 0.0F, false));

        ModelRenderer lightning2 = new ModelRenderer(this);
        lightning2.setRotationPoint(-1.1733F, -26.3333F, 0.0F);
        setRotationAngle(lightning2, 0.0F, 0.0F, 3.1416F);
        Lightning.addChild(lightning2);

        ModelRenderer bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(bone2);
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.9274F, -7.6002F, 3.766F, 0, 2, 0, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.6933F, -5.6828F, 3.766F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.92F, -5.5628F, 3.766F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.92F, -4.9761F, 3.766F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.92F, -5.0828F, 3.766F, 0, 0, 1, 0.0F, false));
        bone2.cubeList.add(new ModelBox(bone2, 0, 0, -0.8953F, -6.1228F, 3.766F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_Point3 = new ModelRenderer(this);
        lightning_Point3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightning_Point3, 0.0F, 0.0F, 0.5236F);
        lightning2.addChild(lightning_Point3);
        lightning_Point3.cubeList.add(new ModelBox(lightning_Point3, 0, 0, -3.285F, -4.8367F, 3.766F, 0, 1, 1, 0.0F, false));

        ModelRenderer lightning_Point4 = new ModelRenderer(this);
        lightning_Point4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_Point4);
        lightning_Point4.cubeList.add(new ModelBox(lightning_Point4, 0, 0, -0.9255F, -6.9651F, 3.766F, -1, 2, 1, 0.0F, false));

        ModelRenderer lightning_Fill3 = new ModelRenderer(this);
        lightning_Fill3.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_Fill3);

        ModelRenderer lightning_Fill4 = new ModelRenderer(this);
        lightning_Fill4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning_Fill3.addChild(lightning_Fill4);
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2267F, -11.6428F, 3.7127F, 2, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2267F, -11.6428F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2267F, -11.6428F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.9274F, -12.6446F, 3.7394F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.9274F, -9.6446F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.3407F, -10.6446F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -1.3007F, -16.1112F, 3.766F, 1, 2, 1, 0.0F, true));
        lightning_Fill4.cubeList.add(new ModelBox(lightning_Fill4, 0, 0, -0.2267F, -13.5895F, 3.8194F, 3, 2, 1, 0.0F, true));

        ModelRenderer lightning_part_2 = new ModelRenderer(this);
        lightning_part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lightning_part_2, 0.0F, 0.0F, -1.0472F);
        lightning2.addChild(lightning_part_2);
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, -2, 0, 7.5733F, -4.2983F, 3.766F, 5, 1, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, -2, 0, 5.56F, -4.3214F, 3.766F, 5, 1, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.8484F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.8782F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.9244F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.9244F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.9013F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 3.9475F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.0399F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.1322F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.2246F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.25F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 4.25F, -3.285F, 3.766F, 1, 0, 1, 0.0F, false));
        lightning_part_2.cubeList.add(new ModelBox(lightning_part_2, 0, 0, 5.0233F, -3.525F, 3.766F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightning_part_3 = new ModelRenderer(this);
        lightning_part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        lightning2.addChild(lightning_part_3);
        lightning_part_3.cubeList.add(new ModelBox(lightning_part_3, -4, 0, -5.0527F, -13.4009F, 3.766F, 4, 0, 1, 0.0F, false));
        lightning_part_3.cubeList.add(new ModelBox(lightning_part_3, -4, 0, -1.0527F, -13.4009F, 3.766F, 4, 0, 1, 0.0F, false));

        ModelRenderer bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-1.1733F, -26.3467F, 7.0F);
        setRotationAngle(bone4, 0.0F, 3.1416F, 0.0F);
        Lightning.addChild(bone4);

        ModelRenderer lightning_shit2 = new ModelRenderer(this);
        lightning_shit2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone4.addChild(lightning_shit2);
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4651F, 13.4061F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4189F, 13.4328F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5478F, 14.4044F, 2.234F, 1, -1, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.442F, 13.4195F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3958F, 13.4461F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3035F, 13.4995F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2111F, 13.5528F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1187F, 13.6061F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0933F, 13.6428F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0933F, 13.6428F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -4.32F, 13.9761F, 2.234F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0667F, 14.2161F, 2.234F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 13.5909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 13.5909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 13.6175F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 13.5909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4152F, 13.5909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2819F, 13.8575F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2552F, 13.8842F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2286F, 13.9642F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3086F, 13.8042F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3086F, 13.8309F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3619F, 13.7242F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3352F, 13.7509F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.2019F, 13.9909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1752F, 13.9909F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1752F, 14.0442F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1486F, 14.0709F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1486F, 14.0975F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1219F, 14.1242F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.1219F, 14.1242F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0952F, 14.1775F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.3619F, 13.7242F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.0552F, 14.2042F, 2.2333F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4952F, 13.4961F, 2.2333F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4686F, 13.5228F, 2.2333F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.4419F, 13.5495F, 2.2333F, 0, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 13.4042F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5467F, 13.4309F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 13.4309F, 2.2333F, 1, 0, 1, 0.0F, false));
        lightning_shit2.cubeList.add(new ModelBox(lightning_shit2, 0, 0, -5.5219F, 13.4575F, 2.2333F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone5, 0.0F, 0.0F, 1.0472F);
        bone4.addChild(bone5);
        bone5.cubeList.add(new ModelBox(bone5, 0, 0, 8.8481F, 11.5095F, 2.2333F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone6, 0.0F, 0.0F, 1.0472F);
        bone4.addChild(bone6);
        bone6.cubeList.add(new ModelBox(bone6, 0, 0, 8.8481F, 11.5095F, 2.2333F, 1, 0, 1, 0.0F, false));

        ModelRenderer lightningshit = new ModelRenderer(this);
        lightningshit.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lightning.addChild(lightningshit);
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4651F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4189F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5478F, -11.1229F, 3.766F, 1, -1, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.442F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3958F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3035F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2111F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1187F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0933F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0933F, -12.1172F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -4.32F, -12.3572F, 3.766F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0667F, -12.1305F, 3.766F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4152F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2819F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2552F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2286F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3086F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3086F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3619F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3352F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.2019F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1752F, -12.1453F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1752F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1486F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1486F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1219F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.1219F, -12.1453F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0952F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.3619F, -12.172F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.0552F, -12.1186F, 3.7654F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4952F, -12.1186F, 3.7654F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4686F, -12.1453F, 3.7654F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.4419F, -12.172F, 3.7654F, 0, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.1186F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5467F, -12.1186F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.1186F, 3.7654F, 1, 0, 1, 0.0F, false));
        lightningshit.cubeList.add(new ModelBox(lightningshit, 0, 0, -5.5219F, -12.1453F, 3.7654F, 1, 0, 1, 0.0F, false));

        ModelRenderer dsa = new ModelRenderer(this);
        dsa.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(dsa, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(dsa);
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 7.8084F, -10.7128F, 3.7654F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 7.7341F, -10.8414F, 3.7654F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 7.7341F, -10.8414F, 3.7654F, 1, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 7.8274F, -10.8148F, 3.7654F, 0, 0, 1, 0.0F, false));
        dsa.cubeList.add(new ModelBox(dsa, 0, 0, 7.7249F, -10.8626F, 3.766F, 1, 0, 1, 0.0F, false));

        ModelRenderer bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone3, 0.0F, 0.0F, -1.0472F);
        Lightning.addChild(bone3);
        bone3.cubeList.add(new ModelBox(bone3, 0, 0, 7.7348F, -10.84F, 3.7654F, 1, 0, 1, 0.0F, false));

        HalfPedestal = new ModelRenderer(this);
        HalfPedestal.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer dsadsadsa = new ModelRenderer(this);
        dsadsadsa.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(dsadsadsa, 0.0F, 0.0F, 0.6109F);
        HalfPedestal.addChild(dsadsadsa);
        dsadsadsa.cubeList.add(new ModelBox(dsadsadsa, 0, 14, -5.7815F, -4.1808F, -0.1467F, 0, 2, 1, 0.0F, false));
        dsadsadsa.cubeList.add(new ModelBox(dsadsadsa, 0, 14, -4.1432F, -5.0476F, -0.1467F, 0, 4, 1, 0.0F, false));

        ModelRenderer bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone14, 0.0F, 0.0F, -0.1745F);
        HalfPedestal.addChild(bone14);
        bone14.cubeList.add(new ModelBox(bone14, 0, 13, -1.2003F, -21.9885F, -0.1467F, 1, 2, 1, 0.0F, false));
        bone14.cubeList.add(new ModelBox(bone14, 0, 13, 1.213F, -19.4285F, -0.1467F, 1, 2, 1, 0.0F, false));

        ModelRenderer bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone13, 0.0F, 0.0F, -0.1745F);
        HalfPedestal.addChild(bone13);
        bone13.cubeList.add(new ModelBox(bone13, 0, 13, 0.5749F, -22.7355F, -0.1467F, 1, 2, 1, 0.0F, false));
        bone13.cubeList.add(new ModelBox(bone13, 0, 13, 0.5749F, -23.7355F, -0.1467F, 1, 2, 1, 0.0F, false));

        ModelRenderer bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone12, 0.0F, 0.0F, 0.0873F);
        HalfPedestal.addChild(bone12);
        bone12.cubeList.add(new ModelBox(bone12, 0, 13, -3.8824F, -21.41F, -0.1467F, 1, 1, 1, 0.0F, false));

        ModelRenderer bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone11);
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -2.0F, -23.4133F, -0.1467F, 1, 1, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -5.0F, -23.4133F, -0.1467F, 1, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -1.84F, -17.5467F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -1.84F, -16.5467F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -1.84F, -14.5467F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -2.4739F, -11.1874F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -2.4739F, -8.418F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -0.4739F, -8.5467F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -0.4739F, -9.5467F, -0.1467F, 0, 2, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -3.0345F, -20.2933F, -0.1467F, 1, 0, 1, 0.0F, false));
        bone11.cubeList.add(new ModelBox(bone11, 0, 13, -2.0F, -22.5067F, -0.1467F, 1, 1, 1, 0.0F, false));

        ModelRenderer bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone10, 0.0F, 1.5708F, 0.0F);
        HalfPedestal.addChild(bone10);
        bone10.cubeList.add(new ModelBox(bone10, 0, 6, -5.0F, -25.0F, -7.0F, 5, 6, 0, 0.0F, false));

        ModelRenderer bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone9, 0.3491F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone9);
        bone9.cubeList.add(new ModelBox(bone9, 0, 3, -9.0F, -23.0241F, 5.811F, 9, 1, 9, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 3, -9.0F, -23.0241F, 2.811F, 9, 1, 9, 0.0F, false));
        bone9.cubeList.add(new ModelBox(bone9, 0, 3, -9.0F, -23.0241F, 7.811F, 9, 1, 9, 0.0F, false));

        ModelRenderer bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone8);
        bone8.cubeList.add(new ModelBox(bone8, 0, 3, -9.0F, -27.6133F, 0.0F, 9, 1, 8, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 4, -9.0F, -28.52F, 1.0F, 1, 1, 7, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -9.0F, -29.3467F, 2.0F, 1, 1, 6, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -9.0F, -30.1733F, 3.0F, 1, 1, 5, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 8, -9.0F, -30.9467F, 4.0F, 1, 1, 4, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 8, -9.0F, -31.8107F, 5.0F, 1, 1, 3, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 10, -9.0F, -32.6373F, 6.0F, 1, 1, 2, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 2, 8, -9.0F, -33.2773F, 7.0F, 1, 1, 1, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -9.0F, -33.0F, 8.0F, 9, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 7, -7.0F, -25.0F, 5.0F, 5, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 7, -5.0F, -25.0F, 5.0F, 5, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -7.0F, -25.0F, 0.0F, 5, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -6.32F, -24.0F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -6.32F, -22.6933F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -5.7333F, -20.3733F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -5.3067F, -18.4F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -4.96F, -17.36F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -4.5333F, -12.6133F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -4.0533F, -18.6133F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -4.0533F, -16.6133F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -4.0533F, -12.6133F, 0.0F, 4, 6, 0, 0.0F, false));
        bone8.cubeList.add(new ModelBox(bone8, 0, 6, -5.0F, -25.0F, 0.0F, 5, 6, 0, 0.0F, false));

        ModelRenderer bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone7, 0.6981F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone7);
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -9.0F, -20.7344F, 16.1742F, 9, 1, 9, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -8.0F, -20.7344F, 11.1742F, 8, 1, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -9.0F, -20.7344F, 13.1742F, 9, 2, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -9.0F, -19.7344F, 16.1742F, 9, 2, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -9.0F, -18.8944F, 18.1742F, 9, 2, 6, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -8.0F, -21.234F, 19.1742F, 16, 1, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, 8.0F, -21.234F, 19.1742F, 1, 1, 8, 0.0F, false));
        bone7.cubeList.add(new ModelBox(bone7, 0, 3, -9.0F, -21.234F, 19.1742F, 1, 1, 8, 0.0F, false));

        ModelRenderer bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone15, 0.0F, 0.0F, -0.4363F);
        HalfPedestal.addChild(bone15);
        bone15.cubeList.add(new ModelBox(bone15, 0, 13, 4.014F, -19.5737F, -0.1467F, 1, 1, 1, 0.0F, false));

        ModelRenderer bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone16, 0.0F, 0.0F, -1.5708F);
        HalfPedestal.addChild(bone16);
        bone16.cubeList.add(new ModelBox(bone16, 0, 13, 18.52F, -4.2133F, -0.1467F, 1, 3, 1, 0.0F, false));

        ModelRenderer bigguslineus = new ModelRenderer(this);
        bigguslineus.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bigguslineus, 0.0F, 0.0F, 0.4363F);
        HalfPedestal.addChild(bigguslineus);
        bigguslineus.cubeList.add(new ModelBox(bigguslineus, 0, 14, -6.9701F, -10.0937F, -0.1467F, 0, 1, 1, 0.0F, false));
        bigguslineus.cubeList.add(new ModelBox(bigguslineus, 0, 14, -4.7348F, -11.0326F, -0.1467F, 0, 2, 1, 0.0F, false));

        ModelRenderer daasd = new ModelRenderer(this);
        daasd.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd, 0.6109F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd);
        daasd.cubeList.add(new ModelBox(daasd, 2, 14, -3.2345F, 2.3512F, -1.7222F, 0, 2, 0, 0.0F, false));

        ModelRenderer bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone17);
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -3.6717F, -4.5907F, -0.1467F, 0, 0, 1, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -5.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -4.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -3.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -2.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -1.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -0.0104F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, 0.9896F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, 1.9896F, -4.5907F, 5.7255F, 1, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, 2.9896F, -4.5907F, 5.7255F, 2, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, 2.9896F, -4.5907F, 5.7255F, 0, 0, 0, 0.0F, false));
        bone17.cubeList.add(new ModelBox(bone17, 0, 14, -2.793F, -2.3283F, -0.1467F, 0, 0, 1, 0.0F, false));

        ModelRenderer bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone18, 0.0F, 1.5708F, -0.4363F);
        HalfPedestal.addChild(bone18);
        bone18.cubeList.add(new ModelBox(bone18, 0, 6, -5.7255F, -19.4595F, 1.6856F, 5, 1, 0, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 6, -5.7255F, -17.747F, 1.2251F, 5, 1, 0, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 6, -5.7255F, -15.747F, 0.7677F, 5, 1, 0, 0.0F, false));
        bone18.cubeList.add(new ModelBox(bone18, 0, 6, -5.7255F, -13.747F, 0.3127F, 5, 2, 0, 0.0F, false));

        ModelRenderer bone19 = new ModelRenderer(this);
        bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone19, 0.0F, 1.5708F, 0.0F);
        HalfPedestal.addChild(bone19);
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -17.6133F, -6.3952F, 5, 1, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -15.6133F, -5.9673F, 5, 1, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -13.6133F, -5.5366F, 5, 1, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -10.6133F, -4.6811F, 5, 4, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -9.4F, -4.6811F, 4, 4, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -8.6996F, -4.6811F, 4, 4, 0, 0.0F, false));
        bone19.cubeList.add(new ModelBox(bone19, 0, 6, -5.7255F, -4.6996F, -4.6811F, 4, 0, 4, 0.0F, false));

        ModelRenderer bone20 = new ModelRenderer(this);
        bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone20, 0.4363F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone20);
        bone20.cubeList.add(new ModelBox(bone20, 0, 6, -4.6811F, -5.707F, 2.7949F, 4, 1, 1, 0.0F, false));

        ModelRenderer daasd2 = new ModelRenderer(this);
        daasd2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd2, 0.9599F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd2);
        daasd2.cubeList.add(new ModelBox(daasd2, 0, 14, -3.2345F, 3.6198F, -3.1065F, 0, 1, 0, 0.0F, false));

        ModelRenderer daasd3 = new ModelRenderer(this);
        daasd3.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd3, 1.5708F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd3);
        daasd3.cubeList.add(new ModelBox(daasd3, 0, 14, -5.1026F, 1.8533F, -5.1678F, 0, 4, 1, 0.0F, false));
        daasd3.cubeList.add(new ModelBox(daasd3, 1, 13, -3.2345F, 2.7255F, -5.1945F, 0, 3, 0, 0.0F, false));

        ModelRenderer bone21 = new ModelRenderer(this);
        bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone21, 0.6981F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone21);
        bone21.cubeList.add(new ModelBox(bone21, 0, 6, -4.6811F, -2.8473F, 3.9179F, 4, 0, 0, 0.0F, false));

        ModelRenderer bone22 = new ModelRenderer(this);
        bone22.setRotationPoint(0.0F, 0.0F, 0.0F);
        HalfPedestal.addChild(bone22);
        bone22.cubeList.add(new ModelBox(bone22, 0, 6, -3.4411F, -4.6133F, 0.5607F, 3, 0, 0, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 0, 6, -3.5211F, -2.6133F, 1.1207F, 3, 0, 0, 0.0F, false));
        bone22.cubeList.add(new ModelBox(bone22, 0, 6, -3.5211F, -4.6133F, 0.5607F, 3, 2, 0, 0.0F, false));

        ModelRenderer daasd4 = new ModelRenderer(this);
        daasd4.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd4, 0.6109F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd4);
        daasd4.cubeList.add(new ModelBox(daasd4, 0, 14, -5.1026F, 3.3322F, -2.2261F, 0, 1, 0, 0.0F, false));

        ModelRenderer daasd5 = new ModelRenderer(this);
        daasd5.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd5, 0.9599F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd5);
        daasd5.cubeList.add(new ModelBox(daasd5, 0, 14, -5.1026F, 3.9062F, -3.5736F, 0, 0, 0, 0.0F, false));

        ModelRenderer daasd6 = new ModelRenderer(this);
        daasd6.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd6, 1.5708F, 0.0F, 1.5708F);
        HalfPedestal.addChild(daasd6);

        ModelRenderer bone23 = new ModelRenderer(this);
        bone23.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone23, 0.0F, 1.5708F, 0.0F);
        HalfPedestal.addChild(bone23);
        bone23.cubeList.add(new ModelBox(bone23, 0, 6, -5.1877F, -4.6133F, -4.6585F, 2, 2, 0, 0.0F, false));
        bone23.cubeList.add(new ModelBox(bone23, 0, 6, -5.7255F, -3.6133F, -4.6585F, 2, 1, 0, 0.0F, false));

        ModelRenderer bone24 = new ModelRenderer(this);
        bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone24, 0.0F, 1.0472F, 0.0F);
        HalfPedestal.addChild(bone24);
        bone24.cubeList.add(new ModelBox(bone24, 0, 6, -4.5211F, -4.6133F, -2.769F, 2, 2, 0, 0.0F, false));

        Half_Pedestal2 = new ModelRenderer(this);
        Half_Pedestal2.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer dsadsadsa2 = new ModelRenderer(this);
        dsadsadsa2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(dsadsadsa2, 0.0F, 0.0F, -0.6109F);
        Half_Pedestal2.addChild(dsadsadsa2);
        dsadsadsa2.cubeList.add(new ModelBox(dsadsadsa2, 0, 14, 4.8891F, -4.1808F, -0.1467F, 0, 2, 1, 0.0F, true));
        dsadsadsa2.cubeList.add(new ModelBox(dsadsadsa2, 0, 14, 3.4751F, -5.0476F, -0.1467F, 0, 4, 1, 0.0F, true));

        ModelRenderer bone25 = new ModelRenderer(this);
        bone25.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone25, 0.0F, 0.0F, 0.1745F);
        Half_Pedestal2.addChild(bone25);
        bone25.cubeList.add(new ModelBox(bone25, 0, 13, 0.2003F, -21.9885F, -0.1467F, 1, 2, 1, 0.0F, true));
        bone25.cubeList.add(new ModelBox(bone25, 0, 13, -2.213F, -19.4285F, -0.1467F, 1, 2, 1, 0.0F, true));

        ModelRenderer bone26 = new ModelRenderer(this);
        bone26.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone26, 0.0F, 0.0F, 0.1745F);
        Half_Pedestal2.addChild(bone26);
        bone26.cubeList.add(new ModelBox(bone26, 0, 13, -1.5749F, -22.7355F, -0.1467F, 1, 2, 1, 0.0F, true));
        bone26.cubeList.add(new ModelBox(bone26, 0, 13, -1.5749F, -23.7355F, -0.1467F, 1, 2, 1, 0.0F, true));

        ModelRenderer bone27 = new ModelRenderer(this);
        bone27.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone27, 0.0F, 0.0F, -0.0873F);
        Half_Pedestal2.addChild(bone27);
        bone27.cubeList.add(new ModelBox(bone27, 0, 13, 2.8824F, -21.41F, -0.1467F, 1, 1, 1, 0.0F, true));

        ModelRenderer bone28 = new ModelRenderer(this);
        bone28.setRotationPoint(0.0F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone28);
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 1.0F, -23.4133F, -0.1467F, 1, 1, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 4.0F, -23.4133F, -0.1467F, 1, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 0.847F, -17.5467F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 0.847F, -16.5467F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 0.847F, -14.5467F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 1.4745F, -11.1874F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 1.4745F, -8.418F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, -0.3418F, -8.5467F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, -0.3418F, -9.5467F, -0.1467F, 0, 2, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 1.0926F, -20.2933F, -0.1467F, 1, 0, 1, 0.0F, true));
        bone28.cubeList.add(new ModelBox(bone28, 0, 13, 1.0F, -22.5067F, -0.1467F, 1, 1, 1, 0.0F, true));

        ModelRenderer bone29 = new ModelRenderer(this);
        bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone29, 0.0F, -1.5708F, 0.0F);
        Half_Pedestal2.addChild(bone29);
        bone29.cubeList.add(new ModelBox(bone29, 0, 6, 0.0F, -25.0F, -7.0F, 5, 6, 0, 0.0F, true));

        ModelRenderer bone30 = new ModelRenderer(this);
        bone30.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone30, 0.3491F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone30);
        bone30.cubeList.add(new ModelBox(bone30, 0, 3, 0.0F, -23.0241F, 5.811F, 9, 1, 9, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 0, 3, 0.0F, -23.0241F, 2.811F, 9, 1, 9, 0.0F, true));
        bone30.cubeList.add(new ModelBox(bone30, 0, 3, 0.0F, -23.0241F, 7.811F, 9, 1, 9, 0.0F, true));

        ModelRenderer bone31 = new ModelRenderer(this);
        bone31.setRotationPoint(0.0F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone31);
        bone31.cubeList.add(new ModelBox(bone31, 0, 3, 0.0F, -27.6133F, 0.0F, 9, 1, 8, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 4, 8.0F, -28.52F, 1.0F, 1, 1, 7, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 8.0F, -29.3467F, 2.0F, 1, 1, 6, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 8.0F, -30.1733F, 3.0F, 1, 1, 5, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 8, 8.0F, -30.9467F, 4.0F, 1, 1, 4, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 8, 8.0F, -31.8107F, 5.0F, 1, 1, 3, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 10, 8.0F, -32.6373F, 6.0F, 1, 1, 2, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 2, 8, 8.0F, -33.2773F, 7.0F, 1, 1, 1, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.0F, -33.0F, 8.0F, 9, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 2.0F, -25.0F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.0F, -25.0F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.0F, -17.64F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -5.3867F, -19.1067F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -5.0933F, -18.12F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -4.5333F, -13.56F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -4.88F, -17.32F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -4.3467F, -11.08F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -0.5867F, -11.08F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -0.5867F, -9.08F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -4.5867F, -9.08F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -0.4F, -16.9733F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -0.4F, -16.9733F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -3.84F, -19.1067F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -5.7067F, -20.6267F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, -6.2933F, -22.6F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.08F, -17.64F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.3733F, -18.8933F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.64F, -20.6533F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 1.3333F, -24.04F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 0.8533F, -20.6533F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 7, 1.2533F, -22.7067F, 5.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 2.0F, -25.0F, 0.0F, 5, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 2.32F, -24.0F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 2.32F, -22.6933F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 1.7333F, -20.3733F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 1.3067F, -18.4F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.96F, -17.36F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.5333F, -12.6133F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.0F, -18.6133F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.0F, -16.6133F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.0F, -12.6133F, 0.0F, 4, 6, 0, 0.0F, true));
        bone31.cubeList.add(new ModelBox(bone31, 0, 6, 0.0F, -25.0F, 0.0F, 5, 6, 0, 0.0F, true));

        ModelRenderer bone32 = new ModelRenderer(this);
        bone32.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone32, 0.6981F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone32);
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -20.7344F, 16.1742F, 9, 1, 9, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -20.7344F, 11.1742F, 8, 1, 8, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -20.7344F, 13.1742F, 9, 2, 6, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -19.7344F, 16.1742F, 9, 2, 6, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -18.8944F, 18.1742F, 9, 2, 6, 0.0F, true));
        bone32.cubeList.add(new ModelBox(bone32, 0, 3, 0.0F, -21.234F, 19.1742F, 8, 1, 8, 0.0F, true));

        ModelRenderer bone33 = new ModelRenderer(this);
        bone33.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone33, 0.0F, 0.0F, 0.4363F);
        Half_Pedestal2.addChild(bone33);
        bone33.cubeList.add(new ModelBox(bone33, 0, 13, -5.0321F, -19.5737F, -0.1467F, 1, 1, 1, 0.0F, true));

        ModelRenderer bone34 = new ModelRenderer(this);
        bone34.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone34, 0.0F, 0.0F, 1.5708F);
        Half_Pedestal2.addChild(bone34);
        bone34.cubeList.add(new ModelBox(bone34, 0, 13, -19.52F, -4.2133F, -0.1467F, 1, 3, 1, 0.0F, true));

        ModelRenderer biggus_lineus2 = new ModelRenderer(this);
        biggus_lineus2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(biggus_lineus2, 0.0F, 0.0F, -0.4363F);
        Half_Pedestal2.addChild(biggus_lineus2);
        biggus_lineus2.cubeList.add(new ModelBox(biggus_lineus2, 0, 14, 6.0701F, -10.0937F, -0.1467F, 0, 1, 1, 0.0F, true));
        biggus_lineus2.cubeList.add(new ModelBox(biggus_lineus2, 0, 14, 3.9956F, -11.0326F, -0.1467F, 0, 2, 1, 0.0F, true));

        ModelRenderer daasd7 = new ModelRenderer(this);
        daasd7.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd7, 0.6109F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd7);
        daasd7.cubeList.add(new ModelBox(daasd7, 0, 14, 2.3283F, 2.3512F, -1.7222F, 0, 2, 0, 0.0F, true));

        ModelRenderer bone35 = new ModelRenderer(this);
        bone35.setRotationPoint(0.0F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone35);
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 2.754F, -4.5907F, -0.1467F, 0, 0, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 2.2457F, -2.3283F, -0.1467F, 0, 0, 1, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 3.8055F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 2.8055F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 1.8055F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, 0.8055F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -0.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -1.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -2.1945F, -2.3283F, 5.8533F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -3.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -2.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -4.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));
        bone35.cubeList.add(new ModelBox(bone35, 0, 14, -5.1945F, -2.3283F, 5.7255F, 1, 0, 0, 0.0F, true));

        ModelRenderer bone36 = new ModelRenderer(this);
        bone36.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone36, 0.0F, -1.5708F, 0.4363F);
        Half_Pedestal2.addChild(bone36);
        bone36.cubeList.add(new ModelBox(bone36, 0, 6, 0.0F, -19.4595F, 1.6856F, 5, 1, 0, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 0, 6, 0.0F, -17.747F, 1.2251F, 5, 1, 0, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 0, 6, 0.0F, -15.747F, 0.7677F, 5, 1, 0, 0.0F, true));
        bone36.cubeList.add(new ModelBox(bone36, 0, 6, 0.0F, -13.747F, 0.3127F, 5, 2, 0, 0.0F, true));

        ModelRenderer bone37 = new ModelRenderer(this);
        bone37.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone37, 0.0F, -1.5708F, 0.0F);
        Half_Pedestal2.addChild(bone37);
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.0F, -17.6133F, -6.3952F, 5, 1, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.0F, -15.6133F, -5.9673F, 5, 1, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.0F, -13.6133F, -5.5366F, 5, 1, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.0F, -10.6133F, -4.6811F, 5, 4, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.7467F, -9.4F, -4.6811F, 4, 4, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 0.7467F, -8.6996F, -4.6811F, 4, 4, 0, 0.0F, true));
        bone37.cubeList.add(new ModelBox(bone37, 0, 6, 1.1711F, -4.6996F, -4.6811F, 4, 0, 4, 0.0F, true));

        ModelRenderer bone38 = new ModelRenderer(this);
        bone38.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone38, 0.4363F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone38);
        bone38.cubeList.add(new ModelBox(bone38, 0, 6, 0.0F, -5.707F, 2.7949F, 4, 1, 1, 0.0F, true));

        ModelRenderer daasd8 = new ModelRenderer(this);
        daasd8.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd8, 0.9599F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd8);
        daasd8.cubeList.add(new ModelBox(daasd8, 0, 14, 2.3283F, 3.6198F, -3.1065F, 0, 1, 0, 0.0F, true));

        ModelRenderer daasd9 = new ModelRenderer(this);
        daasd9.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd9, 1.5708F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd9);
        daasd9.cubeList.add(new ModelBox(daasd9, 0, 14, 4.5907F, 1.7255F, -5.0104F, 0, 4, 0, 0.0F, true));
        daasd9.cubeList.add(new ModelBox(daasd9, 0, 14, 2.3283F, 2.7255F, -5.1945F, 0, 3, 0, 0.0F, true));

        ModelRenderer bone39 = new ModelRenderer(this);
        bone39.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone39, 0.6981F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone39);
        bone39.cubeList.add(new ModelBox(bone39, 0, 6, 0.0F, -2.8473F, 3.9179F, 4, 0, 0, 0.0F, true));

        ModelRenderer bone40 = new ModelRenderer(this);
        bone40.setRotationPoint(0.0F, 0.0F, 0.0F);
        Half_Pedestal2.addChild(bone40);
        bone40.cubeList.add(new ModelBox(bone40, 0, 6, -0.24F, -4.6133F, 0.5607F, 3, 0, 0, 0.0F, true));
        bone40.cubeList.add(new ModelBox(bone40, 0, 6, -0.16F, -2.6133F, 1.1207F, 3, 0, 0, 0.0F, true));
        bone40.cubeList.add(new ModelBox(bone40, 0, 6, -0.24F, -4.6133F, 0.5607F, 3, 2, 0, 0.0F, true));

        ModelRenderer daasd10 = new ModelRenderer(this);
        daasd10.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd10, 0.6109F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd10);
        daasd10.cubeList.add(new ModelBox(daasd10, 0, 14, 4.5907F, 3.2806F, -2.1191F, 0, 1, 0, 0.0F, true));

        ModelRenderer daasd11 = new ModelRenderer(this);
        daasd11.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd11, 0.9599F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd11);
        daasd11.cubeList.add(new ModelBox(daasd11, 0, 14, 4.5907F, 3.8006F, -3.4553F, 0, 0, 0, 0.0F, true));

        ModelRenderer daasd12 = new ModelRenderer(this);
        daasd12.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(daasd12, 1.5708F, 0.0F, -1.5708F);
        Half_Pedestal2.addChild(daasd12);

        ModelRenderer bone41 = new ModelRenderer(this);
        bone41.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone41, 0.0F, -1.5708F, 0.0F);
        Half_Pedestal2.addChild(bone41);
        bone41.cubeList.add(new ModelBox(bone41, 0, 6, 2.5309F, -4.6133F, -4.6585F, 2, 2, 0, 0.0F, true));
        bone41.cubeList.add(new ModelBox(bone41, 0, 6, 2.7709F, -3.6133F, -4.6585F, 2, 1, 0, 0.0F, true));

        ModelRenderer bone42 = new ModelRenderer(this);
        bone42.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone42, 0.0F, -1.0472F, 0.0F);
        Half_Pedestal2.addChild(bone42);
        bone42.cubeList.add(new ModelBox(bone42, 0, 6, 2.2461F, -4.6133F, -2.769F, 2, 2, 0, 0.0F, true));

        FootHalf = new ModelRenderer(this);
        FootHalf.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer bone43 = new ModelRenderer(this);
        bone43.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone43, 0.0F, -0.9599F, 0.0F);
        FootHalf.addChild(bone43);
        bone43.cubeList.add(new ModelBox(bone43, 0, 14, -2.5733F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 0, 14, -3.3867F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 0, 14, -4.1333F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, false));
        bone43.cubeList.add(new ModelBox(bone43, 0, 14, -4.84F, -2.3283F, 2.2038F, 0, 1, 2, 0.0F, false));

        ModelRenderer bone45 = new ModelRenderer(this);
        bone45.setRotationPoint(0.0F, 0.0F, 0.0F);
        FootHalf.addChild(bone45);
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -4.5814F, -2.3283F, -2.7007F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -4.8949F, -1.3283F, -3.1485F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -2.8949F, -1.3283F, -3.1485F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -2.5814F, -2.3283F, -2.7007F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -4.2152F, -2.3283F, 8.1463F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -4.3183F, -1.3417F, 8.3757F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 15, -2.3183F, -1.3417F, 8.3757F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 15, -1.3183F, -1.3417F, 8.3757F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -2.2152F, -2.3283F, 8.1463F, 2, 1, 0, 0.0F, false));
        bone45.cubeList.add(new ModelBox(bone45, 0, 14, -1.2152F, -2.3283F, 8.1463F, 2, 1, 0, 0.0F, false));

        ModelRenderer bone46 = new ModelRenderer(this);
        bone46.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone46, 0.0F, -2.1817F, 0.0F);
        FootHalf.addChild(bone46);
        bone46.cubeList.add(new ModelBox(bone46, 0, 14, 8.9987F, -2.3283F, -1.3512F, 0, 1, 2, 0.0F, false));
        bone46.cubeList.add(new ModelBox(bone46, 0, 14, 9.2787F, -1.3417F, -1.3512F, 0, 1, 2, 0.0F, false));

        ModelRenderer bone47 = new ModelRenderer(this);
        bone47.setRotationPoint(0.0F, 0.0F, 0.0F);
        FootHalf.addChild(bone47);
        bone47.cubeList.add(new ModelBox(bone47, 0, 14, -6.3404F, -2.3283F, -1.4689F, 0, 1, 2, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 0, 14, -6.3404F, -2.3283F, 2.5311F, 0, 1, 2, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 0, 14, -6.3404F, -2.3283F, 0.5311F, 0, 1, 2, 0.0F, false));
        bone47.cubeList.add(new ModelBox(bone47, 0, 14, -6.3404F, -2.3283F, 4.5311F, 0, 1, 2, 0.0F, false));

        ModelRenderer bone48 = new ModelRenderer(this);
        bone48.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone48, 0.0F, -0.9599F, 0.0F);
        FootHalf.addChild(bone48);
        bone48.cubeList.add(new ModelBox(bone48, 0, 14, -5.3867F, -1.3283F, 2.2038F, 0, 1, 2, 0.0F, false));

        ModelRenderer bone44 = new ModelRenderer(this);
        bone44.setRotationPoint(0.0F, 0.0F, 0.0F);
        FootHalf.addChild(bone44);
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -5.9333F, -1.8533F, -1.0F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -1.3322F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -2.3322F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -3.3322F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.3322F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.3322F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.9055F, -0.3417F, -2.1567F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -6.5055F, -0.3417F, -1.5701F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -6.5055F, -0.3417F, 1.4966F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -6.5055F, -0.3417F, 3.0433F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -6.3189F, -0.3417F, 3.1766F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -6.0522F, -0.3417F, 3.3099F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.9455F, -0.3417F, 3.4166F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.5189F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.0522F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.7455F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -2.7189F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -1.4122F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -1.4122F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.3989F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.6922F, -0.3417F, 4.2966F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.7055F, -0.3417F, 3.6033F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.4389F, -0.3417F, 3.7633F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.1989F, -0.3417F, 3.9233F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.9322F, -0.3417F, 4.1633F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -4.5322F, -0.3417F, 4.3233F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -5.0655F, -0.3417F, 4.0299F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -3.2522F, -0.3417F, 4.0299F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -3.2522F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 0, 13, -2.3989F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -5.6133F, -1.8533F, -0.7733F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -5.3467F, -1.8533F, -0.5067F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -4.9467F, -1.8533F, -0.3467F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -4.6533F, -1.8533F, -0.08F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -4.3867F, -1.8533F, 0.16F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -5.3733F, -1.8533F, -2.0F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -4.4133F, -1.8533F, -2.0F, 5, 1, 8, 0.0F, false));
        bone44.cubeList.add(new ModelBox(bone44, 12, 14, -6.0133F, -1.8533F, -1.52F, 5, 1, 8, 0.0F, false));

        ModelRenderer bone49 = new ModelRenderer(this);
        bone49.setRotationPoint(0.0F, 0.0F, 0.0F);
        FootHalf.addChild(bone49);
        bone49.cubeList.add(new ModelBox(bone49, 0, 14, -6.654F, -1.3417F, -1.9167F, 0, 1, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 0, 14, -6.654F, -1.3417F, 2.8876F, 0, 1, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 0, 14, -6.654F, -1.3417F, 0.8876F, 0, 1, 2, 0.0F, false));
        bone49.cubeList.add(new ModelBox(bone49, 0, 14, -6.654F, -1.3417F, 4.8876F, 0, 1, 2, 0.0F, false));

        ModelRenderer bone50 = new ModelRenderer(this);
        bone50.setRotationPoint(0.0F, 0.0F, 0.0F);
        FootHalf.addChild(bone50);
        bone50.cubeList.add(new ModelBox(bone50, 0, 14, -3.3867F, -2.3283F, 6.2038F, 0, 0, 2, 0.0F, false));

        ModelRenderer bone51 = new ModelRenderer(this);
        bone51.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone50.addChild(bone51);
        bone51.cubeList.add(new ModelBox(bone51, 0, 14, -3.3867F, -2.3283F, 5.6171F, 0, 0, 2, 0.0F, false));

        Foot_Half2 = new ModelRenderer(this);
        Foot_Half2.setRotationPoint(0.0F, 24.0F, 0.0F);

        ModelRenderer bone52 = new ModelRenderer(this);
        bone52.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone52, 0.0F, 0.9599F, 0.0F);
        Foot_Half2.addChild(bone52);
        bone52.cubeList.add(new ModelBox(bone52, 0, 14, 1.7222F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, true));
        bone52.cubeList.add(new ModelBox(bone52, 0, 14, 2.5355F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, true));
        bone52.cubeList.add(new ModelBox(bone52, 0, 14, 3.2822F, -2.3283F, 2.2038F, 0, 0, 2, 0.0F, true));
        bone52.cubeList.add(new ModelBox(bone52, 0, 14, 3.9888F, -2.3283F, 2.2038F, 0, 1, 2, 0.0F, true));

        ModelRenderer bone53 = new ModelRenderer(this);
        bone53.setRotationPoint(0.0F, 0.0F, 0.0F);
        Foot_Half2.addChild(bone53);
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 2.1124F, -2.3283F, -2.7007F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 2.1124F, -1.3283F, -3.1485F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 0.1124F, -1.3283F, -3.1485F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, -0.8876F, -1.3283F, -3.1485F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 0.1124F, -2.3283F, -2.7007F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, -0.8876F, -2.3283F, -2.7007F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 2.1124F, -2.3283F, 8.1463F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 2.1124F, -1.3417F, 8.3757F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 15, 0.1124F, -1.3417F, 8.3757F, 2, 1, 0, 0.0F, true));
        bone53.cubeList.add(new ModelBox(bone53, 0, 14, 0.1124F, -2.3283F, 8.1463F, 2, 1, 0, 0.0F, true));

        ModelRenderer bone54 = new ModelRenderer(this);
        bone54.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone54, 0.0F, 2.1817F, 0.0F);
        Foot_Half2.addChild(bone54);
        bone54.cubeList.add(new ModelBox(bone54, 0, 14, -9.2787F, -2.3283F, -1.3512F, 0, 1, 2, 0.0F, true));
        bone54.cubeList.add(new ModelBox(bone54, 0, 14, -9.4585F, -1.3417F, -1.3512F, 0, 1, 2, 0.0F, true));

        ModelRenderer bone55 = new ModelRenderer(this);
        bone55.setRotationPoint(0.0F, 0.0F, 0.0F);
        Foot_Half2.addChild(bone55);
        bone55.cubeList.add(new ModelBox(bone55, 0, 14, 5.8522F, -2.3283F, -1.4689F, 0, 1, 2, 0.0F, true));
        bone55.cubeList.add(new ModelBox(bone55, 0, 14, 5.8522F, -2.3283F, 2.5311F, 0, 1, 2, 0.0F, true));
        bone55.cubeList.add(new ModelBox(bone55, 0, 14, 5.8522F, -2.3283F, 0.5311F, 0, 1, 2, 0.0F, true));
        bone55.cubeList.add(new ModelBox(bone55, 0, 14, 5.8522F, -2.3283F, 4.5311F, 0, 1, 2, 0.0F, true));

        ModelRenderer bone56 = new ModelRenderer(this);
        bone56.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(bone56, 0.0F, 0.9599F, 0.0F);
        Foot_Half2.addChild(bone56);
        bone56.cubeList.add(new ModelBox(bone56, 0, 14, 4.5355F, -1.3283F, 2.2038F, 0, 1, 2, 0.0F, true));

        ModelRenderer bone57 = new ModelRenderer(this);
        bone57.setRotationPoint(0.0F, 0.0F, 0.0F);
        Foot_Half2.addChild(bone57);
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, 0.9333F, -1.8533F, -1.0F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, -0.0667F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 0.9333F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 1.9333F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 2.9333F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.9333F, -0.3417F, -2.5034F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.5067F, -0.3417F, -2.1567F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 5.1067F, -0.3417F, -1.5701F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 5.1067F, -0.3417F, 1.4966F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 5.1067F, -0.3417F, 3.0433F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.92F, -0.3417F, 3.1766F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.6533F, -0.3417F, 3.3099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.5467F, -0.3417F, 3.4166F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.12F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 2.6533F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.3467F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 1.32F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 0.0133F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, -0.68F, -0.3417F, 0.8033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 0.0133F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, -0.68F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.0F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.2933F, -0.3417F, 4.2966F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.3067F, -0.3417F, 3.6033F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 4.04F, -0.3417F, 3.7633F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.8F, -0.3417F, 3.9233F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.5333F, -0.3417F, 4.1633F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.1333F, -0.3417F, 4.3233F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 3.6667F, -0.3417F, 4.0299F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 1.8533F, -0.3417F, 4.0299F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 1.8533F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 0, 13, 1.0F, -0.3417F, 4.5099F, 1, 0, 3, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, 0.6133F, -1.8533F, -0.7733F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, 0.3467F, -1.8533F, -0.5067F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, -0.0533F, -1.8533F, -0.3467F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, -0.3467F, -1.8533F, -0.08F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, -0.6133F, -1.8533F, 0.16F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, 0.3733F, -1.8533F, -2.0F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, -0.5867F, -1.8533F, -2.0F, 5, 1, 8, 0.0F, true));
        bone57.cubeList.add(new ModelBox(bone57, 12, 14, 1.0133F, -1.8533F, -1.52F, 5, 1, 8, 0.0F, true));

        ModelRenderer bone58 = new ModelRenderer(this);
        bone58.setRotationPoint(0.0F, 0.0F, 0.0F);
        Foot_Half2.addChild(bone58);
        bone58.cubeList.add(new ModelBox(bone58, 0, 14, 6.3322F, -1.3417F, -1.9167F, 0, 1, 2, 0.0F, true));
        bone58.cubeList.add(new ModelBox(bone58, 0, 14, 6.3322F, -1.3417F, 2.8876F, 0, 1, 2, 0.0F, true));
        bone58.cubeList.add(new ModelBox(bone58, 0, 14, 6.3322F, -1.3417F, 0.8876F, 0, 1, 2, 0.0F, true));
        bone58.cubeList.add(new ModelBox(bone58, 0, 14, 6.3322F, -1.3417F, 4.8876F, 0, 1, 2, 0.0F, true));

        ModelRenderer bone59 = new ModelRenderer(this);
        bone59.setRotationPoint(0.0F, 0.0F, 0.0F);
        Foot_Half2.addChild(bone59);
        bone59.cubeList.add(new ModelBox(bone59, 0, 14, 2.5355F, -2.3283F, 6.2038F, 0, 0, 2, 0.0F, true));

        ModelRenderer bone60 = new ModelRenderer(this);
        bone60.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone59.addChild(bone60);
        bone60.cubeList.add(new ModelBox(bone60, 0, 14, 2.5355F, -2.3283F, 5.6171F, 0, 0, 2, 0.0F, true));

        extras = new ModelRenderer(this);
        extras.setRotationPoint(0.0F, 24.0F, 0.0F);
        extras.cubeList.add(new ModelBox(extras, 0, 4, -9.0F, -33.0F, 7.3166F, 18, 0, 0, 0.0F, false));
    }

    @Override
    public void renderTrailEditor() {
        Lightning.render(1f);
        HalfPedestal.render(1f);
        Half_Pedestal2.render(1f);
        FootHalf.render(1f);
        Foot_Half2.render(1f);
        extras.render(1f);
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
