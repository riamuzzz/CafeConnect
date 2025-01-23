package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import bean.Product;
import bean.User;
import dao.OrderDao;
import tool.Action;

public class OnlineOrderViewAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		OrderDao orderDao =new OrderDao();
		Product product =null;
		User user =null;
		Date orderTime =null;

		//リストを初期化
		List<Order> list = new ArrayList<>();
		//リクエストパラメータ―の取得 2
		//カテゴリプルダウン表示のデータ取得
		//DBからデータ取得 3
		 //絞り込み結果、何も絞り込みの条件がなかったら発送済みではない商品のみを取得
		list = orderDao.filter(product,user,(java.sql.Date)orderTime);
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにカテゴリをセット
		req.setAttribute("order", list);
		//JSPへフォワード 7
		req.getRequestDispatcher("onlineOrderView.jsp").forward(req, res);
	}

}
