package cafeconnect.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.Product;
import bean.User;
import dao.CartDao;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class SettlementExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー
		ProductDao pDao = new ProductDao();
		CartDao cDao = new CartDao();
		OrderDao oDao = new OrderDao();
		List<Integer> productIds = new ArrayList<>();
		List<String> nums = new ArrayList<>();
		List<Product> pList = new ArrayList<>();
		List<Cart> cList = new ArrayList<>();
		int i = 0;
		int count = 0;
		//リクエストパラメータ取得
		while(true){
			String productIdStr = req.getParameter("productId" + Integer.toString(i));
			if (productIdStr != null){
				int productId = Integer.parseInt(productIdStr);
				productIds.add(productId);
			}
			if (productIdStr == null){
				break;
			}
			i = i + 1;
		}
		//DBからデータ取得 3
		//order作成
		for (int productId : productIds){
			if (productId != 0){
				Product product = pDao.get(productId);
				Cart cart = cDao.get(user, product);
				Boolean order = oDao.create(cart);
			}
		}

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("user", user);
		req.setAttribute("pList", pList);
		req.setAttribute("cList", cList);


		//JSPへフォワード 7
		req.getRequestDispatcher("settlementDone.jsp").forward(req, res);
	}
}
