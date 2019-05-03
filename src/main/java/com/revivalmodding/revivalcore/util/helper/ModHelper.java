package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.launchwrapper.Launch;

public class ModHelper {

    public static boolean getIsDev() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public static void startupChecks() {
        if (!getIsDev()) {

        }
    }
}
