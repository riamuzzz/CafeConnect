package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;
import bean.Product;

public class ProductDao extends Dao {
	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from PRODUCT ";


	/**
	 * getメソッド カフェ店員IDを元に、カフェ店員インスタンスを1件取得する
	 *
	 * @param cafeUserId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public Product get(String productId) throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//結果を格納するTeacherを初期化
		Product product = new Product();

		try{
			// SQL条件文の初期化
		    String condition = "where product_id =?";

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);

			//プレースホルダー（？の部分）に値を設定し、SQLを実行
			statement.setString(1,productId);
			ResultSet rSet = statement.executeQuery();

			//カテゴリDaoを初期化
			CategoryDao categoryDao =new CategoryDao();

			//取得した情報をproductインスタンスに保存
			if(rSet.next()) {
				product.setProductId(rSet.getString("product_id"));
				product.setProductName(rSet.getString("product_name"));
				product.setPrice(rSet.getInt("price"));
				product.setImage(rSet.getString("image"));
				product.setProductDetail(rSet.getString("product_detail"));
				product.setCount(rSet.getInt("count"));
				product.setSell(rSet.getBoolean("sell"));
				product.setInStockDay(rSet.getDate("in_stock_day"));
				//categoryDaoのgetでカテゴリ情報取得
				product.setCategory(categoryDao.get(rSet.getString("category_id")));
			} else {
				//対応する教員がいない場合はnullを返す
				product = null;
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
	return product;
	}


	/**
	 * getIdメソッド 最後の行のカフェ店員のID、カフェ店員インスタンスを1件取得する
	 *
	 * @param cafeUserId:String カフェ店員ID
	 * @return カフェ店員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */

