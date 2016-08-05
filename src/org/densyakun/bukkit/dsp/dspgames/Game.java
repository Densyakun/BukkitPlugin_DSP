package org.densyakun.bukkit.dsp.dspgames;
import org.bukkit.entity.Player;
public abstract class Game {
	public int play = 0;
	public String name = "unknown";
	public DSPGames dspga;
	public Player owner;
	public Game(String name, DSPGames dspga, Player owner) {
		this.name = name;
		this.dspga = dspga;
		this.owner = owner;
		dspga.games.add(this);
	}
	public void gamestart() {
		play = 1;
	}
	public void gameend() {
		play = 2;
		for (int a = 0; a < dspga.games.size(); a++) {
			if ((dspga.games.get(a).play == play) && (dspga.games.get(a).name.equals(name)) && dspga.games.get(a).owner.getName().equals(owner.getName())) {
				dspga.games.remove(a);
			}
		}
	}
}
