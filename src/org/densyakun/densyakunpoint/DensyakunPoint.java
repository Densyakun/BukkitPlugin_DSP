package org.densyakun.densyakunpoint;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DensyakunPoint{
	public static final File dir=new File("D:/DP/");
	public static final File dir_p=new File(dir,"Player/");
	static List<DensyakunPointListener>listeners=new ArrayList<DensyakunPointListener>();
	public static final String ver="2.0";
	public static void init(){
		dir.mkdirs();
		dir_p.mkdirs();
	}
	public static void addListener(DensyakunPointListener listener){
		listeners.add(listener);
	}
	public File file;
	public String name;
	public double point=0;
	public DensyakunPoint(String name)throws IOException{
		this.file=new File(dir_p,name+".dat");
		this.name=name;
		load();
	}
	public File getFile(){
		return file;
	}
	public String getName(){
		return name;
	}
	public void setPoint(double point)throws IOException{
		double b=this.point;
		this.point=point;
		save();
		//TODO rankreload();
		for(int a=0;a<listeners.size();a++)listeners.get(a).PointChanged(b,this);
	}
	public double getPoint(){
		return point;
	}
	public int getRank()throws IOException{
		File[]a=dir_p.listFiles();
		List<Object>c=new ArrayList<Object>();
		for(int b=0;b<a.length;b++){
			DensyakunPoint d=new DensyakunPoint(a[b].getName().split("\\.")[0]);
			if(point<=d.point)c.add(d);
		}
		return c.size();
	}
	/*public void rankreload(){
		//TODO
	}*/
	private void load()throws IOException{
		if(!file.exists()){
			file.createNewFile();
			point=0;
			save();
		}else{
			BufferedReader r=new BufferedReader(new FileReader(file));
			point=Double.valueOf(r.readLine());
			r.close();
		}
	}
	private void save()throws IOException{
		if(!file.exists())file.createNewFile();
		BufferedWriter w=new BufferedWriter(new FileWriter(file));
		w.write(String.valueOf(point));
		w.close();
	}
	/*public static DensyakunPoint getRank(int rank){
		File[]a=dir_p.listFiles();
		List<Object>c=new ArrayList<Object>();
		for(int b=0;b<a.length;b++){
			DensyakunPoint d=new DensyakunPoint(a[b].getName().split("\\.")[0]);
			if(point<=d.point)c.add(d);
		}
		return null;
	}
	public static List<DensyakunPoint>getRanks(){
		//TODO
		return null;
	}*/
}