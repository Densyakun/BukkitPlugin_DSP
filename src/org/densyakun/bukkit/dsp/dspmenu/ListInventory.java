package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public abstract class ListInventory<E> extends MenuInventory {
	int page = 0;
	int maxpage = 0;
	E[] e;
	Material type;
	public ListInventory(DSPMenu dspme, String name, E[] e, Material type) {
		super(dspme, 54, name);
		this.e = e;
		this.type = type;
		ItemStack item0 = new ItemStack(Material.WOOD_BUTTON);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("前のページへ").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.WOOD_BUTTON);
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
			ListClick(((51 * (page + 1)) - 51) + (e.getRawSlot() - 3), e);
			break;
		}
	}
	public void reload() {
		int n = 0;
		for (int a = ((51 * (page + 1)) - 51); a < e.length; a++) {
			ItemStack item = new ItemStack(type);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append(getName(item, itemmeta, e[a])).toString());
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
	public void ListClick(int n, InventoryClickEvent e) {
	}
	public String getName(ItemStack item, ItemMeta meta, E e) {
		return new String();
	}
}
