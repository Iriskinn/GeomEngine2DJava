package geometry;

public class Vector {
	private double a, b, c;
	
	
	public Vector(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Vector(Vector v) {
		this.a = v.a;
		this.b = v.b;
		this.c = v.c;
	}
	
	public Vector(double val[]) {
		a = val[0];
		b = val[1];
		c = val[2];
	}
	
	public double[] toArray() {
		double[] res = {a, b, c};
		return res;
	}
	
	public String toString() {
		return "[" + a + " " + b + " " + c + "]";
	}
	
	protected double getNormalizedA() throws InfinitePointException {
		if (c == 0) {
			throw new InfinitePointException();
		}
		return a / c;
	}
	
	protected double getNormalizedB() throws InfinitePointException {
		if (c == 0) {
			throw new InfinitePointException();
		}
		return b / c;
	}
	
	protected double getA() {
		return a;
	}
	
	protected double getB() {
		return b;
	}
	
	protected double getC() {
		return c;
	}
	
	protected boolean isZeroVector() {
		return (a == 0 && b == 0 && c == 0);
	}
	
	public Vector clone() {
		return new Vector(this);
	}
	
	public Vector xProd(Vector other) {
		double newA = this.b * other.c - this.c * other.b;
		double newB = this.c * other.a - this.a * other.c;
		double newC = this.a * other.b - this.b * other.a;
		
		return new Vector(newA, newB, newC);
	}
	
	public Vector scale(double s) {
		return new Vector(a * s, b * s, c * s);
	}
	
	public double sProd(Vector other) {
		return (this.a * other.a + this.b * other.b + this.c * other.c);
	}
	
	public Vector matrMul(Matrix m) {
		return new Vector(sProd(m.getRow(0)), sProd(m.getRow(1)), sProd(m.getRow(2)));
	}
}
