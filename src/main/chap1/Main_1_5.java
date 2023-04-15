package main.chap1;

public class Main_1_5 {

	public static void main(String[] args) {
		int hour = 7;
		int minute = 15;
		int second = 0;
		int milliSecond =150;
		String message = String.format("現在の時刻は%d時%d分%d秒%dです。", hour, minute, second, milliSecond);
		System.out.println(message);

		String message2 = String.format("現在の時刻は%02d時%02d分%02d秒%04dです。", hour, minute, second, milliSecond);
		System.out.println(message2);
		
	}

}
