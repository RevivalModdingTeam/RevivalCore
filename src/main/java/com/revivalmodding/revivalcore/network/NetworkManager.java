package com.revivalmodding.revivalcore.network;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.network.packets.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RevivalCore.MODID);
    private static int id = -1;

    public static void init() {
        INSTANCE.registerMessage(PacketSyncProcessTileEntity.Handler.class, PacketSyncProcessTileEntity.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSetPower.Handler.class, PacketSetPower.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSyncAbilities.Handler.class, PacketSyncAbilities.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketAbilityAction.Handler.class, PacketAbilityAction.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketSendCapabilitiesToServer.Handler.class, PacketSendCapabilitiesToServer.class, id++, Side.SERVER);
    }
}
