package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class InventoryAdminOpenEC extends MenuInventory {
	DSPMenu dspme;
	public InventoryAdminOpenEC(DSPMenu dspme) {
		super(dspme, 54, "管理者専用 > エンダーチェストを開く");
		this.dspme = dspme;
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
			if (e.getInventory().getItem(e.getRawSlot()) != null) {
				OfflinePlayer player = dspme.main.getServer().getOfflinePlayer(ChatColor.stripColor(e.getInventory().getItem(e.getRawSlot()).getItemMeta().getDisplayName()));
				if (player != null) {
					e.getWhoClicked().openInventory(player.getPlayer().getEnderChest());
					player = null;
				} else {
					System.out.println("1");
				}
			} else {
				System.out.println("0");
			}
			break;
		}
	}
	public void reload() {
		OfflinePlayer[] players = dspme.main.getServer().getOfflinePlayers();
		for (int a = 0; a < players.length; a++) {
			ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append(players[a].getName()).toString());
			item.setItemMeta(itemmeta);
			itemmeta = null;
			inv.setItem(2 + a, item);
			item = null;
			if (inv.getSize() <= a) {
				break;
			}
		}
	}
}
