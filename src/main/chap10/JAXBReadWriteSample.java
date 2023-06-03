package main.chap10;

import java.io.File;

import javax.xml.bind.JAXB;

public class JAXBReadWriteSample {
	
	private static final String XML_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap10/sample.xml";

	public static void main(String[] args) {
		
		File file = new File(XML_FILE);
		UserList initialData = new UserList(User.getUserData());
		System.out.println("【初期データ】");
		User.printUserData(initialData.getUsers());
		
		
		//初期データをfileにエクスポート
		JAXB.marshal(initialData, file);
		
		//ファイルからインポート
		UserList loadData = JAXB.unmarshal(file, UserList.class);
		System.out.println("【ロードデータ】");
		User.printUserData(loadData.getUsers());
		
	}

}
