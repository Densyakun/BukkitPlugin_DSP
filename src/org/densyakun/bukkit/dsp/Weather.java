package org.densyakun.bukkit.dsp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
public class Weather{
	Block[][][] blocks=new Block[10][10][10];
	public Weather(Location loc){
		for(int a=0;a<10;a++)for(int b=0;b<10;b++)for(int c=0;c<10;c++)blocks[a][b][c]=new Location(loc.getWorld(),loc.getBlockX()-5+a,loc.getBlockY()-5+b,loc.getBlockZ()-5+c).getBlock();
	}
	public boolean isBlock(Material material){
		for(int a=0;a<10;a++)for(int b=0;b<10;b++)for(int c=0;c<10;c++)if(blocks[a][b][c].getType()==material)return true;
		return false;
	}
	public static double getCelsius(Location loc){
		double celsius=20;
		Location pc=loc;
		Location pg=pc;
		pg.setY(pg.getY()-1);
		Weather weather=new Weather(loc);
		if(weather.isBlock(Material.LAVA))celsius+=16.5;
		if(weather.isBlock(Material.WATER))celsius-=3;
		if(pc.getBlock().getType()==Material.LAVA)celsius+=120;
		if(pc.getBlock().getType()==Material.WATER)celsius-=10;
		if(pc.getBlock().getBiome()==Biome.ICE_MOUNTAINS)celsius-=18.4;
		if(pc.getBlock().getBiome()==Biome.ICE_PLAINS)celsius-=10;
		if(pc.getBlock().getBiome()==Biome.DESERT)celsius+=5;
		if(pc.getBlock().getBiome()==Biome.DESERT_HILLS)celsius+=1.5;
		if(pc.getBlock().getBiome()==Biome.FROZEN_OCEAN)celsius-=17.5;
		if(pc.getBlock().getBiome()==Biome.FROZEN_RIVER)celsius-=26;
		if(pc.getBlock().getBiome()==Biome.FOREST)celsius+=0.3;
		if(pc.getBlock().getBiome()==Biome.FOREST_HILLS)celsius-=0.1;
		if(pc.getBlock().getBiome()==Biome.JUNGLE)celsius+=11;
		if(pc.getBlock().getBiome()==Biome.JUNGLE_HILLS)celsius+=6.1;
		if(pc.getBlock().getBiome()==Biome.OCEAN)celsius-=2.4;
		if(pc.getBlock().getBiome()==Biome.BEACH)celsius-=0.4;
		if(pc.getBlock().getBiome()==Biome.EXTREME_HILLS)celsius-=0.3;
		if(pc.getBlock().getBiome()==Biome.PLAINS)celsius+=0.1;
		if(pc.getBlock().getBiome()==Biome.SMALL_MOUNTAINS)celsius+=1;
		if(pc.getBlock().getBiome()==Biome.SWAMPLAND)celsius+=1;
		if(pc.getBlock().getBiome()==Biome.TAIGA)celsius-=10;
		if(pc.getBlock().getBiome()==Biome.TAIGA_HILLS)celsius-=16.3;
		if(pc.getBlock().getType()==Material.REDSTONE_WIRE)celsius+=0.1;
		if(pc.getBlock().getType()==Material.REDSTONE_TORCH_ON)celsius+=1;
		if(pc.getBlock().getType()==Material.SNOW)celsius-=15;
		if(pg.getBlock().getType()==Material.GRASS)celsius+=0.4;
		if(pg.getBlock().getType()==Material.ICE)celsius-=19;
		if(pg.getBlock().getType()==Material.IRON_BLOCK)celsius-=1;
		if(pg.getBlock().getType()==Material.DIRT)celsius+=0.6;
		if(pc.getBlockY()>63)celsius-=(pc.getBlockY()-63)*0.1;
		if(pc.getBlock().getBiome()!=Biome.OCEAN)if(pc.getBlockY()<63)celsius+=(pc.getBlockY()-63)*0.1;
		celsius+=pc.getBlock().getLightFromSky()*0.1;
		long time=loc.getWorld().getTime();
		celsius-=(0.0001*-time);
		celsius-=(0.0001*time);
		return celsius;
	}
}