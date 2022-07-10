package assignment;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class Mesh extends GraphicalObject {
	
	public HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;

	public void setReader(MeshReader reader) {
		this.reader = reader;
	}

	public void setWriter(MeshWriter writer) {
		this.writer = writer;
	}
	
	public void readFromFile(String filename) throws WrongFileFormatException {
		polygons = reader.read(filename);
	}
	
	public void writeToFile(String filename) throws WrongFileFormatException, IOException {
		writer.write(polygons, filename);
	}

	@Override
	public void transform(double[][] transformMatrix) {
		for(Polygon polygon : polygons)
			polygon.transform(transformMatrix);
	}

	@Override
	public int hashCode() {
		return Objects.hash(polygons, reader, writer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Mesh))
			return false;
		Mesh other = (Mesh) obj;
		return Objects.equals(polygons, other.polygons);
	}
	
/*	@Override
	public String toString() {
		String ans = "Mesh :\n";
		for(Polygon polygon : polygons) {
			ans = ans + polygon.toString() + "\n";
		}
		
		return ans;
	} */

}
