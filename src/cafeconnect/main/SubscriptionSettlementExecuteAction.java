package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import bean.User;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class SubscriptionSettlementExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー
		ProductDao pDao = new ProductDao();
		OrderDao oDao = new OrderDao();
		String productIdStr = req.getParameter("productId");
		String countStr = req.getParameter("count");
		int count = Integer.parseInt(countStr);
		int productId = Integer.parseInt(productIdStr);
		Product product = pDao.get(productId);
		oDao.create(user, product, count);
		//JSPへフォワード 7
		req.getRequestDispatcher("settlementDone.jsp").forward(req, res);
	}
}
