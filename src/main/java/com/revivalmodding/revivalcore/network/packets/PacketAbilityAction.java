package com.revivalmodding.revivalcore.network.packets;

import com.revivalmodding.revivalcore.RevivalCore;
import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.common.events.AbilityEvent;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAbilityAction implements IMessage {

    private Ability ability;
    private AbilityAction action;
    private int data;

    public PacketAbilityAction() {
    }

    public PacketAbilityAction(Ability ability, AbilityAction action) {
        this(ability, -1, action);
    }

    public PacketAbilityAction(int data, AbilityAction action) {
        this(null, data, action);
    }

    public PacketAbilityAction(Ability ability, int data, AbilityAction action) {
        this.ability = ability;
        this.data = data;
        this.action = action;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, ability != null ? ability.getName() : "null");
        buf.writeInt(action.ordinal());
        buf.writeInt(data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.ability = Ability.fromString(ByteBufUtils.readUTF8String(buf));
        this.action = AbilityAction.values()[buf.readInt()];
        this.data = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketAbilityAction, IMessage> {

        @Override
        public IMessage onMessage(PacketAbilityAction message, MessageContext ctx) {
            ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                ICoreCapability coreCapability = CoreCapabilityImpl.getInstance(player);
                PlayerAbilityData abilityData = coreCapability.getAbilityData();
                switch (message.action) {
                    case TOGGLE: {
                        int key = message.data;
                        if(key < 0) return;
                        abilityData.toggleAbility(player, key);
                        message.ability.onActivate(player);
                        break;
                    }
                    case ACTIVATE: {
                        if(abilityData.getActiveAbilityCount() < 3) {
                            abilityData.activateAbility(message.ability);
                            PlayerHelper.sendMessage(player, TextFormatting.GREEN + "You have activated " + message.ability.getDisplayName(), true);
                        }
                        break;
                    }
                    case DEACTIVATE: {
                        if(abilityData.getActiveAbilityCount() > 0) {
                            boolean b = abilityData.deactivateAbility(message.ability);
                            if(!b) {
                                RevivalCore.logger.error("Couldn't disable ability {}!", message.ability);
                                return;
                            }
                            message.ability.onDeactivate(player);
                            PlayerHelper.sendMessage(player, TextFormatting.RED + "You have deactivated " + message.ability.getDisplayName(), true);
                        }
                        break;
                    }
                    case UNLOCK: {
                        Ability ability = message.ability;
                        if(!abilityData.getUnlockedAbilities().contains(ability)) {
                            if(abilityData.getLevel() >= ability.getPrice()) {
                                abilityData.unlockAbility(ability);
                                PlayerHelper.sendMessage(player, TextFormatting.GREEN + "You have unlocked " + ability.getDisplayName(), true);
                            }
                        }
                        break;
                    }
                }
                MinecraftForge.EVENT_BUS.post(new AbilityEvent(player, message.ability, message.action));
                coreCapability.sync();
            });
            return null;
        }
    }

    public enum AbilityAction {
        TOGGLE,
        ACTIVATE,
        DEACTIVATE,
        UNLOCK
    }
}
