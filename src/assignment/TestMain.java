package assignment;

import java.util.regex.Pattern;

public class TestMain {
	public static void main(String[] args) {
		Pattern vertexPattern = Pattern.compile("v( +\\d+(\\.\\d+)?){3}");
		String testStr = "v 1.0 string 3.01";
		System.out.println(vertexPattern.matcher(testStr).matches());
	}
}
