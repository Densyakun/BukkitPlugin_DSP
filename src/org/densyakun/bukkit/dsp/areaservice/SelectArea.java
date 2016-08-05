package org.densyakun.bukkit.dsp.areaservice;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
public class SelectArea{
	public Block a;
	public Block b;
	public Player c;
	public SelectArea(Block a,Block b,Player d){
		a(a);
		b(b);
		c=d;
	}
	public void a(Block b){
		a=b;
		re();
	}
	public void b(Block a){
		b=a;
		re();
	}
	private void re(){
		if((a!=null)&&(b!=null)){
			World g=a.getWorld();
			Location h=a.getLocation();
			Location i=b.getLocation();
			if(h.getX()>i.getX()){
				h.setX(i.getX());
				i.setX(c.getLocation().getX());
			}
			if(h.getY()>i.getY()){
				h.setY(i.getY());
				i.setY(c.getLocation().getY());
			}
			if(h.getZ()>i.getZ()){
				h.setZ(i.getZ());
				i.setZ(c.getLocation().getZ());
			}
			a=g.getBlockAt(h);
			b=g.getBlockAt(i);
		}
	}
}