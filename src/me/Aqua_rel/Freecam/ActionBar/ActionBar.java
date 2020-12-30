package me.Aqua_rel.Freecam.ActionBar;

import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R1.ChatMessageType;
import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R1.PacketPlayOutChat;

public class ActionBar {
	public void sendMessage(String message, Player... players) {
		IChatBaseComponent text = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		for (Player p : players) {
			PacketPlayOutChat bar = new PacketPlayOutChat(text, ChatMessageType.GAME_INFO, p.getUniqueId());
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
		}
	}
}
