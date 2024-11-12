package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		User user = new User();
		UserDao uDao = new UserDao();

		//リクエストパラメータ―の取得 2
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		//DBからデータ取得 3
<<<<<<< HEAD
		user = uDao.login(email,password);

=======
		cafeUser=cafeUserDAO.login(id,password);
		//なし
>>>>>>> branch 'master' of https://github.com/riamuzzz/CafeConnect.git
		//ビジネスロジック 4



		if (user != null) {
			// 認証済みフラグを立てる
			user.setAuthenticated(true);
			//Sessionを有効にする
			HttpSession session = req.getSession(true);
			//セッションに"user"という変数名で値はuser変数の中身
			session.setAttribute("user", user);
			//リダイレクト
			url = "main/MyPage.action";
			res.sendRedirect(url);
		} else {
			Map<String, String> errors = new HashMap<>();// エラーメッセージ
			errors.put("1", "ログインに失敗しました。IDまたはPasswardが違います。");
			req.setAttribute("errors", errors);
			url = "login.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}


		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		//req.getRequestDispatcher("main/Menu.action").forward(req, res);


	}

}
