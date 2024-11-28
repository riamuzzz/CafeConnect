package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
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
		String userId = user.getUserId();

		String countStr = req.getParameter("num");
		String productId = req.getParameter("product");

		Product product=pDao.get(productId);

		try {
		    int count = Integer.parseInt(countStr); // count を int 型に変換
		    cDao.save(user, product, count); // int 型の値を渡す
		} catch (NumberFormatException e) {
		    // 数値に変換できない場合のエラー処理
		    System.err.println("Invalid count value: " + countStr);
		    // 必要に応じて適切なエラーメッセージを返したり、デフォルト値を設定したりする
		}


		req.getRequestDispatcher("../ProductView.action").forward(req, res);
	}
}
