package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	private static final String URL = "jdbc:postgresql://localhost:5432/cafeconnect"; // データベースURL
    private static final String USER = "postgres"; // ユーザー名
    private static final String PASSWORD = "pass"; // パスワード

    // データベース接続を作成
    public Connection getConnection() throws SQLException {
        try {
            // PostgreSQL JDBCドライバをロード
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL JDBC Driver not found.");
        }

        // データベースに接続
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

