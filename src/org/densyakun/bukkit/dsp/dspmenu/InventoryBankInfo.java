package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.dspbank.DSPBank;
public class InventoryBankInfo implements DSPMenuKeyboardEnter {
	DSPMenu dspme;
	Player player;
	int a = 0;
	String name;
	String password;
	public InventoryBankInfo(DSPMenu dspme, Player player) {
		this.dspme = dspme;
		this.player = player;
		player.openInventory(new InventoryKeyboard(dspme, "残高照会 - 口座名を入力してください", this, 2).getInventory());
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
				player.openInventory(new InventoryKeyboard(dspme, "残高照会 - パスワードを入力してください", this, 1).getInventory());
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
						player.closeInventory();
						player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.AQUA.toString()).append("口座名: ").append(name).append(" 残高: ").append(b.getDp()).append("DP").toString());
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
