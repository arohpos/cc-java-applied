package main.chap4;

public class Employee {

	private int id;
	private String name;
	private int age;
	
	protected Employee(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "社員番号:\t" + this.id + "\t社員名:\t" + this.name + "\t年齢:\t" + this.age;
	}
	
	protected int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected int getAge() {
		return age;
	}
	protected void setAge(int age) {
		this.age = age;
	}
	
	
}
