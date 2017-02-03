package geometry;

public class Transformation extends Matrix {
	public Transformation() {
		super();
	}
	
	public Transformation(Matrix m) {
		super(m);
	}
	
	public Transformation(double[][] val) {
		super(val);
	}

	public Transformation addTranslation(double dx, double dy) {
		double tform[][] = {{1, 0, dx},
							{0, 1, dy},
							{0, 0, 1}};
		return new Transformation(new Matrix(tform).xMatrix(this));
	}
	
	public Transformation addRotation(double alpha) {
		double tform[][] = {{Math.cos(alpha), - Math.sin(alpha), 0},
							{Math.sin(alpha), Math.cos(alpha), 0},
							{0, 0, 1}};
		return new Transformation(new Matrix(tform).xMatrix(this));
	}
	
	public Transformation addRotation(Point center, double alpha) throws InfinitePointException {
		return this.addTranslation(- center.getNormalizedA(), - center.getNormalizedB())
				.addRotation(alpha)
				.addTranslation(center.getNormalizedA(), center.getNormalizedB());
	}
	
	public Transformation addScaling(double ax, double by) {
		double tform[][] = {{ax, 0, 0},
							{0, by, 0},
							{0, 0, 1}};
		return new Transformation(new Matrix(tform).xMatrix(this));
	}
	
	public Transformation addScaling(Point center, double ax, double by) throws InfinitePointException {
		return this.addTranslation(- center.getNormalizedA(), - center.getNormalizedB())
					.addScaling(ax, by)
					.addTranslation(center.getNormalizedA(), center.getNormalizedB());
	}
}
