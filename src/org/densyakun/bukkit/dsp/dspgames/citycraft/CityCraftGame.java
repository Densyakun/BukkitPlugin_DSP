package org.densyakun.bukkit.dsp.dspgames.citycraft;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.densyakun.bukkit.dsp.dspgames.DSPGames;
import org.densyakun.bukkit.dsp.dspgames.SingleGame;
import org.densyakun.bukkit.dsp.dspmenu.InventoryCityCraftMenu;
import org.densyakun.csvm.CSVFile;
public class CityCraftGame extends SingleGame implements Listener, Runnable {
	public static File dir = new File(DSPGames.dir, "CityCraft/");
	public File dir_p;
	public CSVFile datafile;
	public CSVFile mapfile;
	public CSVFile buildsfile;
	public CSVFile companyfile;
	public Land[][]map = new Land[1024][1024];
	public List<Build>builds = new ArrayList<Build>();
	public List<Company>companys = new ArrayList<Company>();
	public List<People>peoples = new ArrayList<People>();
	public long money = 10000000000L;
	public CityCraftGame(DSPGames dspga, Player player) {
		super("-CityCraft-", dspga, player);
		dir_p = new File(dir, new StringBuffer(player.getName()).append("/").toString());
		dir_p.mkdirs();
		datafile = new CSVFile(new File(dir_p, "data.csv"));
		if (datafile.getFile().exists()) {
			try {
				List<List<String>>a = datafile.AllRead();
				money = Long.valueOf(a.get(0).get(0));
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				datafile.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mapfile = new CSVFile(new File(dir_p, "map.csv"));
		for (int a = 0; a < map.length; a++) {
			for (int b = 0; b < map[a].length; b++) {
				map[a][b] = new Land(this, a, b, LandType.air);
			}
		}
		if (mapfile.getFile().exists()) {
			try {
				List<List<String>>a = mapfile.AllRead();
				for (int b = 0; b < a.size(); b++) {
					map[Integer.valueOf(a.get(b).get(0))][Integer.valueOf(a.get(b).get(1))] = new Land(this, Integer.valueOf(a.get(b).get(0)), Integer.valueOf(a.get(b).get(1)), LandType.valueOf(a.get(b).get(2)));
				}
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				mapfile.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		buildsfile = new CSVFile(new File(dir_p, "builds.csv"));
		if (buildsfile.getFile().exists()) {
			try {
				List<List<String>>a = buildsfile.AllRead();
				for (int b = 0; b < a.size(); b++) {
					List<Land>c = new ArrayList<Land>();
					for (int d = 1; (d + 1) < a.get(b).size(); d += 2) {
						c.add(map[Integer.valueOf(a.get(b).get(d))][Integer.valueOf(a.get(b).get(d + 1))]);
					}
					builds.add(new Build(this, BuildType.valueOf(a.get(b).get(0)), c));
					c = null;
				}
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				buildsfile.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		companyfile = new CSVFile(new File(dir_p, "companys.csv"));
		if (companyfile.getFile().exists()) {
			try {
				List<List<String>>a = companyfile.AllRead();
				for (int b = 0; b < a.size(); b++) {
					List<Land>c = new ArrayList<Land>();
					for (int d = 1; (d + 1) < a.get(b).size(); d += 2) {
						c.add(map[Integer.valueOf(a.get(b).get(d))][Integer.valueOf(a.get(b).get(d + 1))]);
					}
					//companys.add(new Company(this, c, d, e));
					c = null;
				}
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				companyfile.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		buildsfile = new CSVFile(new File(dir_p, "builds.csv"));
		if (buildsfile.getFile().exists()) {
			try {
				List<List<String>>a = buildsfile.AllRead();
				for (int b = 0; b < a.size(); b++) {
					List<Land>c = new ArrayList<Land>();
					for (int d = 1; (d + 1) < a.get(b).size(); d += 2) {
						c.add(map[Integer.valueOf(a.get(b).get(d))][Integer.valueOf(a.get(b).get(d + 1))]);
					}
					builds.add(new Build(this, BuildType.valueOf(a.get(b).get(0)), c));
					c = null;
				}
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				buildsfile.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new Thread(this).start();
		dspga.main.getServer().getPluginManager().registerEvents(this, dspga.main);
	}
	public void save() {
		List<List<String>>a = new ArrayList<List<String>>();
		for (int b = 0; b < map.length; b++) {
			for (int c = 0; c < map[b].length; c++) {
				List<String>d = new ArrayList<String>();
				d.add(String.valueOf(b));
				d.add(String.valueOf(c));
				d.add(map[b][c].type.toString());
				a.add(d);
				d = null;
			}
		}
		try {
			mapfile.AllWrite(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a = new ArrayList<List<String>>();
		for (int b = 0; b < builds.size(); b++) {
			List<String>c = new ArrayList<String>();
			c.add(builds.get(b).type.toString());
			for (int d = 0; d < builds.get(b).lands.size(); d++) {
				c.add(String.valueOf(builds.get(b).lands.get(d).x));
				c.add(String.valueOf(builds.get(b).lands.get(d).z));
			}
			a.add(c);
			c = null;
		}
		try {
			buildsfile.AllWrite(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a = null;
	}
	@Override
	public void gamestart() {
		super.gamestart();
		owner.openInventory(new InventoryCityCraftMenu(dspga.main.dspme).getInventory());
	}
	@Override
	public void gameend() {
		super.gameend();
		save();
		owner.closeInventory();
	}
	@Override
	public void run() {
		while (play == 1) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
