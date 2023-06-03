package main.chap10;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleSample {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);//コメントアウトすると日本語メッセージが出る。
		ResourceBundle resource = ResourceBundle.getBundle("messages");
		System.out.println(resource.getString("greeting.message"));
	}

}
