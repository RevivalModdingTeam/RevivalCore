package com.revivalmodding.revivalcore.core.abilities;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DebugAbility4 extends AbilityBase {
	
	private static final ResourceLocation RL = new ResourceLocation(RevivalCore.MODID, "icon.png");
	
	public DebugAbility4() {
		super("debug4");
	}
	
	@Override
	public int getAbilityPrice() {
		return 12;
	}
	
	@Override
	public String getFullName() {
		return "Debug Ability 4";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return RL;
	}
	
	@Override
	public void update(EntityPlayer player) {
		if(isActive()) {
			PlayerHelper.sendMessage(player, "Ability Debug 4 is Active", false);
		}
	}
}
