package scoremanager.cafe;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
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

		String productId = "AA13";                   // 商品ID
		String categoryId = null;                   // カテゴリID
		Category category = null;                   // カテゴリ
		String productName = null;                  // 商品名
		int price = 0;                               // 価格
		int count = 0;                               // 在庫数
		String productDetail = null;                // 商品詳細
		String img = null;                           // 写真
		String sellStr = null;                       // 販売状況str型
		Boolean sell = null;                         // 販売状況
		LocalDate nowDate = LocalDate.now();         // LcalDateインスタンスを取得
		String nowDateStr = String.valueOf(nowDate); // string型に変換
		Date inStockDay = Date.valueOf(nowDateStr);  // string型からDate型に変換

		// リクエストパラメータの取得
		categoryId = req.getParameter("category");           // カテゴリId
		productName = req.getParameter("productName");       // 商品名
		price = Integer.parseInt(req.getParameter("price")); // 価格
		count = Integer.parseInt(req.getParameter("count")); // 在庫数
		productDetail = req.getParameter("productDetail");   // 商品詳細
		img = req.getParameter("image");                     // 写真
		sellStr = req.getParameter("sell");                  // 販売状況

		// sellStrが"on"ではない時販売状況をfalseにする
		if (sellStr != "on"){
			sell = false;
		} else {
			sell = true;
		}

		// ファイルを保存したい！！
		String fileName = new File(img).getName();// pathからファイル名取得

		// 入力されたcategoryIdからcategoryを取得
		category = categoryDao.get(categoryId);

		// productを初期化
		Product product = new Product();

		// productに値をset
		product.setProductId(productId);
		product.setCategory(category);
		product.setProductName(productName);
		product.setPrice(price);
		product.setCount(count);
		product.setProductDetail(productDetail);
		product.setImage(fileName);
		product.setSell(sell);
		product.setInStockDay(inStockDay);

		// productをsave
		productDao.save(product);

		// 商品登録ページに遷移
		req.getRequestDispatcher("productCreate.jsp").forward(req, res);

	}
}
