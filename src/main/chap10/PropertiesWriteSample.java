package main.chap10;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class PropertiesWriteSample {
	
	private static final String PROPERTIES_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap10/sample2.properties";

	public static void main(String[] args) {
		
		User user = new User(1, "Alice", 25);
		try(
				Writer out = new FileWriter(PROPERTIES_FILE);){
			Properties properties = new Properties();
			
			properties.setProperty("id", Integer.toString(user.getId()));
			properties.setProperty("name", user.getName());
			properties.setProperty("age",Integer.toString(user.getAge()));
			
			properties.store(out, "プロパティファイルのテスト");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
