package org.densyakun.bukkit.dsp.dspgames.citycraft;
public class Land {
	public CityCraftGame game;
	public int x;
	public int z;
	public LandType type;
	public Land(CityCraftGame game, int x, int z, LandType type) {
		this.game = game;
		this.x = x;
		this.z = z;
		this.type = type;
	}
}
