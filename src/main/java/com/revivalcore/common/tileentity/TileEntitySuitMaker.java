package com.revivalcore.common.tileentity;

import com.revivalcore.core.registry.SuitMakerRecipeRegistry;
import com.revivalcore.recipes.RVRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Set;


public class TileEntitySuitMaker extends TileEntityRC implements ICraftSystem<RVRecipe>
{
    public static final TextComponentTranslation NAME = new TextComponentTranslation("container.suitMaker");
    protected NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(17, ItemStack.EMPTY);

    @Override
    public ITextComponent getDisplayName()
    {
        return NAME;
    }

    @Override
    public String getName()
    {
        return NAME.getKey();
    }

    @Override
    public NonNullList<ItemStack> getInventory()
    {
        return inventory;
    }

    @Override
    public int getOutput()
    {
        return 0;
    }

    @Override
    public int getCraftingMatrixEnd()
    {
        return 1;
    }

    @Override
    public int getCraftingMatrixStart()
    {
        return 16;
    }

    @Override
    public Set<RVRecipe> getRegistry()
    {
        return SuitMakerRecipeRegistry.RECIPES;
    }
    
    @Override
    public void closeInventory(EntityPlayer player)
    {
    	InventoryHelper.dropInventoryItems(world, pos, this);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }


}