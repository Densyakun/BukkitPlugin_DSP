package org.densyakun.bukkit.dsp.dsprouting;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
public class Router {
	public DSPRouting dspro;
	public int play = 0;
	public Player player;
	public Route route;
	public int point = 0;
	public Router(DSPRouting dspro, Player player, Route route) {
		this.dspro = dspro;
		this.player = player;
		this.route = route;
		dspro.routers.add(this);
		player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("ルート案内を開始しました。ご利用ありがとうございます").toString());
		nextPoint();
	}
	public void nextPoint() {
		if (point < (route.points.size() - 1)) {
			point ++;
			Point a = route.points.get(point);
			player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("次の地点: ").append(a.name).append(" X: ").append(a.location.getBlockX()).append(" Y: ").append(a.location.getBlockY()).append(" Z: ").append(a.location.getBlockZ()).toString());
			a = null;
			if (point == (route.points.size() - 1)) {
				player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("目的地に到着しました").toString());
				end();
			}
		}
	}
	public void end() {
		for (int b = 0; b < dspro.routers.size(); b++) {
			if (dspro.routers.get(b).player.getName().equals(player.getName())) {
				dspro.routers.remove(b);
			}
		}
		play = 1;
		player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("ルート案内を終了します。またご利用ください").toString());
	}
}
