package cafeconnect.cafe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class ProductUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		boolean isSell = false;//商品フラグ
		ProductDao pDao = new ProductDao();
		CategoryDao cDao = new CategoryDao();
		List<Category> cList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//リクエストパラメータ―の取得 2
		String categoryId = req.getParameter("category");
		String instockday = req.getParameter("instockday");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String count = req.getParameter("count");
		String detail = req.getParameter("detail");
		String image = req.getParameter("image");
		String isSellStr = req.getParameter("is_sell");
		// 商品フラグにチェックが入っていた場合
		if (isSellStr != null) {
			// 商品フラグを立てる
			isSell = true;
		}
		//DBからデータ取得 3
		//商品番号から商品インスタンスを取得
		Product product = pDao.get(id);
		//カテゴリIDからカテゴリインスタンスを取得
		Category category = cDao.get(categoryId);

		//ビジネスロジック 4
		//DBへデータ保存 5
		if (product != null) {
			// 商品が存在していた場合
			// インスタンスに値をセット
			product.setCategory(category);
			product.setProductId(id);
			product.setProductName(name);
			product.setPrice(Integer.parseInt(price));
			product.setCount(Integer.parseInt(count));
			product.setProductDetail(detail);
			product.setImage(image);
			product.setSell(isSell);
			product.setInStockDay(dateFormat.parse(instockday));
			// 商品を保存
			pDao.save(product);
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7
		//cList = cDao.get();//カテゴリを全取得
		//req.setAttribute("categories", cList);//フォワードした先でクラスの選択をさせるため

		req.getRequestDispatcher("product_update_done.jsp").forward(req, res);
	}
}
