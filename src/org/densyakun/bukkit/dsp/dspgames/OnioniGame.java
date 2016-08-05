package org.densyakun.bukkit.dsp.dspgames;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.densyakun.csvm.CSVFile;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class OnioniGame extends MultiGame implements Listener {
	public static final String name = "onioni(Ver: 2.0)";
	public boolean oni_play = false;
	private List<Player>playersok = new ArrayList<Player>();
	private List<List<Object>>playersrank = new ArrayList<List<Object>>();
	public static CSVFile levels = new CSVFile(new File(DSPGames.dir, "OnioniGame-Levels.csv"));
	public OnioniGame(Player oni, DSPGames dspga) {
		super(name, dspga, oni);
		if (!levels.getFile().exists()) {
			try {
				levels.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setMaxplayers(0);
		setQuitend(true);
		if (oni == null) {
			setStartable(false);
		} else {
			dspga.main.getServer().getPluginManager().registerEvents(this, dspga.main);
		}
	}
	public boolean playerok(Player player) {
		List<Player>a = getPlayers();
		for (int b = 0; b < a.size(); b++) {
			if (player.getName().equals(a.get(b).getName())) {
				for (int c = 0; c < playersok.size(); c++) {
					if (playersok.get(c).getName().equals(player.getName())) {
						return false;
					}
				}
				playersok.add(player);
				if (a.size() == playersok.size()) {
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("子が全員隠れたため鬼が動き出しました").toString());
					for (int c = 0; c < a.size(); c++) {
						a.get(c).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("子が全員隠れたため鬼が動き出しました").toString());
					}
					oni_play = true;
				}
				a = null;
				return true;
			}
		}
		a = null;
		return false;
	}
	@Override
	public boolean removePlayer(String name) {
		if (super.removePlayer(name)) {
			if (name.equals(owner.getName())) {
				gameend();
			} else {
				for (int a = 0; a < playersok.size(); a++) {
					if (playersok.get(a).getName().equals(name)) {
						playersok.remove(a);
					}
				}
			}
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
					}
				}
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
					}
				}
			}
		}
	}
	@EventHandler
	public void PlayerInteractEntity(PlayerInteractEntityEvent e) {
		if ((play == 1) && (e.getPlayer().getName().equals(owner.getName())) && (e.getRightClicked() instanceof Player)) {
			List<Player>a = getPlayers();
			for (int b = 0; b < a.size(); b++) {
				if (a.get(b).getName().equals(((Player) e.getRightClicked()).getName())) {
					setLevel(e.getPlayer().getName(), getLevel(e.getPlayer().getName()) + 0.0001);
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(((Player) e.getRightClicked()).getName()).append(ChatColor.RED).append("さんを捕まえた!").toString());
					for (int c = 0; c < a.size(); c++) {
						a.get(c).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(((Player) e.getRightClicked()).getName()).append(ChatColor.RED).append("さんが鬼に捕まりました").toString());
					}
					List<Object>c = new ArrayList<Object>();
					c.add(((Player) e.getRightClicked()).getName());
					c.add(playersok.size());
					playersrank.add(c);
					c = null;
					removePlayer(((Player) e.getRightClicked()).getName());
				}
			}
		}
	}
	@EventHandler
	public void EntityDamage(EntityDamageEvent e) {
		if ((play == 1) && (e.getEntity() instanceof Player)) {
			if ((((Player) e.getEntity()).getName()).equals(owner.getName())) {
				e.setCancelled(true);
			}
			List<Player>a = getPlayers();
			for (int b = 0; b < a.size(); b++) {
				if (a.get(b).getName().equals(((Player) e.getEntity()).getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		if (play == 1) {
			List<Player>a = getPlayers();
			for (int b = 0; b < a.size(); b++) {
				if (a.get(b).getName().equals(e.getEntity().getName())) {
					owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(e.getEntity().getName()).append(ChatColor.RED).append("さんが死んだため負けました").toString());
					for (int c = 0; c < a.size(); c++) {
						a.get(c).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD).append(e.getEntity().getName()).append(ChatColor.RED).append("さんが死んだため負けました").toString());
					}
					removePlayer(e.getEntity().getName());
				}
			}
		}
	}
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e) {
		if (play == 1) {
			if (e.getPlayer().getName().equals(owner.getName())) {
				if (oni_play) {
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 1));
					setLevel(e.getPlayer().getName(), getLevel(e.getPlayer().getName()) + 0.0001);
				} else {
					e.setCancelled(true);
				}
			} else {
				for (int c = 0; c < playersok.size(); c++) {
					if (playersok.get(c).getName().equals(e.getPlayer().getName())) {
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 1));
						setLevel(e.getPlayer().getName(), getLevel(e.getPlayer().getName()) + 0.0001);
					}
				}
			}
		}
	}
	@Override
	public void gameend() {
		super.gameend();
		for (int a = 0; a < playersrank.size(); a++) {
			if ((playersrank.get(a).get(0) instanceof String) && (playersrank.get(a).get(1) instanceof Integer)) {
				try {
					DensyakunPoint dp = new DensyakunPoint((String) playersrank.get(a).get(0));
					dp.setPoint(dp.getPoint() + (((10*playersrank.size())/((Integer) playersrank.get(a).get(1))) * Math.floor(getLevel(dp.name))));
					setLevel(dp.name, getLevel(dp.name) + (((10*playersrank.size())/((Integer) playersrank.get(a).get(1))) / 1000));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
	@Override
	public void gamestart() {
		super.gamestart();
		List<Player>a = getPlayers();
		owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GREEN.toString()).append("onioniレベル: ").append(ChatColor.GOLD.toString()).append((int) Math.floor(getLevel(owner.getName()))).append(" 経験値: ").append(new DecimalFormat("00").format(getLevel(owner.getName()) - (int) Math.floor(getLevel(owner.getName())))).append(" / 100").toString());
		for (int b = 0; b < a.size(); b++) {
			a.get(b).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPGa]").append(ChatColor.GREEN.toString()).append("onioniレベル: ").append(ChatColor.GOLD.toString()).append((int) Math.floor(getLevel(a.get(b).getName()))).append(" 経験値: ").append(new DecimalFormat("00").format(getLevel(a.get(b).getName()) - (int) Math.floor(getLevel(a.get(b).getName())))).append(" / 100").toString());
		}
		a = null;
	}
}
