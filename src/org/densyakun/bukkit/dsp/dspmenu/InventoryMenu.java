package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.DSPAdmin;
import org.densyakun.bukkit.dsp.dsq.Quiz;
public class InventoryMenu extends MenuInventory {
	DSPMenu dspme;
	public InventoryMenu(DSPMenu dspme) {
		super(dspme, 9, "メニュー");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("電鯖銀行").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		
		ItemStack item1 = new ItemStack(Material.IRON_BLOCK);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.GRAY.toString()).append("電鯖どこでもショップ").toString());
		List<String>item1lore = new ArrayList<String>();
		item1lore.add(new StringBuffer(ChatColor.RED.toString()).append("開発中").toString());
		item1meta.setLore(item1lore);
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		
		ItemStack item2 = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("電鯖クイズ").toString());
		List<String>item2lore = new ArrayList<String>();
		item2lore.add(new StringBuffer(ChatColor.WHITE.toString()).append("Ver: ").append(Quiz.ver).toString());
		item2lore.add(new StringBuffer(ChatColor.WHITE.toString()).append("問題数: ").append(Quiz.quizs.length).toString());
		item2meta.setLore(item2lore);
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
		
		ItemStack item4 = new ItemStack(Material.RECORD_3);
		ItemMeta item4meta = item4.getItemMeta();
		item4meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("ミニゲーム").toString());
		item4.setItemMeta(item4meta);
		item4meta = null;
		inv.setItem(4, item4);
		item4 = null;
		
		ItemStack item5 = new ItemStack(Material.RAILS);
		ItemMeta item5meta = item5.getItemMeta();
		item5meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("ルート案内").toString());
		item5.setItemMeta(item5meta);
		item5meta = null;
		inv.setItem(5, item5);
		item5 = null;
		
		ItemStack item6 = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta item6meta = item6.getItemMeta();
		item6meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("管理者専用").toString());
		item6.setItemMeta(item6meta);
		item6meta = null;
		inv.setItem(6, item6);
		item6 = null;
		
		ItemStack item7 = new ItemStack(Material.COMMAND);
		ItemMeta item7meta = item7.getItemMeta();
		item7meta.setDisplayName(new StringBuffer(ChatColor.GRAY.toString()).append("設定").toString());
		List<String>item7lore = new ArrayList<String>();
		item7lore.add(new StringBuffer(ChatColor.RED.toString()).append("開発中").toString());
		item7meta.setLore(item7lore);
		item7.setItemMeta(item7meta);
		item7meta = null;
		inv.setItem(7, item7);
		item7 = null;
		
		ItemStack item8 = new ItemStack(Material.TRAP_DOOR);
		ItemMeta item8meta = item8.getItemMeta();
		item8meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("サーバーを抜ける").toString());
		item8.setItemMeta(item8meta);
		item8meta = null;
		inv.setItem(8, item8);
		item8 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 1:
			e.getWhoClicked().openInventory(new InventoryBank(dspme).getInventory());
			break;
		case 3:
			e.getWhoClicked().openInventory(new InventoryQuiz(dspme).getInventory());
			break;
		case 4:
			e.getWhoClicked().openInventory(new InventoryGames(dspme).getInventory());
			break;
		case 5:
			e.getWhoClicked().openInventory(new InventoryRouting(dspme).getInventory());
			break;
		case 6:
			switch (DSPAdmin.getPermission(e.getWhoClicked())) {
			case other:
				e.getWhoClicked().closeInventory();
				if (e.getWhoClicked() instanceof Player) {
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPAd]").append(ChatColor.RED.toString()).append("管理者ではありません").toString());
				}
				break;
			default:
				e.getWhoClicked().openInventory(new InventoryAdmin(dspme).getInventory());
				break;
			}
			break;
		case 8:
			e.getWhoClicked().getServer().getPlayer(e.getWhoClicked().getName()).kickPlayer("またお越しください");
			break;
		default:
			break;
		}
	}
}
