package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.Cart;
import bean.Product;
import bean.User;
import dao.CartDao;
import dao.ProductDao;
import tool.Action;

public class SettlementAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー
		ProductDao pDao = new ProductDao();
		CartDao cDao = new CartDao();
		List<String> productIds = new ArrayList<>();
		List<String> nums = new ArrayList<>();
		List<Product> pList = new ArrayList<>();
		List<Cart> cList = new ArrayList<>();
		int i = 0;
		int count = 0;
		//リクエストパラメータ取得
		while(true){
			String productId = req.getParameter("productId" + Integer.toString(i));
			String num = req.getParameter("num" + Integer.toString(i));
			productIds.add(productId);
			nums.add(num);
			if (productId == null || num == null){
				productIds.remove(i);
				nums.remove(i);
				break;
			}
			i = i + 1;
		}
		//DBからデータ取得 3
		for (String productId : productIds){
			pList.add(pDao.get(productId));
			Cart cart = cDao.get(user, pDao.get(productId));
			cart.setCount(Integer.parseInt(nums.get(count)));
			cList.add(cart);
			count++;
		}
		Card card =user.getCard();

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("user", user);
		req.setAttribute("card", card);
		req.setAttribute("pList", pList);
		req.setAttribute("cList", cList);


		//JSPへフォワード 7
		req.getRequestDispatcher("settlement.jsp").forward(req, res);
	}
}
