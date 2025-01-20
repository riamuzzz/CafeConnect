package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CafeUser;
import dao.CafeUserDao;
import tool.Action;

public class CafePassResetExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<String> error = new ArrayList<>();
		// 現在のパスワードを取得する(JSPで入力されたもの)
		String oldPass = req.getParameter("oldPass");
		// JSPから新しく設定したいパスワードを取得する
		String newPass = req.getParameter("newPass");
		// DBから現在のパスワードを取得
		CafeUserDao cDao = new CafeUserDao();
		// セッション立ち上げ
		HttpSession session = req.getSession();
		// 現在ログインされているユーザの情報をセッションから取得
		CafeUser cafeUser = (CafeUser)session.getAttribute("cafe_user");
		// 上の２つを比較しあっていたら新しいパスワードをDBに設定する
		if (cafeUser.getCafeUserPassword().equals(oldPass)) {
			cafeUser.setCafeUserId(cafeUser.getCafeUserId());
			cafeUser.setCafeUserName(cafeUser.getCafeUserName());
			cafeUser.setAuthenticated(cafeUser.isAuthenticated());
			cafeUser.setCafeUserPassword(newPass);
			cDao.save(cafeUser);
			req.getRequestDispatcher("CafeTop.action").forward(req, res);
		} else {
			// 違っていたらエラーメッセージを追加する
			error.add("error");
			req.setAttribute("error", error);
			req.getRequestDispatcher("cafePassReset.jsp").forward(req, res);
		}
	}
}
