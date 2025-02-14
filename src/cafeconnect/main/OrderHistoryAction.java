package cafeconnect.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.User;
import dao.OrderDao;
import tool.Action;

public class OrderHistoryAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);
		List<Order> allOrderList = new ArrayList<>();
		List<Order> orderList = new ArrayList<>();
		OrderDao oDao =new OrderDao();
		//現在ログインされているユーザを取得
		User user = (User)session.getAttribute("user");
		//現在ログイン中のユーザの注文情報を取得
		allOrderList = oDao.filter(user.getUserId());
		//サブスクリプションの商品は除外する
		for (Order order : allOrderList) {
			if (order.isSubscription() == false) {
				orderList.add(order);
			}
		}
		//レスポンス値をセット 6
		req.setAttribute("orders", orderList);
		//JSPへフォワード 7
		req.getRequestDispatcher("orderHistory.jsp").forward(req, res);
	}
}
