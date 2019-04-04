package com.revivalcore.common.blocks;

import com.revivalcore.common.items.CoreItems;
import com.revivalcore.revivalcore.RevivalCore;
import com.revivalcore.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class BlockBasic extends Block implements IHasModel {
    private String[] description = new String[0];

    public BlockBasic(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);

        CoreBlocks.BLOCK_LIST.add(this);
        CoreItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockBasic(String name) {
        this(name, Material.ROCK);
    }

    public BlockBasic(String name, Material material, String... description) {
        this(name, material);
        this.description = description;
    }

    public BlockBasic addDescription(String... strings) {
        this.description = strings;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        if (description.length > 0) {
            for (String s : description) {
                tooltip.add(s);
            }
        }
    }

    @Override
    public void registerModels() {
        RevivalCore.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
