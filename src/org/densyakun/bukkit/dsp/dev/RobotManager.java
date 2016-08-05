package org.densyakun.bukkit.dsp.dev;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.densyakun.csvm.CSVFile;
public class RobotManager implements Runnable, Listener {
	public DSPDev dspd;
	public boolean run = true;
	public CSVFile csv = new CSVFile(new File(DSPDev.dir, "Dev.csv"));
	public UUID id;
	public Sheep robot;
	public RobotManager(DSPDev dspd) {
		this.dspd = dspd;
		if (!csv.getFile().exists()) {
			try {
				csv.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			List<List<String>> a = csv.AllRead();
			if ((a.size() == 1) && (a.get(0).size() == 2)) {
				id = new UUID(Long.valueOf(a.get(0).get(0)), Long.valueOf(a.get(0).get(1)));
			}
			a = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (id != null) {
			List<Entity> a = dspd.main.dspwo.world_kyozyuku.getEntities();
			for (int b = 0; b < a.size(); b++) {
				if (a.get(b) instanceof Sheep) {
					if (a.get(b).getUniqueId().equals(id)) {
						robot = (Sheep) a.get(b);
						break;
					}
				}
			}
			a = null;
			if (robot == null) {
				Entity b = dspd.main.dspwo.world_kyozyuku.spawnEntity(dspd.main.dspwo.world_kyozyuku.getSpawnLocation(), EntityType.SHEEP);
				if (b != null) {
					if (b instanceof Sheep) {
						robot = (Sheep) b;
						id = b.getUniqueId();
						List<List<String>> c = new ArrayList<List<String>>();
						List<String> d = new ArrayList<String>();
						d.add(String.valueOf(id.getMostSignificantBits()));
						d.add(String.valueOf(id.getLeastSignificantBits()));
						c.add(d);
						d = null;
						try {
							csv.AllWrite(c);
						} catch (IOException e) {
							e.printStackTrace();
						}
						c = null;
						((Sheep) b).setCustomName("試運転開発用羊型ロボット");
						((Sheep) b).setCustomNameVisible(true);
					}
					b = null;
				}
			}
		} else {
			Entity a = dspd.main.dspwo.world_kyozyuku.spawnEntity(dspd.main.dspwo.world_kyozyuku.getSpawnLocation(), EntityType.SHEEP);
			if (a != null) {
				if (a instanceof Sheep) {
					robot = (Sheep) a;
					id = a.getUniqueId();
					List<List<String>> b = new ArrayList<List<String>>();
					List<String> c = new ArrayList<String>();
					c.add(String.valueOf(id.getMostSignificantBits()));
					c.add(String.valueOf(id.getLeastSignificantBits()));
					b.add(c);
					c = null;
					try {
						csv.AllWrite(b);
					} catch (IOException e) {
						e.printStackTrace();
					}
					b = null;
				}
				a = null;
			}
		}
		dspd.main.getServer().getPluginManager().registerEvents(this, dspd.main);
		new Thread(this).start();
	}
	@Override
	public void run() {
		while (run) {
			if (robot != null) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@EventHandler
	public void EntityDeath(EntityDeathEvent e) {
		if (e.getEntity().getUniqueId().equals(id)) {
			if (e.getEntity().getKiller() != null) {
				e.getEntity().getKiller().sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPD]").append(ChatColor.AQUA.toString()).append("殺したな!?でも大丈夫!再起動時にまた生まれます").toString());
			}
		}
	}
}
