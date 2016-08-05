package org.densyakun.bukkit.dsp.areaservice;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.densyakun.csvm.CSVFile;
public class AreaManager{
	public DSAS a;
	public CSVFile b;
	public List<SelectArea>c;
	public List<Area>d;
	public AreaManager(DSAS c){
		d(c);
		a();
	}
	public void a(){
		d.clear();
		try{
			List<List<String>>h=b.AllRead();
			for(int e=0;e<h.size();e++){
				Area f=new Area(a.main.getServer().getWorld(h.get(e).get(0)).getBlockAt(Integer.parseInt(h.get(e).get(1)),Integer.parseInt(h.get(e).get(2)),Integer.parseInt(h.get(e).get(3))),a.main.getServer().getWorld(h.get(e).get(0)).getBlockAt(Integer.parseInt(h.get(e).get(4)),Integer.parseInt(h.get(e).get(5)),Integer.parseInt(h.get(e).get(6))));
				f.f(Integer.parseInt(h.get(e).get(7)));
				if(!h.get(e).get(8).equals("null"))f.a(h.get(e).get(8));
				if(!h.get(e).get(9).equals("null"))f.b(h.get(e).get(9));
				if(!h.get(e).get(10).equals("null"))f.c(h.get(e).get(10));
				f.d(Boolean.valueOf(h.get(e).get(11)));
				f.e(Boolean.valueOf(h.get(e).get(12)));
				f.g(Boolean.valueOf(h.get(e).get(13)));
				f.h(Double.valueOf(h.get(e).get(14)));
				d.add(f);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void b(){
		List<List<String>>a=new ArrayList<List<String>>();
		for(int b=0;b<d.size();b++){
			List<String>e=new ArrayList<String>();
			e.add(d.get(b).h.getName());
			e.add(d.get(b).a.getX()+"");
			e.add(d.get(b).a.getY()+"");
			e.add(d.get(b).a.getZ()+"");
			e.add(d.get(b).b.getX()+"");
			e.add(d.get(b).b.getY()+"");
			e.add(d.get(b).b.getZ()+"");
			e.add(d.get(b).i+"");
			if(d.get(b).c==null)e.add("null");
			else e.add(d.get(b).c);
			if(d.get(b).d==null)e.add("null");
			else e.add(d.get(b).d);
			if(d.get(b).e==null)e.add("null");
			else e.add(d.get(b).e);
			e.add(d.get(b).f+"");
			e.add(d.get(b).g+"");
			e.add(d.get(b).j+"");
			e.add(d.get(b).k+"");
			a.add(e);
		}
		try{
			b.AllWrite(a);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void c(){
		b();
		a();
	}
	public void d(DSAS f){
		this.a=f;
		b=new CSVFile(new File(a.a,"AreaData.csv"));
		try{
			if(!b.getFile().exists())b.getFile().createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		c=new ArrayList<SelectArea>();
		d=new ArrayList<Area>();
	}
	public SelectArea e(Player a){
		for(int b=0;b<c.size();b++)if(c.get(b).c==a)return c.get(b);
		SelectArea b=new SelectArea(null,null,a);
		c.add(b);
		return b;
	}
	public Area f(Block a){
		for(int b=0;b<d.size();b++){
			Area e=d.get(b);
			if (((e.a.getX() <= a.getX()) && (a.getX() <= e.b.getX())) &&
					((e.a.getY() <= a.getY()) && (a.getY() <= e.b.getY())) &&
					((e.a.getZ() <= a.getZ()) && (a.getZ() <= e.b.getZ()))) {
				return e;
			}
			//if((((a.getX()>=e.a.getX())&&(a.getX()<=e.b.getX()))&&((a.getY()>=e.a.getY())&&(a.getY()<=e.b.getY())))&&((a.getZ()>=e.a.getZ())&&(a.getZ()<=e.b.getZ())))return e;
			//if((((e.a.getX()<=a.getX())&&(a.getX()<=e.b.getX()))&&((e.a.getY()<=a.getY())&&(a.getY()<=e.b.getY())))&&((e.a.getZ()<=a.getZ())&&(a.getZ()<=e.b.getZ())))return e;
		}
		return null;
	}
	public void g(Area a){
		a.i=h(0);
	}
	private int h(int a){
		for(int b=0;b<d.size();b++)if(d.get(b).i==a)return h(a+1);
		return a;
	}
	public Area i(int a){
		for(int b=0;b<d.size();b++)if(a==d.get(b).i)return d.get(b);
		return null;
	}
	public Area i(String a){
		for(int b=0;b<d.size();b++)if(a.equalsIgnoreCase(d.get(b).c)||a.equalsIgnoreCase(d.get(b).d))return d.get(b);
		return null;
	}
}