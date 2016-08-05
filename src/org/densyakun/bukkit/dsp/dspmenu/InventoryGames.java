package org.densyakun.bukkit.dsp.dspmenu;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dspgames.Game;
import org.densyakun.bukkit.dsp.dspgames.MultiGame;
import org.densyakun.bukkit.dsp.dspgames.SingleGame;
public class InventoryGames extends MenuInventory {
	DSPMenu dspme;
	public InventoryGames(DSPMenu dspme) {
		super(dspme, 9, "メニュー > ミニゲーム");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.WOOL);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("新しいミニゲーム").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.WOOL, 1, (short) 1);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("エントリーまたはプレイ中のミニゲーム").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		ItemStack item2 = new ItemStack(Material.FENCE_GATE);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("エントリーまたはプレイ中のミニゲームをやめる").toString());
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
		ItemStack item3 = new ItemStack(Material.WOOL, 1, (short) 2);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("エントリーまたはプレイ中のミニゲームの操作").toString());
		item3.setItemMeta(item3meta);
		item3meta = null;
		inv.setItem(4, item3);
		item3 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 1:
			e.getWhoClicked().openInventory(new InventoryGamesNew(dspme).getInventory());
			break;
		case 2:
			e.getWhoClicked().openInventory(new InventoryGamesNow(dspme).getInventory());
			break;
		case 3:
			if (e.getWhoClicked() instanceof Player) {
				List<Game>games = dspme.main.dspga.games;
				for (int a = 0; a < games.size(); a++) {
					if (games.get(a).owner.getName().equals(((Player) e.getWhoClicked()).getName())) {
						if (games.get(a) instanceof SingleGame) {
							games.get(a).gameend();
							((Player)e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ミニゲームから抜けました").toString());
						} else if (games.get(a) instanceof MultiGame) {
							((MultiGame) games.get(a)).removePlayer(((Player) e.getWhoClicked()).getName());
							((Player)e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ミニゲームから抜けました").toString());
						}
					} else {
						if (games.get(a) instanceof MultiGame) {
							List<Player>players = ((MultiGame) games.get(a)).getPlayers();
							for (int b = 0; b < players.size(); b++) {
								if (players.get(b).getName().equals(((Player)e.getWhoClicked()).getName())) {
									((MultiGame) games.get(a)).removePlayer(players.get(b).getName());
									e.getWhoClicked().closeInventory();
									((Player)e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ミニゲームから抜けました").toString());
									break;
								}
							}
							players = null;
						}
					}
				}
				games = null;
				e.getWhoClicked().closeInventory();
				((Player)e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("エントリーまたはプレイ中のミニゲームがありません").toString());
			}
			break;
		case 4:
			if (e.getWhoClicked() instanceof Player) {
				if (!dspme.main.dspga.GameInit((Player) e.getWhoClicked())) {
					List<Game>games = dspme.main.dspga.games;
					for (int a = 0; a < games.size(); a++) {
						if (games.get(a) instanceof SingleGame) {
							if (games.get(a).owner.getName().equals(((Player) e.getWhoClicked()).getName())) {
								e.getWhoClicked().openInventory(new InventoryGamesAction(dspme, games.get(a)).getInventory());
							}
						} else if (games.get(a) instanceof MultiGame) {
							if (games.get(a).owner.getName().equals(((Player) e.getWhoClicked()).getName())) {
								e.getWhoClicked().openInventory(new InventoryGamesAction(dspme, games.get(a)).getInventory());
							} else {
								List<Player>players = ((MultiGame) dspme.main.dspga.games.get(a)).getPlayers();
								for (int b = 0; b < players.size(); b++) {
									if (players.get(b).getName().equals(((Player) e.getWhoClicked()).getName())) {
										e.getWhoClicked().openInventory(new InventoryGamesAction(dspme, games.get(a)).getInventory());
									}
								}
							}
						}
					}
				} else {
					e.getWhoClicked().closeInventory();
					((Player)e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("エントリーまたはプレイ中のミニゲームがありません").toString());
				}
			}
			break;
		default:
			break;
		}
	}
}
