package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;

public class CategoryDao extends Dao{

	public Category get(String categoryId) throws Exception {

		//ユーザインスタンスを初期化
		Category category =new Category();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;


		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT * FROM CATEGORY WHERE category_id = ?");
			//各部分に値を設定
			statement.setString(1,categoryId );

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();


			if (rSet.next()){
				category.setCategoryId(rSet.getString("category_id"));
				category.setCategoryName(rSet.getString("category_name"));
			}else {
				//リザルトセットが存在しない場合
				//ユーザインスタンスにnullをセット
				category = null;
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
		return category;
	}

	private List<Category> postFilter(ResultSet rSet) throws Exception {
		//リストを初期化
		List<Category> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Category category = new Category();
				//学生インスタンスに検索結果をセット
				category.setCategoryId(rSet.getString("category_id"));
				category.setCategoryName(rSet.getString("category_name"));
				//リストに追加
				list.add(category);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;


	}


	public List<Category> get() throws Exception {

		//ユーザインスタンスを初期化
		Category category =new Category();


		//リストを初期化
		List<Category> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

	    // SQL条件文の初期化
	    String condition = "SELECT * FROM CATEGORY";


		try{

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(condition);


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

}
