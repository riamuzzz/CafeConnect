package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class StockViewAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		ProductDao productDao =new ProductDao();
		CategoryDao categoryDao = new CategoryDao();
		String categoryId ="";

		//リストを初期化
		List<Category> list = new ArrayList<>();
		List<Product> productList = new ArrayList<>();


		//リクエストパラメータ―の取得 2
		//カテゴリプルダウン表示のデータ取得
		categoryId = req.getParameter("f1");
		//検索ワードの取得
		String keyword = req.getParameter("keyword");
		//検索ワードが空白なNULLが入る
		keyword = (keyword == null || keyword.trim().isEmpty()) ? null : keyword.trim();

		//DBからデータ取得 3
		//プルダウン表示のためのカテゴリ全取得
		 list = categoryDao.get();
		 //絞り込みのためオブジェクト型に変更
		 Category category=categoryDao.get(categoryId);
		 //検索ワードに引っかかる商品リストから名前を抽出

		 //絞り込み結果

		productList = productDao.search(category, keyword);


		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにカテゴリをセット
		req.setAttribute("categoryName", list);
		req.setAttribute("product", productList);

		//JSPへフォワード 7
		req.getRequestDispatcher("stockView.jsp").forward(req, res);
	}

}
