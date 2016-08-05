package org.densyakun.bukkit.dsp;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permissible;
public class DSPAdmin implements Listener {
	public Main main;
	public boolean emergency = false;
	public DSPAdmin(Main main) {
		this.main = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	public void setEMG(boolean emg) {
		emergency = emg;
		if (emg) {
			Player[]ps = main.getServer().getOnlinePlayers().toArray(new Player[0]);
			for (int a = 0; a < ps.length; a++) {
				if (getPermission(ps[a]) != AdminPermissions.other) {
					ps[a].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPAd]").append(ChatColor.RED.toString()).append("緊急時モードが有効になりました").toString());
				}
			}
		} else {
			Player[]ps = main.getServer().getOnlinePlayers().toArray(new Player[0]);
			for (int a = 0; a < ps.length; a++) {
				if (getPermission(ps[a]) != AdminPermissions.other) {
					ps[a].setGameMode(GameMode.SURVIVAL);
					ps[a].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPAd]").append(ChatColor.RED.toString()).append("緊急時モードが無効になりました").toString());
				}
			}
		}
	}
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		switch (e.getPlayer().getGameMode()) {
		case CREATIVE:
			if (!emergency) {
				e.getPlayer().setGameMode(GameMode.SURVIVAL);
			}
			break;
		default:
			break;
		}
	}
	@EventHandler
	public void PlayerGameModeChange(PlayerGameModeChangeEvent e) {
		switch (e.getNewGameMode()) {
		case CREATIVE:
			if (!emergency) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPAd]").append(ChatColor.RED.toString()).append("緊急時以外ゲームモードをクリエイティブにすることは出来ません").toString());
			}
			break;
		default:
			break;
		}
	}
	public static AdminPermissions getPermission(Permissible permission) {
		if (permission.hasPermission("dsp.owner")) {
			return AdminPermissions.owner;
		} else if (permission.hasPermission("dsp.fukuadmin")) {
			return AdminPermissions.fukuadmin;
		} else if (permission.hasPermission("dsp.admin")) {
			return AdminPermissions.admin;
		}
		return AdminPermissions.other;
	}
}
