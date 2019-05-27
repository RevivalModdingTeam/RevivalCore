package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DebugAbility3 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility3() {
		super("debug3");
	}
	
	@Override
	public int getAbilityPrice() {
		return 4;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability 3";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
	
	@Override
	public void update(EntityPlayer player) {
		if(isActive()) {
			PlayerHelper.sendMessage(player, "Ability Debug 3 is Active", false);
		}
	}
}
