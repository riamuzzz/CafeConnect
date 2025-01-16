package scoremanager.cafe;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.ProductDao;
import tool.Action;

public class StockUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		ProductDao pDao = new ProductDao();
		List<String> error = new ArrayList<>();
		//リクエストパラメータ―の取得
		String[] productIds = req.getParameterValues("productId");//商品番号のリスト
		//商品番号ごとの入荷数廃棄数を取得
		for (String productId : productIds) {
		    String join = req.getParameter("join_" + productId);
		    String disposal = req.getParameter("disposal_" + productId);
		    Product product = pDao.get(Integer.parseInt(productId));//商品番号をもとに商品インスタンスを取得
		    if (product != null) {
				// 商品が存在していた場合
				// インスタンスに値をセット
				product.setCategory(product.getCategory());
				product.setProductId(product.getProductId());
				product.setProductName(product.getProductName());
				product.setPrice(product.getPrice());
				// 在庫が増やされた場合
				if (!join.equals("0") && disposal.equals("0")) {
				    product.setCount(product.getCount() + Integer.parseInt(join));
				// 在庫が減らされた場合
				} else if (!disposal.equals("0") && join.equals("0")) {
					// 在庫数より入力された値が大きかった場合はDBに反映せずエラーメッセージを残す
					if (product.getCount() >=  Integer.parseInt(disposal)) {
						product.setCount(product.getCount() - Integer.parseInt(disposal));
					} else {
						error.add("error");
						req.setAttribute("error", error);
						product.setCount(product.getCount());
					}
				// どちらも入力された場合
				} else if (!join.equals("0") && !disposal.equals("0")) {
					System.out.println(product.getCount() + (Integer.parseInt(join) - Integer.parseInt(disposal)));
					if (product.getCount() <=  product.getCount() + (Integer.parseInt(join) - Integer.parseInt(disposal))) {
						System.out.println("★");
						product.setCount(product.getCount() + (Integer.parseInt(join) - Integer.parseInt(disposal)));
					} else {
						System.out.println("★2");
						error.add("error");
						req.setAttribute("error", error);
						product.setCount(product.getCount());
					}
				} else {
					product.setCount(product.getCount());
				}
				product.setProductDetail(product.getProductDetail());
				product.setImage(product.getImage());
				product.setSell(product.isSell());
				product.setInStockDay(product.getInStockDay());
				// 商品を保存
				pDao.save(product);
		    }
		}
		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.getRequestDispatcher("StockView.action").forward(req, res);
	}
}
