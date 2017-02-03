package program;

import geometry.*;


public class TestProgram {
	public static void main(String args[]) throws InfinitePointException {
		Point A = new Point(2, 0);
		Point B = new Point(0, 4);
		Point C = new Point(6, 2);
		Point D = new Point(5, 4);
		
		Point[] pol = {A, B, D, C};
		
		Point E = new Point(2, 4);
		Point F = new Point(1, 1);
		
		Polygon p = new Polygon(pol);
		System.out.println(p.isPointIn(E));
		System.out.println(p.isPointIn(F));
	}
}
