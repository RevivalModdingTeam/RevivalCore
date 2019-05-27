package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;

import net.minecraft.util.ResourceLocation;

public class DebugAbility0 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility0() {
		super("debug");
	}
	
	@Override
	public int getAbilityPrice() {
		return 2;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
}
