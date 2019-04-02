package com.psychokiller.common.blocks;

import java.util.List;

import com.psychokiller.revivalcore.Registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSH extends Block
{
	private String[] description = new String[0];
	
	public BlockSH(String name, Material material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		
		// automatically registers item block for this instance of block
		Registries.Registry.registerItemBlock(this);
	}
	
	public BlockSH(String name)
	{
		this(name, Material.ROCK);
	}
	
	public BlockSH(String name, Material material, String... description)
	{
		this(name, material);
		this.description = description;
	}
	
	public BlockSH addDescription(String... strings)
	{
		this.description = strings;
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		if(description.length > 0)
		{
			for(String s : description)
			{
				tooltip.add(s);
			}
		}
	}
}
