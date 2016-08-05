package org.densyakun.bukkit.dsp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.Player;
public class DSPPlayer {
	public static void Init() {
		new File(Main.dir, "P/").mkdirs();
	}
	public static void PlayerLogin(final Player player, final String address) {
		try {
			System.out.println(new StringBuffer("[DSPP] - ").append(player.getName()).append("がログインしました。アドレス: ").append(address).toString());
			File lpf = new File(Main.dir, new StringBuffer("P/").append(player.getName()).append(".txt").toString());
			if (!lpf.exists()) {
				lpf.createNewFile();
				BufferedWriter lpfw = new BufferedWriter(new FileWriter(lpf));
				lpfw.write(new StringBuffer(player.getName()).append("\n").append(address).toString());
				lpfw.close();
			} else {
				BufferedReader lpfr = new BufferedReader(new FileReader(lpf));
				String line;
				for (int a = 0; (line = lpfr.readLine()) != null; a++) {
					if (a==1) {
						if (!line.equalsIgnoreCase(address)) {
							System.out.println(new StringBuffer(player.getName()).append("のIPアドレスが登録されたIPアドレスと異なります 登録IP: ").append(line).append(" 現在のIP: ").append(address).toString());
						}
					}
				}
				lpfr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
