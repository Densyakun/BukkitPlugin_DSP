package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsoi.DSOI;
import org.densyakun.bukkit.dsp.dsoi.item.recipe.DSOIRecipe;
public class InventoryCrafting extends ListInventory<DSOIRecipe> {
	public InventoryCrafting(DSPMenu dspme) {
		super(dspme, "作業台 > 作るものを選択", DSOI.recipes, Material.WOOD);
	}
	@Override
	public String getName(ItemStack item, ItemMeta meta, DSOIRecipe e) {
		String a = "材料: ";
		for (int b = 0; b < e.objs.length; b++) {
			a += e.objs[b].name;
			if (b != e.objs.length - 1) {
				a += ", ";
			}
		}
		String b = "作成: ";
		for (int c = 0; c < e.mats.length; c++) {
			b += e.mats[c].name;
			if (c != e.mats.length - 1) {
				b += ", ";
			}
		}
		List<String> c = new ArrayList<String>();
		c.add(b);
		b = null;
		meta.setLore(c);
		c = null;
		return a;
	}
	@Override
	public void ListClick(int n, InventoryClickEvent e) {
		e.getWhoClicked().openInventory(new InventoryCraftingMat(dspme, DSOI.recipes[n]).getInventory());
	}
}
