package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String redirecturl = "";
		String url = "";
		String num;
		//リクエストパラメータ―の取得 2
		redirecturl = req.getParameter("redirect");
		num = req.getParameter("num");
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("redirect", redirecturl);
		req.setAttribute("num", num);
		//JSPへフォワード 7
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}
}