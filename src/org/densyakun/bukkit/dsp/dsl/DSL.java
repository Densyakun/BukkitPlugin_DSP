package org.densyakun.bukkit.dsp.dsl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.densyakun.bukkit.dsp.Main;
public class DSL implements Listener {
	//public static final String LOGFILE = "DSL.log";
	Main main;
	//private Logger logger = null;
	public DSL(Main main) {
		this.main = main;
		/*logger = Logger.getLogger(this.getClass().getName());
		try {
			FileHandler fh = new FileHandler(LOGFILE, true);
			fh.setFormatter(new java.util.logging.SimpleFormatter());
			logger.addHandler(fh);
		} catch (IOException x) {
			x.printStackTrace();
		}*/
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		System.out.println(e.getBlock().getLocation().distance(e.getPlayer().getEyeLocation()));
		if (6.5 < e.getBlock().getLocation().distance(e.getPlayer().getEyeLocation())) {
			e.getPlayer().kickPlayer("DSLにより自動BANされました\n詳しくはWikiをご覧下さい");
			e.getPlayer().setBanned(true);
			main.getLogger().severe(new StringBuffer("DSL-BBr: プレイヤー\"").append(e.getPlayer().getName()).append("\"をHackingClientと判断しました").toString());
		}
		System.out.println(new StringBuffer("DSL-BBr:").append(e.getBlock().getLocation().toString()).append(",").append(e.getBlock().getType()).append(",").append(e.getPlayer().getName()).append(",").append(e.getPlayer().getItemInHand().toString()).toString());
		//logger.log(Level.FINE, e.getEventName(), new StringBuffer("Block: ").append(e.getBlock().getLocation().toString()).append(", ").append(e.getBlock().getType()).append(" Player: ").append(e.getPlayer().getLocation()).append(", ").append(e.getPlayer().getName()).toString());
	}
	/*@EventHandler
	public void BlockBurn(BlockBurnEvent e) {
		logger.log(Level.FINE, e.getEventName(), new StringBuffer("Block: ").append(e.getBlock().getLocation().toString()).append(", ").append(e.getBlock().getType()).toString());
	}*/
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e) {
		/*System.out.println(e.getBlock().getLocation().distance(e.getPlayer().getEyeLocation()));
		if (6.5 < e.getBlock().getLocation().distance(e.getPlayer().getEyeLocation())) {
			e.getPlayer().kickPlayer("DSLにより自動BANされました\n詳しくはWikiをご覧下さい");
			e.getPlayer().setBanned(true);
			main.getLogger().severe(new StringBuffer("DSL-BP: プレイヤー\"").append(e.getPlayer().getName()).append("\"をHackingClientと判断しました").toString());
		}*/
		System.out.println(new StringBuffer("DSL-BP:").append(e.getBlock().getLocation().toString()).append(",").append(e.getBlock().getType()).append(",").append(e.getPlayer().getName()).append(",").append(e.getPlayer().getItemInHand().toString()).toString());
		//logger.log(Level.FINE, e.getEventName(), new StringBuffer("Block: ").append(e.getBlock().getLocation().toString()).append(", ").append(e.getBlock().getType()).append(" Player: ").append(e.getPlayer().getLocation()).append(", ").append(e.getPlayer().getName()).toString());
	}
	@EventHandler
	public void EntityDamageByEntity(EntityDamageByEntityEvent e) {
		/*if (e.getDamager() instanceof LivingEntity) {
			System.out.println(((LivingEntity) e.getDamager()).getEyeLocation().distance(e.getEntity().getLocation()));
		}
		if (e.getDamager() instanceof Player) {
			System.out.println(new StringBuffer("DSL-EDBE[0]:").append(((Player) e.getDamager()).getName()).toString());
			if (6.5 < ((Player) e.getDamager()).getEyeLocation().distance(e.getEntity().getLocation())) {
				((Player) e.getDamager()).kickPlayer("DSLにより自動BANされました\n詳しくはWikiをご覧下さい");
				((Player) e.getDamager()).setBanned(true);
				main.getLogger().severe(new StringBuffer("DSL-EDBE[1]: プレイヤー\"").append(((Player) e.getDamager()).getName()).append("\"をHackingClientと判断しました").toString());
			}
		}*/
		if (e.getEntity() instanceof Player) {
			System.out.println(new StringBuffer("DSL-EDBE[2]:").append(((Player) e.getEntity()).getName()).toString());
		}
		System.out.println(new StringBuffer("DSL-EDBE[3]:").append(e.getDamage()).append(",").append(e.getCause()).append(",").append(e.getDamager().getLocation().toString()).append(",").append(e.getDamager().getUniqueId().toString()).append(",").append(e.getDamager().getType()).append(",").append(e.getEntity().getLocation().toString()).append(",").append(e.getEntity().getUniqueId().toString()).append(",").append(e.getEntity().getType()).toString());
	}
	@EventHandler
	public void EntityDeath(EntityDeathEvent e) {
		String a = null;
		if (e.getEntity().getKiller() != null) {
			a = e.getEntity().getKiller().getName();
		}
		System.out.println(new StringBuffer("DSL-EDe:").append(e.getEntity().getLastDamage()).append(",").append(a).append(",").append(e.getEntity().getLocation().toString()).append(",").append(e.getEntity().getType()).toString());
	}
}
