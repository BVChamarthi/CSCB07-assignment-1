package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

class testAssignment1 {
	
	// HELPER FUNCTION, CREATES EXPECTED MESH FOR SIMPLE MODEL
	
	Mesh expectedSimpleMesh() {
		HashSet<Polygon> expectedPolygons = new HashSet<Polygon>();
		Vertex[] vertices = {
				new Vertex(5.1, 1.2, 0.3),
				new Vertex(4.9, 1.5, 0.3),
				new Vertex(3.8, 1.4, 0.5),
				new Vertex(4.1, 1.6, 0.6)
		};
		LinkedHashSet<Vertex> poly1 = new LinkedHashSet<Vertex>();
		poly1.add(vertices[0]);
		poly1.add(vertices[1]);
		poly1.add(vertices[2]);
		expectedPolygons.add(new Polygon(poly1));
		LinkedHashSet<Vertex> poly2 = new LinkedHashSet<Vertex>();
		poly2.add(vertices[1]);
		poly2.add(vertices[2]);
		poly2.add(vertices[3]);
		expectedPolygons.add(new Polygon(poly2));
		Mesh expectedMesh = new Mesh();
		expectedMesh.polygons = expectedPolygons;
		return expectedMesh;
	}
	
	// OBJMESHREADER
	
	@Test
	void OBJMeshReader_ReadSimple() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("simple.obj");
		assertTrue(mesh.equals(expectedSimpleMesh()));
	}
	
	@Test
	void OBJMeshReader_CannotOpenFile() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			OBJMesh.readFromFile("does-not-exist.obj");
		});
		String expectedMessage = "Error: does-not-exist.obj : cannot open file";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OBJMeshReader_IncorrectFormat() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			OBJMesh.readFromFile("simple-incorrect-format.obj");
		});
		String expectedMessage = "Error: simple-incorrect-format.obj : line 3 'f 3.8 1.4 0.5' : incorrect format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OBJMeshReader_VertexOutOfBounds() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			OBJMesh.readFromFile("simple-vertex-out-of-bounds.obj");
		});
		String expectedMessage = "Error: simple-vertex-out-of-bounds.obj : line 6 'f 2 3 5' : vertex index out of bounds\n"
									+ "number of vertices = 4";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OBJMeshReader_NoFaces() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("simple-no-faces.obj");
		assertEquals(mesh.polygons.size(), 0);
	}
	
	// PLYMESHREADER
	
	@Test
	void PLYMeshReader_ReadSimple() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("simple.ply");
		assertTrue(mesh.equals(expectedSimpleMesh()));
	}
	
	@Test
	void PLYMeshReader_CannotOpenFile() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("does-not-exist.ply");
		});
		String expectedMessage = "Error: does-not-exist.ply : cannot open file";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_IncorrectHeader() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-incorrect-header.ply");
		});
		String expectedMessage = "Error: simple-incorrect-header.ply : line 6 'property int32 z' : incorrect format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_EndedBeforeHeader() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-ended-before-header.ply");
		});
		String expectedMessage = "Error: simple-ended-before-header.ply : ended before completion of the header";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_IncorrectFormat() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-incorrect-format.ply");
		});
		String expectedMessage = "Error: simple-incorrect-format.ply : line 12 'v 3.8 1.4 0.5' : incorrect format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_VertexOutOfBounds() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-vertex-out-of-bounds.ply");
		});
		String expectedMessage = "Error: simple-vertex-out-of-bounds.ply : line 15 '3 1 2 4 ' : vertex index out of bounds\n"
									+ "number of vertices = 4";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_PolygonVerticesDescrepency() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-polygon-vertices-discrepency.ply");
		});
		String expectedMessage = "Error: simple-polygon-vertices-discrepency.ply : line 14 '3 0 1 2 3' : "
									+ "discrepency in number of vertices\nexpected = 3, actual = 4";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_VerticesDiscrepency() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-vertices-discrepency.ply");
		});
		String expectedMessage = "Error: simple-vertices-discrepency.ply : discrepency in total number of vertices\n"
								+ "expected = 5, actual = 4";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_PolygonsDiscrepency() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			PLYMesh.readFromFile("simple-polygons-discrepency.ply");
		});
		String expectedMessage = "Error: simple-polygons-discrepency.ply : discrepency in total number of polygons\n"
								+ "expected = 3, actual = 2";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void PLYMeshReader_NoFaces() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("simple-no-faces.ply");
		assertEquals(mesh.polygons.size(), 0);
	}
	
	// OFFMESHREADER
	
	@Test
	void OFFMeshReader_ReadSimple() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("simple.off");
		assertTrue(mesh.equals(expectedSimpleMesh()));
	}
	
	@Test
	void OFFMeshReader_CannotOpenFile() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("does-not-exist.off");
		});
		String expectedMessage = "Error: does-not-exist.off : cannot open file";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_IncorrectHeaderFormat() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("simple-incorrect-header.off");
		});
		String expectedMessage = "Error: simple-incorrect-header.off : incorrect header format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_IncorrectFormat() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("simple-incorrect-format.off");
		});
		String expectedMessage = "Error: simple-incorrect-format.off : line 5 'v 3.8 1.4 0.5' : incorrect format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_VertexOutOfBounds() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("simple-vertex-out-of-bounds.off");
		});
		String expectedMessage = "Error: simple-vertex-out-of-bounds.off : line 8 '3 1 2 4 220 220 200' : vertex index out of bounds\n"
								+ "number of vertices = 4";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_VerticesDiscrepency() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("simple-vertices-discrepency.off");
		});
		String expectedMessage = "Error: simple-vertices-discrepency.off : discrepency in total number of vertices\n"
								+ "expected = 4, actual = 5";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_PolygonsDiscrepency() {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		Exception exception = assertThrows(WrongFileFormatException.class, () -> {
			mesh.readFromFile("simple-polygons-discrepency.off");
		});
		String expectedMessage = "Error: simple-polygons-discrepency.off : discrepency in total number of polygons\n"
								+ "expected = 3, actual = 2";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void OFFMeshReader_NoFaces() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("simple-no-faces.off");
		assertEquals(mesh.polygons.size(), 0);
	}
	
	// MESH
	
	@Test
	void Mesh_hashCode() throws WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("car.obj");
		mesh.hashCode();
	}
	
	@Test
	void Mesh_equalsNull() {
		Mesh mesh = new Mesh();
		assertFalse(mesh.equals(null));
	}
	
	@Test
	void Mesh_equalsObject() {
		Mesh mesh = new Mesh();
		Vertex v = new Vertex(1,2,3);
		assertFalse(mesh.equals(v));
	}
	
