package com.revivalmodding.revivalcore.command;

import com.revivalmodding.revivalcore.core.abilities.Ability;
import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerAbilityData;
import com.revivalmodding.revivalcore.core.registry.Registries;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command for modifying player data like level,
 * abilities etc.
 * Calls SuperpowersCommandExecuteEvent on Forge's event bus.
 * The event is only called when first command arg == "all"
 *
 * Made by Toma
 */
public class CommandSuperpowers extends CommandBase {
	
	@Override
	public String getName() {
		return "superpowers";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "cmd.superpowers";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length > 0) {
			if(!(sender instanceof EntityPlayerMP)) {
				throw new WrongUsageException("Command must be executed by player!");
			}
			EntityPlayerMP player = (EntityPlayerMP)sender;
			ICoreCapability coreCapability = CoreCapabilityImpl.getInstance(player);
			PlayerAbilityData data = coreCapability.getAbilityData();
			switch(args[0]) {
				case "level": {
					if(args.length == 1)
						throw new WrongUsageException("Unknown parameter");
					if(args[1].equalsIgnoreCase("remove")) {
						if(args.length > 2) {
							try {
								int amount = Integer.parseInt(args[2]);
								int level = data.getLevel();
								amount = amount > level ? level : amount;
								data.setLevel(level - amount);
								coreCapability.sync();
								sendFeedback(player, "Updated level to " + data.getLevel());
							} catch(NumberFormatException e) {
								throw new WrongUsageException("Invalid level value");
							}
						} else {
							throw new WrongUsageException("You must specify level!");
						}
					} else if(args[1].equalsIgnoreCase("add")) {
						if(args.length > 2) {
							try {
								int amount = Integer.parseInt(args[2]);
								data.setLevel(data.getLevel() + amount);
								if(data.getLevel() < 0) {
									data.setLevel(0);
								}
								coreCapability.sync();
								sendFeedback(player, "Updated level to " + data.getLevel());
							} catch(NumberFormatException e) {
								throw new WrongUsageException("Invalid level value");
							}
						} else {
							throw new WrongUsageException("You must specify level!");
						}
					} else if(args[1].equalsIgnoreCase("reset")) {
						data.setLevel(0);
						coreCapability.sync();
						sendFeedback(player, "Your level has been reset to 0");
					} else {
						throw new WrongUsageException("Unknown parameter");
					}
					break;
				}
				case "abilities": {
					if(args.length == 1)
						throw new WrongUsageException("Unknown parameter");
					if(args[1].equalsIgnoreCase("unlockAll")) {
						Registries.ABILITIES.forEach(a -> {
							if(!Ability.hasAbility(a.getName(), player, true)) {
								data.unlockAbility(a);
							}
						});
						coreCapability.sync();
						sendFeedback(player, "Unlocked all abilities");
					} else if(args[1].equalsIgnoreCase("lockAll")) {
						data.clear(false);
						coreCapability.sync();
						sendFeedback(player, "Locked all abilities");
					} else if(args[1].equalsIgnoreCase("unlock")) {
						if(args.length > 2) {
							Ability ability = Ability.fromString(args[2]);
							if(ability == null) {
								throw new WrongUsageException("Unknown ability");
							}
							data.unlockAbility(ability);
							coreCapability.sync();
							sendFeedback(player, "Unlocked: " + ability.getDisplayName());
						} else {
							throw new WrongUsageException("You must specify ability");
						}
					} else if(args[1].equalsIgnoreCase("lock")) {
						if(args.length > 2) {
							Ability ability = Ability.fromString(args[2]);
							if(ability == null) {
								throw new WrongUsageException("Unknown ability");
							}
							data.lockAbility(ability);
							data.deactivateAbility(ability);
							coreCapability.sync();
							sendFeedback(player, "Locked: " + ability.getDisplayName());
						} else {
							throw new WrongUsageException("You must specify ability");
						}
					}
					break;
				}
				case "all": {
					if(data.getLevel() < 99) {
						data.setLevel(99);
					}
					Registries.ABILITIES.forEach(a -> {
						if(!Ability.hasAbility(a, player, true)) {
							data.unlockAbility(a);
						}
					});
					coreCapability.sync();
					MinecraftForge.EVENT_BUS.post(new SuperpowersCommandExecuteEvent(data, player));
					sendFeedback(player, "Unlocked everything!");
					break;
				}
				default:
					throw new WrongUsageException("Unknown parameter. Valid paramaters: [level,abilities,all]");
			}
		} else {
			throw new WrongUsageException("Not enough parameters. Try /superpowers <level,abilities,all>");
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		if(args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "level","abilities","all");
		}
		else if(args.length == 2) {
			switch(args[0]) {
				case "level" : {
					return getListOfStringsMatchingLastWord(args, "add","remove","reset");
				}
				case "abilities": {
					return getListOfStringsMatchingLastWord(args, "unlock","lock","unlockAll","lockAll");
				}
			}
		}
		else if(args.length == 3) {
			switch(args[1]) {
				// xp
				case "unlock": case "lock": {
					List<String> names = new ArrayList<>();
					Registries.ABILITIES.forEach(a -> names.add(a.getName()));
					return getListOfStringsMatchingLastWord(args, names);
				}
			}
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	public void sendFeedback(EntityPlayer player, String message) {
		if(player.sendCommandFeedback()) {
			player.sendMessage(new TextComponentString(message));
		}
	}
	
	public static class SuperpowersCommandExecuteEvent extends Event {
		public final PlayerAbilityData abilityData;
		public final EntityPlayerMP player;

		public SuperpowersCommandExecuteEvent(PlayerAbilityData abilityData, EntityPlayerMP player) {
			this.abilityData = abilityData; this.player = player;
		}
	}
}
