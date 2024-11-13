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

		String productName = "";
		String categoryId = "CATE02";
		List<Product> products = null;// productリスト

		System.out.print("01");
		ProductDao productDao = new ProductDao();
		System.out.print("02");
		CategoryDao categoryDao = new CategoryDao();
		System.out.print("03");

		Category category = categoryDao.get(categoryId);

		System.out.print("04");
		products = productDao.filter(category, productName);

		System.out.println(products);
		req.setAttribute("products", products);


		System.out.println("1111");
		req.getRequestDispatcher("top_page.jsp").forward(req, res);
	}
}
