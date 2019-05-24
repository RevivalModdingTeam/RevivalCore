package com.revivalmodding.revivalcore.util.helper;

import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import net.minecraft.util.IStringSerializable;

public class EnumHelper {

    public enum InjectionTypes implements IStringSerializable {
        EMPTY("none"),
        SPEEDSTER(MetaPowerStrings.SPEEDSTER);

        private String metapowerstring;

        InjectionTypes(String powername) {
            this.metapowerstring = powername;
        }

        @Override
        public String getName() {
            return metapowerstring;
        }
    }
}
