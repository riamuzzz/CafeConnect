package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import bean.User;
import dao.UserDao;
import tool.Action;

public class CafeUserViewAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		UserDao userDao =new UserDao();
		String userName ="";
		Card card =new Card();

		//リストを初期化
		List<User> list = new ArrayList<>();

		//リクエストパラメータ―の取得 2

		//カテゴリプルダウン表示のデータ取得
		userName = req.getParameter("userName");
		//検索ワードの取得
		String tel = req.getParameter("tel");
		//検索ワードが空白なNULLが入る
		userName = (userName == null || userName.trim().isEmpty()) ? null : userName.trim();
		tel = (tel == null || tel.trim().isEmpty()) ? null : tel.trim();

		//DBからデータ取得 3
		 //絞り込み結果
		System.out.println("*");
		list = userDao.filter(userName, tel,card);
		System.out.println("**");

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにカテゴリをセット
		req.setAttribute("user", list);
		System.out.println(list);

		//JSPへフォワード 7
		req.getRequestDispatcher("cafeUserView.jsp").forward(req, res);
	}

}
