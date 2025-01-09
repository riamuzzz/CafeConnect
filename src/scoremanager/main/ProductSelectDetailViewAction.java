package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.Product;
import bean.User;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class ProductSelectDetailViewAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);//セッションを有効にする
		User user = (User)session.getAttribute("user");//ログイン中のユーザ情報取得
		OrderDao oDao = new OrderDao();//注文Dao
		ProductDao pDao = new ProductDao();//商品Dao
		List<Order> orders = new ArrayList<>();
		int g = 300;//グラムの変数
		//リクエストパラメータ―の取得 2
		String productId = req.getParameter("productId");//品番
		//DBからデータ取得 3
		Product product = pDao.get(productId);//商品IDから商品インスタンスを取得
		orders = oDao.filter(user.getUserId());
		//現在ログイン中のユーザが過去にサブスクで商品を注文していたか確認
		if (!orders.isEmpty()){
			for (Order order : orders) {
				//現在ログイン中のユーザがサブスク会員の場合
				if(order.isSubscription() == true) {
					//グラム数を追加していく
					g = g - order.getCount();
					//300gを下回ったら
					if (0 >= g) {
						//ログイン中のユーザのサブスク購入履歴に飛ぶ
						String error = "300g以上は選択できません";
						req.setAttribute("error", error);
					}
				} else {

				}
			}
		}
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("g", g);
		req.setAttribute("product", product);
		//JSPへフォワード 7
		req.getRequestDispatcher("productSelectDetail.jsp").forward(req, res);
	}
}
