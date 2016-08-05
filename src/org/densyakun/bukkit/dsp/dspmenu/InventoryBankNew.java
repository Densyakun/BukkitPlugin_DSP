package org.densyakun.bukkit.dsp.dspmenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.densyakun.bukkit.dsp.dspbank.DSPBank;
public class InventoryBankNew implements DSPMenuKeyboardEnter {
	DSPMenu dspme;
	Player player;
	int a = 0;
	String name;
	String password;
	public InventoryBankNew(DSPMenu dspme, Player player) {
		this.dspme = dspme;
		this.player = player;
		player.openInventory(new InventoryKeyboard(dspme, "口座作成 - 口座名を入力してください", this, 2).getInventory());
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
				player.openInventory(new InventoryKeyboard(dspme, "口座作成 - パスワードを入力してください", this, 1).getInventory());
			}
			break;
		case 1:
			if (str.length() < 6) {
				player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("文字数が少なすぎます。6文字以上にしてください").toString());
				player.closeInventory();
			} else {
				password = str;
				a++;
				if (DSPBank.createBank(name, password) != null) {
					player.closeInventory();
					player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.AQUA.toString()).append("口座を作成しました 口座名: ").append(name).append(" 暗証番号: ").append(password).toString());
				} else {
					player.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPBa]").append(ChatColor.RED.toString()).append("同じ名前の口座がすでにあるか作成できません。最初からやり直してください").toString());
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
