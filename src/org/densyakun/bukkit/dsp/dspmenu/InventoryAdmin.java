package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class InventoryAdmin extends MenuInventory {
	DSPMenu dspme;
	public InventoryAdmin(DSPMenu dspme) {
		super(dspme, 9, "メニュー > 管理者専用");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.CHEST);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("プレイヤーのインベントリを開く").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		ItemStack item1 = new ItemStack(Material.GLASS);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("姿を隠す").toString());
		List<String> item1lore = new ArrayList<String>();
		item1lore.add(new StringBuffer(ChatColor.RED.toString()).append("!注意 後からログインしたり死亡後のプレイヤーからは表示されます!").toString());
		item1meta.setLore(item1lore);
		item1lore = null;
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		ItemStack item2 = new ItemStack(Material.GLASS);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("姿を表示").toString());
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		ItemStack item3 = new ItemStack(Material.ENDER_CHEST);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("プレイヤーのエンダーチェストを開く").toString());
		item3.setItemMeta(item3meta);
		item3meta = null;
		inv.setItem(4, item3);
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 1:
			e.getWhoClicked().openInventory(new InventoryAdminOpenPI(dspme).getInventory());
			break;
		case 2:
			e.getWhoClicked().closeInventory();
			if (e.getWhoClicked() instanceof Player) {
				Player[] a = e.getWhoClicked().getServer().getOnlinePlayers().toArray(new Player[0]);
				for (int b = 0; b < a.length; b++) {
					if (!a[b].hasPermission("dsp.admin")) {
						a[b].hidePlayer(((Player) e.getWhoClicked()));
					}
				}
				a = null;
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.AQUA.toString()).append("管理者以外からの姿を隠しました").toString());
			}
			break;
		case 3:
			e.getWhoClicked().closeInventory();
			if (e.getWhoClicked() instanceof Player) {
				Player[] a = e.getWhoClicked().getServer().getOnlinePlayers().toArray(new Player[0]);
				for (int b = 0; b < a.length; b++) {
					if (!a[b].hasPermission("dsp.admin")) {
						a[b].showPlayer(((Player) e.getWhoClicked()));
					}
				}
				a = null;
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.AQUA.toString()).append("管理者以外からの姿を表示しました").toString());
			}
			break;
		case 4:
			e.getWhoClicked().openInventory(new InventoryAdminOpenEC(dspme).getInventory());
			break;
		default:
			break;
		}
	}
}
