package com.revivalcore.core.common.events;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RVItemCraftedEvent extends Event {

    private World world;
    private ItemStack item;

    public RVItemCraftedEvent(World world, ItemStack itemStack) {
        this.world = world;
        this.item = itemStack;
    }

    public World getWorld() {
        return world;
    }

    public ItemStack getCraftedItem() {
        return item;
    }
}
