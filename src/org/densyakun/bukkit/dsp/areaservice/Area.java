package org.densyakun.bukkit.dsp.areaservice;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
public class Area{
	public Block a;
	public Block b;
	public String c;
	public String d;
	public String e;
	public boolean f=true;
	public boolean g=true;
	public World h;
	public int i;
	public boolean j=false;
	public double k=0;
	public Area(SelectArea a){
		this(a.a,a.b);
	}
	public Area(Block c,Block d){
		h=c.getWorld();
		Location f=c.getLocation();
		Location g=d.getLocation();
		if(f.getX()>g.getX()){
			f.setX(g.getX());
			g.setX(c.getLocation().getX());
		}
		if(f.getY()>g.getY()){
			f.setY(g.getY());
			g.setY(c.getLocation().getY());
		}
		if(f.getZ()>g.getZ()){
			f.setZ(g.getZ());
			g.setZ(c.getLocation().getZ());
		}
		a=h.getBlockAt(f);
		b=h.getBlockAt(g);
	}
	public void a(String a){
		c=a;
	}
	public void b(String a){
		d=a;
	}
	public void c(String a){
		e=a;
	}
	public void d(boolean a){
		f=a;
	}
	public void e(boolean a){
		g=a;
	}
	public void f(int a){
		i=a;
	}
	public void g(boolean a){
		j=a;
	}
	public void h(double a){
		k=a;
	}
	public Location j(){
		Location c=new Location(h,(a.getX()+b.getX())/2,(a.getY()+b.getY())/2,(a.getZ()+b.getZ())/2);
		return c;
	}
}