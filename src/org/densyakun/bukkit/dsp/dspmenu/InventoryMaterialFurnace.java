package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.densyakun.bukkit.dsp.dsoi.DSOI;
import org.densyakun.bukkit.dsp.dsoi.furnace.Furnace;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
public class InventoryMaterialFurnace extends UseInventory implements Runnable {
	public boolean run = true;
	ItemStack[] a = new ItemStack[53];
	int[] b = new int[53];
	Furnace furnace;
	public InventoryMaterialFurnace(DSPMenu dspme, Furnace furnace) {
		super(dspme, 54, "かまど>材料");
		this.furnace = furnace;
		for (int c = 0; c < b.length; c++) {
			b[c] = 0;
		}
		new Thread(this).start();
	}
	@Override
	public void run() {
		while (run) {
			if (furnace.firepower != 0) {
				for (int c = 0; c < a.length; c++) {
					if (a[c] != null) {
						Item d = DSOI.toItem(a[c]);
						if (d != null) {
							ItemStack e = inv.getItem(1 + c);
							if (e != null) {
								if (a[c].equals(e)) {
									b[c]++;
									if (10 <= b[c]) {
										b[c] = 0;
										for (int f = 0; f < DSOI.frecipes.length; f++) {
											if (d.equals(DSOI.frecipes[f].mat)) {
												if (DSOI.frecipes[f].mata <= a[c].getAmount()) {
													if (inv.firstEmpty() != -1) {
														if ((a[c].getAmount() - DSOI.frecipes[f].mata) <= 0) {
															inv.clear(1 + c);
														} else {
															a[c].setAmount(a[c].getAmount() - DSOI.frecipes[f].mata);
														}
														for (int g = 0; g < DSOI.frecipes[f].objs.length; g++) {
															if (inv.firstEmpty() != -1) {
																inv.addItem(DSOI.frecipes[f].objs[g]);
															} else {
																furnace.getBlock().getWorld().dropItem(furnace.getBlock().getLocation(), DSOI.frecipes[f].objs[g]);
															}
														}
														furnace.getBlock().getWorld().playSound(furnace.getBlock().getLocation(), Sound.FIRE, 1, 0);
													}
												} else {
													b[c] = 0;
												}
											}
										}
									}
								} else {
									b[c] = 0;
									a[c] = e;
								}
								e = null;
							}
							d = null;
						}
					} else {
						ItemStack d = inv.getItem(1 + c);
						if (d != null) {
							a[c] = d;
							b[c] = 0;
							d = null;
						}
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
