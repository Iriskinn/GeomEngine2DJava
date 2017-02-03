package geometry;


public class Segment extends Line {
	private Point p1, p2;
	
	public Segment(Point p1, Point p2) {
		super(p1.joinWith(p2));
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public String toString() {
		return "segment (on " + super.toString() + ") from " + 
				p1.toString() + " to " + p2.toString();
	}
	
	public Point intersectionWith(Line l) {
		return super.intersectionWith((Line) l);
	}
	
	public boolean intersectedBy(Line l) {
		Point intersection = intersectionWith(l);
		return (intersection.isFinitePoint() && intersection.inArea(p1, p2));
	}
	
	public boolean intersectedBy(Segment s) {
		return (this.intersectedBy((Line) s) && s.intersectedBy((Line) this));
	}
	
	public Segment applyTransformation(Transformation trafo) {
		return new Segment(p1.applyTransformation(trafo), p2.applyTransformation(trafo));
	}
	
	public Point getClosesPointTo(Point p) throws InfinitePointException {
		Point projection = p.projectionTo(this);
		if (projection.inArea(p1, p2)) {
			return projection;
		} else {
			if (p.distanceTo(p1) < p.distanceTo(p2)) {
				return (Point) p1.clone();
			} else {
				return (Point) p2.clone();
			}
		}
	}
}
