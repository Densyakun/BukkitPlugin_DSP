package org.densyakun.bukkit.dsp.dspmenu;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.dspbank.DSPBank;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class InventoryBankRemove implements DSPMenuKeyboardEnter {
	DSPMenu dspme;
	Player player;
	int a = 0;
	String name;
	String password;
	public InventoryBankRemove(DSPMenu dspme, Player player) {
		this.dspme = dspme;
		this.player = player;
		player.openInventory(new InventoryKeyboard(dspme, "口座削除 - 口座名を入力してください", this, 2).getInventory());
	}
	@Override
	public void Enter(int num) {
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
				player.openInventory(new InventoryKeyboard(dspme, "口座削除 - パスワードを入力してください", this, 1).getInventory());
			}
			break;
		case 1:
			if (str.length() < 6) {
				player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("文字数が少なすぎます。6文字以上にしてください").toString());
				player.closeInventory();
			} else {
				password = str;
				a++;
				DSPBank b = DSPBank.getBank(name);
				if (b != null) {
					if (b.getPassword().equals(password)) {
						try {
							DensyakunPoint c = new DensyakunPoint(player.getName());
							c.setPoint(c.getPoint() + b.getDp());
							c = null;
						} catch (IOException e) {
							e.printStackTrace();
						}
						DSPBank.removeBank(name);
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.AQUA.toString()).append("口座を削除しました 口座名: ").append(name).toString());
					} else {
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("パスワードが間違っています").toString());
					}
				} else {
					player.closeInventory();
					player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("口座が見つかりませんでした").toString());
				}
			}
			break;
		default:
			player.closeInventory();
			player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("エラーが発生しました").toString());
			break;
		}
	}
}
