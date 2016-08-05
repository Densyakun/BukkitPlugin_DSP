package org.densyakun.bukkit.dsp;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.densyakun.bukkit.dsp.areaservice.DSAS;
import org.densyakun.bukkit.dsp.dev.DSPDev;
import org.densyakun.bukkit.dsp.dsl.DSL;
import org.densyakun.bukkit.dsp.dsm.DSPMap;
import org.densyakun.bukkit.dsp.dsoi.DSOI;
import org.densyakun.bukkit.dsp.dspgames.DSPGames;
import org.densyakun.bukkit.dsp.dspmenu.DSPMenu;
import org.densyakun.bukkit.dsp.dsprouting.DSPRouting;
import org.densyakun.bukkit.dsp.dsprouting.Point;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class Main extends JavaPlugin implements Runnable, Listener, ActionListener {
	String pro = "Author: Densyakun\n" +
			"DensyakunServerPluginPlayer[DSPP] Densyakun\n" +
			"DensyakunServerPluginCorporation[DSPCo] Densyakun\n" +
			"DensyakunServerPluginTNTBlocker[DSPTNTB] Densyakun\n" +
			"DensyakunServerPluginEntityControl[DSPEC] Densyakun\n" +
			"DensyakunServerPluginWorlds[DSPWo] Densyakun\n" +
			"DensyakunServerPluginGames[DSPG] Densyakun\n" +
			"DensyakunServerPluginWeather[DSPWa] Densyakun\n" +
			"DensyakunServerPluginReload[DSPRe] Densyakun\n" +
			"DensyakunServerPluginNews[DSPN] Densyakun\n" +
			"DensyakunServerPluginRouting[DSPRo] Densyakun\n" +
			"DensyakunServerWeatherRealTimeSystem[DSWRS] Densyakun\n" +
			"DensyakunServerPluginPlayerNews[DSPPN] Densyakun\n" +
			"DensyakunServerPluginMail[DSPMai] Densyakun\n" +
			"DensyakunServerPluginAdmin[DSPAd] Densyakun\n" +
			"DensyakunServerPluginRet[DSPRe] Densyakun\n" +
			"DensyakunServerPluginCalc[DSPCa] Densyakun\n" +
			"DensyakunServerPluginCreative[DSPCr] Densyakun\n" +
			"DensyakunServerPluginMenu[DSPMe] Densyakun\n" +
			"DensyakunServerPluginTreasureChest[DSPTC] Densyakun\n" +
			"DensyakunServerPluginExplosionBlocker[DSPEB] Densyakun\n" +
			"DensyakunServerPluginMap[DSPMap] Ver" + DSPMap.ver + " Densyakun\n" +
			"DensyakunServerPluginBank[DSPBa] Densyakun\n" +
			"DensyakunServerAreaService[DSAS] Densyakun\n" +
			"DensyakunServerOriginalItems[DSOI] Ver" + DSOI.ver + " Densyakun\n" +
			"DensyakunServerQuiz[DSQ] Densyakun\n" +
			"DensyakunServerPluginDev[DSPD] Densyakun\n" +
			"DensyakunServerLogging[DSL] Densyakun";
	AutoReload reloaderc;
	Thread reloader;
	public static final String CWN = "kyozyuku";
	public static final String Creativewn = "CreativeWorld";
	public static final File dir = new File("./plugins/DSP/");
	public TrayIcon tray;
	public DSPWorlds dspwo;
	public DSPGames dspga;
	public DSPMenu dspme;
	public DSPAdmin dspad;
	public DSPTreasureChest dsptc;
	public DSPExplosionBlocker dspeb;
	public DSPMap dspmap;
	public DSPRouting dspro;
	public DSAS dsas;
	public DSOI dsoi;
	public DSPDev dspdev;
	public DSL dsl;
	@Override
	public void run() {
	}
	public void onEnable(){
		new Thread(this).start();
		System.out.println("[DSP] - 初期化中...");
		try{
			PopupMenu menu=new PopupMenu();
			MenuItem item0=new MenuItem("プレイヤーリスト");
			menu.add(item0);
			item0.addActionListener(this);
			SystemTray.getSystemTray().add(tray=new TrayIcon(ImageIO.read(new File("./server-icon.png")),"DSP",menu));
			tray.displayMessage("DSP","初期化中...",MessageType.INFO);
		}catch(IOException|AWTException e){
			e.printStackTrace();
		}
		//[DSL]
		dsl = new DSL(this);
		//[DSPP]
		DSPPlayer.Init();
		//[DSPCo]
		Corporation.init();
		//[DSPN]
		News.reload();
		//[DSPCr]
		InventoryContent.invdir.mkdirs();
		//[DSPCa]
		Calc.Init();
		//[DSPR]
		reloaderc=new AutoReload();
		reloader=new Thread(reloaderc);
		reloader.start();
		reloaderc.server = getServer();
		//[DSPWo]
		dspwo = new DSPWorlds(this);
		//[DSPG]
		dspga = new DSPGames(this);
		//[DSPMe]
		dspme = new DSPMenu(this, dspwo);
		//[DSPAd]
		dspad = new DSPAdmin(this);
		//[DSPTC]
		dsptc = new DSPTreasureChest(this, dspwo);
		//[DSPEB]
		dspeb = new DSPExplosionBlocker(this);
		//[DSPMap]
		dspmap = new DSPMap(this);
		//[DSPRo]
		dspro = new DSPRouting(this);
		//[DSAS]
		dsas = new DSAS(this);
		//[DSOI]
		dsoi = new DSOI(this);
		//[DSPD]
		dspdev = new DSPDev(this);
		getServer().getPluginManager().registerEvents(this, this);
		System.out.println("[DSP] - 初期化完了");
		tray.displayMessage("DSP", "初期化完了", MessageType.INFO);
	}
	public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[]args){
		try{
			Player player=null;
			if(sender instanceof Player){
				player=(Player)sender;
				String labela = "/" + label;
				for(int a = 0; a < args.length; a++)labela += " " + args[a];
				System.out.println("[DSP] - "+player.getName()+"が"+labela+"と入力しました");
				tray.displayMessage("DSP",player.getName()+"が"+labela+"と入力しました",MessageType.INFO);
				if(label.equalsIgnoreCase("dsp_pro")){
					if(args.length!=0) {
						sender.sendMessage("[DSPC] - 正常に動きますが、パラメーターが多すぎます");
					}
					sender.sendMessage(ChatColor.AQUA+pro);
				}else if(label.equalsIgnoreCase("dsp_c_add")){
					if(args.length==0) {
						return parerror(sender);
					}
					if(args.length>2) {
						sender.sendMessage("[DSPC] - 正常に動きますが、パラメーターが多すぎます");
					}
					if(Corporation.exist(args[0])) {
						sender.sendMessage("[DSPCo] - 同じ名前の会社が既に存在します");
					} else{
						Corporation corp=new Corporation(args[0],player.getName());
						if(args.length==2) {
							corp.info=args[1];
						}
						Corporation.addCorp(corp);
						sender.sendMessage(ChatColor.BLUE+"[DSPCo] - 会社を作成しました 会社名:"+args[0]+"社長:"+player.getName());
					}
				}else if(label.equalsIgnoreCase("dsp_c_del")||label.equalsIgnoreCase("dsp_c_delete")){
					if(args.length==0) {
						return parerror(sender);
					}
					if(args.length>2) {
						sender.sendMessage("[DSPC] - 正常に動きますが、パラメーターが多すぎます");
					}
					if(Corporation.exist(args[0])){
						if(Corporation.getCorp(args[0]).owner.equalsIgnoreCase(player.getName())){
							Corporation.delCorp(args[0]);
							sender.sendMessage(ChatColor.BLUE+"[DSPCo] - 会社が削除されました 会社名:"+args[0]);
						} else {
							sender.sendMessage("[DSPCo] - 自分の会社のみ削除出来ます");
						}
					} else {
						sender.sendMessage("[DSPCo] - 会社が存在しません");
					}
				}else if(label.equalsIgnoreCase("dsp_c_info")){
					if(args.length==0) {
						return parerror(sender);
					}
					if(args.length!=1) {
						sender.sendMessage("[DSPC] - 正常に動きますが、パラメーターが多すぎます");
					}
					if(Corporation.exist(args[0])){
						Corporation corp=Corporation.getCorp(args[0]);
						sender.sendMessage("[DSPCo]\n"+args[0]+" - 会社情報\n会社名:"+corp.name+"\n社長:"+corp.owner+"\n開業:"+corp.date+"\n会社について:\n"+corp.info);
					} else {
						sender.sendMessage("[DSPCo] - 会社が存在しません");
					}
				}else if(label.equalsIgnoreCase("dsp_c_setinfo")){
					if(args.length==0) {
						return parerror(sender);
					}
					if(args.length!=1) {
						sender.sendMessage("[DSPC] - 正常に動きますが、パラメーターが多すぎます");
					}
					if(Corporation.exist(args[0])){
						Corporation corp=Corporation.getCorp(args[0]);
						if(corp.owner.equalsIgnoreCase(player.getName())){
							Corporation.delCorp(corp.name);
							corp.info=args[1];
							Corporation.addCorp(corp);
						} else {
							sender.sendMessage("[DSPCo] - 自分の会社のみ設定出来ます");
						}
					} else {
						sender.sendMessage("[DSPCo] - 会社が存在しません");
					}
				}else if(label.equalsIgnoreCase("dsp_c_clear")){
					Corporation.clear();
					System.out.println("[DSP][重要][DSPCo] - 会社データを初期化しました");
					sender.sendMessage("[DSPCo] - 会社データを初期化しました");
				}else if(label.equalsIgnoreCase("dsp_c_list")){
					List<Corporation>a=Corporation.getCorps();
					String[]b=new String[a.size()+2];
					b[0]=ChatColor.RED+"[DSP] "+ChatColor.GOLD+"会社一覧";
					int c=0;
					boolean d=true;
					while(d){
						d=false;
						for(int e=0;e<a.size();e++){
							b[e+2]=" "+ChatColor.GOLD+"会社名:"+a.get(e).name+" 社長:"+a.get(e).owner+" 会社情報:"+a.get(e).info+" 開業日時:"+a.get(e).date;
							if(c<b[e+2].length()){
								c=b[e+2].length();
								d=true;
								break;
							}
						}
					}
				}else if(label.equalsIgnoreCase("dsp_reload")){
					sender.sendMessage("[DSP] - リロード中...");
					onDisable();
					onEnable();
					sender.sendMessage("[DSP] - リロード完了");
				}else if(label.equalsIgnoreCase("dsp_w_now")){
					double celsius=Weather.getCelsius(player.getLocation());
					if(player.isFlying()) {
						celsius-=0.1;
					}
					if(player.isSneaking()) {
						celsius+=0.2;
					}
					String cels=celsius+"";
					char a;
					char b;
					char c;
					if(cels.split(".")[0].length()==2){
						a=cels.charAt(1);
						b=cels.charAt(3);
						c=cels.charAt(0);
					}else{
						a=cels.charAt(0);
						b=cels.charAt(2);
						c=0;
					}
					if(Integer.parseInt(b+"")<=5) {
						celsius=Integer.parseInt(c+a+"")+1;
					} else {
						Integer.parseInt(c+a+"");
					}
					sender.sendMessage("[DSPW] - 電車君サーバーウェザー\nあなたの体感温度:"+celsius+"°C");
				}else if(label.equalsIgnoreCase("dsp_n_add")){
					Date time=new Date();
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
					File newsfile=new File(dir+"/news/player/"+player.getName()+"_"+format.format(time)+"_add.txt");
					newsfile.createNewFile();
					BufferedWriter bw=new BufferedWriter(new FileWriter(newsfile));
					bw.write(args[0]);
					bw.close();
					player.sendMessage("[DSPN] - ニュースを追加しましたらメールでお知らせします");
				}else if(label.equalsIgnoreCase("dsp_n_del")||label.equalsIgnoreCase("dsp_n_delete")){
					Date time=new Date();
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
					File newsfile=new File(dir+"/news/player/"+player.getName()+"_"+format.format(time)+"_add.txt");
					newsfile.createNewFile();
					BufferedWriter bw=new BufferedWriter(new FileWriter(newsfile));
					bw.write(args[0]);
					bw.close();
					player.sendMessage("[DSPN] - ニュースを削除しましたらメールでお知らせします");
				}else if(label.equalsIgnoreCase("dsp_n_reload")) {
					News.reload();
				} else if(label.equalsIgnoreCase("dsp_m_send")){
					new File(dir+"/mail/"+args[0]+"/").mkdirs();
					int num=0;
					while(new File(dir+"/mail/"+args[0]+"/"+num+".txt").exists()) {
						num++;
					}
					File mailfile=new File(dir+"/mail/"+args[0]+"/"+num+".txt");
					mailfile.createNewFile();
					BufferedWriter bw=new BufferedWriter(new FileWriter(mailfile));
					bw.write("sender:"+player.getName()+"\ntitle:"+args[1]+"\n"+args[2]);
					bw.close();
					player.sendMessage("[DSPM] - メールを送信しました 宛先:"+args[0]);
				}else if(label.equalsIgnoreCase("dsp_m_del")||label.equalsIgnoreCase("dsp_m_delete")){
					File mailfile=new File(dir+"/mail/"+player.getName()+"/"+args[0]+".txt");
					if(mailfile.exists()){
						mailfile.delete();
						player.sendMessage("[DSPM] - メールを削除しました メール番号:"+args[0]);
					} else {
						player.sendMessage("[DSPM] - メール番号"+args[0]+"のメールは存在しません");
					}
				}else if(label.equalsIgnoreCase("dsp_m_open")){
					File mail=new File(dir+"/mail/"+player.getName()+"/").listFiles()[Integer.parseInt(args[0])];
					String mailsender=null;
					String title=null;
					String message="";
					try{
						BufferedReader br=new BufferedReader(new FileReader(mail));
						String line;
						for(int a=0;(line=br.readLine())!=null;a++){
							if(a==0) {
								mailsender=line.split("sender:")[1];
							} else if(a==1) {
								title=line.split("title:")[1];
							} else {
								message+=line;
							}
						}
						br.close();
					}catch(IOException e){
						e.printStackTrace();
					}
					player.sendMessage("送信元:"+mailsender);
					player.sendMessage("題名:"+title);
					player.sendMessage("本文:\n"+message);
				}else if(label.equalsIgnoreCase("ret")||label.equalsIgnoreCase("dsp_ret")){
					new File(dir+"/mail/AdminMail/").mkdirs();
					int num=0;
					while(new File(dir+"/mail/AdminMail/"+num+".txt").exists()) {
						num++;
					}
					File mailfile=new File(dir+"/mail/AdminMail/"+num+".txt");
					mailfile.createNewFile();
					BufferedWriter bw=new BufferedWriter(new FileWriter(mailfile));
					bw.write("sender:"+player.getName()+"\n"+args[0]);
					bw.close();
					player.sendMessage("[DSPR] - 報告が完了しました。返信をお待ち下さい");
				}else if((label.equalsIgnoreCase("dsp_ret_open")||label.equalsIgnoreCase("ret_open"))&&player.hasPermission("dsp.admin")){
					File mail=new File(dir+"/mail/AdminMail/").listFiles()[Integer.parseInt(args[0])];
					String mailsender=null;
					String message="";
					try{
						BufferedReader br=new BufferedReader(new FileReader(mail));
						String line;
						for(int a=0;(line=br.readLine())!=null;a++){
							if(a==0) {
								mailsender=line.split("sender:")[1];
							} else {
								message+=line;
							}
						}
						br.close();
					}catch(IOException e){
						e.printStackTrace();
					}
					player.sendMessage("送信元:"+mailsender);
					player.sendMessage("本文:\n"+message);
				}else if((label.equalsIgnoreCase("dsp_ret_del")||label.equalsIgnoreCase("dsp_ret_delete"))&&player.hasPermission("dsp.admin")){
					File retfile=new File(dir+"/mail/AdminMail/"+args[0]+".txt");
					if(retfile.exists()){
						retfile.delete();
						sender.sendMessage("[DSPR] - No."+args[0]+"の報告を削除しました");
					} else {
						sender.sendMessage("[DSPR] - No."+args[0]+"の報告は存在しません");
					}
				} else if(label.equalsIgnoreCase("dgs")&&player.hasPermission("dsp.admin")) {
					player.setGameMode(GameMode.SURVIVAL);
				} else if(label.equalsIgnoreCase("dgc")&&player.hasPermission("dsp.admin")) {
					player.setGameMode(GameMode.CREATIVE);
				} else if(label.equalsIgnoreCase("ca")){
					sender.sendMessage("[DSP] - 現在このコマンドは使用できません");
					/*if(args.length>=2)sender.sendMessage("[DSPCa] - 通訳を利用する際、内容文に空白を入れることは出来ません");
					else{
						String str=Calc.calc(args[0]);
						String[] sp=str.split(":c:");
						str="";
						for(int a=1;a<sp.length-1;a+=2)sp[a]=Calc.calc(sp[a]);
						for(int a=0;a<sp.length;a++)str+=sp[a];
						Player[] players=getServer().getOnlinePlayers();
						for(int a=0;a<players.length;a++)players[a].sendMessage(player.getName()+" "+str+" - ([DSPCa]による通訳");
					}*/
				}else if(label.equalsIgnoreCase("crea")||label.equalsIgnoreCase("creative")){
					if(!InventoryContent.Player(player.getName())){
						InventoryContent.Save(this,player);
						player.getInventory().clear();
						player.getInventory().setArmorContents(null);
						player.teleport(new Location(getServer().getWorld(Creativewn),1388,4,290));
						player.setGameMode(GameMode.CREATIVE);
						sender.sendMessage("[DSPCr] - クリエモードになりました。\nバグがありましたら/ret [内容]で報告して下さい。");
						Player[]players=getServer().getOnlinePlayers().toArray(new Player[0]);
						for(int a=0;a<players.length;a++) {
							players[a].sendMessage(ChatColor.GREEN+"[DSPCr] - "+player.getDisplayName()+"がクリエモードになりました。");
						}
					} else {
						sender.sendMessage("[DSPCr] - 既にクリエモードになっています。\nバグの報告は/ret [内容]で出来ます。");
					}
				}else if(label.equalsIgnoreCase("sback")){
					if(InventoryContent.Player(player.getName())){
						try{
							InventoryContent.Load(this,player);
						}catch(ArrayIndexOutOfBoundsException e){
						}
						player.setGameMode(GameMode.SURVIVAL);
						player.teleport(dspwo.world_kyozyuku.getSpawnLocation());
						sender.sendMessage("[DSPCr] - クリエモードから戻りました。");
						Player[]players=getServer().getOnlinePlayers().toArray(new Player[0]);
						for(int a=0;a<players.length;a++) {
							players[a].sendMessage(ChatColor.GREEN+"[DSPCr] - "+player.getDisplayName()+"がクリエモードから戻りました。");
						}
					} else {
						sender.sendMessage("[DSPCr] - クリエモードになっていません。\nバグは/ret [内容]で報告出来ます。");
					}
				} else if(label.equalsIgnoreCase("dsp_tp")&&player.hasPermission("dsp.admin")) {
					Point point = dspro.SearchPoint(args[0]);
					if (point != null) {
						player.teleport(point.location);
						point = null;
					}
				} else if(label.equalsIgnoreCase("dp")){
					DensyakunPoint a=null;
					if(args.length==0){
						try{
							a=new DensyakunPoint(((Player)sender).getName());
							sender.sendMessage(new StringBuffer().append(ChatColor.RED).append("[DSP] ").append(ChatColor.GOLD).append(((Player)sender).getDisplayName()).append(ChatColor.GOLD).append("の統計:\nDP: ").append(ChatColor.WHITE).append(a.getPoint()).append(ChatColor.GOLD).append("\nDPランク: ").append(ChatColor.WHITE).append(a.getRank()).append("位\n").toString());
						}catch(IOException x){
							x.printStackTrace();
						}
					}else{
						try{
							a=new DensyakunPoint(args[0]);
							String b=a.getName();
							Player c=getServer().getPlayer(b);
							if(c!=null){
								b=c.getDisplayName();
								c=null;
							}
							sender.sendMessage(new StringBuffer().append(ChatColor.RED).append("[DSP] ").append(ChatColor.GOLD).append(b).append(ChatColor.GOLD).append("の統計:\nDP: ").append(ChatColor.WHITE).append(a.getPoint()).append(ChatColor.GOLD).append("\nDPランク: ").append(ChatColor.WHITE).append(a.getRank()).append("位\n").toString());
							b=null;
						}
						catch(IOException x){
							x.printStackTrace();
						}
					}
					a=null;
				} else if (label.equalsIgnoreCase("dsp")) {
					if (args.length == 0) {
						sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.RED.toString()).append("パラメーターがありません").toString());
					} else if (args[0].equalsIgnoreCase("menu")) {
						dspme.OpenMenu(player);
					} else if (args[0].equalsIgnoreCase("admin")) {
						if (DSPAdmin.getPermission(sender) != AdminPermissions.other) {
							if (args.length == 1) {
								sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.RED.toString()).append("パラメーターが足りません").toString());
							} else if (args[1].equalsIgnoreCase("emergency") || args[1].equalsIgnoreCase("emg")) {
								dspad.setEMG(!dspad.emergency);
								sender.sendMessage(String.valueOf(dspad.emergency));
							} else if (args[1].equalsIgnoreCase("developing") || args[1].equalsIgnoreCase("dev")) {
								if (args.length == 2) {
									sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.RED.toString()).append("パラメーターが足りません").toString());
								} else {
									try {
										int a = Integer.valueOf(args[2]);
										switch (a) {
										case 0:
											sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPD]").append(ChatColor.RESET.toString()).append(DSPDev.ver).toString());
											break;
										case 1:
											if (dspdev.robotmanager.robot.isDead()) {
												sender.sendMessage("[DSPD]Dead.");
											} else {
												sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPD]").append(ChatColor.RESET.toString()).append(dspdev.robotmanager.robot.getLocation().toString()).toString());
											}
											break;
										default:
											break;
										}
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}
								}
							}
						} else {
							sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.RED.toString()).append("このコマンドを実行する権限がありません").toString());
						}
					} else if (args[0].equalsIgnoreCase("routing") || args[0].equalsIgnoreCase("ro")) {
						if (args.length < 3) {
							sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSP]").append(ChatColor.RED.toString()).append("パラメーターが足りません").toString());
						} else if (args[1].equalsIgnoreCase("add")) {
							File a=new File(dir, new StringBuffer("Locations/").append(args[2]).append("_add.txt").toString());
							a.createNewFile();
							BufferedWriter bw=new BufferedWriter(new FileWriter(a));
							Location loc=player.getLocation();
							bw.write(player.getName()+"\n"+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ());
							bw.close();
							sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("位置情報の登録申し込みが完了しました").toString());
						} else if (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
							File a=new File(dir, new StringBuffer("Locations/").append(args[2]).append("_del.txt").toString());
							a.createNewFile();
							BufferedWriter bw=new BufferedWriter(new FileWriter(a));
							bw.write(player.getName());
							bw.close();
							sender.sendMessage(new StringBuffer(ChatColor.GOLD.toString()).append("[DSPRo]").append(ChatColor.AQUA.toString()).append("位置情報の削除申し込みが完了しました").toString());
						}
					}
				} else {
					return dsas.onCommand(sender, cmd, label, args);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			sender.sendMessage(ChatColor.DARK_RED+"[DSP] - エラーが発生しました:\n"+e.toString());
		}
		return true;
	}
	public boolean parerror(final CommandSender sender) {
		sender.sendMessage("[DSPC] - パラメーターが足りません");
		return false;
	}
	public void onDisable() {
		Player[] players = getServer().getOnlinePlayers().toArray(new Player[0]);
		for (int a = 0; a < players.length; a++) {
			players[a].kickPlayer("再起動またはサーバー停止のため自動キックされました");
		}
		//[DSPG]
		dspga.allstop();
		//[DSPR]
		reloaderc.server = null;
		//[DSPTC]
		dsptc.stop();
		//[DSPMap]
		dspmap.stop();
		//[DSAS]
		dsas.onDisable();
		//[DSPD]
		dspdev.stop();
		//[DSOI]
		dsoi.save();
		//[DSPMe]
		dspme.stop();
		SystemTray.getSystemTray().remove(tray);
	}
	@EventHandler
	public void PlayerLogin(final PlayerLoginEvent evt) {
		String address=evt.getAddress().toString();
		DSPPlayer.PlayerLogin(evt.getPlayer(), address);
	}
	@EventHandler
	public void PlayerJoin(final PlayerJoinEvent evt) {
		Player player = evt.getPlayer();
		NameReload(player);
		switch (DSPAdmin.getPermission(player)) {
		case admin:
		case fukuadmin:
			File maildir2 = new File(dir, "mail/AdminMail/");
			File[] mails2 = maildir2.listFiles();
			if (maildir2.exists() && mails2.length != 0) {
				player.sendMessage(ChatColor.GOLD + "[DSPM] - " + mails2.length + "通の報告があります\n");
			}
			break;
		case owner:
			File maildir3 = new File(dir, "mail/AdminMail/");
			File[] mails3 = maildir3.listFiles();
			if (maildir3.exists() && mails3.length != 0) {
				player.sendMessage(ChatColor.GOLD + "[DSPM] - " + mails3.length + "通の報告があります\n");
			}
			if (new File(dir, "news/player/").listFiles().length != 0) {
				player.sendMessage(ChatColor.GOLD + "[DSPN] - ニュース登録または削除申し込みが届いています");
			}
			if (new File(dir, "Locations/").listFiles().length != 0) {
				player.sendMessage(ChatColor.GOLD + "[DSPN] - 位置情報登録または削除申し込みが届いています");
			}
			break;
		default:
			break;
		}
		File maildir = new File(dir + "/mail/" + player.getName() + "/");
		File[] mails = maildir.listFiles();
		if (maildir.exists() && mails.length != 0) {
			player.sendMessage(ChatColor.GOLD + "[DSPM] - " + mails.length + "通のメールが来ています\n");
		}
		evt.setJoinMessage(player.getDisplayName() + ChatColor.AQUA + "がログインしました");
		tray.displayMessage("DSP", ChatColor.stripColor(player.getDisplayName()) + "がログインしました", MessageType.INFO);
	}
	@EventHandler
	public void PlayerQuit(final PlayerQuitEvent evt) {
		evt.setQuitMessage(evt.getPlayer().getDisplayName() + ChatColor.RED + "がログアウトしました");
		tray.displayMessage("DSP", ChatColor.stripColor(evt.getPlayer().getDisplayName()) + "がログアウトしました", MessageType.INFO);
	}
	@EventHandler
	public void BlockPlace(final BlockPlaceEvent evt) {
		Block bc = evt.getBlock();
		if ((bc.getType() == Material.ENDER_CHEST) && (bc.getWorld().getName().equals(getServer().getWorld(Creativewn).getName()))) {
			evt.setCancelled(true);
			evt.getPlayer().sendMessage(ChatColor.DARK_RED + "[DSPTNT] - エンダーチェストはクリエイティブワールドで設置できません");
		}
	}
	@EventHandler
	public void PlayerDropItem(final PlayerDropItemEvent evt) {
		if (evt.getPlayer().getGameMode() != GameMode.CREATIVE) {
			evt.getPlayer().sendMessage(ChatColor.YELLOW + "[DSPP] - アイテムはポイ捨てしないで下さい");
			System.out.println("[DSPP] - " + evt.getPlayer().getName() + "が" + evt.getItemDrop().getItemStack().toString() + "をポイ捨てしました");
		}
	}
	@EventHandler
	public void PlayerDead(final PlayerDeathEvent evt) {
		Playernews.addPlayernews(evt.getEntity(), PlayernewsType.dead);
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
		Player[] a = getServer().getOnlinePlayers().toArray(new Player[0]);
		String b = new String();
		for (int c = 0; c < a.length; c++) {
			try {
				DensyakunPoint d = new DensyakunPoint(a[c].getName());
				Location f = a[c].getLocation();
				b += "アカウント名::" + a[c].getName() + " 表示名:" + ChatColor.stripColor(a[c].getDisplayName()) + " 座標:" + " X:" + f.getX() + " Y:" + f.getY() + " Z:" + f.getZ() + " DP:" + d.getPoint() + " DPランク:" + d.getRank();
			} catch (IOException x) {
				x.printStackTrace();
			}
		}
		tray.displayMessage("DSP", b, MessageType.INFO);
	}
	@EventHandler
	public void ItemDespawn(ItemDespawnEvent e) {
		/*switch (e.getEntity().getItemStack().getType()) {
		case BONE:
			for (int a = 0; a < e.getEntity().getItemStack().getAmount(); a++) {
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.SKELETON);
				e.getEntity().getItemStack().setAmount(e.getEntity().getItemStack().getAmount() - 1);
			}
			break;
		case EGG:
		case FEATHER:
			break;
		case SEEDS:
			for (int a = 0; a < e.getEntity().getItemStack().getAmount(); a++) {
				if ((e.getEntity().getLocation().getBlock() != null) && (e.getEntity().getLocation().getBlock().getType() == Material.AIR)) {
					e.getEntity().getLocation().getBlock().setType(Material.DEAD_BUSH);
					e.getEntity().getLocation().getBlock().setData((byte) 1);
					e.getEntity().getItemStack().setAmount(e.getEntity().getItemStack().getAmount() - 1);
				} else {
					e.setCancelled(true);
				}
			}
			break;
		case STRING:
			for (int a = 0; a < e.getEntity().getItemStack().getAmount(); a++) {
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.SPIDER);
				e.getEntity().getItemStack().setAmount(e.getEntity().getItemStack().getAmount() - 1);
			}
			break;
		case SULPHUR:
			for (int a = 0; a < e.getEntity().getItemStack().getAmount(); a++) {
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.CREEPER);
				e.getEntity().getItemStack().setAmount(e.getEntity().getItemStack().getAmount() - 1);
			}
			break;
		default:
			if (e.getEntity().getItemStack().getType().isBlock()) {
				for (int a = 0; a < e.getEntity().getItemStack().getAmount(); a++) {
					if ((e.getEntity().getLocation().getBlock() != null) && (e.getEntity().getLocation().getBlock().getType() == Material.AIR)) {
						e.getEntity().getLocation().getBlock().setType(e.getEntity().getItemStack().getType());
						e.getEntity().getLocation().getBlock().setData(e.getEntity().getItemStack().getData().getData());
						e.getEntity().getItemStack().setAmount(e.getEntity().getItemStack().getAmount() - 1);
					} else {
						e.setCancelled(true);
					}
				}
			} else {
				e.setCancelled(true);
			}
			break;
		}*/
		if (e.getEntity().getItemStack().getType() != Material.EGG) {
			e.setCancelled(true);
		}
	}
	public void NameReload(Player player) {
		String a = "";
		switch (DSPAdmin.getPermission(player)) {
		case admin:
			a += new StringBuffer(ChatColor.RED.toString()).append("[Ad]").append(ChatColor.RESET.toString()).toString();
			File maildir=new File(dir+"/mail/AdminMail/");
			File[]mails=maildir.listFiles();
			if(maildir.exists()&&mails.length!=0){
				player.sendMessage(ChatColor.GOLD+"[DSPM] - "+mails.length+"通の報告があります\n");
			}
			break;
		case fukuadmin:
			a += new StringBuffer(ChatColor.RED.toString()).append("[VM]").append(ChatColor.RESET.toString()).toString();
			File maildir2=new File(dir+"/mail/AdminMail/");
			File[]mails2=maildir2.listFiles();
			if(maildir2.exists()&&mails2.length!=0){
				player.sendMessage(ChatColor.GOLD+"[DSPM] - "+mails2.length+"通の報告があります\n");
			}
			break;
		case owner:
			a += new StringBuffer(ChatColor.RED.toString()).append("[Ow]").append(ChatColor.RESET.toString()).toString();
			File maildir3=new File(dir+"/mail/AdminMail/");
			File[]mails3=maildir3.listFiles();
			if(maildir3.exists()&&mails3.length!=0){
				player.sendMessage(ChatColor.GOLD+"[DSPM] - "+mails3.length+"通の報告があります\n");
			}
			if(new File(dir+"/news/player/").listFiles().length!=0) {
				player.sendMessage(ChatColor.GOLD+"[DSPN] - ニュース登録申し込みが届いています");
			}
			break;
		default:
			break;
		}
		if (!dspga.GameInit(player)) {
			a += new StringBuffer(ChatColor.GOLD.toString()).append("[Ga]").append(ChatColor.RESET.toString()).toString();
		}
		player.setDisplayName(new StringBuffer(a).append(player.getName()).toString());
	}
	@EventHandler
	public void VehicleEnter(VehicleEnterEvent e) {
		if (e.getVehicle().getType() == EntityType.MINECART) {
			if (e.getEntered() instanceof Player) {
				((Player) e.getEntered()).sendMessage(new StringBuffer(ChatColor.RED.toString()).append("[DSP]").append(ChatColor.GREEN.toString()).append("手動運転の方法:\n左クリック: ブレーキ(連打)\n右クリック: 加速(ブロックを連打・移動しているときのみ)\n手動運転にはdp(電車君ポイント)が必要です /dp\n手動運転区間についてはこちら\nhttp://densyakun.wiki.fc2.com/wiki/%E6%89%8B%E5%8B%95%E9%81%8B%E8%BB%A2%E5%8C%BA%E9%96%93%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6").toString());
			}
		}
	}
	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e) {
		if ((e.getPlayer().getVehicle() != null) && (e.getPlayer().getVehicle() instanceof Minecart)) {
			Vector a = e.getPlayer().getVehicle().getVelocity();
			switch (e.getAction()) {
			case LEFT_CLICK_AIR:
			case LEFT_CLICK_BLOCK:
				try {
					DensyakunPoint b = new DensyakunPoint(e.getPlayer().getName());
					if (0.05 <= b.getPoint()) {
						b.setPoint(b.getPoint() - 0.05);
						if (a.getX() < 0) {
							a.setX(a.getX() + 0.015);
						} else if (0 < a.getX()) {
							a.setX(a.getX() - 0.015);
						}
						if (a.getY() < 0) {
							a.setY(a.getY() + 0.015);
						} else if (0 < a.getY()) {
							a.setY(a.getY() - 0.015);
						}
						if (a.getZ() < 0) {
							a.setZ(a.getZ() + 0.015);
						} else if (0 < a.getZ()) {
							a.setZ(a.getZ() - 0.015);
						}
						e.getPlayer().getVehicle().getWorld().playSound(e.getPlayer().getVehicle().getLocation(), Sound.BLAZE_HIT, 1, 0);
					} else {
						e.getPlayer().getVehicle().getWorld().playSound(e.getPlayer().getVehicle().getLocation(), Sound.BLAZE_HIT, 1, -12);
					}
					b = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case RIGHT_CLICK_AIR:
			case RIGHT_CLICK_BLOCK:
				try {
					DensyakunPoint b = new DensyakunPoint(e.getPlayer().getName());
					if (0.05 <= b.getPoint()) {
						b.setPoint(b.getPoint() - 0.05);
						if (a.getX() < 0) {
							a.setX(a.getX() - 0.015);
						} else if (0 < a.getX()) {
							a.setX(a.getX() + 0.015);
						}
						if (a.getY() < 0) {
							a.setY(a.getY() - 0.015);
						} else if (0 < a.getY()) {
							a.setY(a.getY() + 0.015);
						}
						if (a.getZ() < 0) {
							a.setZ(a.getZ() - 0.015);
						} else if (0 < a.getZ()) {
							a.setZ(a.getZ() + 0.015);
						}
						e.getPlayer().getVehicle().getWorld().playSound(e.getPlayer().getVehicle().getLocation(), Sound.BLAZE_HIT, 1, 0);
					} else {
						e.getPlayer().getVehicle().getWorld().playSound(e.getPlayer().getVehicle().getLocation(), Sound.BLAZE_HIT, 1, -12);
					}
					b = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			default:
				break;
			}
			e.getPlayer().getVehicle().setVelocity(a);
			a = null;
		}
	}
	//2周年記念
	/*@EventHandler
	public void EntityDeath(EntityDeathEvent e) {
		switch (e.getEntityType()) {
		case BLAZE:
		case CAVE_SPIDER:
		case CREEPER:
		case ENDERMAN:
		case ENDER_DRAGON:
		case GHAST:
		case GIANT:
		case MAGMA_CUBE:
		case PIG_ZOMBIE:
		case SILVERFISH:
		case SKELETON:
		case SLIME:
		case SNOWMAN:
		case SPIDER:
		case WITCH:
		case WITHER:
		case WITHER_SKULL:
		case ZOMBIE:
			if (e.getEntity().getKiller() != null) {
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COAL_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.IRON_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GOLD_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.LAPIS_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.REDSTONE_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.DIAMOND_BLOCK));
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.EMERALD_BLOCK));
			}
			break;
		default:
			break;
		}
	}*/
	@EventHandler
	public void BlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void BlockSpread(BlockSpreadEvent e) {
		if (e.getSource().getType() == Material.FIRE) {
			e.setCancelled(true);
		}
	}
	public void Block(EntityChangeBlockEvent e) {
		e.setCancelled(true);
	}
}
