package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Sale;
import bean.User;


public class SaleDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "SELECT * FROM USERS ";
	private String dateBaseSql = "SELECT orders.order_time as date, sum(product.price * orders.count) as price from orders join product on orders.product_id = product.product_id GROUP by orders.order_time;";
	private String yearSql = "SELECT to_char(orders.order_time, 'yyyy') as year, to_char(orders.order_time, 'mm') as month, sum(product.price * orders.count) as price from orders join product on orders.product_id = product.product_id GROUP by year, month order by year, month;";


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


		private List<Sale> datePostFilter(ResultSet rSet) throws Exception {
			//リストを初期化
			List<Sale> list = new ArrayList<>();

			try{
				//リザルトセットを全件走査
				while (rSet.next()){
					Sale sale = new Sale();
					//顧客インスタンスに検索結果をセット
					sale.setYear(rSet.getString("year"));
					sale.setMonth(rSet.getString("month"));
					sale.setPrice(rSet.getInt("price"));
					//リストに追加
					list.add(sale);

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




		public List<Sale> dateFilter(Date date) throws Exception {

			//リストを初期化
			List<Sale> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			//SQL分のソート
//			String order = " order by orders.date_time asc";

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(yearSql);

//				statement.setDate(1, date);

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();

				list = datePostFilter(rSet);

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
}