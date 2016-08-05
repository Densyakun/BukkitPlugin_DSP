package org.densyakun.bukkit.dsp.dspgames.citycraft;
public class People {
	public CityCraftGame game;
	public Build home;
	public Build shoping;
	public Build work;
	public long money;
	public People(CityCraftGame game, Build home, Build shoping, Build work) {
		this(game, home, shoping, work, 1000000);
	}
	public People(CityCraftGame game, Build home, Build shoping, Build work, long money) {
		this.game = game;
		this.home = home;
		this.shoping = shoping;
		this.work = work;
		this.money = money;
	}
}
