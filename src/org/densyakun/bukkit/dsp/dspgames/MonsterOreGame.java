package org.densyakun.bukkit.dsp.dspgames;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.densyakun.csvm.CSVFile;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class MonsterOreGame extends SingleGame implements Listener {
	public static final String name = "Monster-<<<Ore>>>";
	public static CSVFile levels = new CSVFile(new File(DSPGames.dir, "MonsterOre-Levels.csv"));
	public MonsterOreGame(DSPGames dspga, Player player) {
		super(name, dspga, player);
		if (!levels.getFile().exists()) {
			try {
				levels.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dspga.main.getServer().getPluginManager().registerEvents(this, dspga.main);
	}
	public static double getLevel(String name) {
		try {
			List<List<String>>a = levels.AllRead();
			for (int b = 0; b < a.size(); b++) {
				if (name.equals(a.get(b).get(0))) {
					return Double.valueOf(a.get(b).get(1));
				}
			}
			a = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public static void setLevel(String name, double level) {
		try {
			List<List<String>>a = levels.AllRead();
			boolean c = true;
			for (int b = 0; b < a.size(); b++) {
				if (name.equals(a.get(b).get(0))) {
					c = false;
					a.get(b).set(1, String.valueOf(level));
					break;
				}
			}
			if (c) {
				List<String>b = new ArrayList<String>();
				b.add(name);
				b.add(String.valueOf(level));
				a.add(b);
				b = null;
				c = false;
			}
			levels.AllWrite(a);
			a = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@EventHandler
	public void EntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (play == 1) {
			if ((e.getDamager() instanceof Player) && (((Player) e.getDamager()).getName().equals(owner.getName()))) {
				if (e.getEntity() instanceof Player) {
					e.setCancelled(true);
				} else {
					switch (e.getEntity().getType()) {
					case CREEPER:
					case PIG_ZOMBIE:
					case SKELETON:
					case SLIME:
					case SPIDER:
					case ZOMBIE:
						setLevel(owner.getName(), getLevel(owner.getName()) + 0.01);
						break;
					case BLAZE:
					case CAVE_SPIDER:
					case ENDERMAN:
					case GHAST:
					case GIANT:
					case IRON_GOLEM:
					case MAGMA_CUBE:
					case SILVERFISH:
					case WITCH:
					case WITHER_SKULL:
						setLevel(owner.getName(), getLevel(owner.getName()) + 0.05);
						break;
					case WITHER:
						setLevel(owner.getName(), getLevel(owner.getName()) + 0.5);
						break;
					case ENDER_DRAGON:
						setLevel(owner.getName(), getLevel(owner.getName()) + 1);
						break;
					default:
						break;
					}
				}
			}
		}
	}
	@EventHandler
	public void EntityDeath(EntityDeathEvent e) {
		if (play == 1) {
			if ((e.getEntity().getKiller() != null) && (((Player) e.getEntity().getKiller()).getName().equals(owner.getName()))) {
				if (!(e.getEntity() instanceof Player)) {
					try {
						DensyakunPoint a = new DensyakunPoint(e.getEntity().getKiller().getName());
						a.setPoint(a.getPoint() + 10);
					} catch (IOException x) {
						x.printStackTrace();
					}
					int a = new Random().nextInt((int) (100 / Math.floor(getLevel(owner.getName()))));
					if (a == 0) {
						switch (new Random().nextInt((int) (10 + (100 / Math.floor(getLevel(owner.getName())))))) {
						case 0:
						case 1:
						case 2:
							e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.IRON_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							break;
						case 3:
						case 4:
							e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GOLD_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							break;
						case 5:
							e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.LAPIS_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							break;
						default:
							e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COAL_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							break;
						}
						if (10 <= Math.floor(getLevel(owner.getName()))) {
							if (new Random().nextInt((int) (100 / Math.floor(getLevel(owner.getName())))) == 0) {
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.IRON_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GOLD_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.LAPIS_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COAL_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.DIAMOND_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							}
						}
						if (50 <= Math.floor(getLevel(owner.getName()))) {
							if (new Random().nextInt((int) (100 / Math.floor(getLevel(owner.getName())))) == 0) {
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.DIAMOND_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.EMERALD_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							}
						}
						if (50 <= Math.floor(getLevel(owner.getName()))) {
							if (new Random().nextInt((int) (100 / Math.floor(getLevel(owner.getName())))) == 0) {
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.EMERALD_ORE, new Random().nextInt((int) (1 + (Math.floor(getLevel(owner.getName())) * 2)))));
							}
						}
					}
					a = 0;
				}
			}
		}
	}
	@Override
	public void gamestart() {
		super.gamestart();
		owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GREEN.toString()).append("レベル: ").append(ChatColor.GOLD.toString()).append(Math.floor(getLevel(owner.getName()))).append(" 経験値: ").append(new DecimalFormat("00").format(getLevel(owner.getName()) - (int) Math.floor(getLevel(owner.getName())))).append(" / 100").toString());
	}
}
