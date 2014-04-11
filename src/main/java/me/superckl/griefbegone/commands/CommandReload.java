package me.superckl.griefbegone.commands;

import me.superckl.griefbegone.GriefBeGone;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload extends AbstractCommand{

	@Override
	public boolean execute(final CommandSender sender, final String[] args) {
		if(!sender.hasPermission("disabler.reload")){
			sender.sendMessage(ChatColor.RED+"You don't have permission to do that.");
			return false;
		}
		if(args.length != 0){
			sender.sendMessage(ChatColor.RED+"Too many arguments. Did you mean to type reload?");
			return false;
		}
		GriefBeGone.getInstance().reload();
		sender.sendMessage(ChatColor.GOLD+"GriefBeGone has been reloaded.");
		return true;
	}

}
