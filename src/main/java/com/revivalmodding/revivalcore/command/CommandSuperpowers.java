package com.revivalmodding.revivalcore.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.core.registry.Registries;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandSuperpowers extends CommandBase {
	
	@Override
	public String getName() {
		return "superpowers";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "cmd.superpowers";
	}
	
	//TODO: WIP. Not tested!
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
					if(args[1].equalsIgnoreCase("add")) {
						if(args.length > 2) {
							try {
								int amount = Integer.parseInt(args[2]);
								int level = cap.getLevel();
								amount = amount > level ? level : amount;
								cap.setLevel(level - amount);
								cap.sync(player);
								
							} catch(NumberFormatException e) {
								throw new WrongUsageException("Invalid level value");
							}
						} else {
							throw new WrongUsageException("You must specify level!");
						}
					}
					break;
				}
				case "abilities": {
					break;
				}
				case "all": {
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
				case "xp" : {
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
}
