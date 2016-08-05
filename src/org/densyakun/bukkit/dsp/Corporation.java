package org.densyakun.bukkit.dsp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.densyakun.csvm.CSVFile;
public class Corporation {
	public String name;
	public String owner;
	public String date;
	public String info = "";
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	public static File dir = new File(Main.dir, "Co/");
	public static CSVFile corps = new CSVFile(new File(dir, "corps.csv"));
	public Corporation(final String name, final String owner) {
		this.name = name;
		this.owner = owner;
		date = format.format(new Date());
	}
	public static void init() {
		dir.mkdirs();
		try {
			if (!corps.getFile().exists()) {
				corps.getFile().createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void addCorp(final Corporation corp) {
		try {
			List<List<String>>data = corps.AllRead();
			List<String>line = new ArrayList<String>();
			line.add(corp.name);
			line.add(corp.owner);
			line.add(corp.date);
			line.add(corp.info);
			data.add(line);
			corps.AllWrite(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void delCorp(final String name) {
		try {
			List<List<String>>data = corps.AllRead();
			for (int a = 0; a < data.size(); a++) {
				if (data.get(a).get(0).equalsIgnoreCase(name)) {
					data.remove(a);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<Corporation>getCorps() {
		try {
			List<List<String>>corpds = corps.AllRead();
			List<Corporation>corps = new ArrayList<Corporation>();
			for (int a = 0; a < corpds.size(); a++) {
				List<String>line = corpds.get(a);
				Corporation corp = new Corporation(line.get(0), line.get(1));
				corp.date = line.get(2);
				if (line.size() == 4) {
					corp.info = line.get(3);
				} else {
					corp.info = "";
				}
				corps.add(corp);
			}
			return corps;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Corporation getCorp(final String name) {
		try {
			List<List<String>>data = corps.AllRead();
			for (int a = 0; a < data.size(); a++) {
				if (data.get(a).get(0).equalsIgnoreCase(name)) {
					List<String>line = data.get(a);
					Corporation corp = new Corporation(line.get(0), line.get(1));
					corp.date = line.get(2);
					if (line.size() == 4) {
						corp.info = line.get(3);
					} else {
						corp.info = "";
					}
					return corp;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean exist(final String name) {
		try {
			List<List<String>>data = corps.AllRead();
			for (int a = 0; a < data.size(); a++) {
				if (data.get(a).get(0).equalsIgnoreCase(name)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void clear() {
		corps.getFile().delete();
		dir.delete();
		init();
	}
}
