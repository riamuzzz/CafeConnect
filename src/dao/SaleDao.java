package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductSale;
import bean.Sale;
import bean.User;


public class SaleDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String sql = null;
	private String allYearSql = "select distinct to_char(orders.order_time, 'yyyy') as year from orders order by year desc;";
	private String baseSql = "SELECT * FROM USERS ";
//	private String dateBaseSql = "SELECT orders.order_time as date, sum(product.price * orders.count) as price from orders join product on orders.product_id = product.product_id GROUP by orders.order_time;";
	private String yearSql = "SELECT to_char(orders.order_time, 'yyyy') as year, to_char(orders.order_time, 'mm') as month, sum(product.price * orders.count) as price from orders join product on orders.product_id = product.product_id GROUP by month, year order by month, year";
	private String productSql = "select product.product_name as product_name, '累計' as year, '累計' as month, sum(product.price * orders.count) as data from orders join product on orders.product_id = product.product_id group by product_name order by data desc;";
//	private String productSql = "select product.product_name as product_name, to_char(orders.order_time, 'yyyy') as year, to_char(orders.order_time, 'mm') as month, sum(product.price * orders.count) as data from orders join product on orders.product_id = product.product_id where product.category_id = 'CATE01' group by product_name, year, month having to_char(orders.order_time, 'yyyy') = '2024' order by data desc;";
	private String productYearSql ="select product.product_name as product_name, to_char(orders.order_time, 'yyyy') as year, '累計' as month, sum(product.price * orders.count) as data from orders join product on orders.product_id = product.product_id group by product_name, year having to_char(orders.order_time, 'yyyy') = ? order by data desc;";
	private String productMonthSql ="select product.product_name as product_name, to_char(orders.order_time, 'yyyy') as year, to_char(orders.order_time, 'mm') as month, sum(product.price * orders.count) as data from orders join product on orders.product_id = product.product_id group by product_name, year, month having to_char(orders.order_time, 'yyyy') = ? and to_char(orders.order_time, 'mm') = ? order by data desc;";

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
					users.setUserId(rSet.getInt("user_id"));
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
					user.setUserId(rSet.getInt("user_id"));
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

		private List<String> yearPostFilter(ResultSet rSet) throws Exception {
			//リストを初期化
			List<String> list = new ArrayList<>();

			try{
				//リザルトセットを全件走査
				while (rSet.next()){
					String year = rSet.getString("year");
					list.add(year);

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
					//時系列別売上インスタンスに検索結果をセット
					sale.setYear(rSet.getString("year"));
					sale.setMonth(rSet.getString("month"));
					sale.setData(rSet.getInt("price"));
					//リストに追加
					list.add(sale);

				}
			} catch (SQLException | NullPointerException e){
				e.printStackTrace();
			}
			return list;


		}


		private List<ProductSale> productPostFilter(ResultSet rSet) throws Exception {
			//リストを初期化
			List<ProductSale> list = new ArrayList<>();

			try{
				//リザルトセットを全件走査
				while (rSet.next()){
					ProductSale productSale = new ProductSale();
					//商品別売上インスタンスに検索結果をセット
					productSale.setProductName(rSet.getString("product_name"));
					productSale.setYear(rSet.getString("year"));
					productSale.setMonth(rSet.getString("month"));
					productSale.setData(rSet.getInt("data"));
					//リストに追加
					list.add(productSale);

				}
			} catch (SQLException | NullPointerException e){
				e.printStackTrace();
			}
			return list;


		}

		/**
		 * filterメソッド サブスク会員を取得
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

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(yearSql);

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


		public List<ProductSale> productFilter(String year, String month) throws Exception {

			//リストを初期化
			List<ProductSale> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			String condition = "";

			System.out.println("year:" + year);
			System.out.println("month:" + month);

//			if (year != null && month != null){
//				sql = productMonthSql;
//				condition = "month";
//			} else if (year != null && month == null){
//				sql = productYearSql;
//				condition = "year";
//			} else {
//				sql = productSql;
//			}

			if (year == null && month == null || year.equals("null") && month.equals("null")) {
				sql = productSql;
			} else if (year != null && month == null || !year.equals("null") && month.equals("null")) {
				sql = productYearSql;
				condition = "year";
			} else {
				sql = productMonthSql;
				condition = "month";
			}

			System.out.println("sql:" + sql);

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(sql);

				if (!condition.isEmpty()) {
					if (condition == "year"){
						statement.setString(1, year);
					} else if (condition == "month"){
						statement.setString(1, year);
						statement.setString(2, month);
					}
		        }

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();

				list = productPostFilter(rSet);

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


		public List<String> yearFilter() throws Exception {

			//リストを初期化
			List<String> list = new ArrayList<>();

			//データベースへのコネクションを確立
			Connection connection = getConnection();

			//プリペアードステートメント
			PreparedStatement statement = null;

			try{

				//プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement(allYearSql);

				//上記のSQL文を実行し結果を取得する
				ResultSet rSet = statement.executeQuery();

				list = yearPostFilter(rSet);

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