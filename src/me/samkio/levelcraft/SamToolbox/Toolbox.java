package me.samkio.levelcraft.SamToolbox;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//Toolbox by Samkio
// (C) 2010

public class Toolbox {
	public static void sendMessage(CommandSender sender, String string, Boolean success) {
		String prefix = ChatColor.GOLD + "[LC] ";
		String msg = prefix;
		if (success)
			msg = msg + ChatColor.GREEN;
		else {
			msg = msg + ChatColor.RED;
		}
		msg = msg + string;
		sender.sendMessage(msg);

	}

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	
}
