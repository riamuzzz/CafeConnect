package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Cart;
import bean.Product;
import bean.User;

public class CartDao extends Dao {
	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from CART where ";


	/**
	 * getメソッド カフェ店員IDを元に、カフェ店員インスタンスを1件取得する
	 *
	 * @param cafeUserId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public Cart get(User user, Product product) throws Exception{

		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//結果を格納するCartを初期化
		Cart cart = new Cart();

		// SQL条件文の初期化
		String condition = "user_id=? and product_id=?";

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql+ condition);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,user.getUserId());
			statement.setString(2,product.getProductId());
			ResultSet rSet = statement.executeQuery();

			//カテゴリDaoを初期化
			UserDao userDao =new UserDao();
			ProductDao productDao =new ProductDao();

			//取得した情報をcartインスタンスに保存
			if(rSet.next()) {
				cart.setUser(userDao.get(rSet.getString("user_id")));
				cart.setProduct(productDao.get(rSet.getString("product_id")));
				cart.setCount(rSet.getInt("count"));
			} else {
				//対応する教員がいない場合はnullを返す
				cart = null;
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
	//検索したユーザインスタンスを返す
	return cart;
	}

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 * @param rSet:リザルトセット
	 * @param school:School
	 *            学校
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<Cart> postFilter(ResultSet rSet, User user,Product product) throws Exception {
		//リストを初期化
		List<Cart> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Cart cart = new Cart();
				//カートインスタンスに検索結果をセット
				cart.setUser(user);
				cart.setProduct(product);
				cart.setCount(rSet.getInt("count"));


				//リストに追加
				list.add(cart);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;


	}

	/**
	 * filterメソッド カフェ店員IDを元に、カフェ店員インスタンスを1件取得する
	 */

	public List<Cart> filter(User user) throws Exception{

		//リストを初期化
		List<Cart> list = new ArrayList<>();

		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		// SQL条件文の初期化
		String condition = "user_id=?";

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql+ condition);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,user.getUserId());
			ResultSet rSet = statement.executeQuery();

			//カテゴリDaoを初期化
			UserDao userDao =new UserDao();
			ProductDao productDao =new ProductDao();

			//取得した情報をcartインスタンスに保存
			while(rSet.next()) {
				Cart cart = new Cart();
				cart.setUser(userDao.get(rSet.getString("user_id")));
				cart.setProduct(productDao.get(rSet.getString("product_id")));
				cart.setCount(rSet.getInt("count"));
				list.add(cart);
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
	//検索したユーザインスタンスを返す
	return list;
	}


	/**
	 * saveメソッド 商品をカートに追加する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param student：Student
	 *            学生
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Cart cart) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		//同じユーザが同じ商品を選択した時UPDATEでCOUNTをプラスする
		//同じユーザが新しい商品を追加したときINSERTでカート内に商品を追加する
		//ログイン中のユーザのカート内に最初の商品を追加する
		Cart old = get(cart.getUser(), cart.getProduct());

		try{
			//ログイン中のユーザのカートに商品が何も入っていない||新しい商品が追加されたとき
			if (old == null) {
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO CART (USER_ID ,PRODUCT_ID ,COUNT) VALUES (?,?,?)");
				//各部分に値を設定
				statement.setString(1, cart.getUser().getUserId());
				statement.setString(2, cart.getProduct().getProductId());
				statement.setInt(3, cart.getCount());
			//ログイン中のユーザが同じ商品をカートに追加しようとしたとき
			}else{
				int newcount = old.getCount();
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"UPDATE CART SET COUNT=? WHERE USER_ID=? and PRODUCT_ID=?");
				//COUNTにもともとの商品の個数と今選択された個数を足す
				statement.setInt(1, cart.getCount() + newcount);
				statement.setString(2, cart.getUser().getUserId());
				statement.setString(3, cart.getProduct().getProductId());

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


	/**
	 * deleteメソッド カート削除
	 *
	 */
	public boolean delete(Cart cart, String productId) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		try{

				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"DELETE from CART WHERE USER_ID=? and PRODUCT_ID=?");
				//各部分に値を設定
				statement.setString(1, cart.getUser().getUserId());
				statement.setString(2, productId);


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
