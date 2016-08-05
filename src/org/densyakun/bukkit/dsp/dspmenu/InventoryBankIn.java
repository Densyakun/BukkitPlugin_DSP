package org.densyakun.bukkit.dsp.dspmenu;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.dspbank.DSPBank;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class InventoryBankIn implements DSPMenuKeyboardEnter {
	DSPMenu dspme;
	Player player;
	int a = 0;
	String name;
	int dp;
	public InventoryBankIn(DSPMenu dspme, Player player) {
		this.dspme = dspme;
		this.player = player;
		player.openInventory(new InventoryKeyboard(dspme, "お預入れ - 口座名を入力してください", this, 2).getInventory());
	}
	@Override
	public void Enter(int num) {
		switch (a) {
		case 1:
			dp = num;
			a++;
			try {
				DensyakunPoint b = new DensyakunPoint(player.getName());
				if (dp <= b.getPoint()) {
					DSPBank c = DSPBank.getBank(name);
					if (c != null) {
						b.setPoint(b.getPoint() - dp);
						c.setDp(c.getDp() + dp);
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.AQUA.toString()).append("口座にDPを預けました 口座名: ").append(name).append(" 金額: ").append(dp).toString());
					} else {
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("口座が見つかりませんでした").toString());
					}
				} else {
					player.closeInventory();
					player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("DPが足りません").toString());
				}
				b = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			player.closeInventory();
			player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("エラーが発生しました").toString());
			break;
		}
	}
	@Override
	public void Enter(String str) {
		switch (a) {
		case 0:
			if (str.length() < 6) {
				player.closeInventory();
				player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("文字数が少なすぎます。6文字以上にしてください").toString());
			} else {
				name = str;
				a++;
				player.openInventory(new InventoryKeyboard(dspme, "お預入れ - 金額を入力してください", this, 0).getInventory());
			}
			break;
		default:
			player.closeInventory();
			player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("エラーが発生しました").toString());
			break;
		}
	}
}
