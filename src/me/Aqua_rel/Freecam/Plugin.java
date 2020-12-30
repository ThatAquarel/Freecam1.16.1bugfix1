package me.Aqua_rel.Freecam;

import org.bukkit.plugin.java.JavaPlugin;

import me.Aqua_rel.Freecam.ActionBar.ActionBar;
import me.Aqua_rel.Freecam.Commands.CamCommand;

public class Plugin extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		new CamCommand(this, new ActionBar());
		getServer().getPluginManager().registerEvents(new Disconnect(this), this);
	}
}