<<<<<<< HEAD
package me.samkio.levelcraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.samkio.levelcraft.Functions.PlayerFunctions;
import me.samkio.levelcraft.Listeners.LCBlockListener;
import me.samkio.levelcraft.Listeners.LCEntityListener;
import me.samkio.levelcraft.Listeners.LCPlayerListener;
import me.samkio.levelcraft.SamToolbox.DataMySql;
import me.samkio.levelcraft.SamToolbox.DataSqlite;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Levelcraft extends JavaPlugin {
	@SuppressWarnings("unused")
	private final Admin Admin = new Admin(this);
	private static final Logger log = Logger.getLogger("Minecraft");
	private final LCPlayerListener playerListener = new LCPlayerListener(this);
	private final LCBlockListener blockListener = new LCBlockListener(this);
	private final LCEntityListener entityListener = new LCEntityListener(this);
	public static String maindirectory = "LevelCraft/";
	public static String datadirectory = "Experience/";
	public static String configdirectory = "Config/";
	public static File WCExpFile = new File(maindirectory + datadirectory
			+ "WoodCutting.exp");
	public static File MiExpFile = new File(maindirectory + datadirectory
			+ "Mining.exp");
	public static File SlayExpFile = new File(maindirectory + datadirectory
			+ "Slaying.exp");
	public static File RangeExpFile = new File(maindirectory + datadirectory
			+ "Ranging.exp");
	public static File FisticuffsExpFile = new File(maindirectory + datadirectory
			+ "Fisticuffs.exp");
	public static File ArcherExpFile = new File(maindirectory + datadirectory
			+ "Archer.exp");
	  public static PermissionHandler Permissions;


/*	public Levelcraft(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super();
	}
*/
	public Levelcraft(){
		
	}

	@Override
	public void onDisable() {
		System.out.println("Levelcraft Disabled");

	}

	@Override
	public void onEnable() {
		load();
		registerEvents();
		if(Settings.UsePerms){
		setupPermissions();
		}else{
			 log.info("[LC] Using Whitelist.");
		}
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[LC] Levelcraft version "
				+ pdfFile.getVersion() + " is enabled!");

	}
	@SuppressWarnings("static-access")
	private void setupPermissions() {
	      Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (this.Permissions == null) {
	          if (test != null) {
	              this.Permissions = ((Permissions)test).getHandler();
	              log.info("[LC] Using Permissions.");
	          } else {
	              log.info("[LC] Permissions not found using whitelist.");
	              Settings.UsePerms = false;
	          }
	      }
	  }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {       
        if (commandLabel.equalsIgnoreCase("level") || commandLabel.equalsIgnoreCase("lvl")) {
        	if (args.length >= 1 && sender instanceof Player) {
        		PlayerFunctions.checkAccount(sender);
        		PlayerFunctions.doThis(sender, args, this);
        		return true;
        	} else {
        		About(sender);
        		return false;
        	}
        } else {
        	return false;
        }
    }
    public static void About(CommandSender sender) {
    	sender.sendMessage(ChatColor.GOLD + "[LC] ---LevelCraftPlugin By Samkio (C)2011--- ");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl list - Shows active stats.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl [w|m|s|r|f|a] - Shows stats statisics.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl notify - Toggles notifications.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl unlocks [w|m|s|r|f|a] - Shows tool level unlocks.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl shout [w|m|s|r|f|a] - Display level to the server.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl or /level - Shows this.");
    }
	void load() {
		new File(maindirectory).mkdirs();
		new File(maindirectory + datadirectory).mkdirs();
		new File(maindirectory + configdirectory).mkdirs();

		Settings.loadMain();
		Settings.loadWoodcut();
		Settings.loadSlayer();
		Settings.loadMine();
		Settings.loadWhitelist();
		Settings.loadRange();
		Settings.loadFisticuffs();
		Settings.loadArcher();
		
		if (Settings.database.equalsIgnoreCase("flatfile")) {
			
			if (!WCExpFile.exists() || !MiExpFile.exists()
					|| !SlayExpFile.exists() || !RangeExpFile.exists() || !FisticuffsExpFile.exists())
				try {
					WCExpFile.createNewFile();
					MiExpFile.createNewFile();
					SlayExpFile.createNewFile();
					RangeExpFile.createNewFile();
					FisticuffsExpFile.createNewFile();
					ArcherExpFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} else if (Settings.database.equalsIgnoreCase("mysql")) {
			DataMySql.PrepareDB();
		} else if (Settings.database.equalsIgnoreCase("sqlite")) {
			DataSqlite.PrepareDB();
		}else{
			log.severe("[Levelcraft] Nowhere to save data! Edit MainConfig Database.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}

	void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGED,
				this.entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_TARGET,
				this.entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_DAMAGED, blockListener,
				Event.Priority.Normal, this);
	}

}
=======
package me.samkio.levelcraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.samkio.levelcraft.Functions.PlayerFunctions;
import me.samkio.levelcraft.Listeners.LCBlockListener;
import me.samkio.levelcraft.Listeners.LCEntityListener;
import me.samkio.levelcraft.Listeners.LCPlayerListener;
import me.samkio.levelcraft.SamToolbox.DataMySql;
import me.samkio.levelcraft.SamToolbox.DataSqlite;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Levelcraft extends JavaPlugin {
	@SuppressWarnings("unused")
	private final Admin Admin = new Admin(this);
	private static final Logger log = Logger.getLogger("Minecraft");
	private final LCPlayerListener playerListener = new LCPlayerListener(this);
	private final LCBlockListener blockListener = new LCBlockListener(this);
	private final LCEntityListener entityListener = new LCEntityListener(this);
	public static String maindirectory = "LevelCraft/";
	public static String datadirectory = "Experience/";
	public static String configdirectory = "Config/";
	public static File WCExpFile = new File(maindirectory + datadirectory
			+ "WoodCutting.exp");
	public static File MiExpFile = new File(maindirectory + datadirectory
			+ "Mining.exp");
	public static File SlayExpFile = new File(maindirectory + datadirectory
			+ "Slaying.exp");
	public static File RangeExpFile = new File(maindirectory + datadirectory
			+ "Ranging.exp");
	public static File FisticuffsExpFile = new File(maindirectory + datadirectory
			+ "Fisticuffs.exp");
	public static File ArcherExpFile = new File(maindirectory + datadirectory
			+ "Archer.exp");

/*	public Levelcraft(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super();
	}
*/
	public Levelcraft(){
		
	}

	@Override
	public void onDisable() {
		System.out.println("Levelcraft Disabled");

	}

	@Override
	public void onEnable() {
		load();
		registerEvents();
         //Enable
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");

	}
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("level") || commandLabel.equalsIgnoreCase("lvl")) {
        	if (args.length >= 1 && sender instanceof Player) {
                        PlayerFunctions.checkAccount(sender);
        		PlayerFunctions.doThis(sender, args, this);
        		return true;
        	} else {
        		About(sender);
        		return false;
        	}
        } else {
        	return false;
        }
    }
    public static void About(CommandSender sender) {
    	sender.sendMessage(ChatColor.GOLD + "[LC] ---LevelCraftPlugin By Samkio (C)2011--- ");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl list - Shows active stats.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl [w|m|s|r|f|a] - Shows stats statisics.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl notify - Toggles notifications.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl unlocks [w|m|s|r|f|a] - Shows tool level unlocks.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl shout [w|m|s|r|f|a] - Display level to the server.");
    	sender.sendMessage(ChatColor.GOLD + "[LC]" +ChatColor.YELLOW+ " /lvl or /level - Shows this.");
    }
	void load() {
		new File(maindirectory).mkdirs();
		new File(maindirectory + datadirectory).mkdirs();
		new File(maindirectory + configdirectory).mkdirs();

		Settings.loadMain();
		Settings.loadWoodcut();
		Settings.loadSlayer();
		Settings.loadMine();
		Settings.loadWhitelist();
		Settings.loadRange();
		Settings.loadFisticuffs();
		Settings.loadArcher();
		
		if (Settings.database.equalsIgnoreCase("flatfile")) {
			
			if (!WCExpFile.exists() || !MiExpFile.exists()
					|| !SlayExpFile.exists() || !RangeExpFile.exists() || !FisticuffsExpFile.exists())
				try {
					WCExpFile.createNewFile();
					MiExpFile.createNewFile();
					SlayExpFile.createNewFile();
					RangeExpFile.createNewFile();
					FisticuffsExpFile.createNewFile();
					ArcherExpFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} else if (Settings.database.equalsIgnoreCase("mysql")) {
			DataMySql.PrepareDB();
		} else if (Settings.database.equalsIgnoreCase("sqlite")) {
			DataSqlite.PrepareDB();
		}else{
			log.severe("[Levelcraft] Nowhere to save data! Edit MainConfig Database.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}

	void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGED,
				this.entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_TARGET,
				this.entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_DAMAGED, blockListener,
				Event.Priority.Normal, this);
	}

}
>>>>>>> d200097c15114e8243117a889a7492188d32cc79
