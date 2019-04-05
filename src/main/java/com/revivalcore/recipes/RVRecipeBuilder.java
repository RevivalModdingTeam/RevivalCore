package com.revivalcore.recipes;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RVRecipeBuilder {
    private ItemStack result;
    private RVIngredient[] ingredients = new RVIngredient[0];
    private String name = "";
    private List<RVIngredient> recList = new ArrayList<RVIngredient>();

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

    public RVRecipe build() throws IllegalArgumentException {
        result = Preconditions.checkNotNull(result);
        if (recList.isEmpty()) throw new IllegalArgumentException("Ingredient list cannot be empty!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty!");
        ingredients = recList.toArray(new RVIngredient[0]);

        return new RVRecipe(name, result, ingredients);
    }
}
