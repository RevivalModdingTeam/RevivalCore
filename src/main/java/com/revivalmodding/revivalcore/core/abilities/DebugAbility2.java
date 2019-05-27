package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DebugAbility2 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility2() {
		super("debug2");
	}
	
	@Override
	public int getAbilityPrice() {
		return 3;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability 2";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
	
	@Override
	public void update(EntityPlayer player) {
		if(isActive()) {
			PlayerHelper.sendMessage(player, "Ability Debug 2 is Active", false);
		}
	}
}
