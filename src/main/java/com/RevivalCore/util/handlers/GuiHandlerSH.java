package com.RevivalCore.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerSH implements IGuiHandler
{
	private static int id = -1;
	
	// GUI registry
	// public static final int GUI_NAME = getID();
	
	@Override
	public Object getClientGuiElement(int i, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(id)
		{
			default: return null;
		}
	}
	
	@Override
	public Object getServerGuiElement(int i, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(id)
		{
			default: return null;
		}
	}
	
	static int getID()
	{
		id++;
		return id;
	}
}
