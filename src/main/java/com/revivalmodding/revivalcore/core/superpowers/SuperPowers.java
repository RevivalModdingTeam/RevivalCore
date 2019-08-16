package com.revivalmodding.revivalcore.core.superpowers;

import java.util.HashMap;
import java.util.Map;

public class SuperPowers {

    private static final Map<String, SuperPower> REGISTRY = new HashMap<>();

    /** Register superpowers here **/
    public static void init() {

    }

    public static Map<String, SuperPower> getRegistry() {
        return new HashMap<>(REGISTRY);
    }

    // can only be used from core, other mods have to use the registry event
    protected static void register(String registryName, SuperPower power) {
        REGISTRY.put(registryName, power);
    }
}
