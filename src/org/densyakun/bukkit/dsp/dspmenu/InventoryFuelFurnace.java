package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.densyakun.bukkit.dsp.dsoi.DSOI;
import org.densyakun.bukkit.dsp.dsoi.furnace.Furnace;
import org.densyakun.bukkit.dsp.dsoi.item.Item;
public class InventoryFuelFurnace extends UseInventory implements Runnable {
	public boolean run = true;
	ItemStack[] a = new ItemStack[53];
	Furnace furnace;
	public InventoryFuelFurnace(DSPMenu dspme, Furnace furnace) {
		super(dspme, 54, "かまど>燃料");
		this.furnace = furnace;
		new Thread(this).start();
	}
	@Override
	public void run() {
		while (run) {
			if (0 < furnace.firepower) {
				furnace.firepower--;
			}
			for (int c = 0; c < a.length; c++) {
				if (a[c] != null) {
					Item d = DSOI.toItem(a[c]);
					if (d != null) {
						ItemStack e = inv.getItem(1 + c);
						if (e != null) {
							if (a[c].equals(e)) {
								for (int f = 0; f < DSOI.ffrecipes.length; f++) {
									if (d.equals(DSOI.ffrecipes[f].mat)) {
										if ((a[c].getAmount() - 1) <= 0) {
											inv.clear(1 + c);
										} else {
											a[c].setAmount(a[c].getAmount() - 1);
										}
										furnace.firepower += DSOI.ffrecipes[f].power;
										furnace.getBlock().getWorld().playSound(furnace.getBlock().getLocation(), Sound.FIRE, 1, 0);
									}
								}
							} else {
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
						d = null;
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
