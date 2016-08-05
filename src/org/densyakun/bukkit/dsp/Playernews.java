package org.densyakun.bukkit.dsp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
public class Playernews{
	public static String[]playernews=new String[0];
	public static void addPlayernews(Player player,int Type){
		if(Type>=1)return;
		if(playernews.length==0)playernews=new String[1];
		else{
			String[]old=playernews;
			playernews=new String[old.length+1];
			for(int a=0;a<old.length;a++)playernews[a]=old[a];
		}
		switch(Type){
		case 0:playernews[playernews.length-1]=new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒").format(new Date())+player.getName()+"さんが生まれ変わりました";
		}
	}
}