package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OBJMeshReader implements MeshReader {

	@Override
	public HashSet<Polygon> read(String fileName) throws WrongFileFormatException {
		try {
			Scanner fileReader = new Scanner(new File(fileName));				// canner to read file
			
			Pattern vertexPattern = Pattern.compile("v( +\\d+(\\.\\d+)?){3}");	// regex for vertex
			Pattern facePattern = Pattern.compile("f( +\\d+(\\.\\d+)?){3}");	// regex for face
			
			String line;										// string to store line read from file
			String[] lineTokens;								// string array to store tokenized line
			int lineNumber = 1;									// line number (useful for error message)
			
			ArrayList<Vertex> vertices = new ArrayList<Vertex>();				// array of vertices
			
			while(fileReader.hasNextLine()) {						// while loop 1 : read vertices
				line = fileReader.nextLine();										// read a line from file
				if(!vertexPattern.matcher(line).matches())							// if it isn't a vertex, go read the faces
					break;
				lineTokens = line.split(" ");										// split the line
				
				vertices.add(new Vertex(	Double.parseDouble(lineTokens[1]),		// create the new vertex
											Double.parseDouble(lineTokens[2]),
											Double.parseDouble(lineTokens[3])));
				
				lineNumber++;
			}
			
			HashSet<Polygon> ans = new HashSet<Polygon>();		// create hash set of polygon
			
			while(fileReader.hasNextLine()) {						// while loop 2 : read faces
				line = fileReader.nextLine();										// read a line from file
				if(!facePattern.matcher(line).matches())			// if it does not match a face, then throw error
					throw new WrongFileFormatException(	"Error in " + fileName + " at line " + 
														lineNumber + ", incorrect formatting");

				lineTokens = line.split(" ");										// split the line
				
				LinkedHashSet<Vertex> polygonVertices = new LinkedHashSet<Vertex>();	// create new linked hash set of vertices (for the polygon)
				for(int i = 1; i < lineTokens.length; i++) {			// loop through the tokenized line
					int vertexIndex = Integer.parseInt(lineTokens[i]);
					if(vertexIndex > vertices.size())					// check if vertex index in within bounds
						throw new WrongFileFormatException(		"Error in " + fileName + " at line " + 
																lineNumber + ", vertex index " + 
																vertexIndex + " out of bounds");

					Vertex polygonVertex = vertices.get(vertexIndex - 1);		// get vertex at vertexIndex
					polygonVertices.add(new Vertex(	polygonVertex.x,			// copy the vertex to the linked hash set
													polygonVertex.y,
													polygonVertex.z));
				}
				ans.add(new Polygon(polygonVertices));	// add new polygon created from the set of vertices, to the set of polygons
				
				lineNumber++;
			}
			
			return ans;

		} catch (FileNotFoundException e) {
			throw new WrongFileFormatException("unable to read from file at path : " + fileName);
		}
	}

}
