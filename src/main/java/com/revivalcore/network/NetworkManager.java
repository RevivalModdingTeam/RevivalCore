package com.revivalcore.network;

import com.revivalcore.network.packets.PacketCapSync;
import com.revivalcore.core.RevivalCore;
import com.revivalcore.network.packets.PacketSetSpeedsterCap;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RevivalCore.MODID);
    private static int id = -1;

    public static void init() {
        INSTANCE.registerMessage(PacketCapSync.Handler.class, PacketCapSync.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSetSpeedsterCap.Handler.class, PacketSetSpeedsterCap.class, id++, Side.SERVER);
    }
}
