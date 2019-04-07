package com.revivalcore.client.gui;

import com.revivalcore.common.container.ContainerSuitMaker;
import com.revivalcore.common.tileentity.TileEntitySuitMaker;
import com.revivalcore.core.RevivalCore;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUISuitMaker extends GuiContainer
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(RevivalCore.MODID + ":textures/gui/suitmaker.png");
	private final InventoryPlayer playerInv;
	private final TileEntitySuitMaker te;
	
	public GUISuitMaker(InventoryPlayer playerInv, TileEntitySuitMaker te)
	{
		super(new ContainerSuitMaker(playerInv, te));
		this.playerInv = playerInv;
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(GUI_TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
