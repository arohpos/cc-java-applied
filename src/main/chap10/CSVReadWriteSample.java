package main.chap10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReadWriteSample {

	private static final String CSV_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap10/sample.csv";

	public static void main(String[] args) {

		List<User> users = User.getUserData();
		System.out.println("【初期データ】");
		User.printUserData(users);

		//CSVファイルへの初期データの書込み処理
		try (BufferedWriter out = Files.newBufferedWriter(Paths.get(CSV_FILE));) {
			for (User user : users) {
				out.write(String.format("%s, %s, %s%n", user.getId(), user.getName(), user.getAge()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//CSVファイルの読み込み処理
		List<User> readData = new ArrayList<>();
		try (BufferedReader in = Files.newBufferedReader(Paths.get(CSV_FILE));) {
			while (true) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				String[] values = line.split(", ");
				User user = new User(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]));
				readData.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("【CSVファイルから読み込んだデータ】");
		System.out.println(readData);

	}

}
