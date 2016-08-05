package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class InventoryBank extends MenuInventory {
	DSPMenu dspme;
	public InventoryBank(DSPMenu dspme) {
		super(dspme, 9, "メニュー > 電鯖銀行");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.WOOL);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("口座作成").toString());
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.EMERALD_ORE);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("お預入れ").toString());
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		ItemStack item2 = new ItemStack(Material.DIAMOND);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.RED.toString()).append("お引き出し").toString());
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
		ItemStack item3 = new ItemStack(Material.GLASS);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("口座削除").toString());
		item3.setItemMeta(item3meta);
		item3meta = null;
		inv.setItem(4, item3);
		item3 = null;
		ItemStack item4 = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta item4meta = item4.getItemMeta();
		item4meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append("残高照会").toString());
		item4.setItemMeta(item4meta);
		item4meta = null;
		inv.setItem(5, item4);
		item4 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 1:
			if (e.getWhoClicked() instanceof Player) {
				new InventoryBankNew(dspme, (Player) e.getWhoClicked());
			}
			break;
		case 2:
			if (e.getWhoClicked() instanceof Player) {
				new InventoryBankIn(dspme, (Player) e.getWhoClicked());
			}
			break;
		case 3:
			if (e.getWhoClicked() instanceof Player) {
				new InventoryBankOut(dspme, (Player) e.getWhoClicked());
			}
			break;
		case 4:
			if (e.getWhoClicked() instanceof Player) {
				new InventoryBankRemove(dspme, (Player) e.getWhoClicked());
			}
			break;
		case 5:
			if (e.getWhoClicked() instanceof Player) {
				new InventoryBankInfo(dspme, (Player) e.getWhoClicked());
			}
			break;
		default:
			break;
		}
	}
}
