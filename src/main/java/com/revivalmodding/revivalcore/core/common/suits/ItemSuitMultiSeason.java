package com.revivalmodding.revivalcore.core.common.suits;

import com.google.common.base.Preconditions;
import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.common.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ItemSuitMultiSeason extends ItemSuit {

    private Season season;
    private final ResourceLocation texture;

    private ItemSuitMultiSeason(String name, EntityEquipmentSlot slot, Season season) {
        super(name, slot);
        String s = name.replaceAll("_", "");
        s = s.replaceAll("legs","");
        s = s.replaceAll("chest","");
        s = s.replaceAll("head","");
        this.season = season;
        this.texture = new ResourceLocation(this.getRegistryName().getNamespace()+":textures/suits/" + s + ".png");
    }

    @Override
    public ResourceLocation get3DTexture() {
        return texture;
    }

    public static class Builder {

        private List<Season> seasons = new ArrayList<>();

        public static Builder create() {
            return new Builder();
        }

        public Builder addSeason(Season season) {
            this.seasons.add(season);
            return this;
        }

        public Builder addSeason(String prefix) {
            this.seasons.add(new Season(prefix));
            return this;
        }

        public ItemSuitMultiSeason[] build(String name, CreativeTabs tab) {
            Preconditions.checkNotNull(name, "Registry name cannot be null!");
            Preconditions.checkState(!name.isEmpty(), "Registry name cannot be empty string!");
            Preconditions.checkState(!seasons.isEmpty(), "You must define season!");
            if(seasons.size() == 1) RevivalCore.logger.warn("Registering multiseason suit with only one season!");
            ItemSuitMultiSeason[] arr = new ItemSuitMultiSeason[seasons.size()*3];
            for(int s = 0; s < seasons.size(); s++) {
                for(int i = 0; i < ItemRegistry.ARMOR.length; i++) {
                    EntityEquipmentSlot slot = ItemRegistry.ARMOR[i];
                    String registryName = seasons.get(s).prefix + "_" + name + "_" + slot.getName();
                    ItemSuitMultiSeason suitMultiSeason = new ItemSuitMultiSeason(registryName, slot, seasons.get(s));
                    suitMultiSeason.setCreativeTab(tab);
                    arr[(s * 3) + i] = suitMultiSeason;
                }
            }
            return arr;
        }
    }

    public static class Season {

        private String prefix;

        public Season(String seasonPrefix) {
            this.prefix = seasonPrefix;
        }

        public String prefix() {
            return prefix;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Season) {
                if(obj == this) {
                    return true;
                } else {
                    Season season = (Season) obj;
                    return season.prefix.equals(this.prefix);
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Season[prefix="+prefix+"]";
        }
    }
}
