package com.revivalcore.meta.util;

import com.revivalcore.meta.capability.IMetaCap;
import net.minecraft.util.IStringSerializable;

public class PEnumHandler {

    public enum MetaPower implements IStringSerializable {

        SPEEDSTER("speedster", 0);

        private int ID;
        private String name;
        private IMetaCap cap;

        MetaPower(String name, int ID) {
            this.name = name;
            this.ID = ID;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
