package org.densyakun.bukkit.dsp.areaservice;
import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.densyakun.bukkit.dsp.Main;
import org.densyakun.densyakunpoint.DensyakunPoint;
public class DSAS implements Listener, Runnable {
	static String msg0 = new StringBuffer(ChatColor.GOLD.toString()).append("[DSAS]").append(ChatColor.RED.toString()).append("パラメーターを入力して下さい").toString();
	static String msg1 = new StringBuffer(ChatColor.GOLD.toString()).append("[DSAS]").append(ChatColor.RED.toString()).append("パラメーターが間違っています").toString();
	static String msg2 = new StringBuffer(ChatColor.GOLD.toString()).append("[DSAS]").append(ChatColor.RED.toString()).append("パラメーターが足りません").toString();
	static String msg3 = new StringBuffer(ChatColor.GOLD.toString()).append("[DSAS]").append(ChatColor.RED.toString()).append("権限がありません").toString();
	File a = new File(Main.dir, "DSAS/");
	AreaManager b;
	Main main;
	public DSAS(Main main) {
		this.main = main;
		new Thread(this).start();
		a.mkdirs();
		b = new AreaManager(this);
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[]args) {
		if(sender instanceof Player){
			if(command.getName().equalsIgnoreCase("dsasp")){
				if(args.length==0)sender.sendMessage(msg0);
				else if(args[0].equalsIgnoreCase("admin")){
					if(!sender.hasPermission("dsp.admin"))sender.sendMessage(msg3);
					else{
						if(args.length<2)sender.sendMessage(msg2);
						else if(args[1].equalsIgnoreCase("create")){
							SelectArea a=b.e((Player)sender);
							if((a.a!=null)&&(a.b!=null)){
								Area c=new Area(a);
								c.c(((Player)sender).getName());
								b.g(c);
								b.d.add(c);
								sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地を作成しました "+ChatColor.GOLD+"ID:"+ChatColor.WHITE+c.i);
								b.b();
							}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"範囲が選択されていません");
						}else if(args[1].equalsIgnoreCase("remove")){
							if(args.length<3)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[2]);
									Area a=b.i(Integer.parseInt(args[2]));
									if(a!=null){
										boolean d=false;
										for(int c=0;c<b.d.size();c++){
											if(b.d.get(c)==a){
												b.d.remove(c);
												d=true;
											}
										}
										if(d){
											sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地を正常に削除しました");
											b.b();
										}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地の削除に失敗しました");
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],args[1],"0"});
								}
							}
						}else if(args[1].equalsIgnoreCase("address")||args[1].equalsIgnoreCase("addr")){
							if(args.length<3)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[2]);
									Area a=b.i(Integer.parseInt(args[2]));
									if(a!=null){
										a.a(args[3]);
										sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の住所を変更しました");
										b.b();
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],args[1],"0",args[3]});
								}
							}
						}else if(args[1].equalsIgnoreCase("player")){
							if(args.length<3)sender.sendMessage(msg2);
							else{
								Integer.parseInt(args[2]);
								Area a=b.i(Integer.parseInt(args[2]));
								if(a!=null){
									if(args.length>2){
										a.c(args[3]);
										this.b.b();
										sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の管理者を変更しました "+ChatColor.GOLD+"管理者:"+ChatColor.WHITE+args[3]);
									}else{
										a.c(null);
										this.b.b();
										sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の管理者を変更しました "+ChatColor.GOLD+"管理者:"+ChatColor.WHITE+"なし");
									}
								}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
							}
						}else if(args[1].equalsIgnoreCase("teleport")||args[1].equalsIgnoreCase("tp")){
							if(args.length<3)sender.sendMessage(msg2);
							else{
								Integer.parseInt(args[2]);
								Area a=b.i(Integer.parseInt(args[2]));
								if(a!=null){
									((Player)sender).teleport(a.j());
									sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地にテレポートしました "+ChatColor.GOLD+"土地ID:"+ChatColor.WHITE+a.i);
								}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
							}
						}else if(args[1].equalsIgnoreCase("load")){
							b.a();
							sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地情報をロードしました");
						}else if(args[1].equalsIgnoreCase("save")){
							b.b();
							sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地情報をセーブしました");
						}else if(args[1].equalsIgnoreCase("reload")){
							b.c();
							sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地情報をリロードしました");
						}else sender.sendMessage(msg1);
					}
				}else{
					if(!sender.hasPermission("dsp.user"))sender.sendMessage(msg3);
					else{
						if(args[0].equalsIgnoreCase("info")){
							if(args.length<2){
								Area a=b.f(((Player)sender).getLocation().getBlock());
								if(a==null)sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした(現在地検索)");
								else{
									String[]msg=new String[10];
									msg[0]=ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地情報が見つかりました(現在地検索)";
									msg[1]=ChatColor.GOLD+"土地ID:"+ChatColor.WHITE+a.i+"";
									Location b=a.j();
									msg[2]=ChatColor.GOLD+"土地座標(中心): ワールド:"+ChatColor.WHITE+a.h.getName()+ChatColor.GOLD+" X:"+ChatColor.WHITE+b.getBlockX()+ChatColor.GOLD+" Y:"+ChatColor.WHITE+b.getBlockY()+ChatColor.GOLD+" Z:"+ChatColor.WHITE+b.getBlockZ();
									//TODO msg[3]=ChatColor.GOLD+"面積:"+ChatColor.WHITE+e(a.a,a.b)+"B";
									if(a.c==null)msg[4]=ChatColor.GOLD+"住所:"+ChatColor.RED+"なし";
									else msg[4]=ChatColor.GOLD+"住所:"+ChatColor.WHITE+a.c;
									if(a.d==null)msg[5]=ChatColor.GOLD+"建造物名:"+ChatColor.RED+"なし";
									else msg[5]=ChatColor.GOLD+"建造物名(名称):"+ChatColor.WHITE+a.d;
									if(a.e==null)msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.RED+"なし";
									else msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.WHITE+a.e;
									if(a.f)msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"無効";
									else msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"有効";
									if(a.g)msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"無効";
									else msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"有効";
									if(a.j)msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売却中 "+a.k+"DP";
									else msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売買なし";
									sender.sendMessage(msg);
								}
							}else{
								Area a=b.i(args[1]);
								if(a==null){
									a=b.i(Integer.parseInt(args[1]));
									if(a==null)sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした(ID・住所・名称検索)");
									else{
										String[]msg=new String[10];
										msg[0]=ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地情報が見つかりました(ID検索)";
										msg[1]=ChatColor.GOLD+"土地ID:"+ChatColor.WHITE+a.i+"";
										Location b=a.j();
										msg[2]=ChatColor.GOLD+"土地座標(中心): ワールド:"+ChatColor.WHITE+a.h.getName()+ChatColor.GOLD+" X:"+ChatColor.WHITE+b.getBlockX()+ChatColor.GOLD+" Y:"+ChatColor.WHITE+b.getBlockY()+ChatColor.GOLD+" Z:"+ChatColor.WHITE+b.getBlockZ();
										//TODO msg[3]=ChatColor.GOLD+"面積:"+ChatColor.WHITE+e(a.a,a.b)+"B";
										if(a.c==null)msg[4]=ChatColor.GOLD+"住所:"+ChatColor.RED+"なし";
										else msg[4]=ChatColor.GOLD+"住所:"+ChatColor.WHITE+a.c;
										if(a.d==null)msg[5]=ChatColor.GOLD+"建造物名:"+ChatColor.RED+"なし";
										else msg[5]=ChatColor.GOLD+"建造物名(名称):"+ChatColor.WHITE+a.d;
										if(a.e==null)msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.RED+"なし";
										else msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.WHITE+a.e;
										if(a.f)msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"有効";
										else msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"無効";
										if(a.g)msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"有効";
										else msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"無効";
										if(a.j)msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売却中 "+a.k+"DP";
										else msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売買なし";
										sender.sendMessage(msg);
									}
								}else{
									String[]msg=new String[10];
									msg[0]=ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地情報が見つかりました(住所・名称検索)";
									msg[1]=ChatColor.GOLD+"土地ID:"+ChatColor.WHITE+a.i+"";
									Location b=a.j();
									msg[2]=ChatColor.GOLD+"土地座標(中心): ワールド:"+ChatColor.WHITE+a.h.getName()+ChatColor.GOLD+" X:"+ChatColor.WHITE+b.getBlockX()+ChatColor.GOLD+" Y:"+ChatColor.WHITE+b.getBlockY()+ChatColor.GOLD+" Z:"+ChatColor.WHITE+b.getBlockZ();
									//TODO msg[3]=ChatColor.GOLD+"面積:"+ChatColor.WHITE+e(a.a,a.b)+"B";
									if(a.c==null)msg[4]=ChatColor.GOLD+"住所:"+ChatColor.RED+"なし";
									else msg[4]=ChatColor.GOLD+"住所:"+ChatColor.WHITE+a.c;
									if(a.d==null)msg[5]=ChatColor.GOLD+"建造物名(名称):"+ChatColor.RED+"なし";
									else msg[5]=ChatColor.GOLD+"建造物名:"+ChatColor.WHITE+a.d;
									if(a.e==null)msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.RED+"なし";
									else msg[6]=ChatColor.GOLD+"管理者:"+ChatColor.WHITE+a.e;
									if(a.f)msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"有効";
									else msg[7]=ChatColor.GOLD+"他人のブロック設置・破壊防止:"+ChatColor.WHITE+"無効";
									if(a.g)msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"有効";
									else msg[8]=ChatColor.GOLD+"他人の侵入防止:"+ChatColor.WHITE+"無効";
									if(a.j)msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売却中 "+a.k+"DP";
									else msg[9]=ChatColor.GOLD+"売買モード:"+ChatColor.WHITE+"売買なし";
									sender.sendMessage(msg);
								}
							}
						}else if(args[0].equalsIgnoreCase("setname")){
							if(args.length==0)sender.sendMessage(msg2);
							else{
								Integer.parseInt(args[1]);
								Area a=b.i(Integer.parseInt(args[1]));
								if(a!=null){
									if(((Player)sender).getName().equalsIgnoreCase(a.e)){
										if(args.length<3)a.b(null);
										else a.b(args[2]);
										sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の名前を変更しました");
										b.b();
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"あなたの土地ではありません");
								}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
							}
						}else if(args[0].equalsIgnoreCase("blocklock")||args[0].equalsIgnoreCase("bl")){
							if(args.length<2)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[1]);
									Area a=b.i(Integer.parseInt(args[1]));
									if(a!=null){
										if(((Player)sender).getName().equalsIgnoreCase(a.e)){
											a.d(Boolean.valueOf(args[2]));
											sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地のブロックロックを変更しました");
											b.b();
										}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"あなたの土地ではありません");
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0",args[2]});
								}
							}
						}else if(args[0].equalsIgnoreCase("movelock")||args[0].equalsIgnoreCase("ml")){
							if(args.length<2)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[1]);
									Area a=b.i(Integer.parseInt(args[1]));
									if(a!=null){
										if(((Player)sender).getName().equalsIgnoreCase(a.e)){
											a.e(Boolean.valueOf(args[2]));
											sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の侵入設定を変更しました");
											b.b();
										}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"あなたの土地ではありません");
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0",args[2]});
								}
							}
						}else if(args[0].equalsIgnoreCase("sale")){
							if(args.length<2)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[1]);
									Area a=b.i(Integer.parseInt(args[1]));
									if(a!=null){
										if(a.e!=((Player)sender).getName())sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"この土地はあなたの物ではありません");
										else{
											try{
												Integer.parseInt(args[2]);
												if(Integer.parseInt(args[2])<1){
													a.g(false);
													a.h(-1);
													sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地の売却を中止しました");
													b.b();
												}else{
													a.g(true);
													a.h(Integer.parseInt(args[2]));
													sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地を売却しました "+ChatColor.GOLD+"土地の値段:"+ChatColor.WHITE+Integer.parseInt(args[2])+"DP");
													b.b();
												}
											}catch(NumberFormatException e){
												onCommand(sender,command,label,new String[]{args[0],args[1],"-1"});
											}
										}
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0",args[2]});
								}
							}
						}else if(args[0].equalsIgnoreCase("buy")){
							if(args.length<2)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[1]);
									Area a=b.i(Integer.parseInt(args[1]));
									if(a!=null){
										if(a.e==((Player)sender).getName())sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"この土地はあなたが所有しています");
										else{
											if(a.j){
												try{
													DensyakunPoint d = new DensyakunPoint(((Player)sender).getName());
													if(a.k<=d.getPoint()){
														d.setPoint(d.getPoint()-a.k);
														a.e=((Player)sender).getName();
														a.j=false;
														double c=a.k;
														a.k=0;
														sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.AQUA+"土地を"+ChatColor.GREEN+c+"DP"+ChatColor.WHITE+"で購入しました");
														b.b();
													}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"DPが足りません "+ChatColor.GOLD+"持ちDP:"+ChatColor.WHITE+d.getPoint()+"DP "+ChatColor.GOLD+"必要DP:"+ChatColor.RED+a.k);
												}catch(IOException e){
													e.printStackTrace();
												}
											}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"この土地は売却中ではありません");
										}
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0"});
								}
							}
						}else if(args[0].equalsIgnoreCase("value")){
							if(args.length<1)sender.sendMessage(msg2);
							else{
								try{
									Integer.parseInt(args[1]);
									Area a=b.i(Integer.parseInt(args[1]));
									if(a!=null){
										/*double b=0;
										for(int c=a.a.getX();c<=a.b.getX();c++)for(int d=a.a.getY();d<=a.b.getY();d++)for(int e=a.a.getZ();e<=a.b.getZ();e++){
											try{
												b+=ValueData.getValueNatural(a.h.getBlockAt(c,d,e).getType().getNewData(a.h.getBlockAt(c,d,e).getData()).toItemStack(1));
											}catch(TradeBanException e1){
												if(e1.getMaterialData().getItemType()!=Material.AIR)e1.printStackTrace();
											}
										}
										sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.GOLD+"この土地の価値: 空間代:"+ChatColor.WHITE+e(a.a,a.b)*10+"DP "+ChatColor.GOLD+"価値代:"+ChatColor.WHITE+b+"DP "+ChatColor.GOLD+"合計:"+ChatColor.WHITE+((e(a.a,a.b)*10)+b)+"DP");*/
										/*TODO*/sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"現在面積に関するバグが発生しており、土地の価値は正確ではありません");
									}else sender.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"土地が見つかりませんでした");
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0"});
								}
							}
						}else if(args[0].equalsIgnoreCase("list")){
							if(args.length<2)onCommand(sender,command,label,new String[]{args[0],"0"});
							else{
								try{
									Integer.parseInt(args[1]);
									Area[]a=new Area[6];
									for(int c=0;c<6;c++)if(((Integer.parseInt(args[1])*6)+c)<b.d.size())a[c]=b.d.get((Integer.parseInt(args[1])*6)+c);
									String[]msg=new String[9];
									msg[0]=ChatColor.GOLD+"[DSAS]\n"+ChatColor.WHITE+"---"+ChatColor.GREEN+"土地リスト"+"(Page"+ChatColor.AQUA+args[1]+ChatColor.GREEN+")"+ChatColor.WHITE+"---";
									msg[1]=ChatColor.GREEN+"--------------------";
									for(int b=0;b<6;b++){
										if(a[b]!=null){
											if(a[b].e!=null)msg[b+2]=ChatColor.GREEN+"- "+ChatColor.GOLD+"ID:"+ChatColor.WHITE+a[b].i+ChatColor.GOLD+" 建造物名:"+ChatColor.WHITE+a[b].d+ChatColor.GOLD+" 管理者:"+ChatColor.WHITE+a[b].e+ChatColor.GREEN+" -";
											else msg[b+2]=ChatColor.GREEN+"- "+ChatColor.GOLD+"ID:"+ChatColor.WHITE+a[b].i+ChatColor.GOLD+" 建造物名:"+ChatColor.WHITE+a[b].d+ChatColor.GOLD+" 管理者:"+ChatColor.WHITE+"--- "+ChatColor.GREEN+"-";
										}else msg[b+2]=ChatColor.GREEN+"- "+ChatColor.GOLD+"ID:"+ChatColor.WHITE+"--- "+ChatColor.GOLD+"建造物名:"+ChatColor.WHITE+"--- "+ChatColor.GOLD+"管理者:"+ChatColor.WHITE+"--- "+ChatColor.GREEN+"-";
									}
									msg[8]=ChatColor.GREEN+"--------------------";
									sender.sendMessage(msg);
								}catch(NumberFormatException e){
									onCommand(sender,command,label,new String[]{args[0],"0"});
								}
							}
						}
					}
				}
			}
		}else sender.sendMessage("[DSAS]プレイヤーのみ実行できます");
		return true;
	}
	public void onDisable(){
		b.b();
	}
	@Override
	public void run(){
	}
	@EventHandler
	public void a(BlockPlaceEvent a){
		Area c=b.f(a.getBlock());
		if(c==null)return;
		if((((c.e!=null)&&(!c.e.equals(a.getPlayer().getName())))&&c.f)){
			a.setCancelled(true);
			a.getPlayer().sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"ここは人の土地です");
			Player b=main.getServer().getPlayer(c.e);
			if(b!=null)b.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+a.getPlayer().getName()+"さんがあなたの土地にブロック"+a.getBlock().getType().name()+"を設置しようとしました "+ChatColor.WHITE+"土地ID:"+c.i);
		}
	}
	@EventHandler
	public void b(BlockBreakEvent a){
		Area c=b.f(a.getBlock());
		if(c==null)return;
		if((((c.e!=null)&&(!c.e.equals(a.getPlayer().getName())))&&c.f)){
			a.setCancelled(true);
			a.getPlayer().sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"ここは人の土地です");
			Player b=main.getServer().getPlayer(c.e);
			if(b!=null)b.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+a.getPlayer().getName()+"さんがあなたの土地のブロック"+a.getBlock().getType().name()+"を破壊しようとしました "+ChatColor.WHITE+"土地ID:"+c.i);
		}
	}
	@EventHandler
	public void c(PlayerMoveEvent a){
		if (!a.getPlayer().hasPermission("dsp.admin")) {
			Area c=b.f(a.getTo().getBlock());
			if(c==null)return;
			if((((c.e!=null)&&(!c.e.equals(a.getPlayer().getName())))&&c.g)){
				Area d=b.f(a.getFrom().getBlock());
				if(d==null){
					a.setCancelled(true);
					a.getPlayer().sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+"ここは人の土地です");
					Player b=main.getServer().getPlayer(c.e);
					if(b!=null)b.sendMessage(ChatColor.GOLD+"[DSAS]"+ChatColor.RED+a.getPlayer().getName()+"さんがあなたの土地に侵入しようとしました "+ChatColor.GOLD+"土地ID:"+ChatColor.WHITE+c.i);
				}
			}
		}
	}
	@EventHandler
	public void d(PlayerInteractEvent e){
		if(e.getPlayer().getItemInHand().getType()==Material.WOOD_AXE){
			if(e.getAction()==Action.LEFT_CLICK_BLOCK){
				SelectArea a=b.e(e.getPlayer());
				a.a(e.getClickedBlock());
				//if((a.a!=null)&&(a.b!=null))e.getPlayer().sendMessage(ChatColor.GOLD+"[DSAS] 面積:"+ChatColor.WHITE+e(a.a,a.b)+"B");
			}
			else if(e.getAction()==Action.RIGHT_CLICK_BLOCK){
				SelectArea a=b.e(e.getPlayer());
				a.b(e.getClickedBlock());
				//if((a.a!=null)&&(a.b!=null))e.getPlayer().sendMessage(ChatColor.GOLD+"[DSAS] 面積:"+ChatColor.WHITE+e(a.a,a.b)+"B");
			}
		}
	}
	/*public int e(Block a,Block f){
		int b=0;
		for(int c=a.getX();c<=f.getX();c++)for(int d=a.getY();d<=f.getY();d++)for(int e=a.getZ();e<=f.getZ();e++)b++;
		return b;
	}*/
}
