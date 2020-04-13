package com.revivalmodding.revivalcore.core.common.events;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RVItemCraftedEvent extends Event {

    private World world;
    private ItemStack item;
    public BlockPos position;

    public RVItemCraftedEvent(World world, ItemStack itemStack, BlockPos pos) {
        this.world = world;
        this.item = itemStack;
        this.position = pos;
    }

    public World getWorld() {
        return world;
    }

    public ItemStack getCraftedItem() {
        return item;
    }
}
