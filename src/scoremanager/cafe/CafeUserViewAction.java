package scoremanager.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class CafeUserViewAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		ProductDao productDao =new ProductDao();
		CategoryDao categoryDao = new CategoryDao();
		String categoryName ="";

		//リストを初期化
		List<Category> list = new ArrayList<>();


		//リクエストパラメータ―の取得 2
		categoryName = req.getParameter("f1");

		//DBからデータ取得 3
		 list = categoryDao.get();


		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストに入学年度をセット
		req.setAttribute("categoryName", list);
		//JSPへフォワード 7
		req.getRequestDispatcher("cafeUserView.jsp").forward(req, res);
	}

}
