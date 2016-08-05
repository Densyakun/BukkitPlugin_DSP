package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dspgames.Game;
import org.densyakun.bukkit.dsp.dspgames.MultiGame;
import org.densyakun.bukkit.dsp.dspgames.OnioniGame;
public class InventoryCityCraftMenu extends MenuInventory {
	DSPMenu dspme;
	public InventoryCityCraftMenu(DSPMenu dspme) {
		super(dspme, 54, "CityCraft - トップ");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.MAP);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("マップ").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		reload();
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 0:
			break;
		case 1:
			reload();
			break;
		default:
			if (e.getWhoClicked() instanceof Player) {
				if (e.getInventory().getItem(e.getRawSlot()) != null) {
					Game game = dspme.main.dspga.games.get(e.getRawSlot() - 2);
					if ((game != null) && (game instanceof MultiGame)) {
						if (dspme.main.dspga.GameInit((Player) e.getWhoClicked()) && ((MultiGame) game).addPlayer((Player)e.getWhoClicked())) {
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("ゲームにエントリーしました").toString());
						} else {
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED).append("ゲームに参加できませんでした").toString());
						}
					}
				}
			}
			break;
		}
	}
	public void reload() {
		int n = 0;
		List<Game>games = dspme.main.dspga.games;
		for (int a = 0; a < games.size(); a++) {
			if (games.get(a).play == 0) {
				if (games.get(a) instanceof MultiGame) {
					ItemStack item = null;
					if (games.get(a).name.equals(OnioniGame.name)) {
						item = new ItemStack(Material.IRON_BOOTS);
					}
					ItemMeta itemmeta = item.getItemMeta();
					itemmeta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append(games.get(a).name).toString());
					List<String>itemlore = new ArrayList<String>();
					String b = String.valueOf((((MultiGame) games.get(a)).getMaxplayers()) + 1);
					if (Integer.valueOf(b)  == 0) {
						b = "人";
					} else {
						b = "/" + b;
					}
					itemlore.add(new StringBuffer(ChatColor.GOLD.toString()).append(((MultiGame) games.get(a)).getPlayers().size() + 1).append(b).toString());
					b = null;
					List<Player>c = ((MultiGame) games.get(a)).getPlayers();
					for (int d = 0; d < c.size(); d++) {
						itemlore.add(new StringBuffer(ChatColor.GREEN.toString()).append(c.get(d).getDisplayName()).toString());
					}
					c = null;
					itemmeta.setLore(itemlore);
					itemlore = null;
					item.setItemMeta(itemmeta);
					itemmeta = null;
					inv.setItem(2 + n, item);
					item = null;
				}
				n++;
				if (inv.getSize() <= n) {
					break;
				}
			}
		}
	}
}
