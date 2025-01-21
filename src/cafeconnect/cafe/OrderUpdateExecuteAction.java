package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OnlineOrder;
import dao.OrderDao;
import tool.Action;

public class OrderUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		OrderDao oDao = new OrderDao();
		List<String> error = new ArrayList<>();

		List<String> oList = new ArrayList<>();
		List<OnlineOrder> orders = new ArrayList<>();
		List<Boolean> receives = new ArrayList<>();


		int i =0;
		int j = 0;

		//リクエストパラメータの取得
		while (true) {
		    String orderId = req.getParameter("orderId" + i);
		    if (orderId == null) {
		        break; // 注文IDがnullの場合、ループ終了
		    }

		    String receiveStr = req.getParameter("receive" + i);
		    System.out.println("orderId: " + orderId);
		    System.out.println("receiveStr: " + receiveStr); // デバッグ出力

		    boolean receive = (receiveStr != null && receiveStr.equalsIgnoreCase("true"));
		    System.out.println("receive: " + receive);

		    oList.add(orderId);
		    receives.add(receive);

		    i++;
		}


		//注文番号ごとの配送状況を取得
		for (String oId : oList) {
			if(oId != null){
				OnlineOrder order =oDao.get(oId);
				order.setReceive(receives.get(j));
				orders.add(order);
				j++;
			}

		}

		//JSPへフォワード 7
		req.getRequestDispatcher("OnlineOrderView.action").forward(req, res);
	}

}