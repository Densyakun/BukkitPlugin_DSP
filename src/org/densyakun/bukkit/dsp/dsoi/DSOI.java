package org.densyakun.bukkit.dsp.dsoi;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantInventory;
import org.densyakun.bukkit.dsp.AdminPermissions;
import org.densyakun.bukkit.dsp.DSPAdmin;
import org.densyakun.bukkit.dsp.Main;
import org.densyakun.bukkit.dsp.dsoi.furnace.Furnace;
import org.densyakun.bukkit.dsp.dsoi.furnace.recipe.FurnaceFuelRecipe;
import org.densyakun.bukkit.dsp.dsoi.furnace.recipe.FurnaceRecipe;
import org.densyakun.bukkit.dsp.dsoi.furnace.recipe.FurnaceRecipeSmallRockSmelting;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
import org.densyakun.bukkit.dsp.dsoi.item.ItemCobbleStone;
import org.densyakun.bukkit.dsp.dsoi.item.ItemGravel;
import org.densyakun.bukkit.dsp.dsoi.item.ItemRock;
import org.densyakun.bukkit.dsp.dsoi.item.ItemSmallRock;
import org.densyakun.bukkit.dsp.dsoi.item.ItemStone;
import org.densyakun.bukkit.dsp.dsoi.item.recipe.DSOIRecipe;
import org.densyakun.bukkit.dsp.dsoi.item.recipe.RecipeRockCrack;
import org.densyakun.bukkit.dsp.dspmenu.InventoryCrafting;
import org.densyakun.bukkit.dsp.dspmenu.InventoryFurnace;
import org.densyakun.csvm.CSVFile;
public class DSOI implements Runnable, Listener {
	public Main main;
	public static final String ver = "1.0";
	public static final Item[] items = new Item[]{new ItemStone(), new ItemCobbleStone(), new ItemRock(), new ItemSmallRock()};
	public static final DSOIRecipe[] recipes = new DSOIRecipe[]{new RecipeRockCrack()};
	public static final FurnaceFuelRecipe[] ffrecipes = new FurnaceFuelRecipe[]{};
	public static final FurnaceRecipe[] frecipes = new FurnaceRecipe[]{new FurnaceRecipeSmallRockSmelting()};
	public static final File dir = new File(Main.dir, "DSOI/");
	public static final CSVFile fcsv = new CSVFile(new File(dir, "Furnaces.csv"));
	public List<Furnace> furnaces = new ArrayList<Furnace>();
	public DSOI(Main main) {
		this.main = main;
		//main.getServer().clearRecipes();
		//main.getServer().getPluginManager().registerEvents(this, main);
		new Thread(this).start();
		dir.mkdirs();
		if (!fcsv.getFile().exists()) {
			try {
				fcsv.getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				List<List<String>> a = fcsv.AllRead();
				for (int b = 0; b < a.size(); b++) {
					boolean c = true;
					for (int d = 0; d < furnaces.size(); d++) {
						if ((Integer.valueOf(a.get(b).get(1)) == furnaces.get(d).x) && (Integer.valueOf(a.get(b).get(2)) == furnaces.get(d).y) && (Integer.valueOf(a.get(b).get(3)) == furnaces.get(d).z)) {
							c = false;
							Item e = toItem(a.get(b).get(4));
							if (e != null) {
								e.setAmount(Integer.valueOf(a.get(b).get(5)));
								if (Boolean.valueOf(a.get(b).get(0))) {
									for (int f = 1; f < furnaces.get(d).matinv.getInventory().getSize(); f++) {
										if (furnaces.get(d).matinv.getInventory().getItem(f) == null) {
											furnaces.get(d).matinv.getInventory().addItem(e);
											break;
										}
									}
								} else {
									for (int f = 1; f < furnaces.get(d).fuelinv.getInventory().getSize(); f++) {
										if (furnaces.get(d).fuelinv.getInventory().getItem(f) == null) {
											furnaces.get(d).fuelinv.getInventory().addItem(e);
											break;
										}
									}
								}
								e = null;
							}
							break;
						}
					}
					if (c) {
						Furnace d = new Furnace(this, Integer.valueOf(a.get(b).get(1)), Integer.valueOf(a.get(b).get(2)), Integer.valueOf(a.get(b).get(3)), Integer.valueOf(a.get(b).get(4)));
						furnaces.add(d);
						Item e = toItem(a.get(b).get(4));
						if (e != null) {
							e.setAmount(Integer.valueOf(a.get(b).get(5)));
							if (Boolean.valueOf(a.get(b).get(0))) {
								for (int f = 1; f < d.matinv.getInventory().getSize(); f++) {
									if (d.matinv.getInventory().getItem(f) == null) {
										d.matinv.getInventory().addItem(e);
										break;
									}
								}
							} else {
								for (int f = 1; f < d.fuelinv.getInventory().getSize(); f++) {
									if (d.fuelinv.getInventory().getItem(f) == null) {
										d.fuelinv.getInventory().addItem(e);
										break;
									}
								}
							}
							e = null;
						}
						d = null;
					}
				}
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void save() {
		List<List<String>> a = new ArrayList<List<String>>();
		for (int b = 0; b < furnaces.size(); b++) {
			for (int c = 1; c < furnaces.get(b).fuelinv.getInventory().getSize(); c++) {
				ItemStack d = furnaces.get(b).fuelinv.getInventory().getItem(c);
				if (d != null) {
					Item e = toItem(d);
					if (e != null) {
						List<String> f = new ArrayList<String>();
						f.add(String.valueOf(false));
						f.add(String.valueOf(furnaces.get(b).x));
						f.add(String.valueOf(furnaces.get(b).y));
						f.add(String.valueOf(furnaces.get(b).z));
						f.add(e.name);
						f.add(String.valueOf(e.getAmount()));
						f.add(String.valueOf(furnaces.get(b).firepower));
						a.add(f);
						f = null;
						e = null;
					}
					d = null;
				}
			}
			for (int c = 1; c < furnaces.get(b).matinv.getInventory().getSize(); c++) {
				ItemStack d = furnaces.get(b).matinv.getInventory().getItem(c);
				if (d != null) {
					Item e = toItem(d);
					if (e != null) {
						List<String> f = new ArrayList<String>();
						f.add(String.valueOf(true));
						f.add(String.valueOf(furnaces.get(b).x));
						f.add(String.valueOf(furnaces.get(b).y));
						f.add(String.valueOf(furnaces.get(b).z));
						f.add(e.name);
						f.add(String.valueOf(e.getAmount()));
						f.add(String.valueOf(furnaces.get(b).firepower));
						a.add(f);
						f = null;
						e = null;
					}
					d = null;
				}
			}
			furnaces.get(b).stop();
		}
		try {
			fcsv.AllWrite(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a = null;
	}
	public void run() {
	}
	public static Item toItem(ItemStack a) {
		if ((a != null) && (a.getItemMeta() != null) && (a.getItemMeta().getDisplayName() != null)) {
			for (int c = 0; c < items.length; c++) {
				if ((a.getType() == items[c].getType()) && (a.getItemMeta().getDisplayName().equals(items[c].name))) {
					Item d = items[c];
					d.setAmount(a.getAmount());
					return d;
				}
			}
		}
		return null;
	}
	public static boolean isDSOIItem(ItemStack a) {
		if ((a != null) && (a.getItemMeta() != null) && (a.getItemMeta().getDisplayName() != null)) {
			for (int c = 0; c < items.length; c++) {
				if ((a.getType() == items[c].getType()) && (a.getItemMeta().getDisplayName().equals(items[c].name))) {
					return true;
				}
			}
		}
		return false;
	}
	@EventHandler
	public void InventoryOpen(InventoryOpenEvent e) {
		if (!(main.dspad.emergency && (DSPAdmin.getPermission(e.getPlayer()) != AdminPermissions.other))) {
			if ((e.getInventory() instanceof AnvilInventory) || (e.getInventory() instanceof BeaconInventory) || (e.getInventory() instanceof BrewerInventory) || (e.getInventory() instanceof EnchantingInventory) || (e.getInventory() instanceof MerchantInventory)) {
				e.setCancelled(true);
				if (e.getPlayer() instanceof Player) {
					((Player) e.getPlayer()).sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSOI]").append(ChatColor.RED.toString()).append("現在アイテム自作化により使用できません").toString());
				}
			} else if (e.getInventory() instanceof CraftingInventory) {
				e.setCancelled(true);
				e.getPlayer().openInventory(new InventoryCrafting(main.dspme).getInventory());
			} else if (e.getInventory() instanceof FurnaceInventory) {
				e.setCancelled(true);
				e.getPlayer().openInventory(new InventoryFurnace(main.dspme, getFurnace(((FurnaceInventory) e.getInventory()).getHolder().getBlock().getX(), ((FurnaceInventory) e.getInventory()).getHolder().getBlock().getY(), ((FurnaceInventory) e.getInventory()).getHolder().getBlock().getZ())).getInventory());
			}
		}
	}
	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			switch (e.getBlock().getType()) {
			case COBBLESTONE:
				if ((e.getPlayer().getItemInHand() != null) && e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemCobbleStone());
				} else {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					ItemRock a = new ItemRock();
					a.setAmount(8);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), a);
					a = null;
				}
				break;
			case FURNACE:
				for (int a = 0; a < furnaces.size(); a++) {
					if ((e.getBlock().getX() == furnaces.get(a).x) && (e.getBlock().getY() == furnaces.get(a).y) && (e.getBlock().getZ() == furnaces.get(a).z)) {
						furnaces.get(a).Break();
					}
				}
				break;
			case GRAVEL:
				if ((e.getPlayer().getItemInHand() != null) && e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemGravel());
				} else {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					ItemSmallRock a = new ItemSmallRock();
					a.setAmount(64);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), a);
					a = null;
				}
				break;
			case STONE:
				if ((e.getPlayer().getItemInHand() != null) && e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStone());
				} else {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemCobbleStone());
				}
				break;
			default:
				Item b = BlockToDSOIItem(e.getBlock().getType());
				if (b != null) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), b);
					b = null;
				}
				break;
			}
		}
	}
	public Item BlockToDSOIItem(Material type) {
		switch (type) {
		case COBBLESTONE:
			return new ItemCobbleStone();
		case GRAVEL:
			return new ItemGravel();
		case STONE:
			return new ItemStone();
		default:
			return null;
		}
	}
	public static Item toItem(String name) {
		for (int a = 0; a < items.length; a++) {
			if (name.equals(items[a].name)) {
				return items[a];
			}
		}
		return null;
	}
	public Furnace getFurnace(int x, int y, int z) {
		for (int a = 0; a < furnaces.size(); a++) {
			if ((x == furnaces.get(a).x) && (y == furnaces.get(a).y) && (z == furnaces.get(a).z)) {
				return furnaces.get(a);
			}
		}
		Furnace a = new Furnace(this, x, y, z, 0);
		furnaces.add(a);
		return a;
	}
}
