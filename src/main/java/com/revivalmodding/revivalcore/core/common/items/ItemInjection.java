package com.revivalmodding.revivalcore.core.common.items;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.util.helper.EnumHelper.InjectionTypes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemInjection extends Item {

    InjectionTypes injectionTypes;

    public ItemInjection(String name, InjectionTypes injectionType) {
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        this.injectionTypes = injectionType;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        if (!injectionTypes.getName().equals(InjectionTypes.EMPTY.getName())) {
            if (!MetaHelper.hasPowers(player)) {
                MetaHelper.setMetaPower(player, injectionTypes.getName());
                CapabilityMeta.get(player).setPowerEnabled(true);
                changeItemInjection(player, stack, InjectionTypes.EMPTY);
            }
        }else{
            if (MetaHelper.hasPowers(player)) {
                changeItemInjection(player, stack, MetaHelper.getMetaEnum(MetaHelper.getMetaPowerName(CapabilityMeta.get(player).getMetaPower())));
                MetaHelper.setEmptyPower(player);
                CapabilityMeta.get(player).setPowerEnabled(false);
            }
        }
        return super.onItemRightClick(worldIn, player, handIn);
    }

    private void changeItemInjection(EntityPlayer player, ItemStack stack, InjectionTypes types) {

        stack.shrink(1);
        for (Item item : CoreItems.ITEM_LIST) {
            if (item instanceof ItemInjection) {
                if (((ItemInjection) item).injectionTypes.getName().equals(types.getName())) {
                    player.addItemStackToInventory(new ItemStack(item));
                }
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 30;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
}
