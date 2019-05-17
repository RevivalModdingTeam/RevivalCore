package com.revivalmodding.revivalcore.core.common.tileentity;

import java.util.Set;

import com.revivalmodding.revivalcore.core.common.events.RVItemCraftedEvent;
import com.revivalmodding.revivalcore.core.recipes.RVIngredient;
import com.revivalmodding.revivalcore.core.recipes.RVRecipe;
import com.revivalmodding.revivalcore.util.helper.RVHelper;

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
        	if(te instanceof IProcessCraftSystem)
        	{
        		((IProcessCraftSystem)te).setProcessing(true);
        		((IProcessCraftSystem)te).setRecipe(recipe);
        		this.consumeIngredients(recipe, te);
        	}
        	else
        	{
        		te.addItemStackToInventory(this.getOutput(), recipe.constructResult());
                MinecraftForge.EVENT_BUS.post(new RVItemCraftedEvent(te.getWorld(), recipe.constructResult()));
                this.consumeIngredients(recipe, te);
        	}
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
     * @return the output slot index
     */
    public int getOutput();

    /**
     * @return the registry of recipes for this impl
     */
    public Set<R> getRegistry();
}