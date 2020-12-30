package me.Aqua_rel.Freecam;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Disconnect implements Listener {
	private Plugin _plugin;

	public Disconnect(Plugin plugin) {
		this._plugin = plugin;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SPECTATOR) {
			String[] parsedStr = this._plugin.getConfig().getString(p.getDisplayName()).split(",");
			Location tpLoc = new Location(p.getWorld(), Float.parseFloat(parsedStr[0]), Float.parseFloat(parsedStr[1]),
					Float.parseFloat(parsedStr[2]));
			tpLoc.setPitch(Float.parseFloat(parsedStr[3]));
			tpLoc.setYaw(Float.parseFloat(parsedStr[4]));
			p.teleport(tpLoc);
			p.setFlySpeed(0.1F);
			p.setGameMode(GameMode.SURVIVAL);
		}
	}
}
