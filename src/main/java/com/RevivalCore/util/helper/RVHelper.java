package com.RevivalCore.util.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class RVHelper
{
	public static NBTTagCompound getOrCreateStackNBT(ItemStack stack)
	{
		return stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
	}
	
	public static boolean areItemstacksCraftable(ItemStack recipe, ItemStack testStack)
	{
		return testStack.getItem() == recipe.getItem() && testStack.getCount() >= recipe.getCount();
	}
}
