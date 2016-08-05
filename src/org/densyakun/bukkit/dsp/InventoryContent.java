package org.densyakun.bukkit.dsp;
import java.io.File;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
public class InventoryContent {
	public static File invdir = new File(Main.dir, "Inventory/");
	public static void Save(final Main s, final Player player) {
		Chest b = (Chest)s.getServer().getWorld(Main.Creativewn).getBlockAt(player.getName().hashCode(), 254, 0);
		Chest c = (Chest)s.getServer().getWorld(Main.Creativewn).getBlockAt(player.getName().hashCode(), 255, 0);
		if((b != null) && (c != null)) {
			b.getInventory().addItem(player.getInventory().getContents());
			c.getInventory().setItem(0,player.getInventory().getHelmet());
			c.getInventory().setItem(1,player.getInventory().getChestplate());
			c.getInventory().setItem(2,player.getInventory().getLeggings());
			c.getInventory().setItem(3,player.getInventory().getBoots());
		}
	}
	public static void Load(final Main s, final Player player) {
		Chest b = (Chest)s.getServer().getWorld(Main.Creativewn).getBlockAt(player.getName().hashCode(), 254, 0);
		Chest c = (Chest)s.getServer().getWorld(Main.Creativewn).getBlockAt(player.getName().hashCode(), 255, 0);
		if((b != null) && (c != null)) {
			player.getInventory().clear();
			player.getInventory().addItem(b.getInventory().getContents());
			player.getInventory().setHelmet(c.getInventory().getItem(0));
			player.getInventory().setChestplate(c.getInventory().getItem(1));
			player.getInventory().setLeggings(c.getInventory().getItem(2));
			player.getInventory().setBoots(c.getInventory().getItem(3));
		}
	}
	public static boolean Player(final String playername) {
		return new File(invdir, new StringBuffer(playername).append(".txt").toString()).exists();
	}
}
