package me.kev3200.itemp.core;

import java.util.logging.Logger;
import me.kev3200.itemp.listeners.blockListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
  
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new blockListener(), this);
    
		PluginDescriptionFile pdfFile = getDescription();
		logger.info(ChatColor.GREEN + pdfFile.getName() + " version " + pdfFile.getVersion() + " is now Enabled!");
	}
  
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		logger.info(pdfFile.getName() + " is now Disabled!");
		saveConfig();
	}
}
