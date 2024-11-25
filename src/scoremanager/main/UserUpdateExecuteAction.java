package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class UserUpdateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);
		//現在ログイン中のユーザ情報取得
		User user = (User)session.getAttribute("user");
		UserDao uDao = new UserDao();
		//リクエストパラメータ―の取得 2
		String nameStr = req.getParameter("name");//名前
		String telStr = req.getParameter("tel");//電話番号
		String addressStr = req.getParameter("address");//住所
		String emailStr = req.getParameter("email");//メールアドレス
		//DBからデータ取得 3
		//ビジネスロジック 4
		if (nameStr != null) {
			user.setUserName(nameStr);
			uDao.save(user);
		}
		if (telStr != null) {
			user.setTel(telStr);
			uDao.save(user);
		}
		if (addressStr != null) {
			user.setAddress(addressStr);
			uDao.save(user);
		}
		if (emailStr != null) {
			user.setTel(emailStr);
			uDao.save(user);
		}
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.getRequestDispatcher("MyPage.action").forward(req, res);
	}

}
