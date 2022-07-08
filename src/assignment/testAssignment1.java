package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class testAssignment1 {
	
	// TEST TRANSLATION FOR CAR MODEL BETWEEN FILE FORMATS

	@Test
	void testTranslateCarOBJtoPLY() throws WrongFileFormatException, IOException {
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.setWriter(new PLYMeshWriter());
		carOBJMesh.readFromFile("car.obj");
		carOBJMesh.writeToFile("new-car.ply");
		
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.readFromFile("new-car.ply");
		
		assertTrue(carOBJMesh.equals(carPLYMesh));
	}
	
	@Test
	void testTranslateCarPLYtoOBJ() throws WrongFileFormatException, IOException {
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.setWriter(new OBJMeshWriter());
		carPLYMesh.readFromFile("car.ply");
		carPLYMesh.writeToFile("new-car.obj");
		
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.readFromFile("new-car.obj");
		
		assertTrue(carOBJMesh.equals(carPLYMesh));
	}
	
	@Test
	void testTranslateCarOBJtoOFF() throws WrongFileFormatException, IOException {
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.setWriter(new OFFMeshWriter());
		carOBJMesh.readFromFile("car.obj");
		carOBJMesh.writeToFile("new-car.off");
		
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.readFromFile("new-car.off");
		
		assertTrue(carOBJMesh.equals(carOFFMesh));
	}
	
	@Test
	void testTranslateCarOFFtoOBJ() throws WrongFileFormatException, IOException {
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.setWriter(new OBJMeshWriter());
		carOFFMesh.readFromFile("car.off");
		carOFFMesh.writeToFile("new-car.obj");
		
		Mesh carOBJMesh = new Mesh();
		carOBJMesh.setReader(new OBJMeshReader());
		carOBJMesh.readFromFile("new-car.obj");
		
		assertTrue(carOBJMesh.equals(carOFFMesh));
	}
	
	@Test
	void testTranslateCarPLYtoOFF() throws WrongFileFormatException, IOException {
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.setWriter(new OFFMeshWriter());
		carPLYMesh.readFromFile("car.ply");
		carPLYMesh.writeToFile("new-car.off");
		
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.readFromFile("new-car.off");
		
		assertTrue(carPLYMesh.equals(carOFFMesh));
	}
	
	@Test
	void testTranslateCarOFFtoPLY() throws WrongFileFormatException, IOException {
		Mesh carOFFMesh = new Mesh();
		carOFFMesh.setReader(new OFFMeshReader());
		carOFFMesh.setWriter(new PLYMeshWriter());
		carOFFMesh.readFromFile("car.off");
		carOFFMesh.writeToFile("new-car.ply");
		
		Mesh carPLYMesh = new Mesh();
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.readFromFile("new-car.ply");
		
		assertTrue(carOFFMesh.equals(carPLYMesh));
	}
	
	// TEST TRANSLATION OF SIMPLE MODEL BETWEEN FILE FORMATS
	
	@Test
	void testTranslateSimpleOBJtoPLY() throws WrongFileFormatException, IOException {
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
	void testTranslateSimplePLYtoOBJ() throws WrongFileFormatException, IOException {
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
	void testTranslateSimpleOBJtoOFF() throws WrongFileFormatException, IOException {
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
	void testTranslateSimpleOFFtoOBJ() throws WrongFileFormatException, IOException {
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
	void testTranslateSimplePLYtoOFF() throws WrongFileFormatException, IOException {
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
	void testTranslateSimpleOFFtoPLY() throws WrongFileFormatException, IOException {
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
	
	// TEST ROTATION OF SIMPLE UNDER DIFFERENT FILE FORMATS
	
	@Test
	void testRotateSimpleOBJPLY() throws WrongFileFormatException {
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
	void testRotateSimpleOBJOFF() throws WrongFileFormatException {
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
	
	// OBJMESHREADER EXCEPTIONS
	
	@Test
	void testOBJMeshReaderCannotOpenFile() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		OBJMesh.readFromFile("does-not-exist.obj");
	}
	
	@Test
	void testOBJMeshReaderIncorrectFormat() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		OBJMesh.readFromFile("simple-incorrect-format.obj");
	}
	
	@Test
	void testOBJMeshReaderVertexOutOfBounds() throws WrongFileFormatException {
		Mesh OBJMesh = new Mesh();
		OBJMesh.setReader(new OBJMeshReader());
		OBJMesh.readFromFile("simple-vertex-out-of-bounds.obj");
	}
	
	// PLYMESHREADER EXCEPTIONS
	
	@Test
	void testPLYMeshReaderCannotOpenFile() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		PLYMesh.readFromFile("does-not-exist.ply");
	}
	
	@Test
	void testPLYMeshReaderIncorrectHeader() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		PLYMesh.readFromFile("simple-incorrect-header.ply");
	}
	
	@Test
	void testPLYMeshReaderEndedBeforeHeader() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		PLYMesh.readFromFile("simple-ended-before-header.ply");
	}
	
	@Test
	void testPLYMeshReaderVerticesDiscrepency() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		PLYMesh.readFromFile("simple-vertices-discrepency.ply");
	}
	
	@Test
	void testPLYMeshReaderIncorrectFormat() throws WrongFileFormatException {
		Mesh PLYMesh = new Mesh();
		PLYMesh.setReader(new PLYMeshReader());
		PLYMesh.readFromFile("simple-incorrect-format.ply");
	}

}
