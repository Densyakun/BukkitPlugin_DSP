package org.densyakun.bukkit.dsp.dsprouting;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.densyakun.bukkit.dsp.Main;
import org.densyakun.csvm.CSVFile;
public class DSPRouting implements Runnable, Listener {
	public Main main;
	public CSVFile pcsv = new CSVFile(new File(Main.dir, "Points.csv"));
	public CSVFile ccsv = new CSVFile(new File(Main.dir, "Connects.csv"));
	public List<Point>points = new ArrayList<Point>();
	public List<Connect>connects = new ArrayList<Connect>();
	public List<Router>routers = new ArrayList<Router>();
	public List<PlayerAndPoint>pap = new ArrayList<PlayerAndPoint>();
	public DSPRouting(Main main) {
		this.main = main;
		if (pcsv.getFile().exists()) {
			try {
				List<List<String>>a = pcsv.AllRead();
				for (int b = 0; b < a.size(); b++) {
					points.add(new Point(a.get(b).get(0), new Location(main.dspwo.world_kyozyuku, Integer.valueOf(a.get(b).get(1)), Integer.valueOf(a.get(b).get(2)), Integer.valueOf(a.get(b).get(3)))));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				pcsv.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ccsv.getFile().exists()) {
			try {
				List<List<String>>a = ccsv.AllRead();
				for (int b = 0; b < a.size(); b++) {
					Point p0 = null;
					Point p1 = null;
					for (int c = 0; c < points.size(); c++) {
						if (points.get(c).name.equals(a.get(b).get(0))) {
							p0 = points.get(c);
						}
						if (points.get(c).name.equals(a.get(b).get(1))) {
							p1 = points.get(c);
						}
					}
					connects.add(new Connect(p0, p1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ccsv.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new Thread(this).start();
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	public Point SearchPoint(String name) {
		for (int a = 0; a < points.size(); a++) {
			if (points.get(a).name.indexOf(name) != -1) {
				return points.get(a);
			}
		}
		return null;
	}
	public Route GenerateRoute(Point start, Point end) {
		Route a = null;
		List<Route>b = new ArrayList<Route>();
		for (int c = 0; c < connects.size(); c++) {
			if (start.name.equals(connects.get(c).p0.name) || start.name.equals(connects.get(c).p1.name)) {
				List<Point>d = new ArrayList<Point>();
				d.add(start);
				b.add(a(start, end, d));
				d = null;
			}
		}
		for (int c = 0; c < b.size(); c++) {
			if (b.get(c) != null) {
				if (a != null) {
					if (b.get(c).getDistance() < a.getDistance()) {
						a = b.get(c);
					}
				} else {
					a = b.get(0);
				}
			}
		}
		return a;
	}
	private Route a(Point a, Point b, List<Point>c) {
		for (int d = 0; d < connects.size(); d++) {
			if (a.name.equals(connects.get(d).p0.name)) {
				boolean f = false;
				for (int e = 0; e < c.size(); e++) {
					if (c.get(e).name.equals(connects.get(d).p1)) {
						f = true;
					}
				}
				if (f) {
					continue;
				}
				c.add(connects.get(d).p1);
				if (connects.get(d).p1.name.equals(b.name)) {
					return new Route(c);
				} else {
					return a(connects.get(d).p1, b, c);
				}
			} else if (a.name.equals(connects.get(d).p1.name)) {
				boolean f = false;
				for (int e = 0; e < c.size(); e++) {
					if (c.get(e).name.equals(connects.get(d).p0)) {
						f = true;
					}
				}
				if (f) {
					continue;
				}
				c.add(connects.get(d).p0);
				if (connects.get(d).p0.name.equals(b.name)) {
					return new Route(c);
				} else {
					return a(connects.get(d).p0, b, c);
				}
			}
		}
		return null;
	}
	public Router getRouter(Player player) {
		for (int a = 0; a < routers.size(); a++) {
			if (routers.get(a).player.getName().equals(player.getName())) {
				return routers.get(a);
			}
		}
		return null;
	}
	@Override
	public void run() {
	}
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e) {
		if (e.getPlayer().getWorld().getName().equals(main.dspwo.world_kyozyuku.getName())) {
			Point a = null;
			for (int b = 0; b < points.size(); b++) {
				if (a != null) {
					if (points.get(b).location.distance(e.getPlayer().getLocation()) < a.location.distance(e.getPlayer().getLocation())) {
						a = points.get(b);
					}
				} else {
					a = points.get(b);
				}
			}
			if (a != null) {
				if (a.location.distance(e.getPlayer().getLocation()) <= 4) {
					boolean b = true;
					for (int c = 0; c < pap.size(); c++) {
						if (pap.get(c).player.getName().equals(e.getPlayer().getName())) {
							if (!pap.get(c).point.name.equals(a.name)) {
								pap.get(c).point = a;
								e.getPlayer().sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.GREEN.toString()).append("現在地: ").append(a.name).toString());
								Router d = getRouter(e.getPlayer());
								if ((d != null) && d.route.points.get(d.point).equals(a)) {
									d.nextPoint();
								}
								d = null;
							}
							b = false;
						}
					}
					if (b) {
						pap.add(new PlayerAndPoint(e.getPlayer(), a));
					}
				}
			}
		}
	}
}
