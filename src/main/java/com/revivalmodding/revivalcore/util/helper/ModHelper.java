package com.revivalmodding.revivalcore.util.helper;

import com.revivalmodding.revivalcore.RevivalCore;
import net.minecraft.launchwrapper.Launch;

public class ModHelper {

    public static boolean getIsDev() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public static void startupChecks() {
        if(!RevivalCore.run) {
            if (!getIsDev()) {
                throw new IllegalStateException("You're not allowed to run this!");
            }
        }
    }
}
