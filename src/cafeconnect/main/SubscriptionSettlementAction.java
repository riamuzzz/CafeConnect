package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.Product;
import bean.User;
import dao.ProductDao;
import tool.Action;

public class SubscriptionSettlementAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ユーザー情報取得
		HttpSession session = req.getSession();// セッション
		User user = (User) session.getAttribute("user");// ログインユーザー
		String productIdStr = req.getParameter("productId");
		String countStr = req.getParameter("count");
		int count = Integer.parseInt(countStr);
		count = count * 10;
		int productId = Integer.parseInt(productIdStr);
		ProductDao pDao = new ProductDao();
		Product product = pDao.get(productId);
		Card card =user.getCard();
		req.setAttribute("product", product);
		req.setAttribute("count", count);
		req.setAttribute("user", user);
		req.setAttribute("card", card);
		// JSPへフォワード 7
		req.getRequestDispatcher("subscriptionSettlement.jsp").forward(req, res);
	}
}
