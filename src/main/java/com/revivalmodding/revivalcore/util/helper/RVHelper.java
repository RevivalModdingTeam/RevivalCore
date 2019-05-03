package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	
	public static void spawnParticles(World world, EnumParticleTypes particle, double x, double y, double z, double speed, int particleCount)
	{
		spawnParticles(world, particle, new BlockPos(x, y, z), speed, particleCount);
	}
	
	public static void spawnParticles(World world, EnumParticleTypes particle, BlockPos pos, double speed, int particleCount)
	{
		for(int i = 0; i < Math.abs(particleCount); i++)
		{
			spawnParticle(world, particle, pos, speed);
		}
	}
	
	private static void spawnParticle(World world, EnumParticleTypes particle, BlockPos pos, double speed)
	{
		world.spawnParticle(particle, pos.getX(), pos.getY(), pos.getZ(), getParticleOffsetSpeed(speed), Math.abs(getParticleOffsetSpeed(speed)), getParticleOffsetSpeed(speed));
	}
	
	private static double getParticleOffsetSpeed(double baseSpeed)
	{
		return (Constants.RANDOM.nextDouble() - Constants.RANDOM.nextDouble()) * baseSpeed;
	}
}
