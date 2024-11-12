package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

public class Dao {
	/**
	 * データソース:DataSource:クラスフィールド
	 */
	static DataSource ds;
	/**
	 * getConnectionメソッド データベースへのコネクションを返す
	 *
	 * @return データベースへのコネクション:Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		// データソースがnullの場合
		if (ds == null) {
			// データベースへ接続
			ds = (DataSource) DriverManager.getConnection("jdbc:postgresql://localhost:5432/cafeconnect",
					"postgres",
					"pass");
		}
		// データベースへのコネクションを返却
		return ds.getConnection();
	}
}
