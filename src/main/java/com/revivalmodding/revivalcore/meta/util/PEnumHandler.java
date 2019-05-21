package com.revivalmodding.revivalcore.meta.util;

import net.minecraft.util.IStringSerializable;

public class PEnumHandler {

    public enum MetaPower implements IStringSerializable {

        SPEEDSTER("speedster", 0);

        private int ID;
        private String name;

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

    ;public enum VelocityTypes implements IStringSerializable {
        VELOCITY_NINE("v9", 60, 5, 1);

        private String names;
        private int timeleft;
        private int maxspeedlevels;
        private int damages;

        VelocityTypes(String name,int time, int maxspeedlevel, int damage) {
            this.names = name;
            this.timeleft = time;
            this.maxspeedlevels = maxspeedlevel;
            this.damages = damage;
        }

        public int getDamages() {
            return damages;
        }

        public int getMaxspeedlevels() {
            return maxspeedlevels;
        }

        public int getTimeleft() {
            return timeleft;
        }

        @Override
        public String getName() {
            return this.names;
        }
    }
}
