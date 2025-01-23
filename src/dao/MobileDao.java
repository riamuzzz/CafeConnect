package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Cart;
import bean.Mobile;

public class MobileDao extends Dao{
	/**
	 * カートの情報を受け取りモバイル内の情報を入れる
	 */

	public boolean Create(Cart cart, int randomNumber) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;

		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO MOBILE (MOBILE_ID ,PRODUCT_ID ,USER_ID ,COUNT) VALUES (?,?,?,?)");
				//各部分に値を設定
				statement.setInt(1, cart.getUser().getUserId()+randomNumber);
				statement.setInt(2,cart.getProduct().getProductId());
				statement.setInt(3, cart.getUser().getUserId());
				statement.setInt(4, cart.getCount());
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

	public List<Mobile> filter(int userId) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//返り値用のリスト
		List<Mobile> list = new ArrayList<>();

		UserDao uDao = new UserDao();
		ProductDao pDao = new ProductDao();
		try{
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"SELECT * FROM MOBILE WHERE USER_ID=?");
				//各部分に値を設定
				statement.setInt(1, userId);

				//プリペアードステートメントを実行
				ResultSet rSet = statement.executeQuery();

				while (rSet.next()){
					Mobile mobile = new Mobile();
					//注文インスタンスに検索結果をセット
					mobile.setMobileId(rSet.getInt("mobile_id"));
					mobile.setProduct(pDao.get(rSet.getInt("product_id")));
					mobile.setUser(uDao.get(rSet.getInt("user_id")));
					mobile.setCount(rSet.getInt("count"));
					//リストに追加
					list.add(mobile);
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
