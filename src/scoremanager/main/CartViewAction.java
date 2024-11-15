package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class CartViewAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String productName = "";
		String categoryId = null;
		List<Product> products = null;// productリスト


		ProductDao productDao = new ProductDao();

		CategoryDao categoryDao = new CategoryDao();


		Category category = categoryDao.get(categoryId);


		products = productDao.filter(category, productName);

		req.setAttribute("products", products);


		req.getRequestDispatcher("top_page.jsp").forward(req, res);
	}
}
