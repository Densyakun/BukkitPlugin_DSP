package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
public abstract class MenuInventory {
	static long idb = 0;
	DSPMenu dspme;
	Inventory inv;
	int size;
	String name;
	long id = idb;
	public MenuInventory(DSPMenu dspme, int size, String name) {
		idb++;
		this.dspme = dspme;
		inv = dspme.main.getServer().createInventory(null, size, name);
		this.size = size;
		this.name = name;
		ItemStack idi = new ItemStack(Material.COMMAND);
		ItemMeta idimeta = idi.getItemMeta();
		idimeta.setDisplayName(new StringBuffer("DSPMe-").append(id).toString());
		idi.setItemMeta(idimeta);
		idimeta = null;
		inv.setItem(0, idi);
		idi = null;
		dspme.mis.add(this);
	}
	public JavaPlugin getPlugin() {
		return dspme.main;
	}
	public Inventory getInventory() {
		return inv;
	}
	public int getSize() {
		return size;
	}
	public String getName() {
		return name;
	}
	public void Click(InventoryClickEvent e) {
	}
	public void Close(InventoryCloseEvent e) {
	}
	public void Open(InventoryOpenEvent e) {
	}
}
