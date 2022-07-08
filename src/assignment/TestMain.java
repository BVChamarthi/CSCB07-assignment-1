package assignment;

import java.io.IOException;
import java.util.LinkedHashSet;

public class TestMain {
	public static void main(String[] args) throws WrongFileFormatException, IOException {

		Mesh carMesh = new Mesh();
		
		carMesh.setReader(new OBJMeshReader());
		carMesh.setWriter(new PLYMeshWriter());
		
		carMesh.readFromFile("car.obj");
		carMesh.writeToFile("new-car.ply");
		
		Mesh carPLYMesh = new Mesh();
		
		carPLYMesh.setReader(new PLYMeshReader());
		carPLYMesh.setWriter(new OBJMeshWriter());
		
		carPLYMesh.readFromFile("car.ply");
		carPLYMesh.writeToFile("new-car.obj");
		
		System.out.println((carMesh.equals(carPLYMesh)));
		
		LinkedHashSet<Integer> intset1 = new LinkedHashSet<Integer>();
		LinkedHashSet<Integer> intset2 = new LinkedHashSet<Integer>();
	}
}
