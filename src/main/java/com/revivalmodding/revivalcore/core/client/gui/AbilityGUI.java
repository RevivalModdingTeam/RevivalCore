package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class AbilityGUI extends GuiScreen {
	
	final IAbilityCap abilities;
	final EntityPlayer player;
	
	public AbilityGUI(EntityPlayer player) {
		this.player = player;
		abilities = IAbilityCap.Impl.get(player);
		
		if(abilities == null) {
			player.closeScreen();
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		this.drawForeground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	private void drawForeground() {
		
	}
	
	private void drawLevelStuff() {
		
	}
	
	private void drawAbilities() {
		
	}
	
	private void drawScrollbar() {
		
	}
	
	public class AbilityButton extends GuiButton {
		
		public AbilityButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
			super(buttonId, x, y, widthIn, heightIn, buttonText);
		}
	}
}
