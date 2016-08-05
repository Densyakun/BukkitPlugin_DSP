package org.densyakun.bukkit.dsp.dsoi.furnace.recipe;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
import org.densyakun.bukkit.dsp.dsoi.item.ItemRock;
import org.densyakun.bukkit.dsp.dsoi.item.ItemSmallRock;
public class FurnaceRecipeSmallRockSmelting extends FurnaceRecipe {
	public static final Item mat = new ItemSmallRock();
	public static final int mata = 8;
	public static final Item[] objs = new Item[]{new ItemRock()};
	public FurnaceRecipeSmallRockSmelting() {
		super(mat, mata, objs);
	}
}
