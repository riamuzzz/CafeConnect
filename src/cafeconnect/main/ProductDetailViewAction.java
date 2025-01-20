package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.ProductDao;
import tool.Action;

public class ProductDetailViewAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		ProductDao pDao = new ProductDao();//商品Dao
		//リクエストパラメータ―の取得 2
		int productId = Integer.parseInt(req.getParameter("productId"));//学番
		String numStr = req.getParameter("num");
		String error = req.getParameter("error");
		if (numStr != null) {
			int num = Integer.parseInt(numStr);
			req.setAttribute("num", num);
		}
		//DBからデータ取得 3
		Product product = pDao.get(productId);//商品IDから商品インスタンスを取得
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("error", error);
		req.setAttribute("product", product);
		//JSPへフォワード 7
		req.getRequestDispatcher("productDetail.jsp").forward(req, res);
	}
}
