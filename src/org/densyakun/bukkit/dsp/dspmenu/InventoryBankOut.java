package org.densyakun.bukkit.dsp.dspmenu;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.dspbank.DSPBank;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class InventoryBankOut implements DSPMenuKeyboardEnter {
	DSPMenu dspme;
	Player player;
	int a = 0;
	String name;
	String password;
	int dp;
	public InventoryBankOut(DSPMenu dspme, Player player) {
		this.dspme = dspme;
		this.player = player;
		player.openInventory(new InventoryKeyboard(dspme, "お引き出し - 口座名を入力してください", this, 2).getInventory());
	}
	@Override
	public void Enter(int num) {
		switch (a) {
		case 2:
			dp = num;
			a++;
			try {
				DensyakunPoint b = new DensyakunPoint(player.getName());
					DSPBank c = DSPBank.getBank(name);
					if (c != null) {
						if (c.getPassword().equals(password)) {
							if (dp <= c.getDp()) {
								c.setDp(c.getDp() - dp);
								b.setPoint(b.getPoint() + dp);
								player.closeInventory();
								player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.AQUA.toString()).append("口座からDPを引き出しました 口座名: ").append(name).append(" 金額: ").append(dp).toString());
							} else {
								player.closeInventory();
								player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("DPが足りません").toString());
							}
						} else {
							player.closeInventory();
							player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("パスワードが間違っています").toString());
						}
					} else {
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("口座が見つかりませんでした").toString());
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
				player.openInventory(new InventoryKeyboard(dspme, "お引き出し - パスワードを入力してください", this, 1).getInventory());
			}
			break;
		case 1:
			if (str.length() < 6) {
				player.closeInventory();
				player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("文字数が少なすぎます。6文字以上にしてください").toString());
			} else {
				password = str;
				a++;
				player.openInventory(new InventoryKeyboard(dspme, "お引き出し - 金額を入力してください", this, 0).getInventory());
			}
			break;
		default:
			player.closeInventory();
			player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("エラーが発生しました").toString());
			break;
		}
	}
}
