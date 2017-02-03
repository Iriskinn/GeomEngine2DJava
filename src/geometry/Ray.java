package geometry;


public class Ray extends Segment{
	public Ray(Point start, Point direction) {
		super(start, start.joinWith(direction).intersectionWith(Line.infiniteLine));
	}
	
	public Ray(Point start, double xDirection, double yDirection) {
		super(start, start
				.joinWith(start.applyTransformation(new Transformation()
						.addTranslation(xDirection, yDirection)))
				.intersectionWith(Line.infiniteLine));
	}
}
