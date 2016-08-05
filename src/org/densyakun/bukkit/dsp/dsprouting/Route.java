package org.densyakun.bukkit.dsp.dsprouting;
import java.util.List;
public class Route {
	public List<Point>points;
	public Route(List<Point>points) {
		this.points = points;
	}
	public double getDistance() {
		double a = 0;
		for (int b = 1; b < points.size(); b++) {
			a += points.get(b - 1).location.distance(points.get(b).location);
		}
		return a;
	}
}
