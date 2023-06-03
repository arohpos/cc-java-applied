package main.chap10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializeSample {

	private static final String DATA_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap10/sample.dat";

	public static void main(String[] args) {
		
		UserList userList = new UserList(User.getUserData());
		System.out.println("[initial data]");
		User.printUserData(userList.getUsers());
		
		try 
			(ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get(DATA_FILE)));){
			os.writeObject(userList);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try
			(ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(DATA_FILE)));){
			UserList loadData = (UserList) is.readObject();
			System.out.println("[loadData]");
			User.printUserData(loadData.getUsers());
	    } catch (IOException | ReflectiveOperationException e) {
	        e.printStackTrace();
	      }
		
		

	}

}
