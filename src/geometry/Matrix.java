package geometry;


public class Matrix {
	private double val[][] = new double[3][3];
	
	protected static double[] I_Val[] = {{1,0,0}, {0,1,0}, {0,0,1}};
	
	
	public Matrix() {
		val = I_Val.clone();
	}
	
	public Matrix(double val[][]) {
		this.val = val.clone();
	}
	
	public Matrix(Matrix m) {
		val = m.val.clone();
	}
	
	public String toString() {
		return getRow(0).toString() + "\n" + 
				getRow(1).toString() + "\n" + 
				getRow(2).toString();
	}
	
	public Vector getRow(int i) {
		return new Vector(val[i]);
	}
	
	public Vector getColumn(int i) {
		double res[] = new double[3];
		for (int k = 0; k < 3; k++)
			res[k] = val[k][i];
		return new Vector(res);
	}
	
	public Matrix xMatrix(Matrix m) {
		double res[][] = new double[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				res[i][j] = getRow(i).sProd(m.getColumn(j));
			}
		}
		return new Matrix(res);
	}
	
	public Matrix transpose() {
		double res[][] = new double[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0;  j < 3; j++) {
				res[i][j] = this.val[j][i];
			}
		}
		return new Matrix(res);
	}
	
	public double determinant() {
		return val[0][0] * (val[1][1] * val[2][2] - val[1][2] * val[2][1]) - 
				val[0][1] * (val[1][0] * val[2][2] - val[1][2] * val[2][0]) + 
				val[0][2] * (val[1][0] * val[2][1] - val[1][1] * val[2][0]);
	}
	
	public Matrix inverse() {
		double res[][] = new double[3][3];
		double det = determinant();
		res[0] = getColumn(1).xProd(getColumn(2)).scale(1 / det).toArray();
		res[1] = getColumn(2).xProd(getColumn(0)).scale(1 / det).toArray();
		res[2] = getColumn(0).xProd(getColumn(1)).scale(1 / det).toArray();
		return new Matrix(res);
	}
}
