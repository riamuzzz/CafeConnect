package cafeconnect.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.User;
import dao.CardDao;
import dao.UserDao;
import tool.Action;

public class CardCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションを有効にする
		HttpSession session = req.getSession(true);
		//セッションからログイン中のユーザ情報を取得する
		User user = (User)session.getAttribute("user");
		CardDao cDao = new CardDao();
		UserDao uDao = new UserDao();
		List<Card> cards = new ArrayList<>();
		Map<String, String> errors = new HashMap<>();
		boolean cNumChk = false;
		//JSPから値取得
		String cardNum =  req.getParameter("card_number");
		String cardDay =  req.getParameter("card_day");
		String cardCvc =  req.getParameter("card_cvc");
		String cardName =  req.getParameter("card_name");
		//カード情報全取得
		cards = cDao.get();
		//カード番号に重複がないかチェック
		for (Card c : cards) {
			if (cardNum == c.getCardNumber()) {
				errors.put("list", "カード番号が重複しています");
				req.setAttribute("errors", errors);
				req.getRequestDispatcher("CardCreate.action").forward(req, res);
			} else {
				cNumChk = true;
			}
		}
		if (cNumChk == true) {
			//カード情報を保存
			Card card = new Card();
			card.setCardNumber(cardNum);
			card.setCardExpiryDate(cardDay);
			card.setCardCvc(cardCvc);
			card.setCardName(cardName);
			cDao.save(card);
			user.setCard(card);
			uDao.saveCard(user);
			req.getRequestDispatcher("MyPage.action").forward(req, res);
		}
	}
}
