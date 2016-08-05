package org.densyakun.bukkit.dsp.dspmenu;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsprouting.Point;
import org.densyakun.bukkit.dsp.dsprouting.Route;
import org.densyakun.bukkit.dsp.dsprouting.Router;
public class InventoryRoutingNew extends MenuInventory {
	DSPMenu dspme;
	int page = 0;
	int maxpage = 0;
	public InventoryRoutingNew(DSPMenu dspme) {
		super(dspme, 54, "ルート案内 > 新しいルート案内");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.BEACON);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("前のページへ").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.BEACON);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("次のページへ").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		reload();
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 0:
			break;
		case 1:
			if (0 < page) {
				page--;
			}
			reload();
			break;
		case 2:
			if (page < maxpage) {
				page++;
			}
			reload();
			break;
		default:
			if (e.getWhoClicked() instanceof Player) {
				if (e.getWhoClicked().getWorld().getName().equals(dspme.main.dspwo.world_kyozyuku.getName())) {
					if (e.getInventory().getItem(e.getRawSlot()) != null) {
						Point start = null;
						int a = 0;
						for (int b = 0; b < dspme.main.dspro.points.size(); b++) {
							if (((Player) e.getWhoClicked()).getLocation().distance(dspme.main.dspro.points.get(b).location) < ((Player) e.getWhoClicked()).getLocation().distance(dspme.main.dspro.points.get(a).location)) {
								a = b;
							}
						}
						start = dspme.main.dspro.points.get(a);
						if (start != null) {
							Point end = dspme.main.dspro.points.get(((51 * (page + 1)) - 51) + (e.getRawSlot() - 3));
							if (!(start.name.equals(end.name))) {
								Route b = dspme.main.dspro.GenerateRoute(start, end);
								if (b != null) {
									e.getWhoClicked().closeInventory();
									((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA).append("ルート案内を開始しました 現在地: ").append(start.name).append(" 目的地: ").append(end.name).toString());
									new Router(dspme.main.dspro, (Player) e.getWhoClicked(), b);
								} else {
									e.getWhoClicked().closeInventory();
									((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED).append("ルートを作成できませんでした").toString());
								}
							} else {
								e.getWhoClicked().closeInventory();
								((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED).append("目的地は現在地です").toString());
							}
						} else {
							e.getWhoClicked().closeInventory();
							((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED).append("現在地が見つかりませんでした").toString());
						}
					}
				} else {
					e.getWhoClicked().closeInventory();
					((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.RED).append("現在地が居住区ワールドではありません").toString());
				}
			}
			break;
		}
	}
	public void reload() {
		int n = 0;
		List<Point>points = dspme.main.dspro.points;
		for (int a = ((51 * (page + 1)) - 51); a < points.size(); a++) {
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append(points.get(a).name).toString());
			item.setItemMeta(itemmeta);
			itemmeta = null;
			inv.setItem(3 + n, item);
			item = null;
			n++;
			if (inv.getSize() <= n) {
				break;
			}
		}
	}
}