	public Product getId() throws Exception{
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		Product product = new Product();

		try{
			// SQL条件文の初期化
		    String condition = " order by product_id desc limit 1;";

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);

			ResultSet rSet = statement.executeQuery();

			//カテゴリDaoを初期化
			CategoryDao categoryDao =new CategoryDao();

			//取得した情報をproductインスタンスに保存
			if(rSet.next()) {
				product.setProductId(rSet.getString("product_id"));
				product.setProductName(rSet.getString("product_name"));
				product.setPrice(rSet.getInt("price"));
				product.setImage(rSet.getString("image"));
				product.setProductDetail(rSet.getString("product_detail"));
				product.setCount(rSet.getInt("count"));
				product.setSell(rSet.getBoolean("sell"));
				product.setInStockDay(rSet.getDate("in_stock_day"));
				//categoryDaoのgetでカテゴリ情報取得
				product.setCategory(categoryDao.get(rSet.getString("category_id")));
			} else {
				//対応する教員がいない場合はnullを返す
				product = null;
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
	return product;
	}



	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 */
	private List<Product> postFilter(ResultSet rSet, Category category) throws Exception {
		//リストを初期化
		List<Product> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Product product = new Product();
				//学生インスタンスに検索結果をセット
				product.setProductId(rSet.getString("product_id"));
				product.setProductName(rSet.getString("product_name"));
				product.setPrice(rSet.getInt("price"));
				product.setImage(rSet.getString("image"));
				product.setProductDetail(rSet.getString("product_detail"));
				product.setCount(rSet.getInt("count"));
				product.setCategory(category);
				product.setSell(rSet.getBoolean("sell"));
				product.setInStockDay(rSet.getDate("in_stock_day"));
				//リストに追加
				list.add(product);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * filterメソッド カテゴリ、商品名を指定して商品の一覧を取得する
	 *
	 */
	public List<Product> filter(Category category, String productName) throws Exception {

		System.out.println(category);
		System.out.println(productName);

		//リストを初期化
		List<Product> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

	    // SQL条件文の初期化
	    String condition = "";
	    int paramIndex = 1;

		//SQL分のソート
		String order = " order by product_id asc";

	    // category のみ設定されている場合の条件
	    if (category != null && productName == null) {
	        condition = "where category_id=? and sell = True";
	        System.out.println("1");
	    }
	    // tel のみ設定されている場合の条件
	    else if (category == null && productName != null) {
	        condition = "where product_name=? and sell = True";
	        System.out.println("2");
	    }
	    // 両方設定されている場合の条件
	    else if (category != null && productName != null) {
	        condition = "where category_id=? and product_name=? and sell = True";
	        System.out.println("3");
	    }else{
	    	condition = "where sell = True";
	    	System.out.println("4");
	    }


		try{

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order );

	        // 値を設定（それぞれの条件に合わせて）
	        if (!condition.isEmpty()) {
	            if (condition.contains("category_id=?")) {
	                statement.setString(paramIndex++, category.getCategoryId());
	            }
	            if (condition.contains("product_name=?")) {
	                statement.setString(paramIndex, productName);
	            }
	        }
	        System.out.println(statement);

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet, category);
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
	 * serchメソッド 検索する
	 *
	 */
	public List<Product> serch(Category category, String productName) throws Exception {

		System.out.println(category);
		System.out.println(productName);

		//リストを初期化
		List<Product> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

	    // SQL条件文の初期化
	    String condition = "";
	    int paramIndex = 1;

		//SQL分のソート
		String order = " order by product_id asc";

	    // category のみ設定されている場合の条件
	    if (category != null && productName == null) {
	        condition = "where category_id=? ";
	        System.out.println("1");
	    }
	    // tel のみ設定されている場合の条件
	    else if (category == null && productName != null) {
	        condition = "where product_name like '%?%' ";
	        System.out.println("2");
	    }
	    // 両方設定されている場合の条件
	    else if (category != null && productName != null) {
	        condition = "where category_id=? and product_name like '%?%'";
	        System.out.println("3");
	    }else{
	    	condition = "";
	    	System.out.println("4");
	    }


		try{

			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order );

	        // 値を設定（それぞれの条件に合わせて）
	        if (!condition.isEmpty()) {
	            if (condition.contains("category_id=?")) {
	                statement.setString(paramIndex++, category.getCategoryId());
	            }
	            if (condition.contains("product_name=?")) {
	                statement.setString(paramIndex, productName);
	            }
	        }

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

			list = postFilter(rSet, category);
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

	public List<Product> get() throws Exception {

		//リストを初期化
		List<Product> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;
		try{
			CategoryDao cDao = new CategoryDao();
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();
			//リザルトセットを全件走査
			while (rSet.next()){
				Product product = new Product();
				//学生インスタンスに検索結果をセット
				product.setProductId(rSet.getString("product_id"));
				product.setProductName(rSet.getString("product_name"));
				product.setPrice(rSet.getInt("price"));
				product.setImage(rSet.getString("image"));
				product.setProductDetail(rSet.getString("product_detail"));
				product.setCount(rSet.getInt("count"));
				product.setCategory(cDao.get(rSet.getString("category_id")));
				product.setSell(rSet.getBoolean("sell"));
				product.setInStockDay(rSet.getDate("in_stock_day"));
				//リストに追加
				list.add(product);
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
	public List<Product> filter(Category category) throws Exception {

		//リストを初期化
		List<Product> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;
		try{
			CategoryDao cDao = new CategoryDao();
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + "WHERE category_id=? and sell = True");
			statement.setString(1, category.getCategoryId());

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();
			//リザルトセットを全件走査
			while (rSet.next()){
				Product product = new Product();
				//学生インスタンスに検索結果をセット
				product.setProductId(rSet.getString("product_id"));
				product.setProductName(rSet.getString("product_name"));
				product.setPrice(rSet.getInt("price"));
				product.setImage(rSet.getString("image"));
				product.setProductDetail(rSet.getString("product_detail"));
				product.setCount(rSet.getInt("count"));
				product.setCategory(cDao.get(rSet.getString("category_id")));
				product.setSell(rSet.getBoolean("sell"));
				product.setInStockDay(rSet.getDate("in_stock_day"));
				//リストに追加
				list.add(product);
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

	/**
	 * saveメソッド 学生インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param student：Student
	 *            学生
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Product product) throws Exception {

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//実行件数
		int count = 0;


		try{
			//データベースから学生を取得
			Product old = get(product.getProductId());

			if (old == null) {
				//学生が存在しなかった場合
				//プリペアードステートメントにInsert文をセット
				statement = connection.prepareStatement(
						"INSERT INTO PRODUCT (PRODUCT_ID ,CATEGORY_ID ,PRODUCT_NAME ,PRICE ,IMAGE ,PRODUCT_DETAIL ,COUNT ,SELL ,IN_STOCK_DAY ) VALUES (?,?,?,?,?,?,?,?,?)");
				//各部分に値を設定
				statement.setString(1, product.getProductId());
				statement.setString(2, product.getCategory().getCategoryId());
				statement.setString(3, product.getProductName());
				statement.setInt(4, product.getPrice());
				statement.setString(5, product.getImage());
				statement.setString(6, product.getProductDetail());
				statement.setInt(7, product.getCount());
				statement.setBoolean(8, product.isSell());
				statement.setDate(9, new java.sql.Date(product.getInStockDay().getTime()));

			}else {
				//学生が存在した場合
				//プリペアードステートメントにUpdate文をセット
				statement = connection.prepareStatement(
						"UPDATE PRODUCT SET PRODUCT_ID=? ,CATEGORY_ID=? ,PRODUCT_NAME=? ,PRICE=? ,IMAGE=? ,PRODUCT_DETAIL=? ,COUNT=? ,SELL=? ,IN_STOCK_DAY=? WHERE PRODUCT_ID=?");
				//各部分に値を設定
				statement.setString(1, product.getProductId());
				statement.setString(2, product.getCategory().getCategoryId());
				statement.setString(3, product.getProductName());
				statement.setInt(4, product.getPrice());
				statement.setString(5, product.getImage());
				statement.setString(6, product.getProductDetail());
				statement.setInt(7, product.getCount());
				statement.setBoolean(8, product.isSell());
				statement.setDate(9, new java.sql.Date(product.getInStockDay().getTime()));
				statement.setString(10, product.getProductId());

			}

			System.out.println(statement);
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
