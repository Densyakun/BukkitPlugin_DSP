package org.densyakun.bukkit.dsp.dsoi.item.recipe;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
public abstract class DSOIRecipe {
	public Item[] mats = new Item[0];
	public Item[] objs = new Item[0];
	public DSOIRecipe(Item[] mats, Item[] objs) {
		this.mats = mats;
		this.objs = objs;
	}
}
