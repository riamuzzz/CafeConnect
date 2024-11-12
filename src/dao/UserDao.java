package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;


public class UserDao {

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


			try{
				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID = ?");
				//各部分に値を設定
				statement.setString(1,userId );

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();


				if (rSet.next()){
					user.setUserId(rSet.getString("user_id"));
					user.setUserPass(rSet.getString("user_pass"));
					user.setUserName(rSet.getString("user_name"));
					user.setTel(rSet.getString("tel"));
					user.setCardNumber(rSet.getString("card_number"));
					user.setSubsuc(rSet.getBoolean("subscription"));

				}else {
					//リザルトセットが存在しない場合
					//ユーザインスタンスにnullをセット
					user = null;
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
			return user;



		}


		/**
		 * baseSql:String 共通SQL文 プライベート
		 */
		private String baseSql = "select * from user where ";

		/**
		 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
		 *
		 * @param rSet:リザルトセット
		 * @param school:School
		 *            学校
		 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
		 * @throws Exception
		 */
		private List<User> postFilter(ResultSet rSet) throws Exception {
			//リストを初期化
			List<User> list = new ArrayList<>();

			try{
				//リザルトセットを全件走査
				while (rSet.next()){
					User user = new User();
					//学生インスタンスに検索結果をセット
					user.setUserId(rSet.getString("user_id"));
					user.setUserPass(rSet.getString("user_pass"));
					user.setUserName(rSet.getString("user_name"));
					user.setTel(rSet.getString("tel"));
					user.setCardNumber(rSet.getString("card_number"));
					user.setSubsuc(rSet.getBoolean("subscription"));
					//リストに追加
					list.add(user);

				}
			} catch (SQLException | NullPointerException e){
				e.printStackTrace();
			}
			return list;


		}

		/**
		 * filterメソッド 名前、電話番号を指定して学生の一覧を取得する
		 *
		 * @param name:String
		 *            ユーザ名
		 * @param tel:String
		 *           電話番号
		 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
		 * @throws Exception
		 */
		public List<User> filter(String userName, String tel) throws Exception {

		    System.out.println("☆名前と電話番号選択の時☆");

		    // リストを初期化
		    List<User> list = new ArrayList<>();

		    // データベースへのコネクションを確立
		    Connection connection = getConnection();

		    // プリペアードステートメント
		    PreparedStatement statement = null;

		    // SQL条件文の初期化
		    String condition = "";
		    int paramIndex = 1;

		    // SQL文のソート
		    String order = " order by user_id asc";

		    // userName のみ設定されている場合の条件
		    if (userName != null && !userName.isEmpty() && (tel == null || tel.isEmpty())) {
		        condition = " and user_name=? ";
		    }
		    // tel のみ設定されている場合の条件
		    else if ((userName == null || userName.isEmpty()) && tel != null && !tel.isEmpty()) {
		        condition = " and tel=? ";
		    }
		    // 両方設定されている場合の条件
		    else if (userName != null && !userName.isEmpty() && tel != null && !tel.isEmpty()) {
		        condition = " and user_name=? and tel=? ";
		    }

		    try {
		        // プリペアードステートメントにSQL文をセット
		        statement = connection.prepareStatement(baseSql + condition + order);

		        // 値を設定（それぞれの条件に合わせて）
		        if (!condition.isEmpty()) {
		            if (condition.contains("user_name=?")) {
		                statement.setString(paramIndex++, userName);
		            }
		            if (condition.contains("tel=?")) {
		                statement.setString(paramIndex, tel);
		            }
		        }

		        // 上記のSQL文を実行し結果を取得する
		        ResultSet rSet = statement.executeQuery();

		        // 結果をリストに追加
		        while (rSet.next()) {
		            User user = new User();
		            user.setUserId(rSet.getInt("user_id"));
		            user.setUserName(rSet.getString("user_name"));
		            user.setTel(rSet.getString("tel"));
		            list.add(user);
		        }

		    } catch (Exception e) {
		        throw e;
		    } finally {
		        // プリペアードステートメントを閉じる
		        if (statement != null) {
		            try {
		                statement.close();
		            } catch (SQLException sqle) {
		                throw sqle;
		            }
		        }
		        // コネクションを閉じる
		        if (connection != null) {
		            try {
		                connection.close();
		            } catch (SQLException sqle) {
		                throw sqle;
		            }
		        }
		    }
		    return list;
		}


		/**
		 * filterメソッド 学校、入学年度、在学フラグを指定して学生の一覧を取得する
		 *
		 * @param school:School
		 *            学校
		 * @param entYear:int
		 *            入学年度
		 * @param isAttend:boolean
		 *            在学フラグ
		 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
		 * @throws Exception
		 */
		public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

			System.out.println("☆入学年度選択の時☆");

			//リストを初期化
			List<Student> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			//リザルトセット
			ResultSet rSet = null;

			//SQLの条件文
			String condition = " and ENT_YEAR=?";
			//SQL分のソート
			String order = " order by student_no asc";

			//SQL文の在学フラグ条件
			String conditionIsAttend ="";
			//在学フラグがtrueの時（在学中）
			if (isAttend){
				conditionIsAttend = " and IS_ATTEND= true";
			}

			try{


				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order );
				//各部分に値を設定
				statement.setString(1, school.getCd());
				statement.setInt(2, entYear);

				//上記のSQL文を実行し結果を取得する
				rSet = statement.executeQuery();

				list = postFilter(rSet, school);

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
		 * filterメソッド 学校、在学フラグを指定して学生の一覧を取得する
		 *
		 * @param school:School
		 *            学校
		 * @param isAttend:boolean
		 *            在学フラグ
		 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
		 * @throws Exception
		 */
		public List<Student> filter(School school, boolean isAttend) throws Exception {

			System.out.println("☆選択なしの時☆");

			//リストを初期化
			List<Student> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			//リザルトセット
			ResultSet rSet = null;

			//SQL分のソート
			String order = " order by student_no asc";

			//SQL文の在学フラグ条件
			String conditionIsAttend ="";
			//在学フラグがtrueの時（在学中）
			if (isAttend){
				conditionIsAttend = " and IS_ATTEND = true";
			}

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(baseSql + conditionIsAttend + order );
				//各部分に値を設定
				statement.setString(1, school.getCd());

				//上記のSQL文を実行し結果を取得する
				rSet = statement.executeQuery();

				list = postFilter(rSet, school);

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
		 * saveメソッド ユーザインスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
		 *
		 * @param user：User
		 *            ユーザ
		 * @return 成功:true, 失敗:false
		 * @throws Exception
		 */
		public boolean save(User User) throws Exception {

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			//実行件数
			int count = 0;


			try{
				//データベースからユーザを取得
				User user = get(user.getUserId());

				if (user == null) {
					//ユーザが存在しなかった場合
					//プリペアードステートメントにInsert文をセット
					statement = connection.prepareStatement(
							"INSERT INTO STUDENT (USER_ID ,USER_PASS, USER_NAME ,EMAIL , ADDRESS ,TEL ,CARD_NUMBER ,SUBSCRIPTION) VALUES (?,?,?,?,?,?,?,?)");
					//各部分に値を設定
					statement.setString(1, user.getUserID());
					statement.setString(2, user.getUserPass());
					statement.setString(3, user.getUserName());
					statement.setString(4, user.getEmail());
					statement.setStirng(5, user.getAddress());
					statement.setString(6, user.getTel());
					statement.setString(7, user.getCardNumber());
					statement.setBoolean(8, user.getSubscription());

				}else {
					//学生が存在した場合
					//プリペアードステートメントにUpdate文をセット
					statement = connection.prepareStatement(
							"UPDATE STUDENT SET USER_PASS=? ,USER_NAME=? ,EMAIL=? ,ADDRESS=? ,CARD_NUMBER=? ,SUBSCRIPTION=? WHERE USER_ID=?");
					//各部分に値を設定

					statement.setString(1, user.getUserPass());
					statement.setString(2, user.getUserName());
					statement.setString(3, user.getEmail));
					statement.setString(4, user.getAddress());
					statement.setString(5, user.getTel());
					statement.setString(6, user.getCardNumber());
					statement.setBoolean(7, user.getSubscription());
					statement.setString(8, user.getUserId());

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
	}

}
