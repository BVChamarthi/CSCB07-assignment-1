package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PLYMeshReader implements MeshReader {

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException {
		
		// READ FROM FILE AND STORE THE LINES IN A STRING ARRAY
		
		Scanner fileReader;
		try {					// attempt to open the file for reading, throw exception if it cannot be opened
			fileReader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new WrongFileFormatException("\nError: " + filename + " : cannot open file");
		}
		
		ArrayList<String> lines = new ArrayList<String>();	// get all the lines of the file in a string array
		while(fileReader.hasNext())
			lines.add(fileReader.nextLine());
		
		// READ, CHECK AND GET INFO FROM HEADER
		
		Pattern[] headerRegexes = {						// initialise regex array to check header
				Pattern.compile("ply"),
				Pattern.compile("format ascii 1.0"),
				Pattern.compile("element vertex \\d+"),
				Pattern.compile("property float32 x"),
				Pattern.compile("property float32 y"),
				Pattern.compile("property float32 z"),
				Pattern.compile("element face \\d+"),
				Pattern.compile("property list uint8 int32 vertex_indices"),
				Pattern.compile("end_header")
		};
		
		int lineNumber = 0;
		for(; lineNumber < headerRegexes.length && lineNumber < lines.size(); lineNumber++) {	// check the formatting of the header
			if(!headerRegexes[lineNumber].matcher(lines.get(lineNumber)).matches())
				throw new WrongFileFormatException("\nError: " + filename + " : line "
													+ String.valueOf(lineNumber+1)
													+ " '" + lines.get(lineNumber)
													+ "' : incorrect format");
		}
		if(lineNumber < headerRegexes.length)		// check if file ended before end of the header
			throw new WrongFileFormatException("\nError: " + filename + " : ended before completion of the header");
		
		ArrayList<String> headerTokens = StringParse.parse(lines.get(2));									// get number of vertices from header
		int expectedVertices = Integer.valueOf(headerTokens.get(2));										
		headerTokens = StringParse.parse(lines.get(6));														// get number of polygons from header
		int expectedPolygons = Integer.valueOf(headerTokens.get(2));		
		
		// SET UP FOR READING LINES OF FILE
		
		Pattern vertexPattern = Pattern.compile(" *-?\\d+(\\.\\d+)?( +-?\\d+(\\.\\d+)?){2} *");	// initialise regexes
		Pattern facePattern = Pattern.compile(" *\\d+( +\\d+)+ *");							// for the vertices and faces
		
		ArrayList<Vertex> allVertices = new ArrayList<Vertex>();
		HashSet<Polygon> ans = new HashSet<Polygon>();
		
		// READ VERTICES
		
		for(; lineNumber < lines.size(); lineNumber++) {		// loop over lines of the string to get vertices
			
			if(!vertexPattern.matcher(lines.get(lineNumber)).matches())	// check if the current line is a vertex
				break;													// start checking faces if it isn't
			
			ArrayList<String> lineTokens = StringParse.parse(lines.get(lineNumber));
			
			allVertices.add(new Vertex(	Double.parseDouble(lineTokens.get(0)),		// create the new vertex
										Double.parseDouble(lineTokens.get(1)),		// by getting coordinates
										Double.parseDouble(lineTokens.get(2))));	// from the tokesized line
		}
		
		// READ POLYGONS
		
		for(;lineNumber < lines.size(); lineNumber++) {
			
			if(!facePattern.matcher(lines.get(lineNumber)).matches())	// check if the line is a face, throw error if it's not
				throw new WrongFileFormatException("\nError: " + filename + " : line " 
													+ String.valueOf(lineNumber+1)
													+ " '" + lines.get(lineNumber)
													+ "' : incorrect format" );
			
			ArrayList<String> lineTokens = StringParse.parse(lines.get(lineNumber));
			int expectedPolygonVertices = Integer.valueOf(lineTokens.get(0));
			LinkedHashSet<Vertex> polygonVertices = new LinkedHashSet<Vertex>();
			
			for(int i = 1; i < lineTokens.size(); i++) {					// loop through vertex indices in the face line
				int vertexIndex = Integer.valueOf(lineTokens.get(i));				// get vertex index
				
				if(vertexIndex >= allVertices.size())
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
			
			if(polygonVertices.size() != expectedPolygonVertices)
				throw new WrongFileFormatException("\nError: " + filename + " : line " 
													+ String.valueOf(lineNumber+1)
													+ " '" + lines.get(lineNumber)
													+ "' : discrepency in number of vertices\n"
													+ "expected = " + expectedPolygonVertices
													+ ", actual = " + polygonVertices.size());
			
			ans.add(new Polygon(polygonVertices)); 			// make a new polygon and add it to the hash set of polygons
		}
		
		if(allVertices.size() != expectedVertices)	// check if actual number of vertices is same as expected number from header
			throw new WrongFileFormatException("\nError: " + filename
												+ " : discrepency in total number of vertices\n"
												+ "expected = " + expectedVertices
												+ ", actual = " + allVertices.size());
		
		if(ans.size() != expectedPolygons)	// check if actual number of polygons is same as expected number from header
			throw new WrongFileFormatException("\nError: " + filename
												+ " : discrepency in total number of polygons\n"
												+ "expected = " + expectedPolygons
												+ ", actual = " + ans.size());
		
		return ans;
	}

}
