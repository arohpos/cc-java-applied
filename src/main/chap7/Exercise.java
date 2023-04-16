package main.chap7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Exercise {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	
		System.out.println("実行したいクラスを入力してください");
		String inputtedClassName = "main.chap7." + scanner.next();
		
		try {
			Class<?> clazz = Class.forName(inputtedClassName);
			//clazzはブロック内でのローカル変数のため、clazzを使う場合はブロック内で処理をする必要がある。
			Method[] methods = clazz.getDeclaredMethods();			
			for(Method method : methods) {
				try {
					//なおclazz.newInstance()はJava9以降非推奨
					method.invoke(clazz.newInstance(), new Object[] {});
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| InstantiationException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("存在しないクラス名が指定されました。");
		}
	}
}
