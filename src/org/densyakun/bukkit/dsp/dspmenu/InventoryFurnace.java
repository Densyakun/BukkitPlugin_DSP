package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsoi.furnace.Furnace;
public class InventoryFurnace extends MenuInventory {
	Furnace furnace;
	public InventoryFurnace(DSPMenu dspme, Furnace furnace) {
		super(dspme, 9, "かまど");
		this.furnace = furnace;
		ItemStack item0 = new ItemStack(Material.COAL);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("燃料").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(2, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.COBBLESTONE);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("材料").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(6, item1);
		item1 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 2:
			e.getWhoClicked().openInventory(furnace.fuelinv.getInventory());
			break;
		case 6:
			e.getWhoClicked().openInventory(furnace.matinv.getInventory());
			break;
		default:
			break;
		}
	}
}
