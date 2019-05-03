package com.revivalmodding.revivalcore.proxy;

import com.revivalmodding.revivalcore.core.client.render.item.RenderSuitMakerItem;
import com.revivalmodding.revivalcore.core.common.blocks.CoreBlocks;
import com.revivalmodding.revivalcore.core.registry.Registries;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        Registries.TileRegistry.bindEntityTEISR();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        Item.getItemFromBlock(CoreBlocks.SUIT_MAKER).setTileEntityItemStackRenderer(new RenderSuitMakerItem());
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Override
    public void registerModelBakeryVariants() {

    }
}
