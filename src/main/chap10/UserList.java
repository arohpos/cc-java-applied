package main.chap10;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class UserList implements Serializable{
	
	private static final long serialVersionUID = -6744139307211432443L;
	private List<User> users;
	
	//JAXBには必ずデフォルトコンストラクタ必要
	public UserList() {
		super();
	}
	
	public UserList(List<User> users) {
		super();
		this.users = users;
	}
	
	@XmlElementWrapper
	@XmlElement(name = "user")
	public List<User> getUsers(){
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
 
}
