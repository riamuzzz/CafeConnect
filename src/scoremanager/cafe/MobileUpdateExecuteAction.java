package scoremanager.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OnlineOrder;
import dao.MobileOrderDao;
import tool.Action;

public class MobileUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		MobileOrderDao oDao = new MobileOrderDao();
		List<String> error = new ArrayList<>();
		Boolean receive = false;

		//リクエストパラメータの取得
		String[] orderIds = req.getParameterValues("orderId");//商品番号のリスト

		System.out.println(orderIds+"*");
		//注文番号ごとの配送状況を取得
		for (String orderId : orderIds) {
		    String receiveStr = req.getParameter("receive_" + orderId);

			// 商品フラグにチェックが入っていた場合
			if (receiveStr != null) {
				// 商品フラグを立てる
				receive = true;
			}

		    System.out.println("****");
		    OnlineOrder order = oDao.get(orderId);//注文番号をもとにorderインスタンスを取得
			//DBへデータ保存 5
			if (order != null) {
				// 商品が存在していた場合
				// インスタンスに値をセット
				order.setReceive(receive);
				oDao.save(order);
			}
		}

		//JSPへフォワード 7
		req.getRequestDispatcher("MobileOrderView.action").forward(req, res);
	}

}