package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import tool.Action;

public class SubscriptionAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションを有効にする
		HttpSession session = req.getSession(true);
		//現在ログイン中のユーザ情報を取得
		User user = (User)session.getAttribute("user");
		//サブスクに入会していなかったら入会ページへ
		if (user.isSubscription() == false) {
			req.getRequestDispatcher("subscription.jsp").forward(req, res);
		} else {
			//サブスクに入会していたら商品選択ページへ
			req.getRequestDispatcher("ProductSelectView.action?categoryId=CATE02").forward(req, res);
		}
	}
}
