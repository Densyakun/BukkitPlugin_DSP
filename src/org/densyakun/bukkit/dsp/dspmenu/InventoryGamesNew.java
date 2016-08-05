package org.densyakun.bukkit.dsp.dspmenu;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.densyakun.bukkit.dsp.dspgames.MonsterOreGame;
import org.densyakun.bukkit.dsp.dspgames.OnioniGame;
import org.densyakun.bukkit.dsp.dspgames.PvPGame;
import org.densyakun.bukkit.dsp.dspgames.TosochuMultiGame;
public class InventoryGamesNew extends MenuInventory {
	DSPMenu dspme;
	public InventoryGamesNew(DSPMenu dspme) {
		super(dspme, 9, "ミニゲーム > 新しいミニゲーム");
		this.dspme = dspme;
		ItemStack item0 = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta item0meta = item0.getItemMeta();
		item0meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append(OnioniGame.name).toString());
		List<String>item0lore = new ArrayList<String>();
		item0lore.add("種類: マルチ 最少人数: 2人");
		item0lore.add("onioniはとってもシンプルなかくれんぼです。");
		item0lore.add("ルールは鬼ごっこと同じで、制限時間はありません。");
		item0lore.add("ミニゲームの管理人が鬼になります。");
		item0lore.add("またルールを自由に追加しても結構です。");
		item0lore.add("(時間制限を自分でつけるなど)");
		item0lore.add("子は隠れたらメニューを開き、");
		item0lore.add("ミニゲーム>エントリーまたはプレイ中のミニゲーム>");
		item0lore.add("もういいよ を押してください。");
		item0lore.add("子が全員隠れ終わると鬼が動けるようになります。");
		item0lore.add("このゲームはDPがもらえます:");
		item0lore.add("賞金: ((10 * プレイヤー数) / 順位 DP) * onioniレベル");
		item0lore.add("※賞金のonioniレベルは小数点をすべて切り捨てます");
		item0meta.setLore(item0lore);
		item0lore = null;
		item0.setItemMeta(item0meta);
		item0meta = null;
		inv.setItem(1, item0);
		item0 = null;
		ItemStack item1 = new ItemStack(Material.WOOD_SWORD);
		ItemMeta item1meta = item1.getItemMeta();
		item1meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append(MonsterOreGame.name).toString());
		List<String>item1lore = new ArrayList<String>();
		item1lore.add("種類: シングル");
		item1lore.add("Monster<<<Ore>>>はモンスターを倒すと鉱石がもらえるゲームです。");
		item1lore.add("ルールは普通に敵を倒すだけ!");
		item1lore.add("制限時間はありません。好きなだけ遊べます");
		item1lore.add("敵を倒すとこのゲームのレベルが上がります");
		item1lore.add("もらえる鉱石は最初は出てきませんが、");
		item1lore.add("レベルが上がれば鉱石が出てくるだけではなく、");
		item1lore.add("もらえる鉱石の数も増えます!");
		item1lore.add("鉱石について:");
		item1lore.add(" Lv.1~: 石炭・鉄・金・ラピスラズリ");
		item1lore.add(" Lv.10~: 石炭(もらえる数が倍になる)・鉄(もらえる数が倍になる)・");
		item1lore.add("金(もらえる数が倍になる)・ラピスラズリ(もらえる数が倍になる)・ダイヤモンド");
		item1lore.add(" Lv.50~: ダイヤモンド(もらえる数が倍になる)・エメラルド");
		item1lore.add(" Lv.100~: エメラルド(もらえる数が倍になる)");
		item1lore.add("このゲームは敵を倒すごとに10DPがもらえます");
		item1meta.setLore(item1lore);
		item1lore = null;
		item1.setItemMeta(item1meta);
		item1meta = null;
		inv.setItem(2, item1);
		item1 = null;
		ItemStack item2 = new ItemStack(Material.IRON_BOOTS);
		ItemMeta item2meta = item2.getItemMeta();
		item2meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append(TosochuMultiGame.name).toString());
		List<String>item2lore = new ArrayList<String>();
		item2lore.add("種類: マルチ");
		item2meta.setLore(item2lore);
		item2lore = null;
		item2.setItemMeta(item2meta);
		item2meta = null;
		inv.setItem(3, item2);
		item2 = null;
		ItemStack item3 = new ItemStack(Material.IRON_SWORD);
		ItemMeta item3meta = item3.getItemMeta();
		item3meta.setDisplayName(new StringBuffer(ChatColor.BLUE.toString()).append(PvPGame.name).toString());
		List<String>item3lore = new ArrayList<String>();
		item3lore.add("種類: マルチ");
		item3lore.add(PvPGame.name + "は簡単な1vs1PvPゲームです");
		item3lore.add("テレポートはされずゲームはすぐに始まります");
		item3lore.add("ゲーム開始前から持っていたアイテムで対戦します");
		item3meta.setLore(item3lore);
		item3lore = null;
		item3.setItemMeta(item3meta);
		item3meta = null;
		inv.setItem(4, item3);
		item3 = null;
	}
	@Override
	public void Click(InventoryClickEvent e) {
		switch (e.getRawSlot()) {
		case 1:
			if ((e.getWhoClicked() instanceof Player) && (dspme.main.dspga.GameInit(((Player) e.getWhoClicked())))) {
				new OnioniGame((Player) e.getWhoClicked(), dspme.main.dspga);
				e.getWhoClicked().openInventory(new InventoryGamesNow(dspme).getInventory());
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("新しくゲーム\"").append(ChatColor.GOLD.toString()).append(OnioniGame.name).append(ChatColor.AQUA.toString()).append("\"を始めました").toString());
			} else {
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ゲームに参加しているか、ゲームを開始できませんでした").toString());
			}
			break;
		case 2:
			if ((e.getWhoClicked() instanceof Player) && (dspme.main.dspga.GameInit(((Player) e.getWhoClicked())))) {
				new MonsterOreGame(dspme.main.dspga, (Player) e.getWhoClicked());
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("新しくゲーム\"").append(ChatColor.GOLD.toString()).append(MonsterOreGame.name).append(ChatColor.AQUA.toString()).append("\"を始めました").toString());
			} else {
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ゲームに参加しているか、ゲームを開始できませんでした").toString());
			}
			break;
		case 3:
			if ((e.getWhoClicked() instanceof Player) && (dspme.main.dspga.GameInit(((Player) e.getWhoClicked())))) {
				new TosochuMultiGame(dspme.main.dspga, (Player) e.getWhoClicked());
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("新しくゲーム\"").append(ChatColor.GOLD.toString()).append(TosochuMultiGame.name).append(ChatColor.AQUA.toString()).append("\"を始めました").toString());
			} else {
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ゲームに参加しているか、ゲームを開始できませんでした").toString());
			}
			break;
		case 4:
			if ((e.getWhoClicked() instanceof Player) && (dspme.main.dspga.GameInit(((Player) e.getWhoClicked())))) {
				new PvPGame(dspme.main.dspga, (Player) e.getWhoClicked());
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.AQUA.toString()).append("新しくゲーム\"").append(ChatColor.GOLD.toString()).append(PvPGame.name).append(ChatColor.AQUA.toString()).append("\"を始めました").toString());
			} else {
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSPG]").append(ChatColor.RED.toString()).append("ゲームに参加しているか、ゲームを開始できませんでした").toString());
			}
			break;
		default:
			break;
		}
	}
}
