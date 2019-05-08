package com.revivalmodding.revivalcore.core.common.blocks;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.items.CoreItems;
import com.revivalmodding.revivalcore.util.helper.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class CoreBlocks {

    public static final List<Block> BLOCK_LIST = new ArrayList<Block>();

    public static Block SUIT_MAKER = RegisterBlock(new BlockSuitMaker(Material.ROCK), "suit_maker", true); // TODO Fix lava particles on break


    public static Block RegisterBlock(Block block, String name, boolean tab) {
        block.setRegistryName(name);
        block.setTranslationKey(name);
        CoreBlocks.BLOCK_LIST.add(block);

        if (block instanceof IHaveItem) {
            if(((IHaveItem) block).hasItem()) {
                ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(name);

                if (tab) {
                    block.setCreativeTab(RevivalCore.coretab);
                }
                CoreItems.registerRender(itemBlock);
                CoreItems.ITEM_LIST.add(itemBlock);
            }
        }
        return block;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(CoreBlocks.BLOCK_LIST.toArray(new Block[0]));
    }
}
