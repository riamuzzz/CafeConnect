package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OnlineOrder;
import bean.Product;
import bean.User;
import dao.MobileReceiveDao;
import tool.Action;

public class MobileReceiveAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1

		MobileReceiveDao orderReceiveDao =new MobileReceiveDao();
		Product product =null;
		User user =null;

		Date orderTime =null;
		String userName=null;
		String productName=null;

		//リストを初期化
		List<OnlineOrder> list = new ArrayList<>();

		//リクエストパラメータ―の取得 2

		//カテゴリプルダウン表示のデータ取得

		//DBからデータ取得 3

		 //絞り込み結果
		System.out.println("*");
		list = orderReceiveDao.filter(product,user,(java.sql.Date) orderTime);
		System.out.println("**");

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにカテゴリをセット
		req.setAttribute("order", list);
		System.out.println(list);

		//JSPへフォワード 7
		req.getRequestDispatcher("mobileReceiveView.jsp").forward(req, res);
	}

}
