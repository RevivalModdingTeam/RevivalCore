package com.revivalmodding.revivalcore.core.client.gui;

import java.util.List;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AbilityButton extends GuiButton {
	
	private final AbilityBase ability;
	private EnumButtonState state = EnumButtonState.INACTIVE;
	private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/abilitybutton.png");
	
	public AbilityButton(AbilityBase ability, int id, int left, int top) {
		super(id, left + 9, top + 1 + 20*(id+1), 150, 20, ability.getName());
		this.ability = ability;
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
