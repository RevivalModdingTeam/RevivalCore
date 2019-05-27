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
	private final IAbilityCap cap;
	private EnumButtonState state = EnumButtonState.INACTIVE;
	private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/abilitybutton.png");
	
	public AbilityButton(AbilityBase ability, int id, IAbilityCap cap, int left, int top) {
		super(id, left + 9, top + 21*(id+1), 150, 20, ability.getName());
		this.ability = ability;
		this.cap = cap;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		
		this.updateState(mouseX, mouseY);
		ImageHelper.drawImageWithUV(mc, BUTTON_TEXTURE, x, y, width, height, 0, 0.16666666666*state.ordinal(), 1, 0.16666666666*state.ordinal()+0.16666666666, true);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		ImageHelper.drawCustomSizedImage(mc, ability.getIcon(), this.x + 3, this.y + 2, 16, 16, true);
		mc.fontRenderer.drawStringWithShadow(ability.getFullName(), this.x + 20, this.y + 6, 0xFFFFFF);
	}
	
	private void updateState(int mouseX, int mouseY) {
		EntityPlayer player = Minecraft.getMinecraft().player;
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
			boolean b = containsAbility(ability.getName(), cap.getAbilities());
			if(b && !hovered) {
				state = EnumButtonState.ACTIVE;
			} else if(b && hovered) {
				state = EnumButtonState.READY_TO_REMOVE;
			} else if(!b && hovered) {
				state = EnumButtonState.AVAILABLE;
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
}
