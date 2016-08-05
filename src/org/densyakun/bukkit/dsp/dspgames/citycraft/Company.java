package org.densyakun.bukkit.dsp.dspgames.citycraft;
import java.util.List;
public class Company {
	public CityCraftGame game;
	public List<Build>builds;
	public People president;
	public List<People>staffs;
	public Company(CityCraftGame game, List<Build>builds, People president, List<People>staffs) {
		this.game = game;
		this.builds = builds;
		this.president = president;
		this.staffs = staffs;
	}
}
