package geometry;

public class Numbers {
	private static double eps = 0.000001;		// 10^(-6)
	
	
	public static boolean isZero(double x) {
		if (-eps < x && x < eps) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int compare(double a, double b) {
		if (-eps < a - b && a - b < eps) {
			return 0;
		} else {
			if (a > b) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	public static boolean isBetween(double a, double b, double c) {
		return ((a - eps < b && b < c + eps) || (a + eps > b && b > c - eps));
	}
}
