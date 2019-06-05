package com.revivalmodding.revivalcore.core.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemEatable extends Item {
    public final int itemUseDuration;
    private final int healAmount;
    private final float saturationModifier;
    private boolean alwaysEdible;
    private PotionEffect potionId;
    private float potionEffectProbability;
    private boolean isDrink;

    public ItemEatable(String name, int amount, float saturation, boolean isDrink) {
        this.itemUseDuration = 32;
        this.healAmount = amount;
        this.saturationModifier = saturation;
        this.isDrink = isDrink;
        setTranslationKey(name);
        setRegistryName(name);

        if(isDrink)
        setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.FOOD);
        addPropertyOverride(new ResourceLocation("empty"), (stack, worldIn, entityIn) -> {
            if (getStackTag(stack) == null) {
                return 0F;
            }

            if (getEmpty(stack) == 1) {
                return 1F;
            }

            return 0F;
        });
    }

    public static NBTTagCompound getStackTag(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("empty", 0);
        }
        return stack.getTagCompound();
    }

    public static int getEmpty(ItemStack stack) {
        return getStackTag(stack).getInteger("empty");
    }

    public static void setEmpty(ItemStack stack, int em) {
        getStackTag(stack).setInteger("empty", em);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            if (getEmpty(stack) == 0) {
                this.onFoodEaten(stack, worldIn, entityplayer);
                entityplayer.getFoodStats().setFoodLevel(entityplayer.getFoodStats().getFoodLevel() + healAmount);

                if (this.isDrink) {
                    setEmpty(stack, 1);
                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                }else{
                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                }

                entityplayer.addStat(StatList.getObjectUseStats(this));
                entityplayer.getFoodStats().addStats(healAmount, saturationModifier);

                if (entityplayer instanceof EntityPlayerMP) {
                    CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
                }
            }else{
                return stack;
            }
        }
        if (!this.isDrink)
            stack.shrink(1);

        return stack;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability) {
            player.addPotionEffect(new PotionEffect(this.potionId));
        }
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.canEat(this.alwaysEdible)) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        } else {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    public int getHealAmount(ItemStack stack) {
        return this.healAmount;
    }

    public float getSaturationModifier(ItemStack stack) {
        return this.saturationModifier;
    }


    public ItemEatable setPotionEffect(PotionEffect effect, float probability) {
        this.potionId = effect;
        this.potionEffectProbability = probability;
        return this;
    }


    public ItemEatable setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }
}
