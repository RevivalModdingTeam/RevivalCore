package com.revivalmodding.revivalcore.core.common.suits;

import com.revivalmodding.revivalcore.core.common.items.CoreItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class SuitDebug extends AbstractSuit
{
	public SuitDebug()
	{
		super("debug");
	}
	
	@Override
	public ItemSuit getHelmet()
	{
		return (ItemSuit) CoreItems.suit_head;
	}
	
	@Override
	public ItemSuit getChest()
	{
		return (ItemSuit) CoreItems.suit_body;
	}
	
	@Override
	public ItemSuit getLeggings() 
	{
		return (ItemSuit) CoreItems.suit_legs;
	}
	
	@Override
	public ItemSuit getBoots()
	{
		return (ItemSuit) CoreItems.suit_boots;
	}
	
	@Override
	public void handleEffects(EntityPlayer player)
	{
		if(!isSuitComplete(player)) {
			return;
		}
		
		if(!player.world.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 10, false, false));
		}
	}
}
