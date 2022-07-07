package assignment;

import java.io.IOException;
import java.util.HashSet;

public interface MeshWriter {

	public void write(HashSet<Polygon> polygons, String filename) throws WrongFileFormatException, IOException;
}
