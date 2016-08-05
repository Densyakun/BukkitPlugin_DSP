package org.densyakun.bukkit.dsp.dspgames;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.Main;
public class DSPGames {
	public static File dir = new File(Main.dir, "Games/");
	public Main main;
	public List<Game>games = new ArrayList<Game>();
	public DSPGames(Main main) {
		this.main = main;
		dir.mkdirs();
	}
	public void allstop() {
		for (int a = 0; a < games.size(); a++) {
			games.get(a).gameend();
		}
	}
	public boolean GameInit(Player player) {
		for (int a = 0; a < games.size(); a++) {
			if (games.get(a).owner.getName().equals(player.getName())) {
				return false;
			}
			if (games.get(a) instanceof MultiGame) {
				for (int b = 0; b < ((MultiGame) games.get(a)).getPlayers().size(); b++) {
					if (((MultiGame) games.get(a)).getPlayers().get(b).getName().equals(player.getName())) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
