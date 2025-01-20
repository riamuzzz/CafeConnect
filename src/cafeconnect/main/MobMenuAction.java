package cafeconnect.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class MobMenuAction extends Action{
	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		ProductDao pDao = new ProductDao();
		CategoryDao cDao = new CategoryDao();
		List<Product> drinkProducts = new ArrayList<>();
		List<Product> foodProducts = new ArrayList<>();

		drinkProducts = pDao.filter(cDao.get("CATE01"));
		foodProducts = pDao.filter(cDao.get("CATE03"));
		req.setAttribute("drinkProducts", drinkProducts);
		req.setAttribute("foodProducts", foodProducts);

		req.getRequestDispatcher("mobMenu.jsp").forward(req, res);;
		}
}
