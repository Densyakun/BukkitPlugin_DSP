package org.densyakun.bukkit.dsp.dspgames;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
public class PvPGame extends MultiGame {
	public static final String name = "PvP(1vs1)";
	public PvPGame(DSPGames dspga, Player owner) {
		super(name, dspga, owner);
		setMinplayers(2);
		setMaxplayers(2);
		setQuitend(true);
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
			boolean c = e.getDamager() instanceof Player;
			if (c) {
				c = ((Player) e.getDamager()).getName().equals(owner.getName());
				if (!c) {
					List<Player>a = getPlayers();
					for (int b = 0; b < a.size(); b++) {
						if (a.get(b).getName().equals(((Player) e.getDamager()).getName())) {
							c = false;
							break;
						}
					}
					a = null;
				}
			} else if (!(e.getDamager() instanceof LivingEntity)) {
				c = true;
			}
			if (!c) {
				c = ((Player) e.getEntity()).getName().equals(owner.getName());
				if (!c) {
					List<Player>a = getPlayers();
					for (int b = 0; b < a.size(); b++) {
						if (a.get(b).getName().equals(((Player) e.getEntity()).getName())) {
							c = true;
							break;
						}
					}
					a = null;
				}
				if (!c) {
					e.setCancelled(true);
				}
			} else {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		if (play == 1) {
			boolean c = false;
			if (e.getEntity().getName().equals(owner.getName())) {
				c = true;
			} else {
				List<Player>a = getPlayers();
				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).getName().equals(e.getEntity().getName())) {
						c = true;
						break;
					}
				}
				a = null;
			}
			if (c) {
				if (e.getEntity().getKiller() != null) {
					owner.getWorld().playSound(owner.getLocation(), Sound.ANVIL_USE, 2, 0);
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(e.getEntity().getKiller().getDisplayName()).append(ChatColor.AQUA).append("は").append(e.getEntity().getDisplayName()).append(ChatColor.AQUA).append("と対戦して勝利した").toString());
					List<Player>a = getPlayers();
					for (int b = 0; b < a.size(); b++) {
						a.get(b).getWorld().playSound(a.get(b).getLocation(), Sound.ANVIL_USE, 2, 0);
						a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(e.getEntity().getKiller().getDisplayName()).append(ChatColor.AQUA).append("は").append(e.getEntity().getDisplayName()).append(ChatColor.AQUA).append("と対戦して勝利した").toString());
					}
					a = null;
				}
				removePlayer(e.getEntity().getName());
				gameend();
			}
		}
	}
}
