package me.Aqua_rel.Freecam.Commands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Aqua_rel.Freecam.Plugin;
import me.Aqua_rel.Freecam.ActionBar.ActionBar;

public class CamCommand implements CommandExecutor {
	private Plugin _plugin;
	private ActionBar _actionbar;

	public CamCommand(Plugin plugin, ActionBar actionbar) {
		this._plugin = plugin;
		this._actionbar = actionbar;
		plugin.getCommand("c").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command!");
			return true;
		}

		Player p = (Player) sender;
		float x = p.getLocation().getBlockX();
		float y = p.getLocation().getBlockY();
		float z = p.getLocation().getBlockZ();
		float ry = p.getLocation().getPitch();
		float rx = p.getLocation().getYaw();

		if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.CREATIVE) {
			String formatLoc = x + "," + y + "," + z + "," + ry + "," + rx;
			this._plugin.getConfig().set(p.getDisplayName(), formatLoc);
			this._actionbar.sendMessage("\u00A7aCamera Mode On!", p);
			p.setFlySpeed(0.6F);
			p.setGameMode(GameMode.SPECTATOR);
		} else if (p.getGameMode() == GameMode.SPECTATOR) {
			String[] parsedStr = this._plugin.getConfig().getString(p.getDisplayName()).split(",");
			Location tpLoc = new Location(p.getWorld(), Float.parseFloat(parsedStr[0]), Float.parseFloat(parsedStr[1]),
					Float.parseFloat(parsedStr[2]));
			tpLoc.setPitch(Float.parseFloat(parsedStr[3]));
			tpLoc.setYaw(Float.parseFloat(parsedStr[4]));
			this._actionbar.sendMessage("\u00A7cCamera Mode Off!", p);
			p.teleport(tpLoc);
			p.setFlySpeed(0.1F);
			p.setGameMode(GameMode.SURVIVAL);
		}
		return true;
	}
}
