package com.revivalcore.util.handlers;

import com.revivalcore.core.client.gui.GUISuitMaker;
import com.revivalcore.core.common.container.ContainerSuitMaker;
import com.revivalcore.core.common.tileentity.TileEntitySuitMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerRV implements IGuiHandler {
    private static int id = -1;

    // GUI registry
    public static final int GUI_SUITMAKER = getID();

    @Override
    public Object getClientGuiElement(int i, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
        	case 0:
        		return new GUISuitMaker(player.inventory, (TileEntitySuitMaker)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getServerGuiElement(int i, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
        	case 0:
        		return new ContainerSuitMaker(player.inventory, (TileEntitySuitMaker)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    static int getID() {
        id++;
        return id;
    }
}
