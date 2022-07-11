package assignment;

public class Approx {
	public static int makeInt(double x) {
		return (int)(x * 1000000 + 0.5);
	}
	
	public static boolean equals(double x, double y) {
		return makeInt(x) == makeInt(y);
	}
}
