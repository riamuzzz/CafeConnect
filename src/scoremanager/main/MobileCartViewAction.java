package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.Product;
import bean.User;
import dao.CartDao;
import dao.ProductDao;
import tool.Action;

public class MobileCartViewAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー

		//カート情報取得
		CartDao cDao =new CartDao();
		List<Cart> cartList = new ArrayList<>();
		List<Product> pList = new ArrayList<>();
		//商品情報取得
		ProductDao pDao = new ProductDao();
		cartList = cDao.filter(user);
		for (Cart cart : cartList){
			Product product = pDao.get(cart.getProduct().getProductId());
			//カートの中の商品がフードorドリンクなら商品インスタンスをリストに追加（モバイルオーダーかオンラインショップか判断）
			if(!product.getCategory().getCategoryId().equals("CATE02") ){
				pList.add(product);
			}
		}

		req.setAttribute("cList", cartList);
		req.setAttribute("pList", pList);

		req.getRequestDispatcher("mobileCartView.jsp").forward(req, res);
	}

}
