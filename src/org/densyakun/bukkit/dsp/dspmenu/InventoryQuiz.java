package org.densyakun.bukkit.dsp.dspmenu;
import java.io.IOException;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dsq.Quiz;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class InventoryQuiz extends MenuInventory {
	Quiz quiz = Quiz.quizs[new Random().nextInt(Quiz.quizs.length)];
	int an = 0;
	public InventoryQuiz(DSPMenu dspme) {
		super(dspme, 27, new StringBuffer("メニュー > 電鯖クイズ").toString());
		ItemStack title = new ItemStack(Material.GLASS);
		ItemMeta titlemeta = title.getItemMeta();
		titlemeta.setDisplayName(new StringBuffer(ChatColor.WHITE.toString()).append(quiz.title).toString());
		title.setItemMeta(titlemeta);
		titlemeta = null;
		inv.setItem(1, title);
		for (int a = 0; a < quiz.answers.length; a++) {
			if (a < 25) {
				int b = 0;
				while (inv.getItem(2 + (b = new Random().nextInt(quiz.answers.length))) != null) {
				}
				if (a == 0) {
					an = b;
				}
				ItemStack item = new ItemStack(Material.GLASS);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName(new StringBuffer(ChatColor.WHITE.toString()).append(quiz.answers[a]).toString());
				item.setItemMeta(itemmeta);
				itemmeta = null;
				inv.setItem(2 + b, item);
			}
		}
	}
	@Override
	public void Click(InventoryClickEvent e) {
		if (2 <= e.getRawSlot()) {
			if (e.getRawSlot() == (2 + an)) {
				Player a = dspme.main.getServer().getPlayer(e.getWhoClicked().getName());
				if (a != null) {
					try {
						DensyakunPoint d = new DensyakunPoint(a.getName());
						d.setPoint(d.getPoint() + 5);
						d = null;
					} catch (IOException x) {
						x.printStackTrace();
					}
					a.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSQ]").append(ChatColor.AQUA.toString()).append("問題\"").append(quiz.title).append("\"の答え\"").append(quiz.answers[0]).append("は正解です(5DP獲得!)").toString());
					a.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSQ]").append(ChatColor.GREEN.toString()).append("解説: \n").append(quiz.info).toString());
					Player[] b = dspme.main.getServer().getOnlinePlayers().toArray(new Player[0]);
					for (int c = 0; c < b.length; c++) {
						b[c].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSQ]").append(ChatColor.AQUA.toString()).append(a.getDisplayName()).append("さんが電鯖クイズで問題\"").append(quiz.title).append("\"をクリアしました!").toString());
					}
					b = null;
					a = null;
				}
			} else {
				Player a = dspme.main.getServer().getPlayer(e.getWhoClicked().getName());
				if (a != null) {
					a.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSQ]").append(ChatColor.RED.toString()).append("問題\"").append(quiz.title).append("\"の答え\"").append(quiz.answers[0]).append("は不正解です").toString());
					Player[] b = dspme.main.getServer().getOnlinePlayers().toArray(new Player[0]);
					for (int c = 0; c < b.length; c++) {
						b[c].sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSQ]").append(ChatColor.RED.toString()).append(a.getDisplayName()).append("さんが電鯖クイズで問題\"").append(quiz.title).append("\"を間違えました...").toString());
					}
					b = null;
					a = null;
				}
			}
			e.getWhoClicked().openInventory(new InventoryQuiz(dspme).getInventory());
		}
	}
}
