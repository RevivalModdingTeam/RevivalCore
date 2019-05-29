package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonChangePage extends GuiButton {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/pagebuttons.png");
	private final boolean right;
	
	public ButtonChangePage(int id, int x, int y, int width, int height, boolean isRightArrow) {
		super(id, x, y, width, height, "");
		this.right = isRightArrow;
		visible = true;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		ImageHelper.drawImageWithUV(mc, TEXTURE, x, y, width, height,
				right ? 0.5 : 0, visible ? hovered ? 1D/3D : 0 : 2D/3D, right ? 1 : 0.5, visible ? hovered ? 2D/3D : 1D/3D : 1, false);
	}
	
	public void update(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isRightArrow() {
		return right;
	}
}