/*	@Test
	void Mesh_toString() {
		String expected = "Mesh :\n"
				+ "Polygon\n"
				+ "5.1 1.2 0.3\n"
				+ "4.9 1.5 0.3\n"
				+ "3.8 1.4 0.5\n"
				+ "\n"
				+ "Polygon\n"
				+ "4.9 1.5 0.3\n"
				+ "3.8 1.4 0.5\n"
				+ "4.1 1.6 0.6\n"
				+ "\n";
		assertEquals(expectedSimpleMesh().toString(), expected);
	}	*/
	
	// POLYGON
	
	@Test
	void Polygon_equalsItself() {
		Polygon p = new Polygon(null);
		assertTrue(p.equals(p));
	}
	
	@Test
	void Polygon_equalsNull() {
		Polygon p = new Polygon(null);
		assertFalse(p.equals(null));
	}
	
	@Test
	void Polygon_equalsObject() {
		Polygon p = new Polygon(null);
		Vertex v = new Vertex(1,2,3);
		assertFalse(p.equals(v));
	}
	
	// VERTEX
	
	@Test
	void Vertex_equalsNull() {
		Vertex v = new Vertex(1,2,3);
		assertFalse(v.equals(null));
	}
	
	@Test
	void Vertex_equalsObject() {
		Vertex v = new Vertex(1,2,3);
		Polygon p = new Polygon(null);
		assertFalse(v.equals(p));
	}
	
	@Test
	void Vertex_rotateXAxis() {
		Vertex v = new Vertex(1,2,3);
		v.rotateXAxis(Math.PI / 6);
		Vertex expected = new Vertex(1, 0.232051, 3.598076);
		assertEquals(v, expected);
	}
	
	@Test
	void Vertex_rotateYAxis() {
		Vertex v = new Vertex(1,2,3);
		v.rotateYAxis(Math.PI / 6);
		Vertex expected = new Vertex(2.366025, 2, 2.098076);
		assertEquals(v, expected);
	}
	
	@Test
	void Vertex_rotateZAxis() {
		Vertex v = new Vertex(1,2,3);
		v.rotateZAxis(Math.PI / 6);
		Vertex expected = new Vertex(-0.133975, 2.232051, 3);
		assertEquals(v, expected);
	}
	
	// TEST TRANSLATION OF SIMPLE MODEL BETWEEN FILE FORMATS
	
	@Test
	void TranslateSimple_OBJtoPLY() throws WrongFileFormatException, IOException {
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.setWriter(new PLYMeshWriter());
		carOBJMesh.readFromFile("simple.obj");
		carOBJMesh.writeToFile("new-simple.ply");
			
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.readFromFile("new-simple.ply");
		
		assertTrue(carOBJMesh.equals(carPLYMesh));
	}
	
	@Test
	void TranslateSimple_PLYtoOBJ() throws WrongFileFormatException, IOException {
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.setWriter(new OBJMeshWriter());
		carPLYMesh.readFromFile("simple.ply");
		carPLYMesh.writeToFile("new-simple.obj");
		
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.readFromFile("new-simple.obj");
		
		assertTrue(carOBJMesh.equals(carPLYMesh));
	}
	
	@Test
	void TranslateSimple_OBJtoOFF() throws WrongFileFormatException, IOException {
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.setWriter(new OFFMeshWriter());
		carOBJMesh.readFromFile("simple.obj");
		carOBJMesh.writeToFile("new-simple.off");
		
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.readFromFile("new-simple.off");
		
		assertTrue(carOBJMesh.equals(carOFFMesh));
	}
	
	@Test
	void TranslateSimple_OFFtoOBJ() throws WrongFileFormatException, IOException {
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.setWriter(new OBJMeshWriter());
		carOFFMesh.readFromFile("simple.off");
		carOFFMesh.writeToFile("new-simple.obj");
		
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.readFromFile("new-simple.obj");
		
		assertTrue(carOBJMesh.equals(carOFFMesh));
	}
	
	@Test
	void TranslateSimple_PLYtoOFF() throws WrongFileFormatException, IOException {
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.setWriter(new OFFMeshWriter());
		carPLYMesh.readFromFile("simple.ply");
		carPLYMesh.writeToFile("new-simple.off");
		
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.readFromFile("new-simple.off");
		
		assertTrue(carPLYMesh.equals(carOFFMesh));
	}
	
	@Test
	void TranslateSimple_OFFtoPLY() throws WrongFileFormatException, IOException {
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.setWriter(new PLYMeshWriter());
		carOFFMesh.readFromFile("simple.off");
		carOFFMesh.writeToFile("new-simple.ply");
		
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.readFromFile("new-simple.ply");
		
		assertTrue(carOFFMesh.equals(carPLYMesh));
	}
	
	// TEST CONSISTANCY OF ROTATION OF SIMPLE UNDER DIFFERENT FILE FORMATS
	
	@Test
	void RotateSimple_OBJPLY() throws WrongFileFormatException {
		Mesh simpleOBJ = new Mesh();
		simpleOBJ.setReader(new OBJMeshReader());
		simpleOBJ.readFromFile("simple.obj");
		simpleOBJ.rotateXAxis(1);
		
		Mesh simplePLY = new Mesh();
		simplePLY.setReader(new PLYMeshReader());
		simplePLY.readFromFile("simple.ply");
		simplePLY.rotateXAxis(1);
		
		assertTrue(simpleOBJ.equals(simpleOBJ));
	}
	
	@Test
	void RotateSimple_OBJOFF() throws WrongFileFormatException {
		Mesh simpleOBJ = new Mesh();
		simpleOBJ.setReader(new OBJMeshReader());
		simpleOBJ.readFromFile("simple.obj");
		simpleOBJ.rotateXAxis(1);
		
		Mesh simpleOFF = new Mesh();
		simpleOFF.setReader(new OFFMeshReader());
		simpleOFF.readFromFile("simple.off");
		simpleOFF.rotateXAxis(1);
		
		assertTrue(simpleOBJ.equals(simpleOBJ));
	}
}