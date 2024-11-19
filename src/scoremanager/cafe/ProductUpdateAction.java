package scoremanager.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class ProductUpdateAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		ProductDao pDao = new ProductDao();
		CategoryDao cDao = new CategoryDao();
		List<Category> cList = new ArrayList<>();
		List<String> error = new ArrayList<>();
		//リクエストパラメータ―の取得 2
		String id = req.getParameter("productId");//商品番号

		//DBからデータ取得 3
		Product product = pDao.get(id);//商品番号から商品インスタンスを取得
		cList = cDao.get();//カテゴリを全取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		req.setAttribute("categories", cList);
		if (product != null) {// 商品が存在していた場合
			req.setAttribute("id", product.getProductId());//商品id
			req.setAttribute("category", product.getCategory());//カテゴリ名
			req.setAttribute("name", product.getProductName());//商品名
			req.setAttribute("price", product.getPrice());//価格
			req.setAttribute("count", product.getCount());//数量
			req.setAttribute("detail", product.getProductDetail());//詳細
			req.setAttribute("image", product.getImage());//写真
			req.setAttribute("is_sell", product.isSell());//販売状況
			req.setAttribute("instockday", product.getInStockDay());
		} else {// 学生が存在していなかった場合
			error.add("商品が存在していません");
			req.setAttribute("error", error);
		}
		System.out.println(product.getInStockDay() + "aaa");
		//JSPへフォワード 7
		req.getRequestDispatcher("product_update.jsp").forward(req, res);
	}
}
