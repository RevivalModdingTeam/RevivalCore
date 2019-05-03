package com.revivalmodding.revivalcore.core.recipes;

import com.revivalmodding.revivalcore.RevivalCore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class RVRecipe
{
	private final RVIngredient[] ingredients;
	private final ItemStack result;
	private final String name;
	
	protected RVRecipe(String name, ItemStack result, RVIngredient... ingredients) throws IllegalArgumentException
	{
		this.result = result;
		this.ingredients = ingredients;
		if(name.isEmpty())
			throw new IllegalArgumentException("Recipe name cannot be empty!");
		this.name = name;
	}
	
	public RVIngredient[] getIngredients()
	{
		return ingredients;
	}
	
	public ItemStack getResult()
	{
		return this.result;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean containsIngredient(ItemStack stack)
	{
		for(int i = 0; i < ingredients.length; i++)
		{
			ItemStack ing = ingredients[i].ingredient;
			
			if(ItemStack.areItemStacksEqual(stack, ing))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Recipe: [Name=" + name + ", Result=" + result.getItem().getRegistryName() + ", Ingredients=" + this.printIngredients() + "]";
	}
	
	private String printIngredients()
	{
		String s = "";
		for(RVIngredient i : ingredients)
		{
			s = s + i.toString() + ",";
		}
		
		return s;
	}
	
	public ItemStack constructResult()
	{
		return new ItemStack(result.getItem(), result.getCount());
	}
	
	public static NBTTagCompound writeRecipeToNBT(NBTTagCompound compound, RVRecipe recipe)
	{
		if(recipe != null)
		{
			NBTTagList list = new NBTTagList();
			compound.setString("recipeName", recipe.name);
			compound.setInteger("resultID", Item.getIdFromItem(recipe.result.getItem()));
			compound.setInteger("resultCount", recipe.constructResult().getCount());
			for(RVIngredient i : recipe.getIngredients())
			{
				list.appendTag(i.writeIngredientNBT(compound, i));
			}
			compound.setTag("ingredients", compound);
		}
		
		return compound;
	}
	
	public static RVRecipe readRecipeFromNBT(NBTTagCompound compound)
	{
		if(compound.hasKey("resultID") && compound.hasKey("resultCount") && compound.hasKey("ingredients") && compound.hasKey("recipeName"))
		{
			List<RVIngredient> ingredients = new ArrayList<RVIngredient>();
			String name = compound.getString("recipeName");
			ItemStack result = new ItemStack(Item.getItemById(compound.getInteger("resultID")), compound.getInteger("resultCount"));
			NBTTagList list = compound.getTagList("ingredients", Constants.NBT.TAG_COMPOUND);
			for(int i = 0; i < list.tagCount(); i++)
			{
				NBTTagCompound ingC = list.getCompoundTagAt(i);
				ingredients.add(RVIngredient.readIngredientFromNBT(ingC));
			}
			RVIngredient[] ingArr = ingredients.toArray(new RVIngredient[0]);
			return new RVRecipe(name, result, ingArr);
		}
		else {
			RevivalCore.logger.error("Attempted to load recipe from NBT, but the required NBT tag doesn't exist!");
			return new RVRecipe("null", ItemStack.EMPTY, new RVIngredient[0]);
		}
	}
}
