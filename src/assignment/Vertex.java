package assignment;

import java.text.DecimalFormat;

public class Vertex extends GraphicalObject{
	public double x;
	public double y;
	public double z;
	public Vertex(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void transform(double[][] transformMatrix) {			// matrix vector multiplication
		double newX = 	transformMatrix[0][0] * x +
						transformMatrix[0][1] * y +
						transformMatrix[0][2] * z	;
		
		double newY = 	transformMatrix[1][0] * x +
						transformMatrix[1][1] * y +
						transformMatrix[1][2] * z	;
		
		double newZ = 	transformMatrix[2][0] * x +
						transformMatrix[2][1] * y +
						transformMatrix[2][2] * z	;
		
		x = newX;
		y = newY;
		z = newZ;
				
	}
	@Override
	public int hashCode() {
		//return Objects.hash(x, y, z);
		return Approx.makeInt(z);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Vertex))
			return false;
		Vertex other = (Vertex) obj;
		return (Approx.equals(x, other.x) && Approx.equals(y, other.y) && Approx.equals(z, other.z));
	}
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.0");
		df.setMaximumFractionDigits(6);
		return df.format(Approx.makeInt(x) / 1000000.0) + " " + df.format(Approx.makeInt(y) / 1000000.0) + " " + df.format(Approx.makeInt(z) / 1000000.0);
	}
	
	
	
}
