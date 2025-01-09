package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.User;
import dao.OrderDao;
import dao.UserDao;
import tool.Action;

public class SabscriptionDetailAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);
		UserDao uDao = new UserDao();
		OrderDao oDao = new OrderDao();
		//現在ログインされているユーザを取得
		User user = (User)session.getAttribute("user");
		List<Order> orderList = new ArrayList<>();
		List<Order> subOrderList = new ArrayList<>();
		//ordersテーブルからログイン中のユーザのみのorderを取得
		orderList = oDao.filter(user.getUserId());
		//orderの中からサブスク登録されているorderのみを取得し別のリストに格納する
		for (Order order : orderList) {
			if (order.isSubscription() == true){
				subOrderList.add(order);
			}
		}
		//レスポンス値をセット 6
		req.setAttribute("user", user);
		req.setAttribute("orders", subOrderList);
		//JSPへフォワード 7
		req.getRequestDispatcher("subscriptionDetail.jsp").forward(req, res);
	}
}
