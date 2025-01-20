package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.User;
import dao.CartDao;
import dao.ProductDao;
import tool.Action;

public class CartCreateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー
		CartDao cDao =new CartDao();
		ProductDao pDao =new ProductDao();
		int userId = user.getUserId();
		String error = null;

		String countStr = req.getParameter("num");
		String productId = req.getParameter("product");
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(pDao.get(Integer.parseInt(productId)));
		if (Integer.parseInt(countStr) < 0) {
			System.out.println(Integer.parseInt(countStr));
			error = "適切な数値を入力してください";
			String url = "ProductDetailView.action?productId=" + productId + "&num=" + Integer.parseInt(countStr);
			req.getRequestDispatcher(url).forward(req, res);
		} else {
			cart.setCount(Integer.parseInt(countStr));
			try {
				cDao.save(cart); // int 型の値を渡す
			} catch (NumberFormatException e) {
				// 数値に変換できない場合のエラー処理
				System.err.println("Invalid count value: " + countStr);
				// 必要に応じて適切なエラーメッセージを返したり、デフォルト値を設定したりする
			}
			req.getRequestDispatcher("ProductView.action").forward(req, res);
		}
	}
}
