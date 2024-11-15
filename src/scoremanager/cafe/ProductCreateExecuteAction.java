package scoremanager.cafe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class ProductCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// productDaoをインスタンス化
		ProductDao productDao = new ProductDao();
		// categoryDaoをインスタンス化
		CategoryDao categoryDao = new CategoryDao();

		String categoryId = null;     // カテゴリID
		Category category = null;     // カテゴリ
		String productName = null;    // 商品名
		Integer price = null;          // 価格
		Integer count = null;          // 在庫数
		String productDetail = null;  // 商品詳細
		String img = null;             // 写真
		String sellStr = null;         // 販売状況str型
		Boolean sell = null;           // 販売状況

		// リクエストパラメータの取得
		categoryId = req.getParameter("Category"); // カテゴリId
		productName = req.getParameter("productName"); // 商品名
		price = Integer.parseInt(req.getParameter("price")); // 価格
		count = Integer.parseInt(req.getParameter("count")); // 在庫数
		productDetail = req.getParameter("productDetail"); // 商品詳細
		img = req.getParameter("img"); // 写真
		sellStr = req.getParameter("sell"); // 販売状況


	}
}
