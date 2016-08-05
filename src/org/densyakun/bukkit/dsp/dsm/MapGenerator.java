package org.densyakun.bukkit.dsp.dsm;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
public class MapGenerator implements Listener {
	public DSPMap dspmap;
	public static File dir;
	public MapGenerator(DSPMap dspmap) {
		this.dspmap = dspmap;
		(dir = new File(DSPMap.dir, "MapImages/")).mkdirs();
	}
	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockBurn(BlockBurnEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockFade(BlockFadeEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockForm(BlockFormEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockFromTo(BlockFromToEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockGrow(BlockGrowEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockIgnite(BlockIgniteEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockPistonExtend(BlockPistonExtendEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockPistonRetract(BlockPistonRetractEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockRedstone(BlockRedstoneEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void BlockSpread(BlockSpreadEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void EntityBlockForm(EntityBlockFormEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	@EventHandler
	public void LeavesDecay(LeavesDecayEvent e) {
		ChunkDraw(e.getBlock().getChunk());
	}
	public void ChunkDraw(Chunk chunk) {
		File file = new File(dir, new StringBuffer("ChunkImage_").append(chunk.getX()).append("_").append(chunk.getZ()).append(".png").toString());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedImage bi = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 256; y++) {
					Color color0 = getColor(chunk.getBlock(x, y, z).getType(), 0);
					Color color1 = getColor(chunk.getBlock(x, y, z).getType(), 1);
					Color color2 = getColor(chunk.getBlock(x, y, z).getType(), 2);
					Color color3 = getColor(chunk.getBlock(x, y, z).getType(), 3);
					BufferedImage bi1 = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
					Graphics g1 = bi.getGraphics();
					if ((color0 != null)) {
						g1.setColor(color0);
						g1.drawRect(0, 0, 0, 0);
						color0 = null;
					}
					if ((color1 != null)) {
						g1.setColor(color1);
						g1.drawRect(1, 0, 0, 0);
						color1 = null;
					}
					if ((color2 != null)) {
						g1.setColor(color2);
						g1.drawRect(0, 1, 0, 0);
						color2 = null;
					}
					if ((color3 != null)) {
						g1.setColor(color3);
						g1.drawRect(1, 1, 0, 0);
						color3 = null;
					}
					g1.dispose();
					g1 = null;
					g.drawImage(bi1, x+1*4-4, z+1*4-4, null);
					bi1 = null;
				}
			}
		}
		g.dispose();
		g = null;
		try {
			ImageIO.write(bi, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bi = null;
	}
	public Color getColor(Material type, int num) {
		switch (type) {
		case ACTIVATOR_RAIL:
			break;
		case ANVIL:
			return new Color(100, 103, 102);
		case BEACON:
			return new Color(142, 209, 224);
		case BEDROCK:
			switch (num) {
			case 1:
			case 2:
				return new Color(100, 103, 102);
			default: return new Color(0, 0, 0);
			}
		case BED_BLOCK:
			return new Color(215, 29, 59);
		case BIRCH_WOOD_STAIRS:
			return new Color(255, 191, 191);
		case BOOKSHELF:
			break;
		case BOOK_AND_QUILL:
			break;
		case BOW:
			break;
		case BOWL:
			break;
		case BREAD:
			break;
		case BREWING_STAND:
			break;
		case BREWING_STAND_ITEM:
			break;
		case BRICK:
			break;
		case BRICK_STAIRS:
			break;
		case BROWN_MUSHROOM:
			break;
		case BUCKET:
			break;
		case BURNING_FURNACE:
			break;
		case CACTUS:
			break;
		case CAKE:
			break;
		case CAKE_BLOCK:
			break;
		case CARPET:
			break;
		case CARROT:
			break;
		case CARROT_ITEM:
			break;
		case CARROT_STICK:
			break;
		case CAULDRON:
			break;
		case CAULDRON_ITEM:
			break;
		case CHAINMAIL_BOOTS:
			break;
		case CHAINMAIL_CHESTPLATE:
			break;
		case CHAINMAIL_HELMET:
			break;
		case CHAINMAIL_LEGGINGS:
			break;
		case CHEST:
			break;
		case CLAY:
			break;
		case CLAY_BALL:
			break;
		case CLAY_BRICK:
			break;
		case COAL:
			break;
		case COAL_BLOCK:
			break;
		case COAL_ORE:
			break;
		case COBBLESTONE:
			break;
		case COBBLESTONE_STAIRS:
			break;
		case COBBLE_WALL:
			break;
		case COCOA:
			break;
		case COMMAND:
			break;
		case COMPASS:
			break;
		case COOKED_BEEF:
			break;
		case COOKED_CHICKEN:
			break;
		case COOKED_FISH:
			break;
		case COOKIE:
			break;
		case CROPS:
			break;
		case DAYLIGHT_DETECTOR:
			break;
		case DEAD_BUSH:
			break;
		case DETECTOR_RAIL:
			break;
		case DIAMOND:
			break;
		case DIAMOND_AXE:
			break;
		case DIAMOND_BARDING:
			break;
		case DIAMOND_BLOCK:
			break;
		case DIAMOND_BOOTS:
			break;
		case DIAMOND_CHESTPLATE:
			break;
		case DIAMOND_HELMET:
			break;
		case DIAMOND_HOE:
			break;
		case DIAMOND_LEGGINGS:
			break;
		case DIAMOND_ORE:
			break;
		case DIAMOND_PICKAXE:
			break;
		case DIAMOND_SPADE:
			break;
		case DIAMOND_SWORD:
			break;
		case DIODE:
			break;
		case DIODE_BLOCK_OFF:
			break;
		case DIODE_BLOCK_ON:
			break;
		case DIRT:
			break;
		case DISPENSER:
			break;
		case DOUBLE_STEP:
			break;
		case DRAGON_EGG:
			break;
		case DROPPER:
			break;
		case EGG:
			break;
		case EMERALD:
			break;
		case EMERALD_BLOCK:
			break;
		case EMERALD_ORE:
			break;
		case EMPTY_MAP:
			break;
		case ENCHANTED_BOOK:
			break;
		case ENCHANTMENT_TABLE:
			break;
		case ENDER_CHEST:
			break;
		case ENDER_PEARL:
			break;
		case ENDER_PORTAL:
			break;
		case ENDER_PORTAL_FRAME:
			break;
		case ENDER_STONE:
			break;
		case EXPLOSIVE_MINECART:
			break;
		case EXP_BOTTLE:
			break;
		case EYE_OF_ENDER:
			break;
		case FEATHER:
			break;
		case FENCE:
			break;
		case FENCE_GATE:
			break;
		case FERMENTED_SPIDER_EYE:
			break;
		case FIRE:
			break;
		case FIREBALL:
			break;
		case FIREWORK:
			break;
		case FIREWORK_CHARGE:
			break;
		case FISHING_ROD:
			break;
		case FLINT:
			break;
		case FLINT_AND_STEEL:
			break;
		case FLOWER_POT:
			break;
		case FLOWER_POT_ITEM:
			break;
		case FURNACE:
			break;
		case GHAST_TEAR:
			break;
		case GLASS:
			break;
		case GLASS_BOTTLE:
			break;
		case GLOWING_REDSTONE_ORE:
			break;
		case GLOWSTONE:
			break;
		case GLOWSTONE_DUST:
			break;
		case GOLDEN_APPLE:
			break;
		case GOLDEN_CARROT:
			break;
		case GOLD_AXE:
			break;
		case GOLD_BARDING:
			break;
		case GOLD_BLOCK:
			break;
		case GOLD_BOOTS:
			break;
		case GOLD_CHESTPLATE:
			break;
		case GOLD_HELMET:
			break;
		case GOLD_HOE:
			break;
		case GOLD_INGOT:
			break;
		case GOLD_LEGGINGS:
			break;
		case GOLD_NUGGET:
			break;
		case GOLD_ORE:
			break;
		case GOLD_PICKAXE:
			break;
		case GOLD_PLATE:
			break;
		case GOLD_RECORD:
			break;
		case GOLD_SPADE:
			break;
		case GOLD_SWORD:
			break;
		case GRASS:
			return new Color(98, 120, 66);
		case GRAVEL:
			break;
		case GREEN_RECORD:
			break;
		case GRILLED_PORK:
			break;
		case HARD_CLAY:
			break;
		case HAY_BLOCK:
			break;
		case HOPPER:
			break;
		case HOPPER_MINECART:
			break;
		case HUGE_MUSHROOM_1:
			break;
		case HUGE_MUSHROOM_2:
			break;
		case ICE:
			break;
		case INK_SACK:
			break;
		case IRON_AXE:
			break;
		case IRON_BARDING:
			break;
		case IRON_BLOCK:
			break;
		case IRON_BOOTS:
			break;
		case IRON_CHESTPLATE:
			break;
		case IRON_DOOR:
			break;
		case IRON_DOOR_BLOCK:
			break;
		case IRON_FENCE:
			break;
		case IRON_HELMET:
			break;
		case IRON_HOE:
			break;
		case IRON_INGOT:
			break;
		case IRON_LEGGINGS:
			break;
		case IRON_ORE:
			break;
		case IRON_PICKAXE:
			break;
		case IRON_PLATE:
			break;
		case IRON_SPADE:
			break;
		case IRON_SWORD:
			break;
		case ITEM_FRAME:
			break;
		case JACK_O_LANTERN:
			break;
		case JUKEBOX:
			break;
		case JUNGLE_WOOD_STAIRS:
			break;
		case LADDER:
			break;
		case LAPIS_BLOCK:
			break;
		case LAPIS_ORE:
			break;
		case LAVA:
			break;
		case LAVA_BUCKET:
			break;
		case LEASH:
			break;
		case LEATHER:
			break;
		case LEATHER_BOOTS:
			break;
		case LEATHER_CHESTPLATE:
			break;
		case LEATHER_HELMET:
			break;
		case LEATHER_LEGGINGS:
			break;
		case LEAVES:
			break;
		case LEVER:
			break;
		case LOG:
			break;
		case LONG_GRASS:
			break;
		case MAGMA_CREAM:
			break;
		case MAP:
			break;
		case MELON:
			break;
		case MELON_BLOCK:
			break;
		case MELON_SEEDS:
			break;
		case MELON_STEM:
			break;
		case MILK_BUCKET:
			break;
		case MINECART:
			break;
		case MOB_SPAWNER:
			break;
		case MONSTER_EGG:
			break;
		case MONSTER_EGGS:
			break;
		case MOSSY_COBBLESTONE:
			break;
		case MUSHROOM_SOUP:
			break;
		case MYCEL:
			break;
		case NAME_TAG:
			break;
		case NETHERRACK:
			break;
		case NETHER_BRICK:
			break;
		case NETHER_BRICK_ITEM:
			break;
		case NETHER_BRICK_STAIRS:
			break;
		case NETHER_FENCE:
			break;
		case NETHER_STALK:
			break;
		case NETHER_STAR:
			break;
		case NETHER_WARTS:
			break;
		case NOTE_BLOCK:
			break;
		case OBSIDIAN:
			break;
		case PAINTING:
			break;
		case PAPER:
			break;
		case PISTON_BASE:
			break;
		case PISTON_EXTENSION:
			break;
		case PISTON_MOVING_PIECE:
			break;
		case PISTON_STICKY_BASE:
			break;
		case POISONOUS_POTATO:
			break;
		case PORK:
			break;
		case PORTAL:
			break;
		case POTATO:
			break;
		case POTATO_ITEM:
			break;
		case POTION:
			break;
		case POWERED_MINECART:
			break;
		case POWERED_RAIL:
			break;
		case PUMPKIN:
			break;
		case PUMPKIN_PIE:
			break;
		case PUMPKIN_SEEDS:
			break;
		case PUMPKIN_STEM:
			break;
		case QUARTZ:
			break;
		case QUARTZ_BLOCK:
			break;
		case QUARTZ_ORE:
			break;
		case QUARTZ_STAIRS:
			break;
		case RAILS:
			break;
		case RAW_BEEF:
			break;
		case RAW_CHICKEN:
			break;
		case RAW_FISH:
			break;
		case RECORD_10:
			break;
		case RECORD_11:
			break;
		case RECORD_12:
			break;
		case RECORD_3:
			break;
		case RECORD_4:
			break;
		case RECORD_5:
			break;
		case RECORD_6:
			break;
		case RECORD_7:
			break;
		case RECORD_8:
			break;
		case RECORD_9:
			break;
		case REDSTONE:
			break;
		case REDSTONE_BLOCK:
			break;
		case REDSTONE_COMPARATOR:
			break;
		case REDSTONE_COMPARATOR_OFF:
			break;
		case REDSTONE_COMPARATOR_ON:
			break;
		case REDSTONE_LAMP_OFF:
			break;
		case REDSTONE_LAMP_ON:
			break;
		case REDSTONE_ORE:
			break;
		case REDSTONE_TORCH_OFF:
			break;
		case REDSTONE_TORCH_ON:
			break;
		case REDSTONE_WIRE:
			break;
		case RED_MUSHROOM:
			break;
		case RED_ROSE:
			break;
		case ROTTEN_FLESH:
			break;
		case SADDLE:
			break;
		case SAND:
			break;
		case SANDSTONE:
			break;
		case SANDSTONE_STAIRS:
			break;
		case SAPLING:
			break;
		case SEEDS:
			break;
		case SHEARS:
			break;
		case SIGN:
			break;
		case SIGN_POST:
			break;
		case SKULL:
			break;
		case SKULL_ITEM:
			break;
		case SLIME_BALL:
			break;
		case SMOOTH_BRICK:
			break;
		case SMOOTH_STAIRS:
			break;
		case SNOW:
			break;
		case SNOW_BALL:
			break;
		case SNOW_BLOCK:
			break;
		case SOIL:
			break;
		case SOUL_SAND:
			break;
		case SPECKLED_MELON:
			break;
		case SPIDER_EYE:
			break;
		case SPONGE:
			break;
		case SPRUCE_WOOD_STAIRS:
			break;
		case STAINED_CLAY:
			break;
		case STATIONARY_LAVA:
			break;
		case STATIONARY_WATER:
			break;
		case STEP:
			break;
		case STICK:
			break;
		case STONE:
			break;
		case STONE_AXE:
			break;
		case STONE_BUTTON:
			break;
		case STONE_HOE:
			break;
		case STONE_PICKAXE:
			break;
		case STONE_PLATE:
			break;
		case STONE_SPADE:
			break;
		case STONE_SWORD:
			break;
		case STORAGE_MINECART:
			break;
		case STRING:
			break;
		case SUGAR:
			break;
		case SUGAR_CANE:
			break;
		case SUGAR_CANE_BLOCK:
			break;
		case SULPHUR:
			break;
		case THIN_GLASS:
			break;
		case TNT:
			break;
		case TORCH:
			break;
		case TRAPPED_CHEST:
			break;
		case TRAP_DOOR:
			break;
		case TRIPWIRE:
			break;
		case TRIPWIRE_HOOK:
			break;
		case VINE:
			break;
		case WALL_SIGN:
			break;
		case WATCH:
			break;
		case WATER:
			break;
		case WATER_BUCKET:
			break;
		case WATER_LILY:
			break;
		case WEB:
			break;
		case WHEAT:
			break;
		case WOOD:
			break;
		case WOODEN_DOOR:
			break;
		case WOOD_AXE:
			break;
		case WOOD_BUTTON:
			break;
		case WOOD_DOOR:
			break;
		case WOOD_DOUBLE_STEP:
			break;
		case WOOD_HOE:
			break;
		case WOOD_PICKAXE:
			break;
		case WOOD_PLATE:
			break;
		case WOOD_SPADE:
			break;
		case WOOD_STAIRS:
			break;
		case WOOD_STEP:
			break;
		case WOOD_SWORD:
			break;
		case WOOL:
			break;
		case WORKBENCH:
			break;
		case WRITTEN_BOOK:
			break;
		case YELLOW_FLOWER:
			break;
		default:
			break;
		}
		return null;
	}
}
