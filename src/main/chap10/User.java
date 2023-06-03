package main.chap10;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * ユーザークラス
 */
@XmlType(propOrder = { "id", "name", "age" }) // JAXB用に要素の順番を調整
public class User implements Serializable{
	
	private static final long serialVersionUID = -1351355083900776865L;

	private int id;
	private String name;
	private int age;

	public User() {
		super();
	}

	public User(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "[id: " + id + ", name: " + name + ", age: " + age + "]";
	}

	public static List<User> getUserData() {
		List<User> userLists = new ArrayList<>();
		userLists.add(new User(1, "Alice", 25));
		userLists.add(new User(2, "Bob", 21));
		userLists.add(new User(3, "Cindy", 22));
		return userLists;
	}

	public static void printUserData(List<User> userLists) {
		for (User user : userLists) {
			System.out.println(user.toString());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
