package org.densyakun.bukkit.dsp.dev;
import java.io.File;

import org.densyakun.bukkit.dsp.Main;
public class DSPDev {
	public static final String ver = "0.1.10";
	public static final File dir = new File(Main.dir, "Dev/");
	public Main main;
	public RobotManager robotmanager;
	public DSPDev(Main main) {
		this.main = main;
		dir.mkdirs();
		robotmanager = new RobotManager(this);
	}
	public void stop() {
		robotmanager.run = false;
	}
}
