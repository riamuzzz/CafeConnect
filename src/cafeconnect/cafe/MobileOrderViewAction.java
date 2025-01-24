package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDao;
import tool.Action;

public class MobileOrderViewAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		OrderDao oDao =new OrderDao();
		String productName =null;
		String userName =null;
		String orderTime = req.getParameter("orderTime");
		userName = req.getParameter("name");
		productName = req.getParameter("product");

		//リストを初期化
		List<Order> list = new ArrayList<>();

		//リクエストパラメータ―の取得 2

		//カテゴリプルダウン表示のデータ取得

		//DBからデータ取得 3

		 //絞り込み結果
		list = oDao.mobileFilter(productName,userName,orderTime);

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにカテゴリをセット
		req.setAttribute("order", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("mobileOrderView.jsp").forward(req, res);
	}

}
