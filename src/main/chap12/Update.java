package main.chap12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {

	public static void main(String[] args) {
		try (
				// connection.close();のためのfinallyブロック省略
				Connection connection = DriverManager.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb",
						"sa", "");
				PreparedStatement pstmt = connection.prepareStatement("UPDATE EMP SET NAME = ? WHERE ID = ?");) {

			for (int i = 0; i < 10; i++) {

				int id = i + 1;
				if (id % 2 == 0) {
					pstmt.setString(1, "ユーザ" + id + "を更新");
					pstmt.setInt(2, id);
					// executeUpdateで更新を行い、更新レコード数を返す
					int cnt = pstmt.executeUpdate();
					System.out.printf("データを%d件UPDATEしました。\n", cnt);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
