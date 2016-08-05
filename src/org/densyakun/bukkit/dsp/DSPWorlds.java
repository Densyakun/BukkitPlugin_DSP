package org.densyakun.bukkit.dsp;
import org.bukkit.World;
import org.bukkit.event.Listener;
public class DSPWorlds implements Listener{
	public Main main;
	public World world_portal;
	public World world_kyozyuku;
	public DSPWorlds(Main main) {
		this.main = main;
		world_portal = main.getServer().getWorld("PortalWorld");
		world_kyozyuku = main.getServer().getWorld("kyozyuku");
		main.getServer().getPluginManager().registerEvents(this, main);
	}
}
