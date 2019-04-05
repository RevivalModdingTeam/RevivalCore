package com.revivalcore.common.tileentity;

import java.util.Set;


import com.revivalcore.common.events.SHItemCraftedEvent;
import com.revivalcore.common.tileentity.TileEntityRC;
import com.revivalcore.recipes.RVIngredient;
import com.revivalcore.recipes.RVRecipe;
import com.revivalcore.util.helper.RVHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Toma1O6
 * @param <R> - the type of set containing all recipes related to this TE
 */
public interface ICraftSystem<R extends RVRecipe>
{
    default void slotChanged(TileEntityRC te)
    {
        if(!te.getStackInSlot(getOutput()).isEmpty())
            return;

        boolean running = false;
        RVRecipe recipe = null;

        for(RVRecipe rec : this.getRegistry())
        {
            running = true;

            for(RVIngredient i : rec.getIngredients())
            {
                if(!RVHelper.areItemstacksCraftable(i.ingredient, te.getStackInSlot(i.index)))
                {
                    running = false;
                    break;
                }
            }

            if(running)
            {
                recipe = rec;
                break;
            }
        }

        // all ingredients were valid (ingnoring slots which weren't specified by the recipe)
        if(running && recipe != null)
        {
            te.setInventorySlotContents(this.getOutput(), recipe.getResult());
            MinecraftForge.EVENT_BUS.post(new SHItemCraftedEvent(te.getWorld(), recipe.getResult()));
            this.consumeIngredients(recipe, te);
        }
    }

    default void consumeIngredients(RVRecipe recipe, TileEntityRC te)
    {
        for(int i = 0; i < recipe.getIngredients().length; i++)
        {
            ItemStack ingredient = recipe.getIngredients()[i].ingredient;
            int index = recipe.getIngredients()[i].index;
            ItemStack teStack = te.getStackInSlot(index);
            te.setInventorySlotContents(index, new ItemStack(teStack.getItem(), teStack.getCount() - ingredient.getCount()));
        }
    }

    /**
     * @return the slot index of first slot in craft matrix
     */
    public int getCraftingMatrixStart();

    /**
     * @return the slot index of last slot in craft matrix
     */
    public int getCraftingMatrixEnd();

    /**
     * @return the output slot index
     */
    public int getOutput();

    public Set<R> getRegistry();

    default boolean isSlotInCraftMatrix(int slotIndex)
    {
        return slotIndex >= this.getCraftingMatrixStart() && slotIndex <= this.getCraftingMatrixEnd();
    }
}