package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DebugAbility1 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility1() {
		super("debug1");
	}
	
	@Override
	public int getAbilityPrice() {
		return 5;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability 1";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
	
	@Override
	public void update(EntityPlayer player) {
		if(isActive()) {
			PlayerHelper.sendMessage(player, "Ability Debug 1 is Active", false);
		}
	}
}
