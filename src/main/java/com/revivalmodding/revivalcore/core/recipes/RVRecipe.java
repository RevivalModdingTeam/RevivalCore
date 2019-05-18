package com.revivalmodding.revivalcore.core.recipes;

import java.util.ArrayList;

import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class RVRecipe implements IRegistryEntry
{
	private final RVIngredient[] ingredients;
	private final ItemStack result;
	private final String name;
	private final int craftTime;

	protected RVRecipe(String name, ItemStack result, int craftTime, RVIngredient... ingredients) throws IllegalArgumentException
	{
		this.result = result;
		this.ingredients = ingredients;
		if(name.isEmpty())
			throw new IllegalArgumentException("Recipe name cannot be empty!");
		this.name = name;
		this.craftTime = craftTime < 0 ? Math.abs(craftTime) : craftTime;
	}

	public RVIngredient[] getIngredients()
	{
		return ingredients;
	}

	public ItemStack getResult()
	{
		return this.result;
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

	public int getCraftTime()
	{
		return craftTime;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public IRegistry getRegistry()
	{
		return Registries.SuitMakerRecipeRegistry.instance();
	}

	public static RVRecipe getRecipeFromName(String name)
	{
		for(RVRecipe recipe : Registries.SUIT_RECIPES) {
			if(name.equalsIgnoreCase(recipe.getName())) {
				return recipe;
			}
		}

		return null;
	}

	/** DO NOT USE, BROKEN **/
	@Deprecated
	public static NBTTagCompound writeRecipeToNBT(NBTTagCompound compound, RVRecipe recipe)
	{
		if(recipe != null)
		{
			NBTTagCompound c = new NBTTagCompound();
			NBTTagList list = new NBTTagList();
			c.setString("recipeName", recipe.name);
			c.setInteger("resultID", Item.getIdFromItem(recipe.result.getItem()));
			c.setInteger("resultCount", recipe.constructResult().getCount());
			c.setInteger("craftTime", recipe.craftTime);
			for(RVIngredient i : recipe.getIngredients())
			{
				list.appendTag(i.writeIngredientNBT(c, i));
			}
			c.setTag("ingredients", list);
			compound.setTag("recipe", c);
		}
		return compound;
	}

	/** DO NOT USE, BROKEN **/
	@Deprecated
	public static RVRecipe readRecipeFromNBT(NBTTagCompound compound)
	{
		if(compound.hasKey("recipe"))
		{
			NBTTagCompound rec = compound.getCompoundTag("recipe");
			NBTTagList list = rec.getTagList("ingredients", Constants.NBT.TAG_COMPOUND);
			ArrayList<RVIngredient> ingredients = new ArrayList<>();

			String name = rec.getString("recipeName");
			ItemStack result = new ItemStack(Item.getItemById(rec.getInteger("resultID")), rec.getInteger("resultCount"));
			for(int i = 0; i < list.tagCount(); i++) {
				ingredients.add(RVIngredient.readIngredientFromNBT(list.getCompoundTagAt(i)));
			}

			RVIngredient[] arr = ingredients.toArray(new RVIngredient[0]);
			int time = rec.getInteger("craftTime");
			return new RVRecipe(name, result, time, arr);
		}
		return null;
	}
}
