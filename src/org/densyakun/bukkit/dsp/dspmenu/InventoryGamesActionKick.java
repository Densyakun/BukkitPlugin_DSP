package org.densyakun.bukkit.dsp.dspmenu;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dspgames.MultiGame;
public class InventoryGamesActionKick extends MenuInventory {
	DSPMenu dspme;
	MultiGame game;
	public InventoryGamesActionKick(DSPMenu dspme, MultiGame game) {
		super(dspme, 54, "...またはプレイ中のミニゲームの操作 > 参加者を退出させる");
		this.dspme = dspme;
		this.game = game;
		
		ItemStack item0 = new ItemStack(Material.BEACON);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("再読み込み").toString());
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
					if (game.removePlayer(e.getInventory().getItem(e.getRawSlot()).getItemMeta().getDisplayName())) {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA).append("プレイヤーを退出させました").toString());
					} else {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED).append("プレイヤーがいないか、退出できませんでした").toString());
					}
				}
			}
			break;
		}
	}
	public void reload() {
		int n = 0;
		List<Player>e = game.getPlayers();
		for (int a = 0; a < e.size(); a++) {
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append(e.get(a).getName()).toString());
			item.setItemMeta(itemmeta);
			itemmeta = null;
			inv.setItem(2 + n, item);
			item = null;
			n++;
			if (inv.getSize() <= n) {
				break;
			}
		}
	}
}
