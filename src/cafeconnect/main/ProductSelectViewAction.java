package cafeconnect.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Order;
import bean.Product;
import bean.User;
import dao.CategoryDao;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class ProductSelectViewAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(true);//セッションを有効にする
		User user = (User)session.getAttribute("user");//ログイン中のユーザ情報取得

		 // 現在の月（YYYY-MM 形式）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentMonth = sdf.format(new Date());

        // 前回の月（セッションから取得）
        String lastMonth = (String) session.getAttribute("lastMonth");
		OrderDao oDao = new OrderDao();//注文Dao
		List<Order> orders = new ArrayList<>();

		int g = 300;//グラムの変数

		String productName = null;
		String categoryId = null;
		List<Product> products = null;// productリスト

		// categoryIdを取得
		categoryId = req.getParameter("categoryId");
		ProductDao productDao = new ProductDao();
		CategoryDao categoryDao = new CategoryDao();
		Category category = categoryDao.get(categoryId);
		products = productDao.filter(category, productName);

     // 現在の月と前回の月を比較
        if (lastMonth != null && !lastMonth.equals(currentMonth)) {
            // 月が変わった場合、数値をリセット
            g = 300;// リセットする数値
            System.out.println("月が変わったため、数値をリセットしました。");

            // 前回の月を更新
            session.setAttribute("lastMonth", currentMonth);
        }else{
			//残りグラム数表示
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
					}
				}
			}
		}

		req.setAttribute("products", products);
		req.setAttribute("category", category);
		req.setAttribute("g", g);
		req.getRequestDispatcher("product_select.jsp").forward(req, res);
	}
}
