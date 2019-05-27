package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DebugAbility0 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility0() {
		super("debug0");
	}
	
	@Override
	public int getAbilityPrice() {
		return 1;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
	
	@Override
	public void update(EntityPlayer player) {
		if(isActive()) {
			PlayerHelper.sendMessage(player, "Ability Debug 0 is Active", false);
		}
	}
}
