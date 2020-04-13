package com.revivalmodding.revivalcore.core.common.suits;

import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistry;
import com.revivalmodding.revivalcore.core.registry.IRegistryEntry;
import com.revivalmodding.revivalcore.core.registry.Registries;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.awt.*;

/**
 * Base class for suit system.
 * Every suit contains 3 armor items (ItemSuit instances)
 *
 * Every suit has to be registered in appropriate event - in this case it's RVRegistryEvent.SuitRegistryEvent
 */
public abstract class AbstractSuit implements IRegistryEntry
{
	/** registry name for the suit, doesn't have to contain modID **/
	private final String name;

	/** color of trails which will be created by this suit *Trails are currently SHR thing, TODO move, and rewrite? **/
	private final Color color;
	
	public AbstractSuit(String name)
	{
		this(name, Color.RED);
	}
	
	public AbstractSuit(String name, float red, float green, float blue)
	{
		this(name, new Color(red, green, blue));
	}
	
	public AbstractSuit(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	/** As the name says, it get's the suit from the playerParameter, returns null if player doesn't have suit **/
    public static AbstractSuit getSuit(EntityPlayer player) {
    	for(EntityEquipmentSlot slot : ItemRegistry.ARMOR) {
    		if(!(player.getItemStackFromSlot(slot).getItem() instanceof ItemSuit)) {
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

	/**
	 * @return the suit's helmet item
	 */
	public abstract ItemSuit getHelmet();

	/**
	 * @return the suit's chestplate item
	 */
	public abstract ItemSuit getChest();

	/**
	 * @return the suit's leggings item
	 */
	public abstract ItemSuit getLeggings();
	
	/**
	 * Called every tick on both sides
	 * <u>but only when is being currently worn by player</u>
	 */
	public abstract void handleEffects(EntityPlayer player);

	/** creates new array of all suit parts/items (helmet = 0, chestplate = 1, leggings = 2) **/
	public final ItemSuit[] getSet()
	{
		return new ItemSuit[] {this.getHelmet(), this.getChest(), this.getLeggings()};
	}
	
	public final boolean isSuitComplete(EntityPlayer player)
	{
		return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this.getHelmet() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this.getChest() &&
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == this.getLeggings();
	}
	
	/** Additional xp player will get when wearing this suit **/
	public float getXPBonus() {
		return 0.001F;
	}
	
	/**
	 * @return Color for trail
	 */
	public final Color getTrailRGB() {
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
