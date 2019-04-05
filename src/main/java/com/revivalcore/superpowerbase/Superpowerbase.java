package com.revivalcore.superpowerbase;

import com.RevivalCore.client.render.SuperpowerRender.ISuperpowerRenderer;
import com.RevivalCore.superpowerbase.abilities.PowerBase;
import com.RevivalCore.superpowerbase.abilities.suppliers.IIPowerProvider;
import com.RevivalCore.superpowerbase.effects.Effect;
import com.RevivalCore.superpowerbase.gui.GuiAbilities;
import com.RevivalCore.util.helper.StringHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class Superpowerbase extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<Superpowerbase> implements IIPowerProvider {

    private String name;

    public Superpowerbase(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        return name;
    }

    public String getDisplayName() {
        return StringHelper.translateToLocal("superpower." + name + ".name");
    }

    @SideOnly(Side.CLIENT)
    public final void renderIcon(Minecraft mc, int x, int y) {
        this.renderIcon(mc, Minecraft.getMinecraft().currentScreen, x, y);
    }

    @SideOnly(Side.CLIENT)
    public void renderIcon(Minecraft mc, Gui gui, int x, int y) {

    }

    public boolean canLevelUp() {
        return false;
    }

    public int getMaxLevel() {
        return 0;
    }

    public int getXPForLevel(int level) {
        switch (level) {
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return (level - 1) * 1000;
            case 11:
            case 12:
            case 13:
            case 14:
                return 10000;
            case 15:
                return 15000;
            case 16:
            case 17:
            case 18:
                return 20000;
            case 19:
            case 20:
                return 25000;
            case 21:
                return 50000;
            case 22:
                return 75000;
            case 23:
                return 100000;
            case 24:
                return 125000;
            case 25:
                return 150000;
            case 26:
                return 190000;
            case 27:
                return 20000;
            case 28:
                return 250000;
            case 29:
                return 300000;
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                return 350000;
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                return 375000;
            default:
                return 0;
        }
    }

    public String getXPTranslationName() {
        return "RevivalCore.info.xp";
    }

    public String getXPName() {
        return StringHelper.translateToLocal(getXPTranslationName());
    }

    @SideOnly(Side.CLIENT)
    public ISuperpowerRenderer getPlayerRenderer() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getAbilityGui(EntityPlayer player) {
        return new GuiAbilities(player, PowerBase.EnumAbilityContext.SUPERPOWER);
    }

    @SideOnly(Side.CLIENT)
    public int getCapsuleColor() {
        return 15073794;
    }

    public List<Effect> getEffects() {
        return null;
    }

    public NBTTagCompound getData() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldAddToTab(EntityPlayer player) {
        return true;
    }

}
