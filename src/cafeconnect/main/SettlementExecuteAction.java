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
		List<Integer> counts = new ArrayList<>();
		List<Product> pList = new ArrayList<>();
		List<Cart> cList = new ArrayList<>();
		boolean online = false;
		int i = 0;
		int count = 0;
		//リクエストパラメータ取得
		while(true){
			String productIdStr = req.getParameter("productId" + Integer.toString(i));
			String countStr = req.getParameter("count" + Integer.toString(i));
			if (productIdStr != null & countStr != null){
				int productId = Integer.parseInt(productIdStr);
				counts.add(Integer.parseInt(countStr));
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
				cart.setCount(counts.get(count));
				//売れた商品の数だけ在庫数を減らす
				pDao.purchaseProduct(cart.getProduct(), counts.get(count));
				// カテゴリでモバイルかオンラインショップか判断
				if (product.getCategory().getCategoryId().equals("CATE02")){
					System.out.println("オンラインショップ");
					oDao.create(cart);
					cDao.delete(cart, productId);
				} else {
					online = true;
					//モバイルdaoでcart内の情報をmobileに移す
					//mDao.Create(cart, randomNumber);
					oDao.mobileCreate(cart);
					System.out.println("モバイルオーダー");
					cDao.delete(cart, productId);
				}
				count++;
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
		req.setAttribute("message", "決済が完了しました");
		//JSPへフォワード 7
//		if (online == true){
//			req.getRequestDispatcher("Mobile.action").forward(req, res);
//		}
		req.getRequestDispatcher("OrderHistory.action").forward(req, res);
	}
}
