package main.chap9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReadWithStreamSample {

	private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/textWriteSample2.txt";
	
	public static void main(String[] args) {
		try {
			Stream<String> stream = Files.lines(Paths.get(INPUT_FILE));
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
