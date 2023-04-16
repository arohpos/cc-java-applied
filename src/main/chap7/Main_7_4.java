package main.chap7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main_7_4 {

	public static void main(String[] args) {
		
		//ジェネリクスを用いたReflectionTest.javaのみ格納可能Classクラスのインスタンス化（クラス情報をメモリに展開）
		Class<ReflectionTest> clazz = ReflectionTest.class;
		
		//clazzインスタンスで指定されたクラス内のメソッド全てを、Method配列に格納
		Method[] methods = clazz.getDeclaredMethods();
		
		//実際のクラスのインスタンス化
		ReflectionTest reflectionTest = new ReflectionTest();
		
		for(Method method : methods) {
			System.out.println(method);
			
			if(!method.getName().startsWith("hoge")) {
				continue;
			}
			
			try {
				method.invoke(reflectionTest, new Object[] {});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
