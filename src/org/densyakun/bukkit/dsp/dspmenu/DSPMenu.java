package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.densyakun.bukkit.dsp.DSPWorlds;
import org.densyakun.bukkit.dsp.Main;
public class DSPMenu implements Listener {
	public Main main;
	public DSPWorlds dspwo;
	public List<MenuInventory>mis = new ArrayList<MenuInventory>();
	public DSPMenu(Main main,DSPWorlds dspwo) {
		this.main = main;
		this.dspwo = dspwo;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	public void OpenMenu(HumanEntity player) {
		player.openInventory(new InventoryMenu(this).inv);
	}
	public void stop() {
		Player[] a = main.getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int b = 0; b < a.length; b++) {
			a[b].closeInventory();
		}
		a = null;
	}
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		for (int a = 0; a < mis.size(); a++) {
			if (mis.get(a) instanceof UseInventory) {
				if (equals(e.getInventory(), mis.get(a).id)) {
					if (e.getRawSlot() == 0) {
						e.setCancelled(true);
					} else {
						if ((e.getWhoClicked() instanceof Player) && (e.getRawSlot() < mis.get(a).size)) {
							((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.NOTE_STICKS, 1, 12);
							mis.get(a).Click(e);
						}
					}
				}
			} else {
				if (equals(e.getInventory(), mis.get(a).id)) {
					e.setCancelled(true);
					if (e.getRawSlot() != 0) {
						switch (e.getAction()) {
						case CLONE_STACK:
						case COLLECT_TO_CURSOR:
						case DROP_ALL_CURSOR:
						case DROP_ALL_SLOT:
						case DROP_ONE_CURSOR:
						case DROP_ONE_SLOT:
						case HOTBAR_MOVE_AND_READD:
						case HOTBAR_SWAP:
						case MOVE_TO_OTHER_INVENTORY:
						case NOTHING:
						case PLACE_ALL:
						case PLACE_ONE:
						case PLACE_SOME:
						case SWAP_WITH_CURSOR:
						case UNKNOWN:
							break;
						case PICKUP_ALL:
						case PICKUP_HALF:
						case PICKUP_ONE:
						case PICKUP_SOME:
							if ((e.getWhoClicked() instanceof Player) && (e.getRawSlot() < mis.get(a).size)) {
								((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.NOTE_STICKS, 1, 12);
								mis.get(a).Click(e);
							}
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		for (int a = 0; a < mis.size(); a++) {
			if (equals(e.getInventory(), mis.get(a).id)) {
				if (e.getPlayer() instanceof Player) {
					((Player) e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.NOTE_PIANO, 1, 0);
					mis.get(a).Close(e);
					mis.remove(a);
				}
			}
		}
	}
	@EventHandler
	public void InventoryDrag(InventoryDragEvent e) {
		for (int a = 0; a < mis.size(); a++) {
			if (equals(e.getInventory(), mis.get(a).id)) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void InventoryOpen(InventoryOpenEvent e) {
		for (int a = 0; a < mis.size(); a++) {
			if (equals(e.getInventory(), mis.get(a).id)) {
				if (e.getPlayer() instanceof Player) {
					((Player) e. getPlayer()).playSound(e.getPlayer().getLocation(), Sound.NOTE_PIANO, 1, 12);
					mis.get(a).Open(e);
				}
			}
		}
	}
	public boolean equals(Inventory inv, long id) {
		return (inv.getItem(0) != null) && (inv.getItem(0).getItemMeta().getDisplayName() != null) && (inv.getItem(0).getItemMeta().getDisplayName().equals(new StringBuffer("DSPMe-").append(String.valueOf(id)).toString()));
	}
}
