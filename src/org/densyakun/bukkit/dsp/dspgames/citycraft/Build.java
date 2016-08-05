package org.densyakun.bukkit.dsp.dspgames.citycraft;
import java.util.List;
public class Build {
	public CityCraftGame game;
	public BuildType type;
	public List<Land>lands;
	public Build(CityCraftGame game, BuildType type, List<Land>lands) {
		this.game = game;
		this.type = type;
		this.lands = lands;
		reload();
	}
	public void reload() {
		switch (type) {
		default:
			for (int a = 0; a < lands.size(); a++) {
				lands.get(a).type = LandType.air;
			}
			break;
		}
	}
}
