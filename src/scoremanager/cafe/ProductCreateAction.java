package scoremanager.cafe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import dao.CategoryDao;
import tool.Action;
public class ProductCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		CategoryDao categoryDao = new CategoryDao(); // カテゴリDaoをインスタンス化

		List<Category> categorys = categoryDao.get(); // カテゴリの情報すべて取得

		System.out.println(categorys);
		req.setAttribute("categorys", categorys); // リクエストパラメータにcategoryをセット

		// 商品登録ページに遷移
		req.getRequestDispatcher("productCreate.jsp").forward(req, res);
	}
}
