package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class AbilityButton extends GuiButton {
	
	private final AbilityBase ability;
	private final AbilityGUI parent;
	private EnumButtonState state = EnumButtonState.INACTIVE;
	private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/abilitybutton.png");
	private static final ResourceLocation TEXT_BACKGROUND = new ResourceLocation(RevivalCore.MODID + ":textures/gui/background.png");
	private List<String> abilityDescription = new ArrayList<>();
	private int descWidth, descHeight;
	
	public AbilityButton(AbilityBase ability, int id, int left, int top, AbilityGUI parentGui) {
		super(id, left + 9, top + 1 + 20*(id+1), 150, 20, ability.getName());
		this.parent = parentGui;
		this.ability = ability;
		this.prepareDescriptionRender();
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		IAbilityCap cap = IAbilityCap.Impl.get(mc.player);
		this.updateState(mouseX, mouseY);
		ImageHelper.drawImageWithUV(mc, BUTTON_TEXTURE, x, y, width, height, 0, 0.14285714285*state.ordinal(), 1, 0.14285714285*state.ordinal()+0.14285714285, true);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		ImageHelper.drawCustomSizedImage(mc, ability.getIcon(), this.x + 3, this.y + 2, 16, 16, true);
		mc.fontRenderer.drawStringWithShadow(ability.getFullName(), this.x + 20, this.y + 6, 0xFFFFFF);
		mc.fontRenderer.drawStringWithShadow(ability.getAbilityPrice()+"", ability.getAbilityPrice() >= 10 ? this.x + 135 : this.x + 140, this.y + 6, 0xFFFFFF);

		if(hovered) {
			this.drawAbilityRequirements(mc, mouseX, mouseY);
		}
	}

	private void prepareDescriptionRender() {
		abilityDescription.clear();
		if(ability.getHoveredDescription() == null) {
			return;
		}
		Minecraft mc = Minecraft.getMinecraft();
		int maxWidth = 0;
		for(int i = 0; i < ability.getHoveredDescription().length; i++) {
			String text = ability.getHoveredDescription()[i];
			int width = text.length();
			int j = 0;
			// split large strings into multiple smaller ones
			while(width > 0) {
				width -= 40;
				if(width < 0) {
					abilityDescription.add(text.substring(j, j + 40 + width));
					j+=40;
					continue;
				}
				abilityDescription.add(text.substring(j, j + 40));
				j+=40;
			}
		}
		// bg width
		for(String s : abilityDescription) {
			int width = mc.fontRenderer.getStringWidth(s);
			if(width > maxWidth) {
				maxWidth = width;
			}
		}
		descWidth = maxWidth + 20;
		descHeight = abilityDescription.size() * 13 + 40;
	}

	private void drawAbilityRequirements(Minecraft mc, int mx, int my) {
		if(ability.getHoveredDescription() == null) {
			return;
		}
		int bgX = mx + descWidth > parent.width ? mx - descWidth : mx + 5;
		int bgY = my + descHeight + 10 > parent.height ? my - 5 - descHeight : my;
		String[] text = ability.getHoveredDescription();
		ImageHelper.drawCustomSizedImage(mc, TEXT_BACKGROUND, bgX, bgY, descWidth, descHeight, false);
		for(int i = 0; i < abilityDescription.size(); i++) {
			String line = abilityDescription.get(i);
			mc.fontRenderer.drawString(line, bgX + 10, bgY + 10 + i * 13, 0xFFFFFF);
		}
		boolean flag = ability.canActivateAbility(mc.player);
		int color = flag ? 0x00FF00 : 0xFF0000;
		mc.fontRenderer.drawString(flag ? "Ability can be activated" : "Ability cannot be activated", bgX + 10, bgY + 10 + (abilityDescription.size() + 1)*13, color);
	}
	
	private void updateState(int mouseX, int mouseY) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		IAbilityCap cap = IAbilityCap.Impl.get(player);
		hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		if(!AbilityBase.hasUnlockedAbility(player, ability.getName())) {
			if(cap.getLevel() >= ability.getAbilityPrice()) {
				state = EnumButtonState.PURCHASABLE;
				if(hovered) {
					state = EnumButtonState.PURCHASABLE_HOVERED;
				}
			} else {
				state = EnumButtonState.INACTIVE;
			}
		} else {
			// already unlocked abilities
			boolean b = containsAbility(ability.getName(), cap.getAbilities());
			// active and not hovered
			if(b && !hovered) {
				state = EnumButtonState.ACTIVE;
			} else if(b && hovered) { // active and hovered
				state = EnumButtonState.READY_TO_REMOVE;
			} else if(!b && hovered && cap.getAbilities().size() < 3) { // inactive and hovered
				state = EnumButtonState.AVAILABLE;
			} else {
				state = EnumButtonState.READY_TO_ACTIVATE;
			}
		}
	}
	
	private static boolean containsAbility(String name, List<AbilityBase> list) {
		for(AbilityBase base : list) {
			if(base.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public AbilityBase getAbility() {
		return ability;
	}
	
	public EnumButtonState getButtonState() {
		return state;
	}
	
	public boolean isActiveButton() {
		return state != EnumButtonState.INACTIVE;
	}
}
