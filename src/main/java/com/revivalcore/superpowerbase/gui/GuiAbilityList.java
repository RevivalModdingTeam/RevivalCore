package com.revivalcore.superpowerbase.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.GuiScrollingList;
import org.lwjgl.opengl.GL11;

import com.RevivalCore.network.packets.PacketDispatcher;
import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.util.helper.StringHelper;

public class GuiAbilityList extends GuiScrollingList {

    private GuiAbilities parent;

    public GuiAbilityList(Minecraft client, GuiAbilities parent) {
        super(client, 214, 140, ((parent.height - parent.ySize_) / 2) + 28, ((parent.height - parent.ySize_) / 2) + 21 + 140, ((parent.width - parent.xSize_) / 2) + 21, 27, parent.width, parent.height);
        this.parent = parent;
    }

    @Override
    protected int getSize() {
        return parent.abilities.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        parent.selectedAbility = index;
        parent.mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1, 1);

        PowerBase ability = parent.abilities.get(index);

        if (doubleClick && ability.showInAbilityBar()) {
            ability.setHidden(!ability.isHidden());
            PacketDispatcher.sendToServer(new MessageToggleAbilityVisibility(parent.abilities.get(index).getKey()));
        }
    }

    @Override
    protected boolean isSelected(int index) {
        return index == parent.selectedAbility;
    }

    @Override
    protected void drawBackground() {

    }

    @Override
    protected int getContentHeight() {
        return super.getContentHeight();
    }

    @Override
    protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
        GlStateManager.enableBlend();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1, 1, 1);
        parent.mc.getTextureManager().bindTexture(GuiAbilities.TEX);
        PowerBase ab = parent.abilities.get(slotIdx);
        boolean isHidden = ab.isHidden();

        parent.drawTexturedModalRect(this.left + 2, slotTop, 0, 189, 22, 22);
        if (!ab.isUnlocked())
            parent.drawTexturedModalRect(this.left + 190, slotTop + 4, 48, 189, 10, 14);

        RenderHelper.drawStringWithOutline(ab.getDisplayName(), left + 30, slotTop + 7 - (isHidden ? 4 : 0), 0xffffff, 0);
        if (isHidden) {
            boolean unicode = Minecraft.getMinecraft().fontRenderer.getUnicodeFlag();
            Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(true);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(TextFormatting.GRAY + StringHelper.translateToLocal("lucraftcore.info.hiddeninabilitybar"), left + 30, slotTop + 12, 0xfefefe);
            Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(unicode);
        }
        GlStateManager.color(1F, 1F, 1F);
        PowerBase.drawIcon(ab, parent.mc, parent, left + 5, slotTop + 3);

        GlStateManager.disableBlend();
    }

}
