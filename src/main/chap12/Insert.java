package main.chap12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

	public static void main(String[] args) {

		try (
				// connection.close();のためのfinallyブロック省略
				Connection connection = DriverManager.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb",
						"sa", "");
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO EMP VALUES(?, ?)");) {
			for (int i = 0; i < 10; i++) {
				int id = i + 1;
				// パラメータ番号1、２それぞれにintとstringを格納する。
				pstmt.setInt(1, id);
				pstmt.setString(2, "ユーザ" + id);
				// executeUpdateで更新を行い、更新レコード数を返す
				int cnt = pstmt.executeUpdate();
				System.out.printf("データを%d件INSERTしました。\n", cnt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
