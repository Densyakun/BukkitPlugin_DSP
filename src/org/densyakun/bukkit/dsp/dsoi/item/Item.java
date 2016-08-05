package org.densyakun.bukkit.dsp.dsoi.item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public abstract class Item extends ItemStack {
	public String name;
	public Item(String a, Material b) {
		this(a, b, (byte) 0);
	}
	public Item(String a, Material b, byte data) {
		super(b);
		this.name = a;
		ItemMeta c = getItemMeta();
		c.setDisplayName(a);
		setItemMeta(c);
		c = null;
		if (data <= 0) {
			getData().setData((byte) 0);
		} else {
			getData().setData(data);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			return equals((Item) obj, this);
		}
		return false;
	}
	public static boolean equals(Item obj0, ItemStack obj1) {
		try {
			if ((obj0.getType() == obj1.getType()) && (obj1 != null) && (obj1.getItemMeta() != null) && (obj1.getItemMeta().getDisplayName().equals(obj0.name))) {
				return true;
			}
		} catch (NullPointerException localNullPointerException) {
		}
		return false;
	}
}
