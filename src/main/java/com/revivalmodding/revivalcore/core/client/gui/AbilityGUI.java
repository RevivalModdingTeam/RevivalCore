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
	
	public AbilityGUI() {
		abilities = IAbilityCap.Impl.get(mc.player);
		
		if(abilities == null) {
			mc.player.closeScreen();
		}
		
		this.initGui();
	}
	
	@Override
	public void initGui() {
		this.initGuiParameters();
		displayedAbilities.clear();
		for(int i = scrollAmount; i < scrollAmount + 6; i++) {
			addAbilityToList(displayedAbilities, i);
		}
		for(int i = 0; i < displayedAbilities.size(); i++) {
			this.buttonList.add(new AbilityButton(i, displayedAbilities.get(i)));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ImageHelper.drawImageWithUV(mc, Constants.Textures.ABILITY_GUI, left, top, xSize, ySize, 0, 0, 0.6862745098, 0.66715625, false);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawForeground();
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
		if(displayedAbilities.isEmpty()) {
			mc.fontRenderer.drawStringWithShadow("NO ABILITIES, WTH", left - 45 + xSize / 2, top + ySize / 2, 0xFFFFFF);
			return;
		}
	}
	
	private void drawLevelStuff() {
	}
	
	private void drawAbilities() {
		
	}
	
	private void drawScrollbar() {
		
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
	
	public class AbilityButton extends GuiButton {
		
		private final ResourceLocation icon;
		
		public AbilityButton(int buttonId, AbilityBase ability) {
			super(buttonId, 7, 21 * buttonId, 150, 20, ability.getName());
			this.icon = ability.getIcon();
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
	        if (this.visible)
	        {
	            FontRenderer fontrenderer = mc.fontRenderer;
	            int i = this.getHoverState(this.hovered);
	            int j = 14737632;
	        	ImageHelper.drawCustomSizedImage(mc, icon, x + 2, y + 2, 16, 16, true);
	            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            GlStateManager.enableBlend();
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
	            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
	            this.mouseDragged(mc, mouseX, mouseY);

	            if (packedFGColour != 0)
	            {
	                j = packedFGColour;
	            }
	            else
	            if (!this.enabled)
	            {
	                j = 10526880;
	            }
	            else if (this.hovered)
	            {
	                j = 16777120;
	            }

	            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
	        }
		}
	}
}
