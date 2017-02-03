package geometry;

import java.util.ArrayList;
import java.util.Random;

public class Polygon implements Transformable {
	private Point[] vertices;
	private double area;
	private int vCount;
	private Point center;
	private ArrayList < Segment > sides = new ArrayList < Segment >();
	
	
	public Polygon(Point[] vertices) throws InfinitePointException {
		this.vertices = vertices.clone();
		vCount = vertices.length;
		area = 0;
		double centerX = 0, centerY = 0;
		for (int i = 0; i < vCount; i++) {
			centerX += vertices[i].getNormalizedA();
			centerY += vertices[i].getNormalizedB();
			area += vertices[i].getNormalizedA() * vertices[(i + 1) % vCount].getNormalizedB();
			area -= vertices[i].getNormalizedB() * vertices[(i + 1) % vCount].getNormalizedA();
			sides.add(new Segment(vertices[i], vertices[(i + 1) % vCount]));
		}
		area = Math.abs(area) / 2;
		center = new Point(centerX / vCount, centerY / vCount);
	}
	
	public Point[] getVertices() {
		return vertices;
	}
	
	public double getArea() {
		return area;
	}
	
	@Override
	public Transformable applyTransformation(Transformation trafo) throws InfinitePointException {
		Point[] res = new Point[vertices.length];
		for (Point p : res) {
			p = p.applyTransformation(trafo);
		}
		return new Polygon(res);
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Point p : vertices) {
			res.append(", ");
			res.append(p.toString());
		}
		return res.toString();
	}

	public Point getCenter() {
		return center;
	}
	
	public boolean isPointIn(Point p) {
		Random rand = new Random();
		
		while (true) {
			double dx = Math.abs(rand.nextDouble()) + 1;
			double dy = Math.abs(rand.nextDouble()) + 1;
			Ray check = new Ray(p, dx, dy);
			
			boolean correct = true; 
			for (Point v : vertices) {
				if (v.isOnLine(check)) {
					correct = false;
					break;
				}
			}
			
			if (!correct) {
				continue;
			}
			
			int intersections = 0;
			for (Segment s : sides) {
				if (s.intersectedBy(check)) {
					intersections++;
				}
			}
			
			return (intersections % 2 != 0);
		}
	}
}
