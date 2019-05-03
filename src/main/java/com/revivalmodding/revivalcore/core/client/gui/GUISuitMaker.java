package com.revivalmodding.revivalcore.core.client.gui;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.container.ContainerSuitMaker;
import com.revivalmodding.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import com.revivalmodding.revivalcore.util.helper.Constants;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
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
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		if(te.isProcessing())
		{
			ImageHelper.drawImageWithUV(mc, Constants.Textures.SUITMAKER_ARROW, 102, 33, 32 * te.getProgressionStage(), 16, 0, 0, 1 * te.getProgressionStage(), 1.0, true);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
