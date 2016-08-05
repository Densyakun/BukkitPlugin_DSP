package org.densyakun.bukkit.dsp.dspgames;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
public class TosochuMultiGame extends MultiGame implements Runnable {
	public static final String name = "-逃走中(マルチ)-";
	public List<PigZombie>hunters = new ArrayList<PigZombie>();
	public int second;
	public TosochuMultiGame(DSPGames dspga, Player owner) {
		super(name, dspga, owner);
		setMinplayers(1);
		setMaxplayers(0);
	}
	@Override
	public void gamestart() {
		super.gamestart();
		List<Player>c = getPlayers();
		for (int a = 0; a < (c.size() + 1); a++) {
			Player d = null;
			if (a == 0) {
				d = owner;
			} else {
				d = c.get(a - 1);
			}
			switch (new Random().nextInt(4)) {
			case 0:
				d.teleport(new Location(owner.getWorld(), owner.getWorld().getSpawnLocation().getX() - 5, owner.getWorld().getSpawnLocation().getY(), owner.getWorld().getSpawnLocation().getZ()));
				break;
			case 1:
				d.teleport(new Location(owner.getWorld(), owner.getWorld().getSpawnLocation().getX(), owner.getWorld().getSpawnLocation().getY(), owner.getWorld().getSpawnLocation().getZ() - 5));
				break;
			case 2:
				d.teleport(new Location(owner.getWorld(), owner.getWorld().getSpawnLocation().getX() + 5, owner.getWorld().getSpawnLocation().getY(), owner.getWorld().getSpawnLocation().getZ()));
				break;
			default:
				d.teleport(new Location(owner.getWorld(), owner.getWorld().getSpawnLocation().getX(), owner.getWorld().getSpawnLocation().getY(), owner.getWorld().getSpawnLocation().getZ() - 5));
				break;
			}
			d = null;
		}
		for (int a = 0; a < (c.size() + 1); a++) {
			Entity b = owner.getWorld().spawnEntity(owner.getWorld().getSpawnLocation(), EntityType.PIG_ZOMBIE);
			if (b instanceof PigZombie) {
				((PigZombie) b).setAngry(true);
				((LivingEntity) b).setCustomName("ハンター");
				((LivingEntity) b).setCustomNameVisible(true);
				hunters.add((PigZombie) b);
			} else {
				b.remove();
			}
			b = null;
		}
		second = 300 * (getPlayers().size() + 1);
		new Thread(this).start();
	}
	@Override
	public void gameend() {
		super.gameend();
		for (int a = 0; a < hunters.size(); a++) {
			hunters.remove(a).remove();
		}
	}
	@Override
	public boolean removePlayer(String name) {
		if (super.removePlayer(name)) {
			owner.getWorld().playSound(owner.getLocation(), Sound.GLASS, 2, 0);
			owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.AQUA).append(TosochuMultiGame.name).append(" 報告\n").append(name).append("を確保").toString());
			List<Player>a = getPlayers();
			for (int b = 0; b < a.size(); b++) {
				a.get(b).getWorld().playSound(a.get(b).getLocation(), Sound.GLASS, 2, 0);
				a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.AQUA).append(TosochuMultiGame.name).append(" 報告\n").append(name).append("を確保").toString());
			}
			a = null;
			return true;
		}
		return false;
	}
	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		if (play == 1) {
			if (e.getPlayer().getName().equals(owner.getName())) {
				e.setCancelled(true);
			} else {
				List<Player>a = getPlayers();
				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).getName().equals(e.getPlayer().getName())) {
						e.setCancelled(true);
						break;
					}
				}
				a = null;
			}
		}
	}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e) {
		if (play == 1) {
			if (e.getPlayer().getName().equals(owner.getName())) {
				e.setCancelled(true);
			} else {
				List<Player>a = getPlayers();
				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).getName().equals(e.getPlayer().getName())) {
						e.setCancelled(true);
						break;
					}
				}
				a = null;
			}
		}
	}
	@EventHandler
	public void EntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (play == 1) {
			if (e.getEntity() instanceof Player) {
				boolean c = false;
				if (((Player) e.getEntity()).getName().equals(owner.getName())) {
					c = true;
				} else {
					List<Player>a = getPlayers();
					for (int b = 0; b < a.size(); b++) {
						if (a.get(b).getName().equals(((Player) e.getEntity()).getName())) {
							c = true;
							break;
						}
					}
					a = null;
				}
				if (c) {
					e.setCancelled(true);
					if ((e.getDamager() instanceof PigZombie) && ((PigZombie) e.getDamager()).getCustomName().equals("ハンター")) {
						e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ANVIL_LAND, 2, 0);
						removePlayer(((Player) e.getEntity()).getName());
					}
				}
			} else if ((e.getEntity() instanceof PigZombie) && ((PigZombie) e.getEntity()).getCustomName().equals("ハンター")) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		if (play == 1) {
			if (e.getEntity().getName().equals(owner.getName())) {
				removePlayer(e.getEntity().getName());
			} else {
				List<Player>a = getPlayers();
				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).getName().equals(e.getEntity().getName())) {
						removePlayer(e.getEntity().getName());
						break;
					}
				}
				a = null;
			}
		}
	}
	@EventHandler
	public void EntityTargetLivingEntity(EntityTargetLivingEntityEvent e) {
		if (play == 1) {
			if (e.getEntity() instanceof PigZombie) {
				if (e.getTarget() instanceof Player) {
					if (!((Player) e.getTarget()).getName().equals(owner.getName())) {
						boolean b = true;
						List<Player>a = getPlayers();
						for (int c = 0; c < a.size(); c++) {
							if (a.get(c).getName().equals(((Player) e.getTarget()).getName())) {
								b = false;
								break;
							}
						}
						a = null;
						if (b) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
	@Override
	public void run() {
		while (true) {
			if (play == 2) {
				break;
			}
			second--;
			if (((300 * (getPlayers().size() + 1)) - 10) < second) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム開始まで: ").append(second - ((300 * (getPlayers().size() + 1)) - 10)).append("秒").toString());
				}
				a = null;
			} else if (second == ((300 * (getPlayers().size() + 1)) - 10)) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム開始").toString());
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 60) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 30) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 15) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 10) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 9) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 8) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 7) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 6) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 5) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 4) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 3) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 2) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 1) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了まで: ").append(second).append("秒").toString());
				}
				a = null;
			} else if (second == 0) {
				List<Player> a = getPlayers();
				a.add(owner);
				for (int b = 0; b < a.size(); b++) {
					a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GOLD.toString()).append("ゲーム終了").toString());
				}
				a = null;
				gameend();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
