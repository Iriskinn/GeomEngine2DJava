package geometry;

public class Line extends Vector implements Transformable {
	public static Line infiniteLine = new Line(new Vector(0, 0, 1));
	
	public Line(Vector v) {
		super(v);
	}
	
	public String toString() {
		return "line: " + super.toString();
	}
	
	public Point intersectionWith(Line l2) {
		return new Point(xProd((Vector) l2));
	}
	
	public boolean intersectedBy(Line l) {
		Point intersection = intersectionWith(l);
		if (intersection.isFinitePoint()) {
			return true;
		} else {
			if (intersection.isPoint()) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public Line applyTransformation(Transformation trafo) {
		return new Line(this.matrMul(trafo.inverse().transpose()));
	}
}
