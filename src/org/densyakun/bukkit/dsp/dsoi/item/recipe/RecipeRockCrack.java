package org.densyakun.bukkit.dsp.dsoi.item.recipe;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
import org.densyakun.bukkit.dsp.dsoi.item.ItemRock;
import org.densyakun.bukkit.dsp.dsoi.item.ItemSmallRock;
public class RecipeRockCrack extends DSOIRecipe {
	public static final Item[] mats = new Item[]{new ItemRock()};
	public static final Item[] objs = new Item[]{new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock(), new ItemSmallRock()};
	public RecipeRockCrack() {
		super(mats, objs);
	}
}
