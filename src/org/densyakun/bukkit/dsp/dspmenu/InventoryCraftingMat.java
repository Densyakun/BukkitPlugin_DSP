package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
import org.densyakun.bukkit.dsp.dsoi.item.recipe.DSOIRecipe;
public class InventoryCraftingMat extends UseInventory {
	DSOIRecipe recipe;
	public InventoryCraftingMat(DSPMenu dspme, DSOIRecipe recipe) {
		super(dspme, 18, "作業台 > 材料を入れる");
		this.recipe = recipe;
		ItemStack item0 = new ItemStack(Material.WORKBENCH);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.GOLD.toString()).append("作る").toString());
		List<String> item0lore = new ArrayList<String>();
		item0lore.add(new StringBuffer(ChatColor.BLACK.toString()).append("上の段にある材料を下の段に置いて[作る]を押します").toString());
		item0meta.setLore(item0lore);
		item0lore = null;
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(9, item0);
		item0 = null;
		for (int a = 0; a < recipe.mats.length; a++) {
			ItemStack item1 = new ItemStack(recipe.mats[a].getType());
			ItemMeta item1meta = item1.getItemMeta();
			item1meta.setDisplayName(recipe.mats[a].name);
			item1.setItemMeta(item1meta);
			item1meta = null;
			inv.setItem(1 + a, item1);
			item1 = null;
		}
	}
	@Override
	public void Close(InventoryCloseEvent e) {
		Inventory c = e.getPlayer().getInventory();
		for (int f = 0; f < 8; f++) {
			if (inv.getItem(10 + f) != null) {
				if (c.firstEmpty() != -1) {
					c.addItem(inv.getItem(10 + f));
				} else {
					e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), inv.getItem(10 + f));
				}
				inv.clear(10 + f);
			}
		}
		c = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getAction()) {
		case PICKUP_ALL:
		case PICKUP_HALF:
		case PICKUP_ONE:
		case PICKUP_SOME:
			switch (e.getRawSlot()) {
			case 9:
				e.setCancelled(true);
				Craft();
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				break;
			default:
				e.setCancelled(true);
				break;
			}
			break;
		case PLACE_ALL:
		case PLACE_ONE:
		case PLACE_SOME:
			switch (e.getRawSlot()) {
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				break;
			default:
				e.setCancelled(true);
				break;
			}
			break;
		default:
			e.setCancelled(true);
			break;
		}
	}
	private void Craft() {
		int c = 0;
		for (int a = 0; a < recipe.mats.length; a++) {
			ItemStack b = inv.getItem(10 + a);
			if (b != null) {
				if (Item.equals(recipe.mats[a], b) && (recipe.mats[a].getAmount() <= b.getAmount())) {
					c++;
				} else {
					c = -1;
					break;
				}
				b = null;
			}
		}
		if (c == recipe.mats.length) {
			for (int a = 0; a < recipe.mats.length; a++) {
				ItemStack b = inv.getItem(10 + a);
				if (b != null) {
					if ((b.getAmount() - recipe.mats[a].getAmount()) <= 0) {
						inv.clear(10 + a);
					} else {
						b.setAmount(b.getAmount() - recipe.mats[a].getAmount());
					}
					b = null;
				}
			}
			String d = new String();
			for (int b = 0; b < recipe.objs.length; b++) {
				d += recipe.objs[b].name;
				if (b != recipe.objs.length - 1) {
					d += ", ";
				}
			}
			List<HumanEntity> a = inv.getViewers();
			for (int b = 0; b < a.size(); b++) {
				if (a.get(b) instanceof Player) {
					Inventory e = a.get(b).getInventory();
					for (int f = 0; f < recipe.objs.length; f++) {
						if (e.firstEmpty() != -1) {
							e.addItem(recipe.objs[f]);
						} else {
							a.get(b).getWorld().dropItem(a.get(b).getLocation(), recipe.objs[f]);
						}
					}
					e = null;
					((Player) a.get(b)).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSOI]").append(ChatColor.AQUA.toString()).append(d).append("を製作しました").toString());
					break;
				}
			}
			a = null;
			d = null;
		}
	}
}
