package main.chap10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.Scanner;

public class Exercise {

	private static final String PROPERTIES_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap10/messages.properties";

	public static void main(String[] args) {
		
		int numberTotal = 0;

		Scanner scanner = new Scanner(System.in);
		try (Reader reader = new FileReader(PROPERTIES_FILE);) {
			Properties properties = new Properties();
			properties.load(reader);

			while (true) {
				try {
					System.out.println("数値データを入力してください（終了：q）");
					String inputString = scanner.next();
					boolean isNumeric = inputString.matches("\\d+");

					// 入力された文字列が数値の場合
					if (isNumeric == true) {
						// 想定範囲内の数値が入力された場合
						if (Integer.parseInt(inputString) <= 10 && Integer.parseInt(inputString) >= 1) {
							// INFO001 : 正しい数値データ{$num}が入力されました
							//置換の仕方がメッセージに依存した個別処理となってしまっている。
							System.out.println(properties.get("INFO001").toString().replace("{$num}", inputString));
							numberTotal = numberTotal + Integer.parseInt(inputString);
							continue;
							// 想定範囲外の数値が入力された場合
						} else if (Integer.parseInt(inputString) > 10 || Integer.parseInt(inputString) < 1) {
							throw new IllegalArgumentException();
						}
						
					// 入力された文字列が数値でない場合
					} else {
						// 終了コマンドが入力された場合
						if (inputString.equals("q")) {
							// INFO002 : 正常に処理が終了しました。入力された値の合計は{$num}となりました。
							//置換の仕方がメッセージに依存した個別処理となってしまっている。
							System.out.println(properties.get("INFO002").toString().replace("{$num}", Integer.toString(numberTotal)));
							break;
						} else {
							// WARN002 : 数値以外の文字が入力されたため、強制終了しました。
							System.out.println(properties.get("WARN002"));
							break;
						}
					}
				} catch (IllegalArgumentException e) {
					// WARN001 : 数値データは1〜10の範囲で入力してください。
					System.out.println(properties.get("WARN001"));
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
