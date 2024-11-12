package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			// パスを取得
			System.out.println("bbb");
			String path = req.getServletPath().substring(1);
			System.out.println("ccc");
			// ファイル名を取得しクラス名に変換
			String name = path.replace(".a", "A").replace('/', '.');
			System.out.println("ddd" + path);
			// アクションクラスのインスタンスを返却
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
			System.out.println("eee");

			// 遷移先URLを取得
			action.execute(req, res);
			System.out.println("fff");
			//String url = action.execute(req, res);
			//req.getRequestDispatcher(url).forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			// エラーページへリダイレクト
			System.out.print("aaaa");
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req,res);

	}
}
