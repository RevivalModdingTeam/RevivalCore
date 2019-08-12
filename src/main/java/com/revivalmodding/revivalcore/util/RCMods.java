package com.revivalmodding.revivalcore.util;

import com.revivalmodding.revivalcore.RevivalCore;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.Loader;

public class RCMods {

    public static final String REVIVALCORE = RevivalCore.MODID;
    public static final String SPEEDSTERREBORN = "shr";
    public static final String METAVERSE = "metaverse";

    public enum Mods implements IStringSerializable {
        SPEEDSTERREBORN("shr");

        private String names;

        Mods(String name) {
            this.names = name;
        }

        @Override
        public String getName() {
            return names;
        }

        public boolean isLoaded() {
            return Loader.isModLoaded(getName());
        }
    }

}
