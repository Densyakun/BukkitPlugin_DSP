package org.densyakun.bukkit.dsp;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
public class DSPExplosionBlocker implements Listener {
	public Main main;
	public DSPExplosionBlocker(Main main) {
		this.main = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	@EventHandler
	public void ExplosionBlock(EntityExplodeEvent e) {
		e.blockList().clear();
	}
}
