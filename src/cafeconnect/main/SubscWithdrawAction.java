package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class SubscWithdrawAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		UserDao uDao = new UserDao();
		HttpSession session = req.getSession(true);

		//現在ログイン中のユーザ情報取得
		User user = (User)session.getAttribute("user");

		//サブスクリプションの入会チェック欄をfalseにする
		user.setSubscription(false);
		//DBに登録
		uDao.save(user);
		// セッションにメッセージを保存
        String message = "退会しました";
        req.setAttribute("message", message);

		// JSPへフォワード 7
		req.getRequestDispatcher("MyPage.action").forward(req, res);
	}
}
