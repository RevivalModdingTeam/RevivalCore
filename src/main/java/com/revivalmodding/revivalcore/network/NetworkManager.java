package com.revivalmodding.revivalcore.network;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.network.packets.PacketActivateAbility;
import com.revivalmodding.revivalcore.network.packets.PacketCapSync;
import com.revivalmodding.revivalcore.network.packets.PacketDeactivateAbility;
import com.revivalmodding.revivalcore.network.packets.PacketSetPower;
import com.revivalmodding.revivalcore.network.packets.PacketSyncProcessTileEntity;
import com.revivalmodding.revivalcore.network.packets.PacketToggleAbility;
import com.revivalmodding.revivalcore.network.packets.PacketUnlockAbility;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RevivalCore.MODID);
    private static int id = -1;

    public static void init() {
        INSTANCE.registerMessage(PacketCapSync.Handler.class, PacketCapSync.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSyncProcessTileEntity.Handler.class, PacketSyncProcessTileEntity.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSetPower.Handler.class, PacketSetPower.class, id++, Side.SERVER);
        INSTANCE.registerMessage(IAbilityCap.PacketSync.Handler.class, IAbilityCap.PacketSync.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PacketToggleAbility.Handler.class, PacketToggleAbility.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketUnlockAbility.Handler.class, PacketUnlockAbility.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketActivateAbility.Handler.class, PacketActivateAbility.class, id++, Side.SERVER);
        INSTANCE.registerMessage(PacketDeactivateAbility.Handler.class, PacketDeactivateAbility.class, id++, Side.SERVER);
    }
}
