package com.revivalmodding.revivalcore.core.common.suits;

import java.awt.Color;

import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;

public abstract class AbstractSuit implements IRegistryEntry
{
	private final String name;
	private final Color color;
	
	public AbstractSuit(String name)
	{
		this(name, Color.RED);
	}
	
	public AbstractSuit(String name, float red, float green, float blue)
	{
		this(name, new Color(red, green, blue));
	}
	
	public AbstractSuit(String name, Color color)
	{
		this.name = name;
		this.color = color;
	}
	
    public static AbstractSuit getSuit(EntityPlayer player) {
    	for(int i = 2; i < 6; ++i) {
    		if(!(player.getItemStackFromSlot(EntityEquipmentSlot.values()[i]).getItem() instanceof ItemSuit)) {
    			return null;
    		}
    	}
    	for(AbstractSuit suit : Registries.SUITS) {
    		if(suit.isSuitComplete(player)) {
    			return suit;
    		}
    	}
    	return null;
    }
	
	public abstract ItemSuit getHelmet();
	
	public abstract ItemSuit getChest();
	
	public abstract ItemSuit getLeggings();
	
	public abstract ItemSuit getBoots();
	
	/**
	 * Called every tick on both sides
	 * <u>but only when is being currently worn by player</u>
	 * @param player
	 */
	public abstract void handleEffects(EntityPlayer player);
	
	public final ItemSuit[] getSet()
	{
		return new ItemSuit[] {this.getHelmet(), this.getChest(), this.getLeggings(), this.getBoots()};
	}
	
	public final boolean isSuitComplete(EntityPlayer player)
	{
		return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this.getHelmet() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this.getChest() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == this.getLeggings() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == this.getBoots();
	}
	
	/** Additional xp player will get when wearing this suit **/
	public double getXPBonus() {
		return 0.001d;
	}
	
	/**
	 * @return Color for trail
	 */
	public final Color getTrailRGB()
	{
		return color;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public IRegistry getRegistry()
	{
		return Registries.SuitRegistry.instance();
	}
}
