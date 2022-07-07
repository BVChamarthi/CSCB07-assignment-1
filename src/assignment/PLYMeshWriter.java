package assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter {

	@Override
	public void write(HashSet<Polygon> polygons, String filename) throws WrongFileFormatException, IOException {
		// OPEN FILE FOR WRITING
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(new File(filename));
		} catch (IOException e) {
			throw new WrongFileFormatException("Error: OBJMeshWriter : " + filename
												+ "cannot be openned for writing");
		}
		
		// CONSTRUCT VERTICES ARRAY
		
		LinkedHashSet<Vertex> allVertices = new LinkedHashSet<Vertex>();
		
		for(Polygon polygon : polygons)
			for(Vertex vertex : polygon.vertices)
				allVertices.add(vertex);
				
		ArrayList<Vertex> verticesList = new ArrayList<Vertex>(allVertices);
				
		// CONSTRUCT POLYGONS ARRAY (WITH INTS REFERING TO VERTICES)
				
		ArrayList<String> polygonReferences = new ArrayList<String>();
				
		for(Polygon polygon : polygons) {
			String polygonReference = String.valueOf(polygon.vertices.size());
			for(Vertex vertex : polygon.vertices)
				polygonReference = polygonReference + String.valueOf(verticesList.indexOf(vertex));

			polygonReferences.add(polygonReference);
		}
		
		// WRITE HEADER
		fileWriter.write("ply");
		fileWriter.write("format ascii 1.0");
		fileWriter.write("element vertex " + verticesList.size());
		fileWriter.write("property int32 x");
		fileWriter.write("property int32 y");
		fileWriter.write("property int32 z");
		fileWriter.write("element face " + polygons.size());
		fileWriter.write("property list uint8 int32 vertex_indices");
		fileWriter.write("end_header");
				
		// WRITE VERTICES ARRAY TO FILE
				
		for(Vertex vertex : verticesList)
			fileWriter.write(vertex.toString() + "\n");
				
		// WRITE POLYGONS ARRAY TO FILE
				
		for(int i = 0; i < polygonReferences.size() - 1; i++)
			fileWriter.write(polygonReferences.get(i) + "\n");

		fileWriter.write(polygonReferences.get(polygonReferences.size() - 1) + "\n");
				
		fileWriter.close();
	}

}
