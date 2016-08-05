package org.densyakun.bukkit.dsp.dspgames;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
public abstract class MultiGame extends Game implements Listener {
	private List<Player>players = new ArrayList<Player>();
	private boolean startable = true;
	private int minplayers = 1;
	private int maxplayers = 0;
	private boolean quitend = false;
	public MultiGame(String name, DSPGames dspga, Player owner) {
		super(name, dspga, owner);
		dspga.main.NameReload(owner);
		Player[] a = owner.getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int b = 0; b < a.length; b++) {
			a[b].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(owner.getDisplayName()).append(ChatColor.AQUA).append(" さんが").append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"のエントリーを開始しました").toString());
		}
		dspga.main.getServer().getPluginManager().registerEvents(this, dspga.main);
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public boolean isStartable() {
		if (maxplayers == 0) {
			if (minplayers <= players.size()) {
				return startable;
			}
		}
		if ((minplayers <= players.size()) && (players.size() <= maxplayers)) {
			return startable;
		}
		return false;
	}
	public void setStartable(boolean startable) {
		this.startable = startable;
	}
	public int getMinplayers() {
		return minplayers;
	}
	public void setMinplayers(int minplayers) {
		if (0 < minplayers) {
			this.minplayers = minplayers;
		} else {
			this.minplayers = 0;
		}
	}
	public int getMaxplayers() {
		return maxplayers;
	}
	public void setMaxplayers(int maxplayers) {
		if (0 < maxplayers) {
			this.maxplayers = maxplayers;
		} else {
			this.maxplayers = 0;
		}
	}
	public boolean addPlayer(Player player) {
		if (player.getName().equals(owner.getName())) {
			return false;
		}
		for (int a = 0; a < players.size(); a++) {
			if (players.get(a).getName().equals(player.getName())) {
				return false;
			}
		}
		if ((maxplayers == 0) || (players.size() < maxplayers)) {
			players.add(player);
			owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(player.getDisplayName()).append(ChatColor.AQUA.toString()).append("さんがゲームに参加しました").toString());
			for (int a = 0; a < players.size(); a++) {
				players.get(a).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(player.getDisplayName()).append(ChatColor.AQUA.toString()).append("さんがゲームに参加しました").toString());
			}
			dspga.main.NameReload(player);
			return true;
		}
		return false;
	}
	public boolean removePlayer(String name) {
		if (name.equals(owner.getName())) {
			if (quitend) {
				gameend();
			} else {
				if (0 < players.size()) {
					owner = players.get(0);
					players.remove(0);
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.AQUA.toString()).append("管理者が").append(owner.getDisplayName()).append("さんに変更されました").toString());
					for (int a = 0; a < players.size(); a++) {
						players.get(a).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.AQUA.toString()).append("管理者が").append(owner.getDisplayName()).append("さんに変更されました").toString());
					}
				} else {
					gameend();
				}
			}
			return true;
		}
		for (int a = 0; a < players.size(); a++) {
			if (name.equals(players.get(a).getName())) {
				dspga.main.NameReload(players.get(a));
				owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(name).append(ChatColor.AQUA.toString()).append("さんがゲームから退出しました").toString());
				for (int b = 0; b < players.size(); b++) {
					players.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(name).append(ChatColor.AQUA.toString()).append("さんがゲームから退出しました").toString());
				}
				players.remove(a);
				if ((players.size() < minplayers) || (players.size() == 0)) {
					if (play == 1) {
						gameend();
					} else {
						play = 2;
					}
				}
				return true;
			}
		}
		return false;
	}
	@Override
	public void gamestart() {
		if (play == 0) {
			if (isStartable()) {
				super.gamestart();
				if (0 < players.size()) {
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を開始しました").toString());
					for (int a = 0; a < players.size(); a++) {
						players.get(a).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を開始しました").toString());
					}
					Player[] a = players.get(0).getServer().getOnlinePlayers().toArray(new Player[0]);
					for (int b = 0; b < a.length; b++) {
						String c = "";
						for (int d = 0; d < players.size(); d++) {
							c += new StringBuffer(players.get(d).getDisplayName()).append(" ").toString();
						}
						a[b].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(c).append(ChatColor.AQUA).append("さんが").append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を開始しました").toString());
					}
				} else {
					gameend();
				}
			}
		}
	}
	@Override
	public void gameend() {
		super.gameend();
		dspga.main.NameReload(owner);
		owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を終了しました").toString());
		for (int a = 0; a < players.size(); a++) {
			dspga.main.NameReload(players.get(a));
			players.get(a).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を終了しました").toString());
		}
		Player[] a = owner.getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int b = 0; b < a.length; b++) {
			a[b].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(owner.getDisplayName()).append(ChatColor.AQUA).append("さんが").append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を終了しました").toString());
		}
	}
	@EventHandler
	public void PlayerQuit(PlayerQuitEvent e) {
		if (play == 1) {
			for (int a = 0; a < players.size(); a++) {
				if (players.get(a).getName().equals(e.getPlayer().getName())) {
					removePlayer(e.getPlayer().getName());
				}
			}
		}
	}
	public boolean isQuitend() {
		return quitend;
	}
	public void setQuitend(boolean quitend) {
		this.quitend = quitend;
	}
}
