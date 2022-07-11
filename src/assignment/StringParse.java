package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StringParse {
	public static ArrayList<String> parse(String input) {
		ArrayList<String> output = new ArrayList<String>(Arrays.asList(input.split(" ")));
		output.removeAll(Collections.singleton(null));
		output.removeAll(Collections.singleton(""));
		return output;
	}
}
