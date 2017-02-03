package geometry;


public class Point extends Vector implements Transformable {
	public Point(double x, double y) {
		super(x, y, 1);
	}
	
	public Point(Vector v) {
		super(v);
	}
	
	public String toString() {
		return "point: " + super.toString();
	}
	
	public double distanceTo(Point p) throws InfinitePointException {
		if (! this.isFinitePoint() || ! p.isFinitePoint()) {
			throw new InfinitePointException();
		}
		double dx = getNormalizedA() - p.getNormalizedA();
		double dy = getNormalizedB() - p.getNormalizedB();
		return Math.sqrt(dx * dx + dy * dy);
	}

	public boolean isFinitePoint() {
		return ! Numbers.isZero(getC());
	}
	
	public boolean isPoint() {
		return ! isZeroVector();
	}
	
	public boolean inArea(Point p1, Point p2) {
		if (! isFinitePoint()) {
			return false;
		}
		
		int compareX1 = Numbers.compare(p1.getA() * getC(), getA() * p1.getC());
		int compareX2 = Numbers.compare(getA() * p2.getC(), p2.getA() * getC());
		int compareY1 = Numbers.compare(p1.getB() * getC(), getB() * p1.getC());
		int compareY2 = Numbers.compare(getB() * p2.getC(), p2.getB() * getC());
		
		if (	(compareX1 == 0 || compareX2 == 0 || 
					(compareX1 > 0 && compareX2 > 0) ||
					(compareX1 < 0 && compareX2 < 0))
				&&
				(compareY1 == 0 || compareY2 == 0 ||
					(compareY1 > 0 && compareY2 > 0) ||
					(compareY1 < 0 && compareY2 < 0))
		) {
			return true;
		} else {
			return false;
		}
	}
	
	public Line joinWith(Point p) {
		return new Line(xProd((Vector) p));
	}
	
	public boolean isOnLine(Line l) {
		return Numbers.isZero(this.sProd(l));
	}
	
	public Point applyTransformation(Transformation trafo) {
		return new Point(this.matrMul(trafo));
	}
	
	public Point projectionTo(Line l) { 
		return l.intersectionWith(Line.infiniteLine)
					.applyTransformation(new Transformation().addRotation(Math.PI / 2))
					.joinWith(this).intersectionWith(l);
	}
}
