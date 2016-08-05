package org.densyakun.bukkit.dsp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
public class News{
	public static String news = new String();
	public static void sendNews(Server s,int hour,Player[] players){
		String bhour=null;
		if((hour>=0)&&(hour<=11))bhour="午前"+hour+"時";
		else if((hour>=12)&&(hour<=23))bhour="午後"+(hour-12)+"時";
		else bhour=hour+"時";
		for(int a=0;a<players.length;a++){
			players[a].sendMessage(ChatColor.GOLD+"--------電鯖ニュース--------\n"+bhour+"になりました。電鯖ニュースをお伝えします\n"+news+"----------------------------\nプレイヤー情報:\n");
			if(Playernews.playernews.length!=0)for(int b=0;b<Playernews.playernews.length;b++)players[a].sendMessage(ChatColor.GOLD+Playernews.playernews[a]);
			else players[a].sendMessage(ChatColor.GOLD+"    なし\n--------電鯖ニュース-----end\n");
			Player player=players[a];
			if(player.getName().equalsIgnoreCase("E231kei"))if(new File(Main.dir+"/news/player/").listFiles().length!=0)player.sendMessage(ChatColor.GOLD+"[DSPN] - ニュース登録申し込みが届いています");
			if(player.hasPermission("dsp.admin")){
				File maildir=new File(Main.dir+"/mail/AdminMail/");
				File[] mails=maildir.listFiles();
				if(maildir.exists()&&mails.length!=0){
					player.sendMessage(ChatColor.GOLD+"[DSPM] - "+mails.length+"通の報告があります\n");
				}
			}
			File maildir=new File(Main.dir+"/mail/"+player.getName()+"/");
			File[] mails=maildir.listFiles();
			if(maildir.exists()&&mails.length!=0){
				player.sendMessage(ChatColor.GOLD+"[DSPM] - "+mails.length+"通のメールが来ています\n");
			}
		}
		Playernews.playernews=new String[0];
	}
	public static void reload(){
		news = new String();
		new File(Main.dir+"/news/player/").mkdirs();
		new File(Main.dir+"/Locations/").mkdirs();
		String dsnstr="";
		try{
			File dsnfile=new File("C:/Users/Densyakun/Dropbox/JavaScript/DSN/News.js");
			BufferedReader dsnloader=new BufferedReader(new InputStreamReader(new FileInputStream(dsnfile),"Unicode"));
			for(String line;(line=dsnloader.readLine())!=null;)if(!line.equalsIgnoreCase(""))dsnstr+=line+"\n";
			dsnloader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		dsnstr=dsnstr.split("//---DSP---start")[1];
		dsnstr=dsnstr.split("//---DSP---end")[0];
		String[] dsnstrsp=dsnstr.split("\"");
		dsnstr="";
		for(int n=1;n<dsnstrsp.length-1;n+=2)dsnstr+=dsnstrsp[n].split("\"")[0].split("<br>")[0]+"\n";
		news+=dsnstr;
	}
}