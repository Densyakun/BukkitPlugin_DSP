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
import org.densyakun.bukkit.dsp.dspgames.MonsterOreGame;
import org.densyakun.bukkit.dsp.dspgames.MultiGame;
import org.densyakun.bukkit.dsp.dspgames.OnioniGame;
import org.densyakun.bukkit.dsp.dspgames.SingleGame;
public class InventoryGamesAction extends MenuInventory {
	DSPMenu dspme;
	Game game;
	public InventoryGamesAction(DSPMenu dspme, Game game) {
		super(dspme, 9, "ミニゲーム > エントリーまたはプレイ中のミニゲームの操作");
		this.dspme = dspme;
		this.game = game;
		
		ItemStack item0 = new ItemStack(Material.COMMAND);
		inv.setItem(1, item0);
		item0 = null;
		reload();
		
		ItemStack item1 = new ItemStack(Material.WOOL);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("ミニゲームを始める").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		
		ItemStack item2 = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("ミニゲームを止める").toString());
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
		
		ItemStack item3 = new ItemStack(Material.FENCE_GATE);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("参加者を退出させる").toString());
		item3.setItemMeta(item3meta);
		item3meta = null;
		inv.setItem(4, item3);
		item3 = null;
		
		if (game instanceof OnioniGame) {
			ItemStack item4 = new ItemStack(Material.LEATHER_BOOTS);
			ItemMeta item4meta = item4.getItemMeta();
			item4meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("onioni - もういいよ").toString());
			item4.setItemMeta(item4meta);
			item4meta = null;
			inv.setItem(5, item4);
			item4 = null;
		}
	}
	@Override
	public void Click(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			switch (e.getRawSlot()) {
			case 1:
				reload();
				break;
			case 2:
				if (((Player) e.getWhoClicked()).getName().equals(game.owner.getName())) {
					if (game.play == 0) {
						if (((MultiGame) game).isStartable()) {
							game.gamestart();
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("ミニゲームを開始しました").toString());
						} else {
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームは開始できません").toString());
						}
					} else {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームはすでに始まっています").toString());
					}
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームの管理人ではありません").toString());
				}
				break;
			case 3:
				if (((Player) e.getWhoClicked()).getName().equals(game.owner.getName())) {
					game.gameend();
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ミニゲームを終了しました").toString());
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームの管理人ではありません").toString());
				}
				break;
			case 4:
				if (((Player) e.getWhoClicked()).getName().equals(game.owner.getName())) {
					if (game instanceof MultiGame) {
						e.getWhoClicked().openInventory(new InventoryGamesActionKick(dspme, (MultiGame) game).getInventory());
					} else {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームはマルチではありません").toString());
					}
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("このミニゲームの管理人ではありません").toString());
				}
				break;
			case 5:
				if (game instanceof OnioniGame) {
					if (!((Player) e.getWhoClicked()).getName().equals(game.owner.getName())) {
						if (((OnioniGame) game).playerok((Player) e.getWhoClicked())) {
							e.getWhoClicked().closeInventory();
							game.owner.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(((Player) e.getWhoClicked()).getDisplayName()).append(ChatColor.AQUA.toString()).append("さんが隠れました").toString());
							List<Player>players = ((MultiGame) game).getPlayers();
							for (int a = 0; a < players.size(); a++) {
								players.get(a).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.GOLD.toString()).append(((Player) e.getWhoClicked()).getDisplayName()).append(ChatColor.AQUA.toString()).append("さんが隠れました").toString());
							}
						} else {
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("すでに隠れています").toString());
						}
					} else {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("鬼はこの操作を実行できません").toString());
					}
				}
				break;
			default:
				break;
			}
		}
	}
	public void reload() {
		ItemStack item0 = new ItemStack(Material.COMMAND);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("ゲームの状態(デベロッパー向け)").toString());
		List<String>item0lore = new ArrayList<String>();
		item0lore.add("クリックで再読み込みします");
		item0lore.add(new StringBuffer("Play: ").append(String.valueOf(game.play)).toString());
		item0lore.add(new StringBuffer("Name: ").append(String.valueOf(game.name)).toString());
		item0lore.add(new StringBuffer("Owner: ").append(game.owner.getDisplayName()).toString());
		if (game instanceof SingleGame) {
			if (game instanceof MonsterOreGame) {
				item0lore.add(new StringBuffer("Lv: ").append(String.valueOf(MonsterOreGame.getLevel(game.owner.getName()))).toString());
			}
		} else if (game instanceof MultiGame) {
			item0lore.add(new StringBuffer("Startable: ").append(String.valueOf(((MultiGame) game).isStartable())).toString());
			item0lore.add(new StringBuffer("Minplayers: ").append(String.valueOf(((MultiGame) game).getMinplayers())).toString());
			item0lore.add(new StringBuffer("Maxplayers: ").append(String.valueOf(((MultiGame) game).getMaxplayers())).toString());
			List<Player>players = ((MultiGame) game).getPlayers();
			for (int a = 0; a < players.size(); a++) {
				item0lore.add(new StringBuffer("Player").append(a).append(": ").append(players.get(a).getDisplayName()).toString());
			}
			if (game instanceof OnioniGame) {
				item0lore.add(new StringBuffer("oni_play: ").append(String.valueOf(((OnioniGame) game).oni_play)).toString());
				item0lore.add(new StringBuffer("Lv: ").append(String.valueOf(OnioniGame.getLevel(game.owner.getName()))).toString());
			}
		}
		item0meta.setLore(item0lore);
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
	}
}
