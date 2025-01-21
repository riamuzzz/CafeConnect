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
		    String receiveStr = req.getParameter("receive" + i);
		    //receiveStrはnullのときfalseに変換されるからnullの時もループを中断しない
		    if (orderId == null) {
		        break; // 注文IDがnullの場合、ループ終了
		    }
		    if (receiveStr == null){
		    	boolean receive = false;
		    	receives.add(receive);
		    } else if (receiveStr.equals("true")){
		    	boolean receive = true;
		    	receives.add(receive);
		    }
		    oList.add(orderId);
		    i++;
		}
		//注文番号ごとの配送状況を取得
		for (String oId : oList) {
			if(oId != null){
				OnlineOrder order =oDao.get(oId);
				order.setReceive(receives.get(j));
				oDao.save(order);
				orders.add(order);
				j++;
			}
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("OnlineOrderView.action").forward(req, res);
	}

}