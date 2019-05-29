package com.revivalmodding.revivalcore.core.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketActivateAbility;
import com.revivalmodding.revivalcore.network.packets.PacketDeactivateAbility;
import com.revivalmodding.revivalcore.network.packets.PacketUnlockAbility;
import com.revivalmodding.revivalcore.util.helper.Constants;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public class AbilityGUI extends GuiScreen {
	
	private static final AbilityBase[] ABILITY_LIST = Registries.ABILITIES.toArray(new AbilityBase[0]);
	private int left;
	private int top;
	private int xSize;
	private int ySize;
	private int scrollAmount, maxScrollAmount;
	private List<AbilityBase> displayedAbilities = new ArrayList<>();
	private List<AbilityButton> buttonListA = new ArrayList<>();
	
	public AbilityGUI(EntityPlayer player) {
		this.initGui();
	}
	
	public void onAbilityButtonPressed(AbilityButton button) {
		EnumButtonState state = button.getButtonState();
		IAbilityCap abilities = IAbilityCap.Impl.get(mc.player);
		switch(state) {
			case PURCHASABLE_HOVERED: {
				NetworkManager.INSTANCE.sendToServer(new PacketUnlockAbility(button.getAbility()));
				break;
			}
			case AVAILABLE: {
				if(abilities.getAbilities().size() < 3 && button.getAbility().canActivateAbility(mc.player)) {
					NetworkManager.INSTANCE.sendToServer(new PacketActivateAbility(button.getAbility()));
				}
				break;
			}
			case READY_TO_REMOVE: {
				if(abilities.getAbilities().size() > 0) {
					NetworkManager.INSTANCE.sendToServer(new PacketDeactivateAbility(button.getAbility()));
				}
				break;
			}
			default:
				break;
		}
	}
	
	@Override
	public void initGui() {
		this.initGuiParameters();
		displayedAbilities.clear();
		buttonListA.clear();
		buttonList.clear();
		
		buttonList.add(new ButtonChangePage(7, left + 65, top + 145, 10, 15, false));
		buttonList.add(new ButtonChangePage(8, left + 115, top + 145, 10, 15, true));
		buttonList.add(new GuiButton(9, left+xSize-45, top + 143, 40, 20, "Close"));
		
		if(scrollAmount == 0) {
			((ButtonChangePage)buttonList.get(0)).update(false);
		}
		
		if(scrollAmount == maxScrollAmount || maxScrollAmount == 0) {
			((ButtonChangePage)buttonList.get(1)).update(false);
		}
		
		for(int i = scrollAmount; i < scrollAmount + 6; i++) {
			addAbilityToList(displayedAbilities, i);
		}
		for(int i = 0; i < displayedAbilities.size(); i++) {
			this.buttonListA.add(new AbilityButton(displayedAbilities.get(i), i, left, top));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left, top, xSize, ySize, 0, 0, 0.6862745098, 0.66715625, false);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawForeground();
		for(AbilityButton bt : buttonListA) {
			bt.drawButton(mc, mouseX, mouseY, partialTicks);
		}
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
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseButton == 0) {
			for(AbilityButton button : this.buttonListA) {
				if(button.mousePressed(mc, mouseX, mouseY)) {
					if(button.isActiveButton()) {
						button.playPressSound(mc.getSoundHandler());
						this.onAbilityButtonPressed(button);
						return;
					}
				}
			}
			for(GuiButton button : buttonList) {
				if(button.mousePressed(mc, mouseX, mouseY) && button.visible) {
					button.playPressSound(mc.getSoundHandler());
					this.actionPerformed(button);
					break;
				}
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof ButtonChangePage) {
			ButtonChangePage btn = (ButtonChangePage)button;
			if(btn.visible) {
				if(btn.isRightArrow() && scrollAmount < maxScrollAmount) {
					++this.scrollAmount;
				} else if(!btn.isRightArrow() && scrollAmount > 0) {
					--this.scrollAmount;
				}
			}
			this.initGui();
		}
		
		if(button.id == 9) {
			mc.displayGuiScreen(null);
		}
	}
	
	private void drawForeground() {
		fontRenderer.drawStringWithShadow(mc.player.getName(), left + 10, top + 8, 0xFFFFFF);
		fontRenderer.drawStringWithShadow("Page " + (scrollAmount+1), left + 78, top + 148, 0xFFFFFF);
		this.drawLevelStuff();
		this.drawScrollbar();
		if(displayedAbilities.isEmpty()) {
			fontRenderer.drawStringWithShadow("NO ABILITIES", left - 45 + xSize / 2, top + ySize / 2, 0xFFFFFF);
			return;
		}
	}
	
	private void drawLevelStuff() {
		IAbilityCap abilities = IAbilityCap.Impl.get(mc.player);
		float progress = (float)(abilities.getXP() / IAbilityCap.Impl.getRequiredXPForNewLevel(abilities));
		int guiX = left + xSize;
		fontRenderer.drawStringWithShadow(abilities.getLevel()+"", abilities.getLevel() < 10 ? left+80.5f : left+77, top + 8, 0xE22C00);
		fontRenderer.drawStringWithShadow(abilities.getLevel()+1+"", abilities.getLevel()+1 < 10 ? guiX-13.5f : guiX-17, top + 8, 0x36FF3B);
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left+92, top+9, 63*progress, 5, 0.68819607843, 0, 0.9294117647, 0.01960784313, false);
		fontRenderer.drawStringWithShadow(abilities.getAbilities().size()+"/3", left + 10, top + 150, 0xFFFFFF);
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
