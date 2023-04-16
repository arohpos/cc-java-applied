package main.chap5;

import java.util.Scanner;

public class Exercise {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("誕生月を入力してください。");
		int inputMonth = scanner.nextInt();
		System.out.println("誕生日を入力してください。");
		int inputDay = scanner.nextInt();		
		System.out.println(Constellation.getType(inputMonth, inputDay));
		
	}

}
