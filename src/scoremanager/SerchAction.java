package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.ProductDao;
import tool.Action;

public class SerchAction  extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		ProductDao pDao =new ProductDao();
		List<Product> pList =new ArrayList<>();

		//リクエストパラメータ―の取得 2
		String keyword =req.getParameter("keyword");

		//DBからデータ取得 3
		pList = pDao.search(keyword);
		System.out.println("aa");

		//レスポンス値をセット 6
		req.setAttribute("pList", pList);

		//JSPへフォワード 7
		req.getRequestDispatcher("serchResult.jsp").forward(req, res);
	}

}
