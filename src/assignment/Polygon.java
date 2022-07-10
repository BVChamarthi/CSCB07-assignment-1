package assignment;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Polygon extends GraphicalObject{

	public LinkedHashSet<Vertex> vertices;

	public Polygon(LinkedHashSet<Vertex> vertices) {
		super();
		this.vertices = vertices;
	}

	@Override
	public void transform(double[][] transformMatrix) {
		for(Vertex vertex : vertices)
			vertex.transform(transformMatrix);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertices);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Polygon))
			return false;
		Polygon other = (Polygon) obj;
		return Objects.equals(vertices, other.vertices);
	}

/*	@Override
	public String toString() {
		String ans = "Polygon\n";
		for(Vertex v : vertices) {
			ans = ans + v.toString() + "\n";
		}
		
		return ans;
	} */
	
}
