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
	private String baseSql = "select * from ORDER where ";


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
		//結果を格納するTeacherを初期化
		Order order = new Order();

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,orderId);
			ResultSet rSet = statement.executeQuery();

			//ProductDao,UserDaoを初期化
			ProductDao productDao =new ProductDao();
			UserDao userDao =new UserDao();

			//取得した情報をproductインスタンスに保存
			if(rSet.next()) {
				order.setOrderId(rSet.getString("order_id"));
				order.setProduct(productDao.get(rSet.getString("product_name")));
				order.setUser(userDao.get(rSet.getString("user_id")));
				order.setOrderTime(rSet.getDate("order_time"));
				order.setCount(rSet.getInt("count"));
				order.setReceive(rSet.getBoolean("receive"));
				order.setSubscription(rSet.getBoolean("subscription"));

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
	private List<Order> postFilter(ResultSet rSet, Product product, User user) throws Exception {
		//リストを初期化
		List<Order> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Order order = new Order();
				//学生インスタンスに検索結果をセット
				order.setOrderId(rSet.getString("order_id"));
				order.setProduct(product);
				order.setUser(user);
				order.setOrderTime(rSet.getDate("order_time"));
				order.setCount(rSet.getInt("count"));
				order.setReceive(rSet.getBoolean("receive"));
				order.setSubscription(rSet.getBoolean("subscription"));
				//リストに追加
				list.add(order);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
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
					"UPDATE ORDER SET RECEIVE=? WHERE ORDER_ID=?");
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
				statement.setString(1, cart.getProduct()+cart.getUser().getUserId());
				statement.setString(2, cart.getProduct().getProductId());
				statement.setString(3, cart.getUser().getUserId());
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
