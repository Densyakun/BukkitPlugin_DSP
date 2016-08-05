package org.densyakun.bukkit.dsp.dsm;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

import org.bukkit.event.Listener;
import org.densyakun.bukkit.dsp.Main;
public class DSPMap implements Runnable, Listener {
	public static File dir = new File(Main.dir, "DSPMap/");
	public Main main;
	public boolean run = true;
	public ServerSocket server;
	public static final String ver = "1.0";
	public MapGenerator mapgen;
	public DSPMap(Main main) {
		this.main = main;
		dir.mkdirs();
		mapgen = new MapGenerator(this);
		/*main.getServer().getPluginManager().registerEvents(this, main);
		main.getServer().getPluginManager().registerEvents(mapgen, main);
		new Thread(this).start();*/
	}
	public void stop() {
		run = false;
		/*try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	@Override
	public void run() {
		while (run) {
			try {
				server = new ServerSocket(50011);
				System.out.println("[DSPMap]接続可能");
				while (run) {
					Socket socket = server.accept();
					System.out.println(new StringBuffer("[DSPMap]接続: ").append(socket.getInetAddress().getHostAddress()).toString());
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					Object obj = ois.readObject();
					if ((obj instanceof String) && (((String) obj).equals(new StringBuffer("DSP-DSM").append(ver).toString()))) {
						oos.writeChars("DSP-DSM-Accept");
						oos.flush();
						while ((obj = ois.readObject()) != null) {
							if (obj instanceof String) {
								int x = Integer.valueOf(((String) obj).split("_")[0]);
								int z = Integer.valueOf(((String) obj).split("_")[1]);
								oos.writeObject(ImageIO.read(new File(MapGenerator.dir, new StringBuffer("ChunkImage_").append(x).append("_").append(z).append(".png").toString())));
							}
						}
					}
					obj = null;
					ois.close();
					oos.close();
					socket.close();
				}
				server.close();
			} catch (Exception e) {  
				e.printStackTrace();
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
