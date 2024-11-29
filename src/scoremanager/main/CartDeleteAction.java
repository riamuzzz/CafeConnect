package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.Product;
import bean.User;
import dao.CartDao;
import dao.ProductDao;
import tool.Action;

public class CartDeleteAction  extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー

		CartDao cDao =new CartDao();
		ProductDao pDao=new ProductDao();
		String productId = "";
		Cart cart =new Cart();
		Product product =new Product();

		productId = req.getParameter("productId");

		cart = cDao.get(user,pDao.get(productId));

		cDao.delete(cart, productId);


		req.getRequestDispatcher("CartView.action").forward(req, res);

	}
}
