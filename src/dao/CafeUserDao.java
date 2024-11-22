package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.CafeUser;

public class CafeUserDao extends Dao {
	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from cafeusers where cafe_userid=?";


	/**
	 * getメソッド カフェ店員IDを元に、カフェ店員インスタンスを1件取得する
	 *
	 * @param cafeUserId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public CafeUser get(String cafeUserId) throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//結果を格納するTeacherを初期化
		CafeUser cafeUser = new CafeUser();

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,cafeUserId);
			ResultSet rSet = statement.executeQuery();

			//取得したカフェ店員ID、パスワード、教員名、学校コードをteacherインスタンスに保存
			//セットするものは[password]じゃなくて「pasword]だよ
			if(rSet.next()) {
				cafeUser.setCafeUserId(rSet.getString("cafe_userid"));
				cafeUser.setCafeUserPassword("cafe_userpass");
				cafeUser.setCafeUserName(rSet.getString("cafe_username"));
			} else {
				//対応する教員がいない場合はnullを返す
				cafeUser = null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

		//コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	}
	//検索した教員インスタンスを返す
	return cafeUser;
	}

	/**
	 * loginメソッド 店員ID、パスワードを元に、店員インスタンスを1件取得する
	 *
	 * @param id:String 店員ID
	 * @param password:String パスワード
	 * @return 店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public CafeUser login(String id,String password)throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		//passwordだよ
		String condition = " and cafe_userpass=?";

		//結果を格納するTeacherを初期化
		CafeUser cafeUser = new CafeUser();

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,id);
			statement.setString(2, password);
			ResultSet rSet = statement.executeQuery();

			//受け取ったものをセットする
			//取得した店員ID、パスワードコードをCafeUserインスタンスに保存
			if(rSet.next()) {
				cafeUser.setCafeUserId(rSet.getString("cafe_userid"));
				cafeUser.setCafeUserPassword(rSet.getString("cafe_userpass"));
				cafeUser.setCafeUserName(rSet.getString("cafe_username"));
			} else {
				//対応する教員がいない場合はnullを返す
				cafeUser = null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

		//コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	}
	//検索した教員インスタンスを返す
	return cafeUser;

	}

	public boolean save(CafeUser cafeUser) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;


		try{
				//学生が存在した場合
				//プリペアードステートメントにUpdate文をセット
				statement = connection.prepareStatement(
						"UPDATE cafeusers SET cafe_userid=? ,cafe_userpass=? ,cafe_username=?");
				//各部分に値を設定
				statement.setString(1, cafeUser.getCafeUserId());
				statement.setString(2, cafeUser.getCafeUserPassword());
				statement.setString(3, cafeUser.getCafeUserName());
				System.out.println(statement);
			//プリペアードステートメントを実行
			count = statement.executeUpdate();

		}catch (Exception e){
			throw e;
		}finally {
			//プリペアステートメントを閉じる
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		if (count > 0) {
			//実行数が1件以上あるとき
			return true;
		}else {
			//実行数が0件以上の場合
			return false;
		}
	}

}
