package org.densyakun.bukkit.dsp.dsprouting;
import org.bukkit.Location;
public class Point {
	public String name;
	public Location location;
	public Point(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Point) && ((Point) obj).name.equals(name) && (((Point) obj).location.getX() == location.getX()) && (((Point) obj).location.getY() == location.getY()) && (((Point) obj).location.getZ() == location.getZ());
	}
}
