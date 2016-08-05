package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsprouting.Router;
public class InventoryRouting extends MenuInventory {
	DSPMenu dspme;
	public InventoryRouting(DSPMenu dspme) {
		super(dspme, 9, "メニュー > ルート案内");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.WOOL);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("新しいルート案内").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.WOOL, 1, (short) 1);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("次の地点へ").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		ItemStack item2 = new ItemStack(Material.FENCE_GATE);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("ルート案内をやめる").toString());
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Router a = dspme.main.dspro.getRouter((Player) e.getWhoClicked());
			switch (e.getRawSlot()) {
			case 1:
				if (a == null) {
					e.getWhoClicked().openInventory(new InventoryRoutingNew(dspme).getInventory());
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED.toString()).append("すでにルート案内が行われております").toString());
				}
				break;
			case 2:
				if (a != null) {
					a.nextPoint();
					e.getWhoClicked().closeInventory();
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED.toString()).append("ルート案内が行われておりません").toString());
				}
				break;
			case 3:
				if (a != null) {
					a.end();
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED.toString()).append("ルート案内が行われておりません").toString());
				}
				break;
			default:
				break;
			}
			a = null;
		}
	}
}
