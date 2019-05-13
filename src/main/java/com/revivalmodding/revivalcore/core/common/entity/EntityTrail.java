package com.revivalmodding.revivalcore.core.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

public class EntityTrail extends EntityLivingBase {

    public EntityPlayer owner;
    public float[] lightningFactor;
    public float alpha = 1F;

    public EntityTrail(World worldIn) {
        super(worldIn);
        noClip = true;
        ignoreFrustumCheck = true;
    }

    public EntityTrail(World world, EntityPlayer owner) {
        this(world);
        this.owner = owner;
        this.setSize(owner.width, owner.height);
        this.setLocationAndAngles(owner.posX, owner.posY, owner.posZ, owner.rotationYaw, owner.rotationPitch);

        this.swingProgress = owner.swingProgress;
        this.prevSwingProgress = owner.swingProgress;
        this.prevRenderYawOffset = owner.renderYawOffset;
        this.renderYawOffset = owner.renderYawOffset;
        this.prevRotationYawHead = owner.rotationYawHead;
        this.rotationYawHead = owner.rotationYawHead;
        this.prevRotationPitch = owner.rotationPitch;
        this.rotationPitch = owner.rotationPitch;
        this.limbSwingAmount = owner.limbSwingAmount;
        this.prevLimbSwingAmount = owner.limbSwingAmount;
        this.limbSwing = owner.limbSwing;

        this.lightningFactor = new float[20];
        for (int i = 0; i < 20; i++) {
            this.lightningFactor[i] = rand.nextFloat();
        }
    }

    public Random getRandom() {
        return new Random(this.getEntityId());
    }

    public Vec3d getLightningPosVector(int i) {
        float halfWidth = width / 2;
        return new Vec3d(posX - halfWidth + (lightningFactor[i] * width), posY, posZ - halfWidth + (lightningFactor[10 + i] * width));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 11) {
            this.setDead();
        }
    }

    @Override
    public void onLivingUpdate() {

    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean writeToNBTOptional(NBTTagCompound par1NBTTagCompound) {
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return Arrays.asList();
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

    }

    @Override
    public EnumHandSide getPrimaryHand() {
        return owner.getPrimaryHand();
    }

}

