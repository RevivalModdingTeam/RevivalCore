package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.registry.Registries;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketAbilityAction;
import com.revivalmodding.revivalcore.util.helper.Constants;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbilityGUI extends GuiScreen {
	
	private static final Ability[] ABILITY_LIST = Registries.ABILITIES.toArray(new Ability[0]);
	private int left;
	private int top;
	private int xSize;
	private int ySize;
	private int scrollAmount, maxScrollAmount;
	private List<Ability> displayedAbilities = new ArrayList<>();
	private List<AbilityButton> buttonListA = new ArrayList<>();
	
	public AbilityGUI(EntityPlayer player) {
		this.initGui();
	}
	
	public void onAbilityButtonPressed(AbilityButton button) {
		EnumButtonState state = button.getButtonState();
		PlayerAbilityData abilityData = CoreCapabilityImpl.getInstance(mc.player).getAbilityData();
		switch(state) {
			case PURCHASABLE_HOVERED: {
				NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(button.getAbility(), PacketAbilityAction.AbilityAction.UNLOCK));
				break;
			}
			case AVAILABLE: {
				if(abilityData.getActiveAbilityCount() < 3 && button.getAbility().canActivate(mc.player)) {
					NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(button.getAbility(), PacketAbilityAction.AbilityAction.UNLOCK));
				}
				break;
			}
			case READY_TO_REMOVE: {
				if(abilityData.getActiveAbilityCount() > 0) {
					NetworkManager.INSTANCE.sendToServer(new PacketAbilityAction(button.getAbility(), PacketAbilityAction.AbilityAction.UNLOCK));
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
		
		for(int i = scrollAmount*6; i < scrollAmount*6 + 6; i++) {
			addAbilityToList(displayedAbilities, i);
		}
		for(int i = 0; i < displayedAbilities.size(); i++) {
			this.buttonListA.add(new AbilityButton(displayedAbilities.get(i), i, left, top, this));
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
		if(i < 0 && scrollAmount < maxScrollAmount) {
			scrollAmount -= i;
			this.initGui();
		}
		if(i > 0 && scrollAmount > 0) {
			scrollAmount -= i;
			this.initGui();
		}
		super.handleMouseInput();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
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
	protected void actionPerformed(GuiButton button) {
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
		if(displayedAbilities.isEmpty()) {
			fontRenderer.drawStringWithShadow("NO ABILITIES", left - 45 + xSize / 2, top + ySize / 2, 0xFFFFFF);
		}
	}
	
	private void drawLevelStuff() {
		PlayerAbilityData abilityData = CoreCapabilityImpl.getInstance(mc.player).getAbilityData();
		float progress = abilityData.getXP() / abilityData.getRequiredXPForLevel();
		int guiX = left + xSize;
		fontRenderer.drawStringWithShadow(abilityData.getLevel()+"", abilityData.getLevel() < 10 ? left+80.5f : left+77, top + 8, 0xE22C00);
		fontRenderer.drawStringWithShadow(abilityData.getLevel()+1+"", abilityData.getLevel()+1 < 10 ? guiX-13.5f : guiX-17, top + 8, 0x36FF3B);
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left+92, top+9, 63*progress, 5, 0.68819607843, 0, 0.9294117647, 0.01960784313, false);
		fontRenderer.drawStringWithShadow(abilityData.getActiveAbilityCount()+"/3", left + 10, top + 150, 0xFFFFFF);
	}
	
	private void initGuiParameters() {
		xSize = 175;
		ySize = 169;
		left = (this.width - 175) / 2;
		top = (this.height - 169) / 2;
		int abilitiesLeft = ABILITY_LIST.length;
		int pageCount = 0;
		if(abilitiesLeft > 6) {
			while(abilitiesLeft > 0) {
				++pageCount;
				abilitiesLeft -= 6;
			}
			--pageCount;
		}
		this.maxScrollAmount = pageCount;
	}
	
	private static void addAbilityToList(List<Ability> list, int i) {
		if(Registries.ABILITIES.size() > i) {
			list.add(ABILITY_LIST[i]);
		}
	}
}
