package com.revivalcore.superpowerbase.suitsets;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.util.helper.PlayerHelper;
import com.RevivalCore.util.helper.StringHelper;
import com.RevivalCore.util.items.ExtendedTooltip.IExtendedItemToolTip;
import com.RevivalCore.util.items.OpenableArmor.IOpenableArmor;

public class ItemSuitSetArmor extends ItemArmor implements IOpenableArmor, IExtendedItemToolTip {

    public SuitSet suitSet;

    public ItemSuitSetArmor(SuitSet suitSet, EntityEquipmentSlot armorSlot) {
        super(suitSet.getArmorMaterial(armorSlot), 0, armorSlot);

        if (suitSet.showInCreativeTab())
            this.setCreativeTab(suitSet.getCreativeTab());
        else
            this.setCreativeTab(null);
        this.suitSet = suitSet;
        this.setTranslationKey(suitSet.getUnlocalizedName() + "_" + getArmorSlotName(armorSlot).toLowerCase());
    }

    @Override
    public ArmorMaterial getArmorMaterial() {
        return suitSet.getArmorMaterial(armorType);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        if (suitSet.showInCreativeTab())
            return suitSet.getCreativeTab();
        else
            return null;
    }

    public SuitSet getSuitSet() {
        return suitSet;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag advanced) {
        if (getSuitSet().hasExtraDescription(stack))
            tooltip.addAll(getSuitSet().getExtraDescription(stack));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return getSuitSet().getDisplayNameForItem(this, stack, this.armorType, super.getItemStackDisplayName(stack));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return getSuitSet().getArmorTexturePath(stack, entity, slot, false, entity instanceof EntityPlayer ? PlayerHelper.hasSmallArms((EntityPlayer) entity) : false, isArmorOpen(entity, stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelBiped armorModel = null;

        if (!itemStack.isEmpty()) {
            boolean smallArms = false;

            if (entityLiving instanceof EntityPlayer)
                smallArms = PlayerHelper.hasSmallArms((EntityPlayer) entityLiving);

            armorModel = getSuitSet().getArmorModel(getSuitSet(), itemStack, entityLiving, armorSlot, false, smallArms, isArmorOpen(entityLiving, itemStack));

            if (armorModel != null) {
                armorModel.setModelAttributes(_default);
                return armorModel;
            }
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    public String getArmorSlotName(EntityEquipmentSlot slot) {
        switch (slot) {
            case HEAD:
                return "Helmet";
            case CHEST:
                return "Chestplate";
            case LEGS:
                return "Legs";
            case FEET:
                return "Boots";
            default:
                return "";
        }
    }

    // TODO First Person Armor
    // @Override
    // public HashMap<ModelBiped, Object[]> getFirstPersonModels(ItemStack stack,
    // EntityPlayer entity, EntityEquipmentSlot armorSlot, boolean smallArms) {
    // HashMap<ModelBiped, Object[]> map = new HashMap<ModelBiped, Object[]>();
    // map.put(getSuitSet().getArmorModel(stack, entity, armorSlot, false,
    // smallArms, false), new Object[] {getSuitSet().getArmorTexturePath(stack,
    // entity, armorSlot, false, smallArms, false), false});
    // if(getSuitSet().hasGlowyThings(entity, armorType))
    // map.put(getSuitSet().getArmorModel(stack, entity, armorSlot, false,
    // smallArms, false), new Object[] {getSuitSet().getArmorTexturePath(stack,
    // entity, armorSlot, true, smallArms, false), true});
    // return map;
    // }


    @Override
    public boolean canBeOpened(Entity entity, ItemStack stack) {
        return this.suitSet.canOpenArmor(this.armorType);
    }

    @Override
    public void setArmorOpen(Entity player, ItemStack stack, boolean open) {
        if (getSuitSet().canOpenArmor(this.armorType)) {
            NBTTagCompound nbt = new NBTTagCompound();
            if (stack.hasTagCompound())
                nbt = stack.getTagCompound();
            nbt.setBoolean("IsOpen", open);
            onArmorToggled(player, stack, nbt.getBoolean("IsOpen"));
            stack.setTagCompound(nbt);
        }
    }

    @Override
    public boolean isArmorOpen(Entity entity, ItemStack stack) {
        if (getSuitSet().canOpenArmor(this.armorType) && stack.hasTagCompound())
            return stack.getTagCompound().getBoolean("IsOpen");

        return false;
    }

    @Override
    public void onArmorToggled(Entity entity, ItemStack stack, boolean open) {
        getSuitSet().onArmorToggled(entity, stack, this.armorType, open);
    }

    @Override
    public boolean shouldShiftTooltipAppear(ItemStack stack, EntityPlayer player) {
        return false;
    }

    @Override
    public List<String> getShiftToolTip(ItemStack stack, EntityPlayer player) {
        return null;
    }

    @Override
    public boolean shouldCtrlTooltipAppear(ItemStack stack, EntityPlayer player) {
        return true;
    }

    @Override
    public List<String> getCtrlToolTip(ItemStack stack, EntityPlayer player) {
        List<String> list = new ArrayList<>();
        Collection<PowerBase> abilities = getSuitSet().addDefaultAbilities(player, new PowerBase.AbilityMap(), PowerBase.EnumAbilityContext.SUIT).values();

        if (abilities.size() > 0) {
            list.add(TextFormatting.RED + StringHelper.translateToLocal("lucraftcore.info.abilities") + TextFormatting.DARK_RED + ":");
            for (PowerBase ability : abilities) {
                list.add(TextFormatting.RED + "- " + TextFormatting.GRAY + ability.getDisplayName());
            }
        }
        return list;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.suitSet.getIsRepairable(toRepair, repair);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        this.getSuitSet().onArmorTick(world, player, itemStack);
    }

}
