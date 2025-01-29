package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Cart;
import bean.Order;
import bean.Product;
import bean.User;

public class OrderDao extends Dao {

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = " select * from orders INNER JOIN USERs  on orders.user_id=users.user_id INNER JOIN Product on orders.product_id = product.product_id";


	/**
	 * getメソッド 注文IDを元に、注文インスタンスを1件取得する
	 *
	 * @param OrderId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public Order get(String orderId) throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

	    String condition = " where order_id=?";

		//結果を格納するTeacherを初期化
		Order order = new Order();
		ProductDao pDao = new ProductDao();
		UserDao uDao = new UserDao();

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);
			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,orderId);
			ResultSet rSet = statement.executeQuery();

			//取得した情報をproductインスタンスに保存
			if(rSet.next()) {
				order.setOrderId(rSet.getString("order_id"));
				order.setProduct(pDao.get(rSet.getInt("product_id")));
				order.setUser(uDao.get(rSet.getInt("user_id")));
				order.setOrderTime(rSet.getTimestamp("order_time"));
				order.setCount(rSet.getInt("count"));
				order.setReceive(rSet.getBoolean("receive"));
				order.setSubscription(rSet.getBoolean("subscription"));
				order.setMobile(rSet.getBoolean("mobile"));


			} else {
				//対応する教員がいない場合はnullを返す
				order = null;
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
	return order;
	}

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 */
	private List<Order> postFilter(ResultSet rSet) throws Exception {
		//リストを初期化
		List<Order> list = new ArrayList<>();
		UserDao uDao = new UserDao();
		ProductDao pDao = new ProductDao();
		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Order order = new Order();
				//学生インスタンスに検索結果をセット
				order.setOrderId(rSet.getString("order_id"));
				order.setProduct(pDao.get(rSet.getInt("product_id")));
				order.setUser(uDao.get(rSet.getInt("user_id")));
				order.setOrderTime(rSet.getTimestamp("order_time"));
				order.setCount(rSet.getInt("count"));
				order.setReceive(rSet.getBoolean("receive"));
				order.setSubscription(rSet.getBoolean("subscription"));
				order.setMobile(rSet.getBoolean("mobile"));
				//リストに追加
				list.add(order);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * filterメソッド 日付、氏名、商品名を指定して学生の一覧を取得する
	 */
	public List<Order> filter(String productName, String userName,String orderTime) throws Exception {
		//リストを初期化
		List<Order> list = new ArrayList<>();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
	    // SQL条件文の初期化
	    String condition = "";
	    int paramIndex = 1;
		//SQL分のソート
		String order = " order by order_time asc";
		// user のみ設定されている場合の条件
		if (productName == null & userName != null & orderTime == null) {
		    condition = " where user_name like ? and receive = False";
		}
		// product のみ設定されている場合の条件
		else if (productName != null & userName == null & orderTime == null) {
		    condition = " where product_name like ? and receive = False";
		}
		// orderTime のみ設定されている場合の条件
		else if (productName == null & userName == null & orderTime != null) {
		    condition = " where order_time::text like ? and receive = False";
		}
		// user と product が設定されている場合の条件
		else if (productName != null & userName != null & orderTime == null) {
		    condition = " where user_name like ? and product_name like ? and receive = False";
		}
		// user と orderTime が設定されている場合の条件
		else if (productName == null & userName != null & orderTime != null) {
		    condition = " where user_name like ? and order_time::text like ? and receive = False";
		}
		// product と orderTime が設定されている場合の条件
		else if (productName != null & userName == null & orderTime != null) {
		    condition = " where product_name like ? and order_time::text like ? and receive = False";
		}
		// すべてが設定されている場合の条件
		else if (productName != null & userName != null & orderTime != null) {
		    condition = " where user_name like ? and product_name like ? and order_time::text like ? and receive = False";
		}
		// それ以外の場合（条件なし）
		else {
		    condition = " where receive = False";
		}
		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order );

	        // 値を設定（それぞれの条件に合わせて）
	        if (!condition.isEmpty()) {
	            if (condition.contains("user_name like ?")) {
	                statement.setString(paramIndex++, "%" + userName + "%");
	            }
	            if (condition.contains("product_name like ?")) {
	                statement.setString(paramIndex++, "%" + productName + "%");
	            }
	            if (condition.contains("order_time::text like ?")) {
	                statement.setString(paramIndex++, "%" + orderTime + "%");
	            }
	        }
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

	public List<Order> mobileFilter(String productName, String userName,String orderTime) throws Exception {
		//リストを初期化
		List<Order> list = new ArrayList<>();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
	    // SQL条件文の初期化
	    String condition = "";
	    int paramIndex = 1;
		//SQL分のソート
		String order = " order by order_time asc";
		// user のみ設定されている場合の条件
		if (productName == null & userName != null & orderTime == null) {
		    condition = " where user_name like ? and receive = False and mobile = true";
		}
		// product のみ設定されている場合の条件
		else if (productName != null & userName == null & orderTime == null) {
		    condition = " where product_name like ? and receive = False and mobile = true";
		}
		// orderTime のみ設定されている場合の条件
		else if (productName == null & userName == null & orderTime != null) {
		    condition = " where order_time::text like ? and receive = False and mobile = true";
		}
		// user と product が設定されている場合の条件
		else if (productName != null & userName != null & orderTime == null) {
		    condition = " where user_name like ? and product_name like ? and receive = False and mobile = true";
		}
		// user と orderTime が設定されている場合の条件
		else if (productName == null & userName != null & orderTime != null) {
		    condition = " where user_name like ? and order_time::text like ? and receive = False and mobile = true";
		}
		// product と orderTime が設定されている場合の条件
		else if (productName != null & userName == null & orderTime != null) {
		    condition = " where product_name like ? and order_time::text like ? and receive = False and mobile = true";
		}
		// すべてが設定されている場合の条件
		else if (productName != null & userName != null & orderTime != null) {
		    condition = " where user_name like ? and product_name like ? and order_time::text like ? and receive = False and mobile = true";
		}
		// それ以外の場合（条件なし）
		else {
		    condition = " where receive = False and mobile = true";
		}
		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order );

	        // 値を設定（それぞれの条件に合わせて）
	        if (!condition.isEmpty()) {
	            if (condition.contains("user_name like ?")) {
	                statement.setString(paramIndex++, "%" + userName + "%");
	            }
	            if (condition.contains("product_name like ?")) {
	                statement.setString(paramIndex++, "%" + productName + "%");
	            }
	            if (condition.contains("order_time::text like ?")) {
	                statement.setString(paramIndex++, "%" + orderTime + "%");
	            }
	        }
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
	 * saveメソッド 注文インスタンスをデータベースに保存する
	 *	注文情報受け取り済みに変更
	 * @param order：Order
	 *            注文
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Order order) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		try{

			//受け取り情報変更
			//プリペアードステートメントにUpdate文をセット
			statement = connection.prepareStatement(
					"UPDATE ORDERS SET RECEIVE=? WHERE ORDER_ID=?");
			//各部分に値を設定
			statement.setBoolean(1, order.isReceive());
			statement.setString(2, order.getOrderId());

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

	/**
	 * createメソッド インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param Order：order
	 *            注文
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean create(Cart cart) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		// 現在の日時を取得
		LocalDateTime now = LocalDateTime.now();

		// SQL用の日付に変換
		java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(now);

		// フォーマットを適用して文字列に変換（注文ID用）
		String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO ORDERS (ORDER_ID ,PRODUCT_ID ,USER_ID ,ORDER_TIME ,COUNT ,RECEIVE ,SUBSCRIPTION ,MOBILE) VALUES (?,?,?,?,?,?,?,?)");
				//各部分に値を設定
				statement.setString(1, formattedDateTime+cart.getUser().getUserId()+cart.getProduct().getProductId());
				statement.setInt(2,cart.getProduct().getProductId());
				statement.setInt(3, cart.getUser().getUserId());
				statement.setTimestamp(4, sqlTimestamp);
				statement.setInt(5, cart.getCount());
				statement.setBoolean(6, false);
				statement.setBoolean(7, false);
				statement.setBoolean(8, false);
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

	public boolean create(User user, Product product, int num) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		// 現在の日時を取得
		LocalDateTime now = LocalDateTime.now();

		// SQL用の日付に変換
		java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(now);

		// フォーマットを適用して文字列に変換（注文ID用）
		String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO ORDERS (ORDER_ID ,PRODUCT_ID ,USER_ID ,ORDER_TIME ,COUNT ,RECEIVE ,SUBSCRIPTION ,MOBILE) VALUES (?,?,?,?,?,?,?,?)");
				//各部分に値を設定
				statement.setString(1, formattedDateTime+user.getUserId());
				statement.setInt(2,product.getProductId());
				statement.setInt(3, user.getUserId());
				statement.setTimestamp(4, sqlTimestamp);
				statement.setInt(5, num);
				statement.setBoolean(6, false);
				statement.setBoolean(7, true);
				statement.setBoolean(8, false);
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

	public boolean mobileCreate(Product product, User user, int num) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		// 現在の日時を取得
		LocalDateTime now = LocalDateTime.now();

		// SQL用の日付に変換
		java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(now);

		// フォーマットを適用して文字列に変換（注文ID用）
		String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO ORDERS (ORDER_ID ,PRODUCT_ID ,USER_ID ,ORDER_TIME ,COUNT ,RECEIVE ,SUBSCRIPTION ,MOBILE) VALUES (?,?,?,?,?,?,?,?)");
				//各部分に値を設定
				statement.setString(1, formattedDateTime+user.getUserId()+product.getProductId());
				statement.setInt(2,product.getProductId());
				statement.setInt(3, user.getUserId());
				statement.setTimestamp(4, sqlTimestamp);
				statement.setInt(5, num);
				statement.setBoolean(6, false);
				statement.setBoolean(7, false);
				statement.setBoolean(8, true);
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

	/**
	 * filterメソッド ユーザIDをもとに注文一覧をリストで返す
	 */
	public List<Order> filter(int userId) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//返り値用のリスト
		List<Order> list = new ArrayList<>();

		UserDao uDao = new UserDao();
		ProductDao pDao = new ProductDao();
		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"SELECT * FROM ORDERS WHERE USER_ID=?");
				//各部分に値を設定
				statement.setInt(1, userId);

				//プリペアードステートメントを実行
				ResultSet rSet = statement.executeQuery();

				while (rSet.next()){
					Order order = new Order();
					//注文インスタンスに検索結果をセット
					order.setOrderId(rSet.getString("order_id"));
					order.setProduct(pDao.get(rSet.getInt("product_id")));
					order.setUser(uDao.get(rSet.getInt("user_id")));
					order.setOrderTime(rSet.getTimestamp("order_time"));
					order.setCount(rSet.getInt("count"));
					order.setReceive(rSet.getBoolean("receive"));
					order.setSubscription(rSet.getBoolean("subscription"));
					order.setMobile(rSet.getBoolean("mobile"));
					//リストに追加
					list.add(order);
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
		return list;
	}

}
