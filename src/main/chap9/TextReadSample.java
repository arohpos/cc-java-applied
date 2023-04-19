package main.chap9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextReadSample {

	private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/textWriteSample2.txt";

	public static void main(String[] args) {
		
		try {
			//書込みと異なりprintメソッドが不要なため、PrintReaderクラスは存在しない。
			BufferedReader in = new BufferedReader(new FileReader(INPUT_FILE));
			
			while(true) {
				String line = in.readLine();
				
				if(line == null) {
					break;
				}
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
