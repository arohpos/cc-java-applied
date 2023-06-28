package main.chap12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise {
//	private static final String CSV_FILE1 = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_1.csv";
//	private static final String CSV_FILE2 = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_2.csv";
//	private static final String CSV_FILE3 = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_3.csv";

	public static void main(String[] args) {

		System.out.println("------------------------------------------");
//		System.out.println("[0]作成：郵便番号テーブル新規作成及びデータ追加");
		System.out.println("[1]検索：郵便番号全件検索");
		System.out.println("[2]検索：郵便番号検索");
		System.out.println("[3]検索：都道府県及び市町村検索");
		System.out.println("[9]終了");
		System.out.println("------------------------------------------");

		Scanner scanner = new Scanner(System.in);

//		boolean createFlg = false;
		boolean searchAllFlg = false;
		boolean filterByZipcodeFlg = false;
		boolean filterByAddressFlg = false;
		boolean quitFlg = false;

		while (true) {
			try {
				System.out.println("メニューから１つを選択し、半角数字１桁で入力してください");
				System.out.print(">");
				int input = scanner.nextInt();
				switch (input) {
//				case 0:
//					System.out.println("0");
//					createFlg = true;
//					break;
				case 1:
					searchAllFlg = true;
					break;
				case 2:
					filterByZipcodeFlg = true;
					break;
				case 3:
					filterByAddressFlg = true;
					break;
				case 9:
					quitFlg = true;
					break;
				default:
					throw new IllegalArgumentException("!！指定以外の値が入力されています!！");
				}
				break;// default以外のcaseの場合、入力制御のためのループを抜ける。
			} catch (InputMismatchException e) {
				System.out.println("!！半角数字以外が入力されています!！");
				scanner.next();// 入力バッファのクリア
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

//		if (createFlg == true) {
//			try (
//					// connection.close();のためのfinallyブロック省略
//					Connection connection = DriverManager.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb",
//							"sa", "");
//					PreparedStatement pstmtCreate = connection.prepareStatement(
//							"INSERT INTO ZIP_CODE SELECT * FROM CSVREAD('/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_1.csv') ");
//					PreparedStatement pstmtInsert1 = connection.prepareStatement(
//							"INSERT INTO ZIP_CODE SELECT * FROM CSVREAD('/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_2.csv') ");
//					PreparedStatement pstmtInsert2 = connection.prepareStatement(
//							"INSERT INTO ZIP_CODE SELECT * FROM CSVREAD('/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap12/zip_data_split_3.csv')");) {
//
//				int cnt1 = pstmtCreate.executeUpdate();
//				int cnt2 = pstmtInsert1.executeUpdate();
//				int cnt3 = pstmtInsert2.executeUpdate();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		
		if (searchAllFlg == true) {			
			int pageCounter = 1;

			while (true) {
				try (
						// connection.close();のためのfinallyブロック省略
						Connection connection = DriverManager
								.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb", "sa", "");
						PreparedStatement pstmtSelectAll = connection
								.prepareStatement("SELECT R AS \"#\",  \"郵便番号\", \"都道府県\", \"市町村\", \"町名\" "
										+ "FROM (SELECT ROWNUM() AS R,  \"郵便番号\", \"都道府県\", \"市町村\", \"町名\" "
										+ "FROM (SELECT LPAD(ZIP_CODE, 7, 0) AS \"郵便番号\" , PREF AS \"都道府県\", CITY AS \"市町村\", TOWN AS \"町名\" FROM ZIP_CODE ORDER BY ZIP_CODE))  "
										+ "WHERE R BETWEEN ? AND ?;");) {

					pstmtSelectAll.setInt(1, (pageCounter - 1) * 10 + 1);
					pstmtSelectAll.setInt(2, pageCounter * 10);
					
					//System.out.println(pstmtSelectAll.toString());

					System.out.println("  #, 郵便番号, 都道府県, 市町村, 町名");
					ResultSet rs = pstmtSelectAll.executeQuery();
					while (rs.next()) {
						String rowNum = rs.getString(1);
						String zipCode = rs.getString(2);
						String prefName = rs.getString(3);
						String cityName = rs.getString(4);
						String townName = rs.getString(5);
						System.out.printf("%3s, %7s, %s, %s, %s\n", rowNum, zipCode, prefName, cityName, townName);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				if(pageCounter == 1) {
					System.out.println("[次の10件を表示：N][終了：Q]");
					String input = scanner.next();
					if(input.equals("N")) {
						pageCounter = pageCounter + 1;
						continue;
					}else if(input.equals("Q")){
						break;
					}else {
						continue;
					}
				}else {
					System.out.println("[前の10件を表示：P][次の10件を表示：N][終了：Q]");
					String input = scanner.next();
					if(input.equals("P")) {
						pageCounter = pageCounter - 1;
						continue;
					}else if(input.equals("N")) {
						pageCounter = pageCounter + 1;
						continue;
					}else if(input.equals("Q")){
						break;
					}else {
						continue;
					}
				}
			}
		}
		
		if(filterByZipcodeFlg == true){
			int zipcode = 0;
			while (true) {
				try {
					System.out.println("検索する郵便番号7桁を入力してください");
					System.out.print(">");
					int input = scanner.nextInt();
					if(String.format("%07d",input).length() != 7) {
						throw new IllegalArgumentException("!！指定桁数以外の値が入力されています!！");
					}
					zipcode = input;
					break;
				} catch (InputMismatchException e) {
					System.out.println("!！半角数字以外が入力されています!！");
					scanner.next();// 入力バッファのクリア
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			
			try (
					Connection connection = DriverManager
							.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb", "sa", "");
					PreparedStatement pstmtSelectAll = connection
							.prepareStatement("SELECT R AS \"#\",  ZIPCODE AS\"郵便番号\", \"都道府県\", \"市町村\", \"町名\" "
									+ "FROM (SELECT ROWNUM() AS R,  ZIPCODE, \"都道府県\", \"市町村\", \"町名\" "
									+ "FROM (SELECT LPAD(ZIP_CODE, 7, 0) AS ZIPCODE , PREF AS \"都道府県\", CITY AS \"市町村\", TOWN AS \"町名\" FROM ZIP_CODE ORDER BY ZIP_CODE))  "
									+ "WHERE ZIPCODE = ?;");) {

				pstmtSelectAll.setInt(1, zipcode);
				
				System.out.println("  #, 郵便番号, 都道府県, 市町村, 町名");
				ResultSet rs = pstmtSelectAll.executeQuery();
				while (rs.next()) {
					String rowNum = rs.getString(1);
					String zipCode = rs.getString(2);
					String prefName = rs.getString(3);
					String cityName = rs.getString(4);
					String townName = rs.getString(5);
					System.out.printf("%3s, %7s, %s, %s, %s\n", rowNum, zipCode, prefName, cityName, townName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(filterByAddressFlg == true){
			System.out.println("検索する都道府県名を入力してください");
			System.out.print(">");
			String pref = scanner.next();
			System.out.println("検索する市町村名を入力してください");
			System.out.print(">");
			String city = scanner.next();
			
			try (
					Connection connection = DriverManager
							.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb", "sa", "");
					PreparedStatement pstmtSelectAll = connection
							.prepareStatement("SELECT R AS \"#\",  ZIPCODE AS\"郵便番号\", PREFNAME AS \"都道府県\", CITYNAME AS \"市町村\", \"町名\" "
									+ "FROM (SELECT ROWNUM() AS R,  ZIPCODE, PREFNAME, CITYNAME, \"町名\" "
									+ "FROM (SELECT LPAD(ZIP_CODE, 7, 0) AS ZIPCODE , PREF AS PREFNAME, CITY AS CITYNAME, TOWN AS \"町名\" FROM ZIP_CODE ORDER BY ZIP_CODE))  "
									+ "WHERE PREFNAME = ? AND CITYNAME = ?;");) {

				pstmtSelectAll.setString(1, pref);
				pstmtSelectAll.setString(2, city);
				
				System.out.println("  #, 郵便番号, 都道府県, 市町村, 町名");
				ResultSet rs = pstmtSelectAll.executeQuery();
				while (rs.next()) {
					String rowNum = rs.getString(1);
					String zipCode = rs.getString(2);
					String prefName = rs.getString(3);
					String cityName = rs.getString(4);
					String townName = rs.getString(5);
					System.out.printf("%3s, %7s, %s, %s, %s\n", rowNum, zipCode, prefName, cityName, townName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
