package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;


public class SaleDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "SELECT * FROM USERS ";


		/**
		 * getメソッド 学生番号を指定して学生インスタンスを1件取得する
		 *
		 * @param no:String
		 *            学生番号
		 * @return 学生クラスのインスタンス 存在しない場合はnull
		 * @throws Exception
		 */
		public User get(String userId) throws Exception {

			//ユーザインスタンスを初期化
			User users =new User();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			// カードDaoを初期化
			CardDao cardDao = new CardDao();


			try{
				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement("where USER_ID = ?");
				//各部分に値を設定
				statement.setString(1,userId );

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();


				if (rSet.next()){
					users.setUserId(rSet.getString("user_id"));
					users.setUserPassword(rSet.getString("user_pass"));
					users.setUserName(rSet.getString("user_name"));
					users.setTel(rSet.getString("tel"));
					users.setCard(cardDao.get(rSet.getString("card_number")));
					users.setSubscription(rSet.getBoolean("subscription"));

				}else {
					//リザルトセットが存在しない場合
					//ユーザインスタンスにnullをセット
					users = null;
				}

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
			return users;
		}

		/**
		 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
		 *
		 * @param rSet:リザルトセット
		 * @param User:User
		 *            	ユーザ
		 * @return 注文のリスト:List<user> 存在しない場合は0件のリスト
		 * @throws Exception
		 */
		private List<User> postFilter(ResultSet rSet) throws Exception {
			//リストを初期化
			List<User> list = new ArrayList<>();

			try{
				//リザルトセットを全件走査
				while (rSet.next()){
					User user = new User();
					//顧客インスタンスに検索結果をセット
					user.setUserId(rSet.getString("user_id"));
					user.setUserPassword(rSet.getString("user_pass"));
					user.setUserName(rSet.getString("user_name"));
					user.setEmail(rSet.getString("email"));
					user.setAddress(rSet.getString("address"));
					user.setTel(rSet.getString("tel"));
					user.setSubscription(rSet.getBoolean("subscription"));
					//リストに追加
					list.add(user);

				}
			} catch (SQLException | NullPointerException e){
				e.printStackTrace();
			}
			return list;


		}

		/**
		 * filterメソッド 名前と電話番号を指定して顧客の一覧を取得する
		 */
		public List<User> filter() throws Exception {

			//リストを初期化
			List<User> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

		    // SQL条件文の初期化
		    String condition = "";

			//SQL分のソート
			String order = " order by user_id asc";

		    condition = "where subscription = true";

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(baseSql + condition + order );

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();

				list = postFilter(rSet);

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
			return list;

		}

		/**
		 * saveメソッド 顧客インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
		 *
		 * @param user：User
		 *            顧客
		 * @return 成功:true, 失敗:false
		 * @throws Exception
		 */
		public boolean save(User user) throws Exception {

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			//実行件数
			int count = 0;


			try{
				//データベースから学生を取得
				User old = get(user.getUserId());

				if (old == null) {
					//学生が存在しなかった場合
					//プリペアードステートメントにInsert文をセット
					statement = connection.prepareStatement(
							"INSERT INTO USERS (USER_ID ,USER_PASS ,USER_NAME ,EMAIL ,ADDRESS ,TEL ,CARD_NUMBER ,SUBSCRIPTION ) VALUES (?,?,?,?,?,?,?,?)");
					//各部分に値を設定
					statement.setString(1, user.getUserId());
					statement.setString(2, user.getUserPassword());
					statement.setString(3, user.getUserName());
					statement.setString(4, user.getEmail());
					statement.setString(5, user.getAddress());
					statement.setString(6, user.getTel());
					statement.setString(7, user.getCard().getCardNumber());
					statement.setBoolean(8, user.isSubscription());
				}else {
					//学生が存在した場合
					//プリペアードステートメントにUpdate文をセット
					statement = connection.prepareStatement(
							"UPDATE USERS SET USER_ID=? ,USER_PASS=? ,USER_NAME=? ,EMAIL=? ,ADDRESS=? ,TEL=? ,CARD_NUMBER=? ,SUBSCRIPTION=? ");
					//各部分に値を設定
					statement.setString(1, user.getUserId());
					statement.setString(2, user.getUserPassword());
					statement.setString(3, user.getUserName());
					statement.setString(4, user.getEmail());
					statement.setString(5, user.getAddress());
					statement.setString(6, user.getTel());
					statement.setString(7, user.getCard().getCardNumber());
					statement.setBoolean(8, user.isSubscription());

				}

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


		public User login(String email, String password) throws Exception {

			//ユーザインスタンスを初期化
			User users =new User();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			// カードDaoを初期化
			CardDao cardDao = new CardDao();


			try{
				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement("SELECT * FROM USERS WHERE email = ? and user_pass = ?");
				//各部分に値を設定
				statement.setString(1,email );
				statement.setString(2,password );

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();


				if (rSet.next()){
					users.setUserId(rSet.getString("user_id"));
					users.setUserPassword(rSet.getString("user_pass"));
					users.setUserName(rSet.getString("user_name"));
					users.setTel(rSet.getString("tel"));
					users.setCard(cardDao.get(rSet.getString("card_number")));
					users.setSubscription(rSet.getBoolean("subscription"));

				}else {
					//リザルトセットが存在しない場合
					//ユーザインスタンスにnullをセット
					users = null;
				}

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
			return users;
		}

}