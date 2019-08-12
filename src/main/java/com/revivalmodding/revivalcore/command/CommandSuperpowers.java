package com.revivalmodding.revivalcore.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
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
			IAbilityCap cap = IAbilityCap.Impl.get(player);
			switch(args[0]) {
				case "level": {
					if(args.length == 1)
						throw new WrongUsageException("Unknown parameter");
					if(args[1].equalsIgnoreCase("remove")) {
						if(args.length > 2) {
							try {
								int amount = Integer.parseInt(args[2]);
								int level = cap.getLevel();
								amount = amount > level ? level : amount;
								cap.setLevel(level - amount);
								cap.sync(player);
								sendFeedback(player, "Updated level to " + cap.getLevel());
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
								cap.setLevel(cap.getLevel() + amount);
								if(cap.getLevel() < 0) {
									cap.setLevel(0);
								}
								cap.sync(player);
								sendFeedback(player, "Updated level to " + cap.getLevel());
							} catch(NumberFormatException e) {
								throw new WrongUsageException("Invalid level value");
							}
						} else {
							throw new WrongUsageException("You must specify level!");
						}
					} else if(args[1].equalsIgnoreCase("reset")) {
						cap.setLevel(0);
						cap.sync(player);
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
							if(!AbilityBase.hasUnlockedAbility(player, a.getName())) {
								cap.unlockAbility(a.getName());
							}
						});
						cap.sync(player);
						sendFeedback(player, "Unlocked all abilities");
					} else if(args[1].equalsIgnoreCase("lockAll")) {
						cap.getUnlockedAbilities().forEach(u -> {cap.lockAbility(u.getName());});
						cap.reset(false, true, false, false);
						cap.sync(player);
						sendFeedback(player, "Locked all abilities");
					} else if(args[1].equalsIgnoreCase("unlock")) {
						if(args.length > 2) {
							AbilityBase ability = AbilityBase.getAbilityFromKey(args[2]);
							if(ability == null) {
								throw new WrongUsageException("Unknown ability");
							}
							cap.unlockAbility(ability.getName());
							cap.sync(player);
							sendFeedback(player, "Unlocked: " + ability.getFullName());
						} else {
							throw new WrongUsageException("You must specify ability");
						}
					} else if(args[1].equalsIgnoreCase("lock")) {
						if(args.length > 2) {
							AbilityBase ability = AbilityBase.getAbilityFromKey(args[2]);
							if(ability == null) {
								throw new WrongUsageException("Unknown ability");
							}
							cap.lockAbility(ability.getName());
							cap.sync(player);
							sendFeedback(player, "Locked: " + ability.getFullName());
						} else {
							throw new WrongUsageException("You must specify ability");
						}
					}
					break;
				}
				case "all": {
					if(cap.getLevel() < 99) {
						cap.setLevel(99);
					}
					Registries.ABILITIES.forEach(a -> {
						if(!AbilityBase.hasAbility(a, cap.getUnlockedAbilities())) {
							cap.unlockAbility(a.getName());
						}
					});
					cap.sync(player);
					MinecraftForge.EVENT_BUS.post(new SuperpowersCommandExecuteEvent(cap, player));
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
					Registries.ABILITIES.forEach(a -> {names.add(a.getName());});
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
		public final IAbilityCap capability;
		public final EntityPlayerMP player;
		public SuperpowersCommandExecuteEvent(IAbilityCap cap, EntityPlayerMP player) {this.capability = cap; this.player = player;}
	}
}
