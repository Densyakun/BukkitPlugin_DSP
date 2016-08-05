package org.densyakun.bukkit.dsp.dsoi.furnace.recipe;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
public class FurnaceRecipe {
	public Item mat;
	public int mata;
	public Item[] objs;
	public FurnaceRecipe(Item mat, int mata, Item[] objs) {
		this.mat = mat;
		this.mata = mata;
		this.objs = objs;
	}
}
