package com.revivalmodding.revivalcore.core.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.util.helper.Constants;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AbilityGUI extends GuiScreen {
	
	private static final AbilityBase[] ABILITY_LIST = Registries.ABILITIES.toArray(new AbilityBase[0]);
	private final IAbilityCap abilities;
	private int left;
	private int top;
	private int xSize;
	private int ySize;
	private int scrollAmount, maxScrollAmount;
	private List<AbilityBase> displayedAbilities = new ArrayList<>();
	
	public AbilityGUI(EntityPlayer player) {
		abilities = IAbilityCap.Impl.get(player);
		
		if(abilities == null) {
			mc.player.closeScreen();
		}
		
		this.initGui();
	}
	
	@Override
	public void initGui() {
		this.initGuiParameters();
		displayedAbilities.clear();
		buttonList.clear();
		for(int i = scrollAmount; i < scrollAmount + 6; i++) {
			addAbilityToList(displayedAbilities, i);
		}
		for(int i = 0; i < displayedAbilities.size(); i++) {
			this.buttonList.add(new AbilityButton(displayedAbilities.get(i), i, abilities, left, top));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left, top, xSize, ySize, 0, 0, 0.6862745098, 0.66715625, false);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawForeground();
		abilities.setLevel(3);
		abilities.setXP(31);
	}
	
	@Override
	public void handleMouseInput() throws IOException {
		int i = Integer.signum(Mouse.getEventDWheel());
		if(scrollAmount > 0 && i < 0) {
			scrollAmount -= i;
			this.initGui();
		}
		if(scrollAmount < maxScrollAmount && i > 0) {
			scrollAmount += i;
			this.initGui();
		}
		super.handleMouseInput();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	private void drawForeground() {
		mc.fontRenderer.drawStringWithShadow(mc.player.getName(), left + 10, top + 8, 0xFFFFFF);
		this.drawLevelStuff();
		this.drawScrollbar();
		if(displayedAbilities.isEmpty()) {
			mc.fontRenderer.drawStringWithShadow("NO ABILITIES, SMH", left - 45 + xSize / 2, top + ySize / 2, 0xFFFFFF);
			return;
		}
	}
	
	private void drawLevelStuff() {
		float progress = (float)(abilities.getXP() / IAbilityCap.Impl.getRequiredXPForNewLevel(abilities));
		int guiX = left + xSize;
		fontRenderer.drawStringWithShadow(abilities.getLevel()+"", abilities.getLevel() < 10 ? left+80.5f : left+77, top + 8, 0xE22C00);
		fontRenderer.drawStringWithShadow(abilities.getLevel()+1+"", abilities.getLevel()+1 < 10 ? guiX-13.5f : guiX-17, top + 8, 0x36FF3B);
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left+92, top+9, 63*progress, 5, 0.68819607843, 0, 0.9294117647, 0.01960784313, false);
	}
	
	private void drawScrollbar() {
		int state = displayedAbilities.size() <= 6 ? 0 : scrollAmount == maxScrollAmount ? 1 : 2;
		switch(state) {
			case 0: {
				ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left+160, top+20, 9, 120, 0.68819607843, 0.02260784313, 0.72849019607, 0.14509803921, false);
				break;
			}
			case 1: {
				int parts = ABILITY_LIST.length - 6;
				int length = 120 / (int)(parts*1.25);
				break;
			}
			case 2: {
				break;
			}
		}
	}
	
	private void initGuiParameters() {
		xSize = 175;
		ySize = 169;
		left = (this.width - 175) / 2;
		top = (this.height - 169) / 2;
		maxScrollAmount = Registries.ABILITIES.size() > 6 ? Registries.ABILITIES.size() - 6 : 0;
	}
	
	private static void addAbilityToList(List<AbilityBase> list, int i) {
		if(Registries.ABILITIES.size() > i) {
			list.add(ABILITY_LIST[i]);
		}
	}
}
