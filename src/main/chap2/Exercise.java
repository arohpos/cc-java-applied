package main.chap2;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int settedYear = 0;
		//指定範囲の半角数字が入力されるまで入力を繰り返す
		while(true) {			
			try {
				System.out.println("カレンダーの年を1900〜2100で入力してください＞");
				settedYear = scanner.nextInt();	
				
				if(settedYear < 1900 || settedYear > 2100) {
					throw new IllegalArgumentException("数値が指定範囲を超えています。");
				}
				
				break;
			} catch (InputMismatchException e) {
				System.out.println("半角数字以外が入力されています。");
				scanner.next();
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
		
		int settedMonth = 0;
		//指定範囲の半角数字が入力されるまで入力を繰り返す
		while(true) {			
			try {
				System.out.println("カレンダーの月を1〜12で入力してください＞");
				settedMonth = scanner.nextInt();	
				
				if(settedMonth < 1 || settedMonth > 12) {
					throw new IllegalArgumentException("数値が指定範囲を超えています。");
				}
				
				break;
			} catch (InputMismatchException e) {
				System.out.println("半角数字以外が入力されています。");
				scanner.next();
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println(settedYear + "年" + settedMonth + "月のカレンダーを表示");
		
		
		DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("dd日（E）");		
		int settedDay = 1;
		//存在しない日付を出力しようとするまで処理を繰り返す
		while(true) {
			try {
				ZonedDateTime calDay = ZonedDateTime.of(settedYear, settedMonth, settedDay, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
				System.out.print(dataTimeFormatter.format(calDay) + " ");
				//土曜日の日付を出力したらカレンダーを折り返す
				if(calDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
					System.out.println("");
				}
				settedDay = settedDay + 1;
			}catch (Exception e) {
				break;
			}
		}
		
	}

}
