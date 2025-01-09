package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.User;
import tool.Action;

public class SettlementAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー

		//リクエストパラメータ取得
		String productId = req.getParameter("productId");
		//DBからデータ取得 3
		Card card =user.getCard();

		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("user", user);
		req.setAttribute("card", card);
		req.setAttribute("productId", productId);


		//JSPへフォワード 7
		req.getRequestDispatcher("settlement.jsp").forward(req, res);
	}
}
