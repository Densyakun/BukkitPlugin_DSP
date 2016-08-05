package org.densyakun.bukkit.dsp.dspbank;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.densyakun.bukkit.dsp.Main;
import org.densyakun.csvm.CSVFile;
public class DSPBank {
	public static CSVFile file = new CSVFile(new File(Main.dir, "Banks.csv"));
	private String name;
	private String password;
	private double dp = 0;
	private DSPBank(String name, String password, double dp) {
		this.setName(name);
		this.setPassword(password);
		this.setDp(dp);
	}
	public static DSPBank createBank(String name, String password) {
		try {
			if (!file.getFile().exists()) {
				file.getFile().createNewFile();
			}
			List<List<String>>a = file.AllRead();
			for (int b = 0; b < a.size(); b++) {
				if (name.equalsIgnoreCase(a.get(b).get(0))) {
					return null;
				}
			}
			a = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		DSPBank a = new DSPBank(name, password, 0);
		a.save();
		return a;
	}
	public static void removeBank(String name) {
		try {
			if (!file.getFile().exists()) {
				file.getFile().createNewFile();
			} else {
				List<List<String>>a = file.AllRead();
				for (int b = 0; b < a.size(); b++) {
					if (name.equalsIgnoreCase(a.get(b).get(0))) {
						a.remove(b);
						break;
					}
				}
				file.AllWrite(a);
				a = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static DSPBank getBank(String name) {
		try {
			if (!file.getFile().exists()) {
				file.getFile().createNewFile();
			}
			List<List<String>>a = file.AllRead();
			for (int b = 0; b < a.size(); b++) {
				if (name.equalsIgnoreCase(a.get(b).get(0))) {
					return new DSPBank(a.get(b).get(0), a.get(b).get(1), Double.valueOf(a.get(b).get(2)));
				}
			}
			a = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private void save() {
		if ((name != null) && (password != null)) {
			try {
				if (!file.getFile().exists()) {
					file.getFile().createNewFile();
				}
				List<List<String>>a = file.AllRead();
				boolean c = true;
				for (int b = 0; b < a.size(); b++) {
					if (name.equalsIgnoreCase(a.get(b).get(0))) {
						c = false;
						a.get(b).set(0, name);
						a.get(b).set(1, password);
						a.get(b).set(2, String.valueOf(dp));
					}
				}
				if (c) {
					List<String>b = new ArrayList<String>();
					b.add(name);
					b.add(password);
					b.add(String.valueOf(dp));
					a.add(b);
					b = null;
				}
				file.AllWrite(a);
				a = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		save();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		save();
	}
	public double getDp() {
		return dp;
	}
	public void setDp(double dp) {
		this.dp = dp;
		save();
	}
}
