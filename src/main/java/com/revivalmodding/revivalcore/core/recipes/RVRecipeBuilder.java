package com.revivalmodding.revivalcore.core.recipes;

import java.util.ArrayList;
import java.util.List;

import com.revivalmodding.revivalcore.util.helper.IBuilder;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RVRecipeBuilder implements IBuilder<RVRecipe> {
    private ItemStack result;
    private RVIngredient[] ingredients = new RVIngredient[0];
    private String name = "";
    private List<RVIngredient> recList = new ArrayList<RVIngredient>();
    private int craftTime;

    private RVRecipeBuilder() {
    }

    public static RVRecipeBuilder create() {
        return new RVRecipeBuilder();
    }
    
    public RVRecipeBuilder name(String name) {
    	this.name = name;
    	return this;
    }

    public RVRecipeBuilder result(Item item, int count) {
        result = new ItemStack(item, count);
        return this;
    }

    public RVRecipeBuilder result(Block block, int count) {
        result = new ItemStack(block, count);
        return this;
    }

    public RVRecipeBuilder addIngredient(Item item, int count, int slotIndex) {
        ItemStack ing = new ItemStack(item, count);
        recList.add(new RVIngredient(slotIndex, ing));
        return this;
    }

    public RVRecipeBuilder addIngredient(Block block, int count, int slotIndex) {
        ItemStack ing = new ItemStack(block, count);
        recList.add(new RVIngredient(slotIndex, ing));
        return this;
    }
    
    public RVRecipeBuilder craftingTime(int time) {
    	craftTime = time;
    	return this;
    }

    @Override
    public RVRecipe build() throws IllegalArgumentException {
        checkNotNull(result);
        checkBoolean(recList.isEmpty(), false);
        checkBoolean(name.isEmpty(), false);
        checkBoolean(result.isEmpty(), false);
        checkInt(craftTime, 20, 500);
        ingredients = recList.toArray(new RVIngredient[0]);

        return new RVRecipe(name, result, craftTime, ingredients);
    }
}
