package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Cart;
import bean.OnlineOrder;
import bean.Product;
import bean.User;

public class MobileOrderDao extends Dao {

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from ORDER ";
	private String baseSql2 = " select * from orders INNER JOIN USERs  on orders.user_id=users.user_id INNER JOIN Product on orders.product_id = product.product_id";


	/**
	 * getメソッド 注文IDを元に、注文インスタンスを1件取得する
	 *
	 * @param OrderId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public OnlineOrder get(String orderId) throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

	    String condition = " where order_id=?";

		//結果を格納するTeacherを初期化
		OnlineOrder order = new OnlineOrder();

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql2 + condition);
			System.out.println(statement);
			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,orderId);
			ResultSet rSet = statement.executeQuery();

			//ProductDao,UserDaoを初期化
			ProductDao productDao =new ProductDao();
			UserDao userDao =new UserDao();

			//取得した情報をproductインスタンスに保存
			if(rSet.next()) {
				order.setOrderId(rSet.getString("order_id"));
				order.setProductName(rSet.getString("product_name"));
				order.setUserName(rSet.getString("user_name"));
				order.setAddress(rSet.getString("address"));
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
	private List<OnlineOrder> postFilter(ResultSet rSet, Product product, User user) throws Exception {
		//リストを初期化
		List<OnlineOrder> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				OnlineOrder order = new OnlineOrder();
				//学生インスタンスに検索結果をセット
				order.setOrderId(rSet.getString("order_id"));
				order.setProductName(rSet.getString("product_name"));
				order.setUserName(rSet.getString("user_name"));
				order.setAddress(rSet.getString("address"));
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
	public List<OnlineOrder> filter(Product product, User user,Date orderTime) throws Exception {

		System.out.println(product);
		System.out.println(user);
		System.out.println(orderTime);


		//リストを初期化
		List<OnlineOrder> list = new ArrayList<>();

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
		if (product == null && user != null && orderTime == null) {
		    condition = " where user_name=? and receive = False and mobile = true";
		    System.out.println("1");
		}
		// product のみ設定されている場合の条件
		else if (product != null && user == null && orderTime == null) {
		    condition = " where product_name=? and receive = False and mobile = true";
		    System.out.println("2");
		}
		// orderTime のみ設定されている場合の条件
		else if (product == null && user == null && orderTime != null) {
		    condition = " where order_time=? and receive = False and mobile = true";
		    System.out.println("3");
		}
		// user と product が設定されている場合の条件
		else if (product != null && user != null && orderTime == null) {
		    condition = " where user_name=? and product_name=? and receive = False and mobile = true";
		    System.out.println("4");
		}
		// user と orderTime が設定されている場合の条件
		else if (product == null && user != null && orderTime != null) {
		    condition = " where user_name=? and order_time=? and receive = False and mobile = true";
		    System.out.println("5");
		}
		// product と orderTime が設定されている場合の条件
		else if (product != null && user == null && orderTime != null) {
		    condition = " where product_name=? and order_time=? and receive = False and mobile = true";
		    System.out.println("6");
		}
		// すべてが設定されている場合の条件
		else if (product != null && user != null && orderTime != null) {
		    condition = " where user_name=? and product_name=? and order_time=? and receive = False and mobile = true";
		    System.out.println("7");
		}
		// それ以外の場合（条件なし）
		else {
		    condition = " where receive = False and mobile = true";
		    System.out.println("条件なし");
		}

		try{

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql2 + condition + order );

	        // 値を設定（それぞれの条件に合わせて）
	        if (!condition.isEmpty()) {
	            if (condition.contains("user_name=?")) {
	                statement.setString(paramIndex++, user.getUserName());
	            }
	            if (condition.contains("product_name=?")) {
	                statement.setString(paramIndex++, product.getProductName());
	            }
	            if (condition.contains("order_time=?")) {
	                statement.setDate(paramIndex++, orderTime);
	            }


	        }
	        System.out.println(statement);

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet,product,user);
			System.out.println(list);
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
	public boolean save(OnlineOrder order) throws Exception {

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

        // フォーマットを定義
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // フォーマットを適用して文字列に変換
        String formattedDateTime = now.format(formatter);


		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO ORDER (ORDER_ID ,PRODUCT_ID ,USER_ID ,ORDER_TIME ,COUNT ,RECEIVE ,SUBSCRIPTION ) VALUES (?,?,?,?,?,?,?)");
				//各部分に値を設定
				statement.setString(1, cart.getProduct()+String.valueOf(cart.getUser().getUserId()));
				statement.setInt(2, cart.getProduct().getProductId());
				statement.setInt(3, cart.getUser().getUserId());
				statement.setString(4, formattedDateTime);
				statement.setInt(5, cart.getCount());
				statement.setBoolean(6, false);
				statement.setBoolean(7, cart.getUser().isSubscription());


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
