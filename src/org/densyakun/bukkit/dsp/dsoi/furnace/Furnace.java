package org.densyakun.bukkit.dsp.dsoi.furnace;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.densyakun.bukkit.dsp.dsoi.DSOI;
import org.densyakun.bukkit.dsp.dspmenu.InventoryFuelFurnace;
import org.densyakun.bukkit.dsp.dspmenu.InventoryMaterialFurnace;
public class Furnace {
	public DSOI dsoi;
	public int x;
	public int y;
	public int z;
	public int firepower;
	public InventoryFuelFurnace fuelinv;
	public InventoryMaterialFurnace matinv;
	public Furnace(DSOI dsoi, int x, int y, int z, int firepower) {
		this.dsoi = dsoi;
		this.x = x;
		this.y = y;
		this.z = z;
		this.firepower = firepower;
		this.fuelinv = new InventoryFuelFurnace(dsoi.main.dspme, this);
		this.matinv = new InventoryMaterialFurnace(dsoi.main.dspme, this);
	}
	public void stop() {
		fuelinv.run = false;
		matinv.run = false;
	}
	public void Break() {
		stop();
		for (int a = 1; a < 54; a++) {
			ItemStack b = matinv.getInventory().getItem(a);
			if (b != null) {
				Block c = getBlock();
				if (c != null) {
					c.getWorld().dropItem(c.getLocation(), b);
					c = null;
				}
				b = null;
				matinv.getInventory().clear(a);
			}
			b = fuelinv.getInventory().getItem(a);
			if (b != null) {
				Block c = getBlock();
				if (c != null) {
					c.getWorld().dropItem(c.getLocation(), b);
					c = null;
				}
				b = null;
				fuelinv.getInventory().clear(a);
			}
		}
	}
	public Block getBlock() {
		return dsoi.main.dspwo.world_kyozyuku.getBlockAt(x, y, z);
	}
}
