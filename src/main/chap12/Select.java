package main.chap12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
	public static void main(String[] args) {
		try (
				// connection.close();のためのfinallyブロック省略
				Connection connection = DriverManager.getConnection("jdbc:h2:/Applications/h2/cc-java-applied-mydb",
						"sa", "");
				PreparedStatement pstmt = connection.prepareStatement("SELECT ID, NAME FROM EMP WHERE ID > ?");) {

			pstmt.setInt(1, 5);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.printf("ID: %5d, NAME: %s\n", id, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
