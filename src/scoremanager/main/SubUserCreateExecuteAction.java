package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class SubUserCreateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserDao uDao = new UserDao();
		HttpSession session = req.getSession(true);
		//現在ログイン中のユーザ情報取得
		User user = (User)session.getAttribute("user");
		//サブスクリプションの入会チェック欄をtrueにする
		user.setSubscription(true);
		//DBに登録
		uDao.save(user);
		req.getRequestDispatcher("ProductSelectView.action?categoryId=CATE02").forward(req, res);
	}
}
