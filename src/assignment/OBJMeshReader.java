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
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		
		// READ FROM FILE AND STORE THE LINES IN A STRING ARRAY
		
		Scanner fileReader;
		try {			// attempt to open the file for reading, throw exception if it cannot be opened
			fileReader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new WrongFileFormatException("\nError: " + filename + " : cannot open file");
		}
		
		ArrayList<String> lines = new ArrayList<String>();		// get all the lines of the file in a string array
		while(fileReader.hasNext())
			lines.add(fileReader.nextLine());
		
//		System.out.println(lines.size());
		
		// SET UP FOR READING LINES OF FILE
		
		Pattern vertexPattern = Pattern.compile("v( +-?\\d+(\\.\\d+)?){3} *");	// regex for vertex
		Pattern facePattern = Pattern.compile("f( +\\d+(\\.\\d+)?){3} *");	// regex for face
		
		ArrayList<Vertex> allVertices = new ArrayList<Vertex>();
		HashSet<Polygon> ans = new HashSet<Polygon>();
		
		int lineNumber = 0;
		
		// READ VERTICES
		
		for(; lineNumber < lines.size(); lineNumber++) {		// loop over lines of the string to get vertices
			
			if(!vertexPattern.matcher(lines.get(lineNumber)).matches())	// check if the current line is a vertex
				break;													// start checking faces if it isn't
			
			String[] lineTokens = lines.get(lineNumber).split(" ");
			
			allVertices.add(new Vertex(	Double.parseDouble(lineTokens[1]),		// create the new vertex
										Double.parseDouble(lineTokens[2]),		// by getting coordinates
										Double.parseDouble(lineTokens[3])));	// from the tokesized line
		}
		
		// READ POLYGONS
		
		for(;lineNumber < lines.size(); lineNumber++) {
			
			if(!facePattern.matcher(lines.get(lineNumber)).matches())	// check if the line is a face, throw error if it's not
				throw new WrongFileFormatException("\nError: " + filename + " : line "
													+ String.valueOf(lineNumber+1)
													+ " '" + lines.get(lineNumber) 
													+ "' : incorrect format");
			
			String[] lineTokens = lines.get(lineNumber).split(" ");
			
			LinkedHashSet<Vertex> polygonVertices = new LinkedHashSet<Vertex>();
			
			for(int i = 1; i < lineTokens.length; i++) {					// loop through vertex indices in the face line
				int vertexIndex = Integer.valueOf(lineTokens[i]) - 1;				// get vertex index
				
				if(vertexIndex > allVertices.size())
					throw new WrongFileFormatException("\nError: " + filename + " : line "
														+ String.valueOf(lineNumber+1)
														+ " '" + lines.get(lineNumber)
														+ "' : vertex index out of bounds\n"
														+ "number of vertices = " 
														+ allVertices.size());
				
				polygonVertices.add(new Vertex(	allVertices.get(vertexIndex).x,		// copy the vertex to a linked hash set
												allVertices.get(vertexIndex).y,		// of vertices, used to construct a polygon
												allVertices.get(vertexIndex).z));
			}
			
			ans.add(new Polygon(polygonVertices)); 			// make a new polygon and add it to the hash set of polygons
		}
		
		return ans;
	}

}
