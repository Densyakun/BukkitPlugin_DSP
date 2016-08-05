package org.densyakun.bukkit.dsp;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
public class DSPTreasureChest implements Runnable{
	public Main main;
	public Chest chest;
	public boolean run = true;
	public DSPTreasureChest(Main main, DSPWorlds dspwo) {
		this.main = main;
		Block block = dspwo.world_kyozyuku.getBlockAt(1181, 63, -701);
		if (block.getState() instanceof Chest)chest = (Chest) block.getState();
		block = null;
		new Thread(this).start();
	}
	public void stop() {
		run = false;
	}
	@Override
	public void run() {
		while (run) {
			if (chest != null) {
				ItemStack item = getRandomItems();
				if (item != null) {
					chest.getBlockInventory().addItem(item);
					System.out.println(new StringBuffer("[DSPTC]アイテムが出現しました: ").append(item.toString()));
				} else {
					System.out.println("[DSPTC]生成したアイテムがありません");
				}
			}
			try {
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public ItemStack getRandomItems() {
		switch (new Random().nextInt(42)) {
		case 0: return new ItemStack(Material.APPLE);
		case 1: return new ItemStack(Material.ARROW);
		case 2: return new ItemStack(Material.BONE);
		case 3: return new ItemStack(Material.BOW);
		case 4: return new ItemStack(Material.CARROT_ITEM);
		case 5: return new ItemStack(Material.CHAINMAIL_BOOTS);
		case 6: return new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		case 7: return new ItemStack(Material.CHAINMAIL_HELMET);
		case 8: return new ItemStack(Material.CHAINMAIL_LEGGINGS);
		case 9: return new ItemStack(Material.CLAY_BALL);
		case 10: return new ItemStack(Material.COAL);
		case 11: return new ItemStack(Material.COBBLESTONE);
		case 12: return new ItemStack(Material.DIAMOND_ORE);
		case 13: return new ItemStack(Material.EGG);
		case 14: return new ItemStack(Material.EMERALD_ORE);
		case 15: return new ItemStack(Material.EXP_BOTTLE);
		case 16: return new ItemStack(Material.FEATHER);
		case 17: return new ItemStack(Material.FLINT);
		case 18: return new ItemStack(Material.GLOWSTONE_DUST);
		case 19: return new ItemStack(Material.GOLD_ORE);
		case 20: return new ItemStack(Material.IRON_ORE);
		case 21: return new ItemStack(Material.LAPIS_ORE);
		case 22: return new ItemStack(Material.LEATHER);
		case 23: return new ItemStack(Material.PORK);
		case 24: return new ItemStack(Material.POTATO_ITEM);
		case 25: return new ItemStack(Material.QUARTZ_ORE);
		case 26: return new ItemStack(Material.RAW_BEEF);
		case 27: return new ItemStack(Material.RAW_CHICKEN);
		case 28: return new ItemStack(Material.RAW_FISH);
		case 29: return new ItemStack(Material.REDSTONE_ORE);
		case 30: return new ItemStack(Material.ROTTEN_FLESH);
		case 31: return new ItemStack(Material.SLIME_BALL);
		case 32: return new ItemStack(Material.SPIDER_EYE);
		case 33: return new ItemStack(Material.STICK);
		case 34: return new ItemStack(Material.STRING);
		case 35: return new ItemStack(Material.WHEAT);
		case 36: return new ItemStack(Material.WOOD);
		case 37: return new ItemStack(Material.WOOD_AXE);
		case 38: return new ItemStack(Material.WOOD_HOE);
		case 39: return new ItemStack(Material.WOOD_PICKAXE);
		case 40: return new ItemStack(Material.WOOD_SPADE);
		case 41: return new ItemStack(Material.WOOD_SWORD);
		default: return null;
		}
	}
}
