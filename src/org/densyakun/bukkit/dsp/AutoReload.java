package org.densyakun.bukkit.dsp;
import org.bukkit.Server;
import org.bukkit.entity.Player;
public class AutoReload implements Runnable {
	Server server;
	@Override
	public final void run() {
		while (true) {
			if (server != null) {
				break;
			}
		}
		while (true) {
			//ReloadSec();
			if (server == null) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	final void ReloadSec() {
		Player[] players = server.getOnlinePlayers().toArray(new Player[0]);
		if (players.length != 0) {
			long time = server.getWorld(Main.CWN).getTime();
			if ((time >= 0) && (time < 20)) {
				News.sendNews(server, 6, players);
			} else if ((time >= 12000) && (time < 12020)) {
				News.sendNews(server, 18, players);
			}
		}
	}
}
