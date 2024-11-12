package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDao;
import tool.Action;

public class CardCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
//		HttpSession session = req.getSession();//セッション
		CardDao cardDao = new CardDao();//カードDao

		String cardNumber = "";     // カードナンバー
		String cardExpiryDate = ""; // 有効期限
		String cardCvc = "";        // セキュリティー番号
		String cardName = "";       // カード名義
		Card card = null;           // カード型 card

		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		//リクエストパラメータ―の取得 2
		cardNumber = req.getParameter("card_number");//カード番号

		//DBからデータ取得 3
		card = cardDao.get(cardNumber);// カード番号からカードインスタンスを取得

		//ビジネスロジック 4
				//DBへデータ保存 5

			if (cardNumber==null) {// カード番号が選択されていない場合
				errors.put("null", "カード番号を入力してください");
			}else{
				if (card == null) {
					// カードインスタンスを初期化
					card = new Card();
					// インスタンスに値をセット
					card.setCardNumber(cardNumber);
					card.setCardExpiryDate(cardExpiryDate);
					card.setCardCvc(cardCvc);
					card.setCardName(cardName);
					// カードを保存
					cardDao.save(card);
				} else {			//入力されたカード番号がDBに保存されていた場合
					errors.put("primary", "カード番号が重複しています");
					}
				}

		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.setAttribute("card_number",cardNumber);
		req.setAttribute("card_expiry_date",cardExpiryDate);
		req.setAttribute("card_cvc", cardCvc);
		req.setAttribute("card_name", cardName);

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("null", cardNumber);
			req.setAttribute("primary", cardNumber);
			req.getRequestDispatcher("card_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("card_create_done.jsp").forward(req, res);
	}
}
