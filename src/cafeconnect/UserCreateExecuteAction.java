package cafeconnect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import bean.User;
import dao.CardDao;
import dao.UserDao;
import tool.Action;

public class UserCreateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String name = req.getParameter("name"); // 氏名
		String tel = req.getParameter("tel"); // 電話番号
		String address = req.getParameter("address"); // 住所
		String email = req.getParameter("email"); // メールアドレス
		String password = req.getParameter("password"); // パスワード
		String cardNumber = req.getParameter("card_number");// カード番号
		String cardDay = req.getParameter("card_day"); // 有効期限
		String cardCvc = req.getParameter("card_cvc"); // セキュリティ番号
		String cardName = req.getParameter("card_name"); // カード氏名


		// Card型のcardに値をセットする
		Card card = new Card();

		card.setCardNumber(cardNumber);
		card.setCardExpiryDate(cardDay);
		card.setCardCvc(cardCvc);
		card.setCardName(cardName);

		// CardDaoをインスタンス化
		CardDao cardDao = new CardDao();

		// card情報をDBに登録
		cardDao.save(card);


		// User型のuserに値をセット
		User user = new User();

		user.setUserName(name);
		user.setTel(tel);
		user.setAddress(address);
		user.setEmail(email);
		user.setUserPassword(password);
		user.setCard(card);

		// UserDaoをインスタンス化
		UserDao userDao = new UserDao();

		// user新規登録をする
		userDao.save(user);

		req.setAttribute("message", "登録完了しました");

		req.getRequestDispatcher("TopPageExecute.action").forward(req, res);

	}
}
