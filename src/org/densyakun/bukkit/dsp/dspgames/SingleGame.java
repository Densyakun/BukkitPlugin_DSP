package org.densyakun.bukkit.dsp.dspgames;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
public abstract class SingleGame extends Game implements Listener {
	public SingleGame(String name, DSPGames dspga, Player player) {
		super(name, dspga, player);
		gamestart();
		dspga.main.getServer().getPluginManager().registerEvents(this, dspga.main);
	}
	@Override
	public void gamestart() {
		super.gamestart();
		dspga.main.NameReload(owner);
		owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.GOLD).append("\"を開始しました").toString());
		Player[] a = owner.getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int b = 0; b < a.length; b++) {
			a[b].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(owner.getDisplayName()).append(ChatColor.AQUA).append(" さんが").append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を開始しました").toString());
		}
	}
	@Override
	public void gameend() {
		super.gameend();
		dspga.main.NameReload(owner);
		owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.GOLD).append("\"を終了しました").toString());
		Player[] a = owner.getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int b = 0; b < a.length; b++) {
			a[b].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(owner.getDisplayName()).append(ChatColor.AQUA).append(" さんが").append("ゲーム\"").append(ChatColor.GOLD).append(name).append(ChatColor.AQUA).append("\"を終了しました").toString());
		}
	}
	@EventHandler
	public void PlayerQuit(PlayerQuitEvent e) {
		if (play == 1) {
			if (e.getPlayer().getName().equals(owner.getName())) {
				gameend();
			}
		}
	}
}
