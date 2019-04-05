package com.revivalcore.superpowerbase.abilities.suppliers;

public enum EnumSync {

    NONE, SELF, EVERYONE;

    public EnumSync add(EnumSync newSync) {
        return newSync.ordinal() > this.ordinal() ? newSync : this;
    }

}
