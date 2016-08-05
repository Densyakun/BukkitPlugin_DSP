package org.densyakun.bukkit.dsp.dsq;
import org.densyakun.bukkit.dsp.dspgames.MonsterOreGame;
import org.densyakun.bukkit.dsp.dspgames.OnioniGame;
import org.densyakun.bukkit.dsp.dspgames.TosochuMultiGame;
public class Quiz {
	public static String ver = "0.2";
	public static Quiz[] quizs = new Quiz[]{
		new Quiz("電鯖が始まった年は?", new String[]{"2013年", "-2013年", "1023年", "2012年", "2014年", "2015年", "2016年"}, "電鯖が始まったのは2013年3月29日でもうすぐ開業から2年が経ちます。ぜひこれからも電鯖へお越しください"),
		new Quiz("リビッツ鉄道で最初に開業した路線は?", new String[]{"中央環状線", "中央臨海線", "臨海線", "臨海環状線", "川崎線", "大森線", "中央線"}, "中央環状線は現在の路線名として知られていますが、当時は環状運転を行っていない\"裕福線\"(ゆうふくせん)で、現在の中央駅である当時の\"福田駅\"と現在の大森駅である当時の\"裕穣駅\"(ゆうじょうえき)を結ぶ路線である。中央環状線をぜひご利用ください"),
		new Quiz("電鯖の管理人の正式な名前は?", new String[]{"電車君", "電気君", "電丸", "電磁君", "電磁気発生くん", "電車くん", "でんしゃくん", "電車", "ビリビリ", "電車男", "鉄道ファン", "でんしゃ君", "電車菌", "鉄道君", "車輪君", "汽車君"}, "もちろん電車君サーバーの管理人は電車君です。他のサイトなどで電車君の文字を使用する際はすべて漢字ですのでご注意を!"),
		new Quiz("電鯖はなんの略?", new String[]{"電車君サーバー", "電気君サーバー", "電気ビリビリサーバー", "感電サーバー", "電車くんサーバー", "電車が走れそうなぐらい細長い鯖"}, "電鯖の略は電車君サーバーです。電車君がやっています。ぜひこれからもお越しください"),
		new Quiz("LRはなんの略?", new String[]{"リビッツ鉄道", "リビッツロビッツ", "リトル鉄道", "レッドストーンリピーター"}, "リビッツ鉄道とは電鯖で最初に開業した鉄道会社で電鯖で一番大きい鉄道会社です。ぜひご利用ください"),
		new Quiz("DPはなんの略?", new String[]{"電車君ポイント", "電磁気発生ポイント", "電車が通るポイント", "民主党"}, "電車君ポイントとは電鯖での通貨である。ぜひゲームを遊んでDPを増やしましょう"),
		new Quiz("電鯖で一番目指してることといえば?", new String[]{"リアリティ", "世界崩壊", "世界征服", "研究", "地形崩壊", "自然破壊", "森林火災", "マインドコントロール"}, "電鯖ではリアルを目指しています。他にはサバイバルを重視したり日々RS回路やプラグインの研究も行っています。ぜひ研究にご協力ください"),
		new Quiz("一番最初にできたミニゲームは?", new String[]{OnioniGame.name, MonsterOreGame.name, TosochuMultiGame.name, "KameKame"}, new StringBuffer(OnioniGame.name).append("は鬼ごっこです。ぜひ一度遊んでみてください").toString()),
		new Quiz("最初に1.7のバイオームを管理した区市町村の名前は?", new String[]{"栗山市", "クリサラ市", "中央区", "1.7区", "1.7市", "雪乃村"}, "栗山市の市長はクリサラ(curisara0413)さんです"),
		new Quiz("最初に雪原バイオームを手にした都道府県は?", new String[]{"中央都", "雪野原県", "雪乃県", "雪ヶ原県"}, "雪野原県と中央都は陸が繋がっていません")
	};
	public String title;
	public String[] answers;
	public String info;
	public Quiz(String title, String[] answers, String info) {
		this.title = title;
		this.answers = answers;
		this.info = info;
	}
}
