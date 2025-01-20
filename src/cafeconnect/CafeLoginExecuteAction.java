package cafeconnect;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CafeUser;
import dao.CafeUserDao;
import tool.Action;

public class CafeLoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		CafeUser cafeUser = new CafeUser();

		//これ作った
		CafeUserDao cafeUserDAO=new CafeUserDao();

		//リクエストパラメータ―の取得 2
		String id = req.getParameter("email");
		String password = req.getParameter("password");

		//DBからデータ取得 3
		cafeUser=cafeUserDAO.login(id,password);
		//なし
		//ビジネスロジック 4

		System.out.println("①★★★★★★★★★★★★★★");
		//もし、ログインが成功したら
		if(cafeUser!=null){
			System.out.println("②★★★★★★★★★★★★★★");

			//Sessionを有効にする
			HttpSession session = req.getSession(true);
			cafeUser.setAuthenticated(true);

			//セッションに"user"という変数名で値はTeacher変数の中身
			session.setAttribute("cafe_user", cafeUser);

				//リダイレクト
			url = "cafe/CafeTop.action";
			res.sendRedirect(url);
		}else{
				System.out.println("ffff");
				//認証失敗
				 List<String> errors = new ArrayList<>();
					errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
					req.setAttribute("errors", errors);

				//JSPへフォワード
				url = "cafeLogin.jsp";
				req.getRequestDispatcher(url).forward(req, res);



	}
}
}

