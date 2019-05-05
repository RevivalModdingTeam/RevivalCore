package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.util.IStringSerializable;

public class  EnumHelper {

    public enum Powers implements IStringSerializable {
        MAIN("main", 0),
        FIRST("firstpower", 1),
        SECOND("secondpower", 2),
        THIRD("thirdpower", 3);

        private int ID;
        private String NAME;

        Powers(String name, int id) {
            this.NAME = name;
            this.ID = id;
        }

        @Override
        public String getName() {
            return NAME;
        }
    }
}
