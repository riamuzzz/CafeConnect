package cafeconnect.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.User;
import dao.CardDao;
import tool.Action;

public class CardCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Card card = new Card();
		//カードDaoの初期化
		CardDao cDao = new CardDao();
		//セッションを有効にする
		HttpSession session = req.getSession(true);
		//セッションからログイン中のユーザ情報を取得する
		User user = (User)session.getAttribute("user");
		//ログイン中のユーザ情報の中からクレカの情報を取得なけれなNULLが入る
		if (user.getCard() == null) {
			card = null;
		} else {
			card = cDao.get(user.getCard().getCardNumber());
		}

		//クレカ情報をリクエストにセット
		req.setAttribute("card", card);
		req.getRequestDispatcher("card_create.jsp").forward(req, res);
	}
}
