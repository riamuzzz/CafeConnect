package cafeconnect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class ProductViewAction  extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String productName = null;
		String categoryId = null;
		List<Product> products = null;// productリスト

		// categoryIdを取得
		categoryId = req.getParameter("categoryId");

		System.out.println(categoryId);

		ProductDao productDao = new ProductDao();
		CategoryDao categoryDao = new CategoryDao();

		Category category = categoryDao.get(categoryId);
		products = productDao.filter(category, productName);

		System.out.println(products);
		System.out.println(category);

		req.setAttribute("products", products);
		req.setAttribute("category", category);

		System.out.println("1111");
		req.getRequestDispatcher("productView.jsp").forward(req, res);
	}

}