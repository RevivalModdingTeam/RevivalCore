package com.revivalcore.superpowerbase.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.RevivalCore.network.packets.PacketDispatcher;
import com.RevivalCore.revivalcore.RevivalCore;
import com.revivalcore.superpowerbase.Superpowerbase;
import com.RevivalCore.util.container.ContainerDummy;
import com.RevivalCore.util.handlers.SuperpowerHandler;
import com.RevivalCore.util.helper.StringHelper;

public class GuiChooseSuperpower extends GuiContainer {

    public static final ResourceLocation TEX = new ResourceLocation(RevivalCore.MODID, "textures/gui/choose_superpower.png");

    public List<Superpowerbase> superpowers = new ArrayList<>();
    public GuiChooseSuperpowerList list;
    public int selected = -1;

    public GuiChooseSuperpower(String[] superpowers) {
        super(new ContainerDummy());

        for (String s : superpowers) {
            Superpowerbase sp = SuperpowerHandler.SUPERPOWER_REGISTRY.getValue(new ResourceLocation(s));
            if (sp != null)
                this.superpowers.add(sp);
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        this.xSize = 256;
        this.ySize = 189;
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        list = new GuiChooseSuperpowerList(mc, this);

        this.addButton(new GuiButtonExt(0, i + 5, j + 167, 70, 18, StringHelper.translateToLocal("lucraftcore.info.confirm")));
        this.addButton(new GuiButtonExt(1, i + 180, j + 167, 70, 18, StringHelper.translateToLocal("lucraftcore.info.close")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            PacketDispatcher.sendToServer(new MessageChooseSuperpower(superpowers.get(selected).getRegistryName().toString()));
            mc.player.closeScreen();
        } else if (button.id == 1)
            mc.player.closeScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        mc.getTextureManager().bindTexture(TEX);
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        String s = StringHelper.translateToLocal("lucraftcore.info.chooseasuperpower");
        int width = fontRenderer.getStringWidth(s);
        fontRenderer.drawString(s, i + this.xSize / 2 - width / 2, j + 6, 4210752);

        if (list != null) {
            list.drawScreen(mouseX, mouseY, partialTicks);
        }

        this.buttonList.get(0).enabled = selected >= 0;
    }

}
