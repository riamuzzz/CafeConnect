package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class TopPageExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String productName = null;
		String categoryId = "CATE02";
		List<Product> products = null;// productリスト
		ProductDao productDao = new ProductDao();
		CategoryDao categoryDao = new CategoryDao();
		Category category = categoryDao.get(categoryId);
		products = productDao.filter(category, productName);
		req.setAttribute("products", products);
		req.getRequestDispatcher("topPage.jsp").forward(req, res);
	}
}
