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
		String productIdStr = "";
		Cart cart =new Cart();
		Product product =new Product();

		productIdStr = req.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);
		product =pDao.get(productId);

		cart = cDao.get(user,pDao.get(productId));

		cDao.delete(cart, productId);
		//商品が豆かどうか判断している（モバイルは豆頼めないからモバイルかオンラインショップかの判断もしている）
		if(product.getCategory().getCategoryId() != "CATE02"){
			req.getRequestDispatcher("CartView.action").forward(req, res);
		}else{
			req.getRequestDispatcher("MobileCartView.action").forward(req, res);
		}

	}
}
