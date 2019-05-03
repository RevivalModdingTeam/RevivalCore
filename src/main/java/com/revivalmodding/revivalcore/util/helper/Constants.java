package com.revivalmodding.revivalcore.util.helper;

import java.util.Random;

import com.revivalmodding.revivalcore.RevivalCore;
import net.minecraft.util.ResourceLocation;

public final class Constants
{
	public static final Random RANDOM = new Random();
	
	public static final class Textures
	{
		public static final ResourceLocation SUITMAKER_ARROW = new ResourceLocation(RevivalCore.MODID + ":textures/gui/sm_arrow.png");
	}
}
